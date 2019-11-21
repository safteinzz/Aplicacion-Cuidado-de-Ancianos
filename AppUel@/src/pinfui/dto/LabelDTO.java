/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.dto;

import javax.swing.JLabel;

/**
 *
 * @author ITCOM
 */
public class LabelDTO {
    
    private JLabel label;
    private int size;

    public LabelDTO(JLabel label, int size) {
        this.label = label;
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
    
    
}
