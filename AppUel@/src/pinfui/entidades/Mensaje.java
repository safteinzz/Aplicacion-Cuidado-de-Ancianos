package pinfui.entidades;

import java.io.Serializable;

public class Mensaje implements Serializable{

	private String dni_Emisor;
	private String dni_Receptor;
	private String asunto;
	private String mensaje;
	private boolean leido;
//	private String enviadoPor;
        private int etiqueta;
	
	public Mensaje() { }

	public Mensaje(String dni_Emisor, String dni_Receptor, String asunto, String mensaje, boolean leido,
			int etiqueta) {
		super();
		this.dni_Emisor = dni_Emisor;
		this.dni_Receptor = dni_Receptor;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.leido = leido;
		this.etiqueta = etiqueta;
	}

	public String getDni_Emisor() {
		return dni_Emisor;
	}

	public void setDni_Emisor(String dni_Emisor) {
		this.dni_Emisor = dni_Emisor;
	}

	public String getDni_Receptor() {
		return dni_Receptor;
	}

	public void setDni_Receptor(String dni_Receptor) {
		this.dni_Receptor = dni_Receptor;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

        public int getEtiqueta() {
            return etiqueta;
        }

        public void setEtiqueta(int etiqueta) {
            this.etiqueta = etiqueta;
        }
	
        
}
