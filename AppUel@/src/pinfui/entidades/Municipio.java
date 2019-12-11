/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.entidades;

/**
 *
 * @author SaFteiNZz
 */
public class Municipio {
    
    //Relaciones
        private int id_Provincia;
        
    //Atributos
        //Primary key
            private int id_Municipio;   
        //Resto
            private int cod_Municipio;
            private int DC;
            private String nombre;
    
    private transient Provincia provincia;
            
    /**
     * Constructor de Municipio
     * @param id_Provincia
     * @param id_Municipio
     * @param nombre 
     * @param dc 
     * @param cod_Municipio 
     */
    public Municipio(int id_Provincia, int id_Municipio, int cod_Municipio, int dc, String nombre)
    {
        this.id_Provincia = id_Provincia;
        this.id_Municipio = id_Municipio;
        this.cod_Municipio = cod_Municipio;
        this.DC = dc;
        this.nombre = nombre;
    }

    public int getId_Provincia() {
        return id_Provincia;
    }

    public void setId_Provincia(int id_Provincia) {
        this.id_Provincia = id_Provincia;
    }

    public int getId_Municipio() {
        return id_Municipio;
    }

    public void setId_Municipio(int id_Municipio) {
        this.id_Municipio = id_Municipio;
    }

    public int getCod_Municipio() {
        return cod_Municipio;
    }

    public void setCod_Municipio(int cod_Municipio) {
        this.cod_Municipio = cod_Municipio;
    }

    public int getDC() {
        return DC;
    }

    public void setDC(int DC) {
        this.DC = DC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}  
}
