package com.pacifico.sistemacitas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.pacifico.sistemacitas.config.Conexion;

@WebServlet("/mis-citas")
public class MisCitasServlet extends HttpServlet {

    private static final String VIEW_MIS_CITAS =
            "/WEB-INF/views/mis-citas.jsp";

    private static final String SQL_LISTAR =
            "SELECT c.id, m.nombre AS medico, c.fecha, c.hora, c.estado " +
                    "FROM citas c " +
                    "INNER JOIN medicos m ON c.medico_id = m.id " +
                    "WHERE c.cliente_id = ? " +
                    "ORDER BY c.fecha DESC, c.hora DESC";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // 🔒 Validar sesión
        if (!sesionValida(session)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int clienteId =
                (Integer) session.getAttribute("idUsuario");

        List<Map<String, String>> citas =
                obtenerCitas(clienteId);

        request.setAttribute("citas", citas);

        request.getRequestDispatcher(VIEW_MIS_CITAS)
                .forward(request, response);
    }

    // 🔥 MÉTODO: validar sesión
    private boolean sesionValida(HttpSession session) {
        return session != null && session.getAttribute("idUsuario") != null;
    }

    // 🔥 MÉTODO: obtener citas
    private List<Map<String, String>> obtenerCitas(int clienteId) {

        List<Map<String, String>> citas = new ArrayList<>();

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_LISTAR)) {

            ps.setInt(1, clienteId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                citas.add(mapearCita(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return citas;
    }

    // 🔥 MÉTODO: mapear datos
    private Map<String, String> mapearCita(ResultSet rs) throws Exception {

        Map<String, String> cita = new HashMap<>();

        cita.put("id", rs.getString("id"));
        cita.put("medico", rs.getString("medico"));
        cita.put("fecha", rs.getString("fecha"));
        cita.put("hora", rs.getString("hora"));
        cita.put("estado", rs.getString("estado"));

        return cita;
    }
}