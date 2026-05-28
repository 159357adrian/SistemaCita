package com.pacifico.sistemacitas.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sistema_citas";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    public static Connection getConexion() {

        Connection conexion = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Conexión exitosa a MySQL");

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println(
                    "Error de conexión: " + e.getMessage()
            );
        }

        return conexion;
    }
}
