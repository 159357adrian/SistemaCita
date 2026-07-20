package com.pacifico.sistemacitas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.pacifico.sistemacitas.config.Conexion;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    private static final String VIEW_LOGIN =
            "/WEB-INF/views/login.jsp";

    private static final String SQL_INSERT =
            "INSERT INTO usuarios " +
                    "(username,password,nombre,apellido,dni,correo,telefono,fecha_nacimiento,sexo,rol,estado) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // 🔹 Obtener datos
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dniRegistro");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String sexo = request.getParameter("sexo");
        String password = request.getParameter("passwordRegistro");
        String confirmarPassword = request.getParameter("confirmarPassword");

        // 🔒 Validación básica
        if (camposVacios(nombre, apellido, dni, correo, password, confirmarPassword)) {
            mostrarError(request, response, "Completa todos los campos obligatorios");
            return;
        }

        // 🔒 Validar contraseña
        if (!password.equals(confirmarPassword)) {
            mostrarError(request, response, "Las contraseñas no coinciden");
            return;
        }

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            // username = dni
            ps.setString(1, dni);
            ps.setString(2, password);
            ps.setString(3, nombre);
            ps.setString(4, apellido);
            ps.setString(5, dni);
            ps.setString(6, correo);
            ps.setString(7, telefono);
            ps.setString(8, fechaNacimiento);
            ps.setString(9, sexo);
            ps.setString(10, "CLIENTE");
            ps.setBoolean(11, true);

            ps.executeUpdate();

            // ✅ Redirigir a login
            response.sendRedirect(request.getContextPath() + "/login");

        } catch (Exception e) {
            e.printStackTrace();
            mostrarError(request, response, "No se pudo registrar");
        }
    }

    // 🔥 MÉTODO: Validar campos vacíos
    private boolean camposVacios(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    // 🔥 MÉTODO: Mostrar error
    private void mostrarError(HttpServletRequest request,
                              HttpServletResponse response,
                              String mensaje)
            throws ServletException, IOException {

        request.setAttribute("errorRegistro", mensaje);
        request.getRequestDispatcher(VIEW_LOGIN)
                .forward(request, response);
    }
}