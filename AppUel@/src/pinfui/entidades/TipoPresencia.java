package pinfui.entidades;

/**
 * Objeto para agrupar las diferentes habitaciones donde estarán los sensores de presencia
 * @author ITCOM
 *
 */
public class TipoPresencia {

	private int id;
	private String lugar;
	
	public TipoPresencia(int id, String lugar) {
		super();
		this.id = id;
		this.lugar = lugar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
}
