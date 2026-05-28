package com.pacifico.sistemacitas.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.pacifico.sistemacitas.config.Conexion;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final String VIEW_HOME =
            "/WEB-INF/views/home.jsp";

    private static final String SQL_ESTADOS =
            "SELECT estado, COUNT(*) total " +
                    "FROM citas WHERE cliente_id = ? GROUP BY estado";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Integer idUsuario =
                (Integer) session.getAttribute("idUsuario");

        // 🔒 Protección básica
        //if (idUsuario == null) {
            //response.sendRedirect(request.getContextPath() + "/login");
            //return;
        //}

        int pendientes = 0;
        int canceladas = 0;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_ESTADOS)) {

            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String estado = rs.getString("estado");
                int total = rs.getInt("total");

                if ("PENDIENTE".equals(estado)) {
                    pendientes = total;
                } else if ("CANCELADA".equals(estado)) {
                    canceladas = total;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 📊 Enviar datos a la vista
        request.setAttribute("pendientes", pendientes);
        request.setAttribute("canceladas", canceladas);

        request.getRequestDispatcher(VIEW_HOME)
                .forward(request, response);
    }
}