/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.entidades;

/**
 * Objeto para identificar los roles de los usuarios
 * @author ITCOM
 */
public class Rol {

    int id_Rol;
    String nombre;

    public Rol(int id_Rol, String nombre) {
        this.id_Rol = id_Rol;
        this.nombre = nombre;
    }

    public int getId_Rol() {
        return id_Rol;
    }

    public void setId_Rol(int id_Rol) {
        this.id_Rol = id_Rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
