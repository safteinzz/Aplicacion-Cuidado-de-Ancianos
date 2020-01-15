package pinfui.dto;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;
//import javax.swing.plaf.basic.BasicComboBoxRenderer;

import pinfui.interfaz.PInfUI;

/**
 * Clase especifica para los items de JComboBox Tipo de rango del JPanel Ritmo cardiaco
 * @author ITCOM
 *
 */
public class ItemRenderer extends DefaultListCellRenderer {
	private JComponent componente;
	
    public Component getListCellRendererComponent(
        JList list, Object value, int index,
        boolean isSelected, boolean cellHasFocus){
    	
    	componente = (JComponent) super.getListCellRendererComponent(list, value, index,
            isSelected, cellHasFocus);

        if (value != null){
            Item item = (Item)value;
            setText( item.getDescription().toUpperCase() );
        }

        if (index == -1){
            Item item = (Item)value;
            setText( item.getDescription() );
        }
        
        if(isSelected) {
        	componente.setBackground(new Color(83, 173, 255));
        }
        
        setFont(new java.awt.Font("Tahoma", 0, 13 + PInfUI.getSizeFuente()));
        return this;
    }
}
