package com.pacifico.sistemacitas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.pacifico.sistemacitas.config.Conexion;

@WebServlet("/reservar-cita")
public class ReservarCitaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("usuario") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login");
            return;
        }

        request.getRequestDispatcher(
                        "/WEB-INF/views/reservar-cita.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        int clienteId =
                (Integer) session
                        .getAttribute("idUsuario");

        String medicoId =
                request.getParameter("medico");

        String fecha =
                request.getParameter("fecha");

        String hora =
                request.getParameter("hora");

        try (Connection con =
                     Conexion.getConexion()) {

            // Validar doble reserva
            String validarSql =
                    "SELECT * FROM citas " +
                            "WHERE medico_id = ? " +
                            "AND fecha = ? " +
                            "AND hora = ? " +
                            "AND estado != 'CANCELADA'";

            PreparedStatement validarPs =
                    con.prepareStatement(validarSql);

            validarPs.setString(1, medicoId);
            validarPs.setString(2, fecha);
            validarPs.setString(3, hora);

            ResultSet rs =
                    validarPs.executeQuery();

            if (rs.next()) {

                request.setAttribute(
                        "errorCita",
                        "Horario no disponible");

                request.getRequestDispatcher(
                                "/WEB-INF/views/reservar-cita.jsp")
                        .forward(request, response);

                return;
            }

            String sql =
                    "INSERT INTO citas " +
                            "(cliente_id, medico_id, " +
                            "fecha, hora, estado) " +
                            "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, clienteId);
            ps.setString(2, medicoId);
            ps.setString(3, fecha);
            ps.setString(4, hora);
            ps.setString(5, "PENDIENTE");

            ps.executeUpdate();

            request.setAttribute(
                    "mensajeCita",
                    "Cita registrada correctamente");

            request.getRequestDispatcher(
                            "/WEB-INF/views/reservar-cita.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "errorCita",
                    "Error al registrar cita");

            request.getRequestDispatcher(
                            "/WEB-INF/views/reservar-cita.jsp")
                    .forward(request, response);
        }
    }
}
