package com.pacifico.sistemacitas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.pacifico.sistemacitas.config.Conexion;
import com.pacifico.sistemacitas.model.Cita;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/doctor/home")
public class DoctorHomeServlet extends HttpServlet {

    // ✅ 🔥 SQL CORREGIDO (CON JOIN)
    private static final String SQL =
            "SELECT c.id, u.nombre AS paciente, u.dni, c.fecha, c.hora, c.estado " +
                    "FROM citas c " +
                    "INNER JOIN usuarios u ON c.cliente_id = u.id " +
                    "WHERE c.medico_id = ?";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer idDoctor = (Integer) session.getAttribute("idMedico");

        System.out.println("✅ ID DOCTOR LOGEADO: " + idDoctor);

        List<Cita> lista = new ArrayList<>();

        if (idDoctor != null) {

            try (Connection con = Conexion.getConexion();
                 PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setInt(1, idDoctor);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        Cita c = new Cita();

                        c.setId(rs.getInt("id"));
                        c.setPaciente(rs.getString("paciente")); // ✅ ahora existe
                        c.setDni(rs.getString("dni"));           // ✅ ahora existe
                        c.setFecha(rs.getString("fecha"));
                        c.setHora(rs.getString("hora"));
                        c.setEstado(rs.getString("estado"));

                        lista.add(c);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("⚠️ No hay sesión de doctor");
        }

        System.out.println("📊 Total citas encontradas: " + lista.size());

        request.setAttribute("citas", lista);

        request.getRequestDispatcher("/WEB-INF/views/doctor/home.jsp")
                .forward(request, response);
    }
}
