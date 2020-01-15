package pinfui.dto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

import pinfui.interfaz.PInfUI;

public class EstiloCabeceraJTable extends DefaultTableCellRenderer {
	private JComponent componente;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		componente = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // To change
		componente.setBackground(new Color(0, 153, 255));

		Border border = BorderFactory.createCompoundBorder();
		if(column == 0) {
			border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(2,2,2,2,Color.black));
		} else if (column == table.getColumnCount() -1) {
			border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(2,0,2,2,Color.black));
		}
		componente.setBorder(border);
		componente.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13 + PInfUI.getSizeFuente()));
		
		setHorizontalAlignment(JLabel.CENTER);
		return componente;

	}
    
}
