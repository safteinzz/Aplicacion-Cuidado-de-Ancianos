package pinfui.entidades;

import java.util.Date;

/**
 * Objeto para controlar el ritmo cardiaco de un paciente
 * @author ITCOM
 *
 */
public class RitmoCardiaco {
	
	private String dniPaciente;
	
	private Date fecha;
	
	private int valor;

	public RitmoCardiaco() {
		
	}
	
	public RitmoCardiaco(String dniPaciente, java.util.Date fecha, int valor) {
		this.dniPaciente = dniPaciente;
		this.fecha = fecha;
		this.valor = valor;
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

	public int getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
