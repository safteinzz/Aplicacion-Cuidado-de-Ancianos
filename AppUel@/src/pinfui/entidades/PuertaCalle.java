package pinfui.entidades;

import java.util.Date;

/**
 * Objeto para el sensor de la puerta de la calle
 * @author ITCOM
 *
 */
public class PuertaCalle {

    private String dni_paciente;

    private Date fecha;

    private boolean abierta;

    public PuertaCalle() {

    }

    public PuertaCalle(String dni_paciente, java.util.Date fecha, boolean abierta) {
        this.dni_paciente = dni_paciente;
        this.fecha = fecha;
        this.abierta = abierta;
    }

    public String getDniPaciente() {
        return dni_paciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dni_paciente = dniPaciente;
    }

    public java.util.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

	public boolean isAbierta() {
		return abierta;
	}

	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}
}
