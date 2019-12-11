/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import pinfui.entidades.Municipio;
import pinfui.entidades.Provincia;
import pinfui.entidades.Rol;

/**
 *
 * @author SaFteiNZz
 */
public class RenderComboBox extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(
                                   JList list,
                                   Object value,
                                   int index,
                                   boolean isSelected,
                                   boolean cellHasFocus) {
        if (value instanceof Provincia) {
            value = ((Provincia)value).getProvincia();
        }
        if (value instanceof Municipio) {
            value = ((Municipio)value).getNombre();
        }
        if (value instanceof Rol) {
            value = ((Rol)value).getNombre();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}
