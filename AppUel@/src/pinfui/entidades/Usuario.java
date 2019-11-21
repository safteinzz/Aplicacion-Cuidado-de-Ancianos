/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.entidades;

/**
 *
 * @author ITCOM
 */
public class Usuario {
    protected String usuario;
    protected String nombre;
    protected String apellidos;
    protected String email;
    protected String telMovil;
    protected String telFijo;
    protected String localidad;
    protected String pais;
    protected String direccion;

    public Usuario(String usuario, String nombre, String apellidos, String email, String telMovil, String telFijo, String localidad, String pais, String direccion) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telMovil = telMovil;
        this.telFijo = telFijo;
        this.localidad = localidad;
        this.pais = pais;
        this.direccion = direccion;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getTelMovil() {
        return telMovil;
    }

    public String getTelFijo() {
        return telFijo;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getPais() {
        return pais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelMovil(String telMovil) {
        this.telMovil = telMovil;
    }

    public void setTelFijo(String telFijo) {
        this.telFijo = telFijo;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
