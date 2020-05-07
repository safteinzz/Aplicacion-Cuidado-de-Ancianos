package pinfui.entidades;

import java.util.Date;

public class Alerta {

        private int id;
	private String dni_Paciente;
	private String sensor;
	private int valor;
	private Date fecha;
	
	public Alerta() {
		
	}
	
	public Alerta(int id, String dni_Paciente, String sensor, int valor, Date fecha) {
		super();
                this.id = id;
		this.dni_Paciente = dni_Paciente;
		this.sensor = sensor;
		this.valor = valor;
		this.fecha = fecha;
	}
        
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

	public String getDni_Paciente() {
		return dni_Paciente;
	}

	public void setDni_Paciente(String dni_Paciente) {
		this.dni_Paciente = dni_Paciente;
	}

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
