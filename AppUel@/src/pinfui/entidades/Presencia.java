package pinfui.entidades;

import java.util.Date;

/**
 * Objeto para el sensor de presencia por las habitaciones
 * @author ITCOM
 *
 */
public class Presencia {

    private String dni_paciente;

    private Date fecha;

    private int id_tipo_presencia;
    
    private transient TipoPresencia tipoPresencia;

    public Presencia(String dni_paciente, Date fecha, int id_tipo_presencia) {
            this.dni_paciente = dni_paciente;
            this.fecha = fecha;
            this.id_tipo_presencia = id_tipo_presencia;
    }

    public Presencia(String dni_paciente, Date fecha, int id_tipo_presencia, TipoPresencia tipoPresencia) {
		super();
		this.dni_paciente = dni_paciente;
		this.fecha = fecha;
		this.id_tipo_presencia = id_tipo_presencia;
		this.tipoPresencia = tipoPresencia;
	}

    public String getDni_paciente() {
		return dni_paciente;
	}

	public void setDni_paciente(String dni_paciente) {
		this.dni_paciente = dni_paciente;
	}

    public java.util.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

	public int getId_tipo_presencia() {
		return id_tipo_presencia;
	}

	public void setId_tipo_presencia(int id_tipo_presencia) {
		this.id_tipo_presencia = id_tipo_presencia;
	}

	public TipoPresencia getTipoPresencia() {
		return tipoPresencia;
	}

	public void setTipoPresencia(TipoPresencia tipoPresencia) {
		this.tipoPresencia = tipoPresencia;
	}
    
}
