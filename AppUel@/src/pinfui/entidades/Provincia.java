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
public class Provincia {
    private int id_Provincia;
    private String provincia;
    
    /**
     * Constructor de Provincia
     * @param id_Provincia Id de la provincia
     * @param provincia Nombre de la provincia
     */
    public Provincia(int id_Provincia, String provincia)
    {
        this.id_Provincia = id_Provincia;
        this.provincia = provincia;
    }

    public int getId_Provincia() {
        return id_Provincia;
    }

    public void setId_Provincia(int id_Provincia) {
        this.id_Provincia = id_Provincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
