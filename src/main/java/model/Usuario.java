package com.pacifico.sistemacitas.model;

public class Usuario {

    private int id;

    private String nombres;

    private String apellidos;

    private String correo;

    private String dni;

    private String password;

    private String rol;


    // CONSTRUCTOR VACÍO
    public Usuario() {
    }


    // CONSTRUCTOR COMPLETO
    public Usuario(
            int id,
            String nombres,
            String apellidos,
            String correo,
            String dni,
            String password,
            String rol
    ) {

        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.dni = dni;
        this.password = password;
        this.rol = rol;
    }


    // GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}