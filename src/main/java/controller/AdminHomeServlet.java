package com.pacifico.sistemacitas.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.pacifico.sistemacitas.config.Conexion;

@WebServlet("/admin/home")
public class AdminHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/home.jsp"
        ).forward(request, response);
    }
}
