package com.pacifico.sistemacitas.dao;

import com.pacifico.sistemacitas.config.Conexion;
import com.pacifico.sistemacitas.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao {

    // LOGIN
    public Usuario login(
            String dni,
            String password
    ) {

        Usuario usuario = null;

        try (Connection con =
                     Conexion.getConexion()) {

            String sql =
                    "SELECT * FROM usuarios " +
                            "WHERE dni=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, dni);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                usuario = new Usuario();

                usuario.setId(
                        rs.getInt("id")
                );

                usuario.setNombres(
                        rs.getString("nombres")
                );

                usuario.setApellidos(
                        rs.getString("apellidos")
                );

                usuario.setCorreo(
                        rs.getString("correo")
                );

                usuario.setDni(
                        rs.getString("dni")
                );

                usuario.setPassword(
                        rs.getString("password")
                );

                usuario.setRol(
                        rs.getString("rol")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return usuario;
    }


    // REGISTRAR USUARIO
    public boolean registrarUsuario(
            Usuario usuario
    ) {

        boolean registrado = false;

        try (Connection con =
                     Conexion.getConexion()) {

            String sql =
                    "INSERT INTO usuarios " +
                            "(nombres, apellidos, correo, dni, password, rol) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    usuario.getNombres()
            );

            ps.setString(
                    2,
                    usuario.getApellidos()
            );

            ps.setString(
                    3,
                    usuario.getCorreo()
            );

            ps.setString(
                    4,
                    usuario.getDni()
            );

            ps.setString(
                    5,
                    usuario.getPassword()
            );

            ps.setString(
                    6,
                    usuario.getRol()
            );

            int filas =
                    ps.executeUpdate();

            registrado = filas > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return registrado;
    }
}