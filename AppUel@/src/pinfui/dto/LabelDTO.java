/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.dto;

import javax.swing.JLabel;

/**
 * Clase para ser utilizado para el cambio de tamaño en las pantallas
 * @author ITCOM
 */
public class LabelDTO {
    
    private JLabel label;
    private int size;
    private String idTexto;

    public LabelDTO(JLabel label, String idTexto, int size) {
        this.label = label;
        this.idTexto = idTexto;
        this.size = size;
    }

    public JLabel getLabel() {
        return label;
    }

    public int getSize() {
        return size;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setSize(int size) {
        this.size = size;
    }

	public String getIdTexto() {
		return idTexto;
	}

	public void setIdTexto(String idTexto) {
		this.idTexto = idTexto;
	}
    
}
