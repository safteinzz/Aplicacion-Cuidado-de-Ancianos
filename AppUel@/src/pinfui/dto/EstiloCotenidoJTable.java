package pinfui.dto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import pinfui.interfaz.PInfUI;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class EstiloCotenidoJTable extends DefaultTableCellRenderer {
	
    
        private JComponent componente;

	@Override

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		componente = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // To
																														// change

                //Color filas                                                                                                                                                                                                                               
		if (row % 2 == 0) {
			componente.setBackground(new Color(232, 232, 232));
		} else {
			componente.setBackground(new Color(255, 255, 255));
		}

                //Filas seleccionadas
		if (isSelected || hasFocus) {
			componente.setBackground(new Color(83, 173, 255));
		}

                
                
                
                //Borde
		Border border = BorderFactory.createCompoundBorder();
		if (column != table.getColumnCount() - 1) 
                {
			border = BorderFactory.createCompoundBorder(border,
					BorderFactory.createMatteBorder(0, 1, 1, 0, Color.black));
		} 
                else 
                {
			border = BorderFactory.createCompoundBorder(border,
					BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
		}
		
                componente.setBorder(border);
                
                //Padding ( NO FUNCIONA POR ALGUN MOTIVO)
//                setBorder(new CompoundBorder(new EmptyBorder(new Insets(10,4,10,4)), getBorder())); //ESTO NO VALE PORQUE PISA EL BORDE DE LA CELDA
                               
                
		int size = 15 + PInfUI.getSizeFuente();
		componente.setFont(new Font("Tahoma", 0, size));
		table.setRowHeight(size + 2);
		return componente;

	}

}
