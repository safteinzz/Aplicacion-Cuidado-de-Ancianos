/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import javax.swing.JFrame;
import javax.swing.tree.DefaultTreeCellEditor;
import pinfui.entidades.Mensaje;
import pinfui.entidades.Usuario;

/**
 *
 * @author Usuario DAM
 */
public class JPanelMEnsajeVisor1 extends JFrame{
    
    private JPanelMensajeriaNuevo visor;
    private JPanelMensajeriaResponder visorRepuesta;
    /**
     * 
     * @param visor     JPanel de visualización del mensaje
     */
    public JPanelMEnsajeVisor1(JPanelMensajeriaNuevo visor){
        this.visor = visor;
        add(this.visor);
        inicializarComponentes();
        //setSize(visor.getSize());
        // Cerra la ventana
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    public JPanelMEnsajeVisor1(JPanelMensajeriaResponder visorRepuesta){
        this.visorRepuesta = visorRepuesta;
        add(this.visorRepuesta);
        inicializarComponentes();
        //setSize(visor.getSize());
        // Cerra la ventana
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    public void inicializarComponentes(){
        setSize(960, 520);
        setTitle("Mensaje");
    }

}
