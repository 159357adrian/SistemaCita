package com.pacifico.sistemacitas.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pacifico.sistemacitas.config.Conexion;

@WebServlet("/cita")
public class CitaServlet extends HttpServlet {

    private static final String VIEW_FORM =
            "/WEB-INF/views/index.jsp";

    private static final String VIEW_RESULT =
            "/WEB-INF/views/resultado.jsp";

    private static final String SQL_VALIDAR =
            "SELECT * FROM citas WHERE fecha = ?";

    // ✅ 🔥 CORREGIDO: ahora incluye medico_id
    private static final String SQL_INSERT =
            "INSERT INTO citas(nombre, fecha, cliente_id, medico_id, estado) VALUES (?, ?, ?, ?, ?)";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (!sesionValida(session)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.getRequestDispatcher(VIEW_FORM)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (!sesionValida(session)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");

        Integer idUsuario =
                (Integer) session.getAttribute("idUsuario");

        // 🔥 TEMPORAL: asignar médico fijo (puedes mejorar luego)
        Integer medicoId = (Integer) session.getAttribute("idMedico");

         // 👈 usa el mismo ID con el que te logeas

        if (medicoId == null) {
            request.setAttribute("mensaje", "Error: no hay médico logueado");
            request.getRequestDispatcher(VIEW_RESULT).forward(request, response);
            return;
        }

        if (nombre == null || nombre.isEmpty() ||
                fecha == null || fecha.isEmpty()) {

            request.setAttribute("mensaje", "Completa todos los campos");
            request.getRequestDispatcher(VIEW_RESULT).forward(request, response);
            return;
        }

        try (Connection con = Conexion.getConexion()) {

            if (existeCita(con, fecha)) {
                request.setAttribute("mensaje", "Horario ocupado");
            } else {

                PreparedStatement ps = con.prepareStatement(SQL_INSERT);

                ps.setString(1, nombre);
                ps.setString(2, fecha);
                ps.setInt(3, idUsuario);
                ps.setInt(4, medicoId); // 🔥 CLAVE
                ps.setString(5, "PENDIENTE");

                ps.executeUpdate();

                request.setAttribute("mensaje", "✅ Cita registrada correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "❌ Error al registrar cita");
        }

        request.getRequestDispatcher(VIEW_RESULT)
                .forward(request, response);
    }

    // ✅ CORREGIDO: usar idUsuario (no "usuario")
    private boolean sesionValida(HttpSession session) {
        return session != null && session.getAttribute("idUsuario") != null;
    }

    private boolean existeCita(Connection con, String fecha) throws Exception {

        PreparedStatement ps = con.prepareStatement(SQL_VALIDAR);
        ps.setString(1, fecha);

        ResultSet rs = ps.executeQuery();

        return rs.next();
    }
}
