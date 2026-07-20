package com.pacifico.sistemacitas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pacifico.sistemacitas.config.Conexion;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String SQL_LOGIN =
            "SELECT * FROM usuarios WHERE dni = ? AND password = ? AND estado = 1";

    // 🔥 NUEVO: obtener medico_id
    private static final String SQL_MEDICO =
            "SELECT id FROM medicos WHERE usuario_id = ?";

    private static final String VIEW_LOGIN =
            "/WEB-INF/views/login.jsp";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(VIEW_LOGIN)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String dni = request.getParameter("dni");
        String password = request.getParameter("password");

        if (dni == null || password == null || dni.isEmpty() || password.isEmpty()) {
            mostrarError(request, response, "Completa todos los campos");
            return;
        }

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_LOGIN)) {

            ps.setString(1, dni);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String rol = rs.getString("rol");

                HttpSession session = request.getSession();

                // ✅ DATOS GENERALES
                session.setAttribute("idUsuario", rs.getInt("id"));
                session.setAttribute("usuario", rs.getString("dni"));
                session.setAttribute("nombre", rs.getString("nombre"));
                session.setAttribute("rol", rol);

                // 🔥 SI ES DOCTOR → obtener id de tabla medicos
                if ("DOCTOR".equalsIgnoreCase(rol)) {

                    PreparedStatement psMed = con.prepareStatement(SQL_MEDICO);

                    // ✅ usar id del usuario
                    psMed.setInt(1, rs.getInt("id"));

                    ResultSet rsMed = psMed.executeQuery();

                    if (rsMed.next()) {
                        int idMedico = rsMed.getInt("id");

                        session.setAttribute("idMedico", idMedico);

                        System.out.println("✅ ID MEDICO: " + idMedico);
                    } else {
                        System.out.println("❌ Usuario no vinculado a un medico");
                    }

                    response.sendRedirect(request.getContextPath() + "/doctor/home");
                }
                else if ("ADMIN".equalsIgnoreCase(rol)) {

                    response.sendRedirect(request.getContextPath() + "/admin/home");

                } else {

                    response.sendRedirect(request.getContextPath() + "/home");
                }

            } else {
                mostrarError(request, response, "DNI o contraseña incorrectos");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarError(request, response, "Error de conexión con BD");
        }
    }

    private void mostrarError(HttpServletRequest request,
                              HttpServletResponse response,
                              String mensaje)
            throws ServletException, IOException {

        request.setAttribute("errorLogin", mensaje);
        request.getRequestDispatcher(VIEW_LOGIN)
                .forward(request, response);
    }
}