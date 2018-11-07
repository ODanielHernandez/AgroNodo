package com.agronodo;

public class Usuarios {
    private String Correo;
    private String Contraseña;

    public Usuarios() {
    }

    public Usuarios(String correo, String contraseña) {
        Correo = correo;
        Contraseña = contraseña;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }
}
