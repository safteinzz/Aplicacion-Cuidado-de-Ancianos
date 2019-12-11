/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.entidades;

/**
 * Objeto para agrupar las relaciones entre usuarios
 * @author ITCOM
 */
public class Asignacion {

    private int idAsignacion;
    private String dniAsignado;
    private String dniAsociado;
    private int idTipoAsociacion;

    private transient TipoAsignacion tipoAsignacion;
    private transient Usuario usuarioAsignado;
    private transient Usuario usuarioAsociado;

    public Asignacion() {
    }

    public Asignacion(int idAsignacion, String dniAsignado, String dniAsociado, int idTipoAsociacion) {
        super();
        this.idAsignacion = idAsignacion;
        this.dniAsignado = dniAsignado;
        this.dniAsociado = dniAsociado;
        this.idTipoAsociacion = idTipoAsociacion;
    }

    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public String getDniAsignado() {
        return dniAsignado;
    }

    public void setDniAsignado(String dniAsignado) {
        this.dniAsignado = dniAsignado;
    }

    public String getDniAsociado() {
        return dniAsociado;
    }

    public void setDniAsociado(String dniAsociado) {
        this.dniAsociado = dniAsociado;
    }

    public int getIdTipoAsociacion() {
        return idTipoAsociacion;
    }

    public void setIdTipoAsociacion(int idTipoAsociacion) {
        this.idTipoAsociacion = idTipoAsociacion;
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
