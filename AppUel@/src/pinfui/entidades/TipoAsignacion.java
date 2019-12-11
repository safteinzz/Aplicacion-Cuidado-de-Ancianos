/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.entidades;

/**
 * Objeto para las diferentes asignaciones existentes entre los usuarios
 * @author ITCOM
 */
public class TipoAsignacion {

    private int idTipoAsignacion;
    private String descripcion;

    public TipoAsignacion() { }

    public TipoAsignacion(int idTipoAsignacion, String descripcion) {
            super();
            this.idTipoAsignacion = idTipoAsignacion;
            this.descripcion = descripcion;
    }

    public int getIdTipoAsignacion() {
            return idTipoAsignacion;
    }
    public void setIdTipoAsignacion(int idTipoAsignacion) {
            this.idTipoAsignacion = idTipoAsignacion;
    }
    public String getDescripcion() {
            return descripcion;
    }
    public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
    }
}

