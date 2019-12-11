package pinfui.dto;

/**
 * Clase especifica para los items de JComboBox Tipo de rango del JPanel Ritmo cardiaco
 * @author ITCOM
 *
 */
public class Item {
	private Object tipo;
    private String description;

    public Item(Object tipo, String description)
    {
        this.tipo = tipo;
        this.description = description;
    }

    public Object getTipo() {
		return tipo;
	}

	public String getDescription()
    {
        return description;
    }

    public String toString()
    {
        return description;
    }
}
