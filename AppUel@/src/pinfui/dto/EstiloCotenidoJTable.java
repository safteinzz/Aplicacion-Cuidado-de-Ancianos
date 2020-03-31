package pinfui.dto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import pinfui.interfaz.PInfUI;

import javax.swing.border.Border;

public class EstiloCotenidoJTable extends DefaultTableCellRenderer {
	private JComponent componente;

	@Override

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		componente = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // To
																														// change

		if (row % 2 == 0) {
			componente.setBackground(new Color(232, 232, 232));
		} else {
			componente.setBackground(new Color(255, 255, 255));
		}

		if (isSelected || hasFocus) {
			componente.setBackground(new Color(83, 173, 255));
		}

		Border border = BorderFactory.createCompoundBorder();
		if (column != table.getColumnCount() - 1) {
			border = BorderFactory.createCompoundBorder(border,
					BorderFactory.createMatteBorder(0, 1, 1, 0, Color.black));
		} else {
			border = BorderFactory.createCompoundBorder(border,
					BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
		}
		
//		border = BorderFactory.createCompoundBorder(border,
//				border = BorderFactory.createEmptyBorder(20, 10, 20, 10));
		
		componente.setBorder(border);
		int size = 15 + PInfUI.getSizeFuente();
		componente.setFont(new Font("Tahoma", 0, size));
		table.setRowHeight(size + 15);
		return componente;

	}

}
