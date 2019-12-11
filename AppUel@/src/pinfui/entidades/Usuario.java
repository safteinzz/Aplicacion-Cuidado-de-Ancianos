/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de usuarios para el control de la aplicación
 * @author ITCOM
 */
public class Usuario {
    
    //Relaciones
        private int id_Rol;
        private int id_Muncipio;
    
    //Atributos
        //Primary key
            private String dni;
        //Resto
            private String nombre;
            private String apellidos;

            private String pass_Hashed;
            private byte[] salt;


            private String email;
            private String tlf_Movil;
            private String tlf_Fijo;    
            private String direccion;  

    //Transients
        private transient Municipio municipio;
        private transient Rol rol;
        private transient List<Asignacion> listaAsignacion;
        private transient List<Mensaje> listaMensajes;

        
    /**
     * Constructor de Usuario para recolección de datos de JSON y MySQL
     * @param id_Rol
     * @param id_Muncipio
     * @param dni
     * @param nombre
     * @param apellidos
     * @param pass_Hashed
     * @param salt
     * @param email
     * @param tlf_Movil
     * @param tlf_Fijo
     * @param direccion 
     */
    public Usuario(int id_Rol, int id_Muncipio, String dni, String nombre, String apellidos,
            String pass_Hashed, byte[] salt, String email, String tlf_Movil, String tlf_Fijo, String direccion) {
        this.id_Rol = id_Rol;
        this.id_Muncipio = id_Muncipio;
        
        //AQUI DEBERIAMOS RELLENAR LOS TRANSIENTS
        
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pass_Hashed = pass_Hashed;
        this.salt = salt;
        this.email = email;
        this.tlf_Movil = tlf_Movil;
        this.tlf_Fijo = tlf_Fijo;
        this.direccion = direccion;
    }       
    
    /**
     * Contructor de User para registro sin telefono Fijo
     * @param dni
     * @param nombre
     * @param apellidos
     * @param email
     * @param tlf_Movil
     * @param municipio
     * @param provincia
     * @param rol
     * @param direccion
     * @param idRol 
     */
    public Usuario(String dni, String nombre, String apellidos, String email, String tlf_Movil,
                    Municipio municipio, Provincia provincia, String direccion, Rol rol, String tlf_Fijo) {
        //Transients
        this.municipio = municipio;
        this.rol = rol;        
        
        //Relaciones (tambien para insertar en JSON)
        this.id_Muncipio = municipio.getId_Municipio();
        this.id_Rol = rol.getId_Rol();
        
        //Atributos
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.tlf_Movil = tlf_Movil; 
        this.direccion = direccion;
        this.tlf_Fijo = tlf_Fijo;
    }
    

    
    
    public String getDni() {
        return dni;
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

    public String gettlf_Movil() {
        return tlf_Movil;
    }

    public String gettlf_Fijo() {
        return tlf_Fijo;
    }

    public Municipio getMunicipio() {
        return municipio;
    }
//
//    public Provincia getProvincia() {
//        return provincia;
//    }

    public String getDireccion() {
        return direccion;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public void settlf_Movil(String tlf_Movil) {
        this.tlf_Movil = tlf_Movil;
    }

    public void settlf_Fijo(String tlf_Fijo) {
        this.tlf_Fijo = tlf_Fijo;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
//
//    public void setProvincia(Provincia provincia) {
//        this.provincia = provincia;
//    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId_Rol() {
        return id_Rol;
    }

    public void setId_Rol(int id_Rol) {
        this.id_Rol = id_Rol;
    }

//    public int getId_Provincia() {
//        return id_Provincia;
//    }
//
//    public void setId_Provincia(int id_Provincia) {
//        this.id_Provincia = id_Provincia;
//    }

    public int getId_Muncipio() {
        return id_Muncipio;
    }

    public void setId_Muncipio(int id_Muncipio) {
        this.id_Muncipio = id_Muncipio;
    }
    
    public Rol getRol() {
        return rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getPass_Hashed() {
        return pass_Hashed;
    }

    public void setPass_Hashed(String hash) {
        this.pass_Hashed = hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
            this.salt = salt;
    }

    public List<Asignacion> getListaAsignacion() {
        if(listaAsignacion == null) {
            this.listaAsignacion = new ArrayList<Asignacion>();
        }
        return listaAsignacion;
    }

    public void setListaAsignacion(List<Asignacion> listaAsignacion) {
        this.listaAsignacion = listaAsignacion;
    }

    public List<Mensaje> getListaMensajes() {
        if(listaMensajes == null) {
            this.listaMensajes = new ArrayList<Mensaje>();
        }
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    } 
}