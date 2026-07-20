package com.pacifico.sistemacitas.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.pacifico.sistemacitas.config.Conexion;

@WebServlet("/cancelar-cita")
public class CancelarCitaServlet extends HttpServlet {

    private static final String SQL_CANCELAR =
            "UPDATE citas SET estado = 'CANCELADA' WHERE id = ?";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        // 🔒 Validar sesión
        if (!sesionValida(session)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String id = request.getParameter("id");

        // 🔒 Validar parámetro
        if (id == null || id.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/mis-citas");
            return;
        }

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_CANCELAR)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔄 refrescar lista
        response.sendRedirect(
                request.getContextPath() + "/mis-citas"
        );
    }

    // 🔥 MÉTODO: validar sesión
    private boolean sesionValida(HttpSession session) {
        return session != null && session.getAttribute("idUsuario") != null;
    }
}