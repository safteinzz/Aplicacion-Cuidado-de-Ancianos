package pinfui.entidades;

import java.util.Date;

/**
 * Objeto para el sensor de la puerta de la calle
 * @author ITCOM
 *
 */
public class PuertaCalle {

    private String dniPaciente;

    private Date fecha;

    private boolean abierta;

    public PuertaCalle() {

    }

    public PuertaCalle(String dniPaciente, java.util.Date fecha, boolean abierta) {
        this.dniPaciente = dniPaciente;
        this.fecha = fecha;
        this.abierta = abierta;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
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
