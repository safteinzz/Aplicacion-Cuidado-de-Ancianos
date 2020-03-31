/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.entidades;

/**
 * Objeto para agrupar las relaciones entre usuarios
 * El usuario asignado es el responsable
 * El usuario asociado es del que se hará responsable el asignado
 * @author ITCOM
 */
public class Asignacion {

    private String dni_Asociado;//paciente
    private String dni_Asignado;//medico y familiar
    private int id_Tipo; // 2 (familiar paciente) 1 (medico paciente)    

    private transient Usuario usuarioAsociado;
    private transient Usuario usuarioAsignado;
    private transient TipoAsignacion tipoAsignacion;

    public Asignacion() {
    }

    /**
     * Metodo de recoleccion de datos MySQL y JSON
     * @param dni_Asignado
     * @param dni_Asociado
     * @param id_Tipo 
     */
    public Asignacion(String dni_Asociado, String dni_Asignado, int id_Tipo) {
        super();        
        this.dni_Asociado = dni_Asociado;
        this.dni_Asignado = dni_Asignado;
        this.id_Tipo = id_Tipo;
    }

    /**
     * Metodo para el registro de asignaciones
     * @param id_Tipo
     * @param usuarioAsignado
     * @param usuarioAsociado 
     */
    public Asignacion(Usuario usuarioAsociado, Usuario usuarioAsignado, int id_Tipo) {        
        this.usuarioAsociado = usuarioAsociado;
        this.dni_Asociado = this.usuarioAsociado.getDni();
        this.usuarioAsignado = usuarioAsignado;
        this.dni_Asignado = this.usuarioAsignado.getDni();        
        this.id_Tipo = id_Tipo;
    }
    
    

    public String getDni_Asignado() {
        return dni_Asignado;
    }

    public void setDni_Asignado(String dni_Asignado) {
        this.dni_Asignado = dni_Asignado;
    }

    public String getDni_Asociado() {
        return dni_Asociado;
    }

    public void setDni_Asociado(String dni_Asociado) {
        this.dni_Asociado = dni_Asociado;
    }

    public int getId_Tipo() {
        return id_Tipo;
    }

    public void setId_Tipo(int id_Tipo) {
        this.id_Tipo = id_Tipo;
    }

    public TipoAsignacion getTipoAsignacion() {
        return tipoAsignacion;
    }

    public void setTipoAsignacion(TipoAsignacion tipoAsignacion) {
        this.tipoAsignacion = tipoAsignacion;
    }

    public Usuario getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(Usuario usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    

}
