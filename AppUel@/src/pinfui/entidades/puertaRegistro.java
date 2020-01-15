/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.entidades;

import java.util.Date;

/**
 *
 * @author Usuario DAM
 */
public class puertaRegistro {
    
    private String dni_paciente;

    private Date fecha;

    private boolean abierta;
    
    public puertaRegistro(String dni_paciente, Date fecha, boolean abierta){
        super();
        
        this.dni_paciente = dni_paciente;
	this.fecha = fecha;
	this.abierta = abierta;
        
    }
    
    public puertaRegistro(){
        
    }

    public String getDni_paciente() {
        return dni_paciente;
    }

    public void setDni_paciente(String dni_paciente) {
        this.dni_paciente = dni_paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isAbierta() {
        return abierta;
    }

    public void setAbierta(boolean abierta) {
        this.abierta = abierta;
    }
    
}
