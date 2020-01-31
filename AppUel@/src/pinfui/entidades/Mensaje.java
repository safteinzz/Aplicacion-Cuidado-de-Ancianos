package pinfui.entidades;

import java.io.Serializable;

public class Mensaje implements Serializable{

	private String dniUsuario;
	private String titulo;
	private String contenido;
	private boolean leido;
	private String enviadoPor;
        private String etiqueta;
	
	public Mensaje() { }

	public Mensaje(String dniUsuario, String titulo, String contenido, boolean leido, String enviadoPor) {
		this.dniUsuario = dniUsuario;
		this.titulo = titulo;
		this.contenido = contenido;
		this.leido = leido;
		this.enviadoPor = enviadoPor;
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	public String getEnviadoPor() {
		return enviadoPor;
	}

	public void setEnviadoPor(String enviadoPor) {
		this.enviadoPor = enviadoPor;
	}

        public String getEtiqueta() {
            return etiqueta;
        }

        public void setEtiqueta(String etiqueta) {
            this.etiqueta = etiqueta;
        }
	
        
}
