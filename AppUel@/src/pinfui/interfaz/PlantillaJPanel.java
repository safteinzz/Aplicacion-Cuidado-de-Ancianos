/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.SwingUtilities;
import pinfui.dto.LabelDTO;
import pinfui.entidades.Usuario;

/**
 * Plantilla para los frames de JPanel
 * @author ITCOM
 */
public class PlantillaJPanel extends javax.swing.JPanel {
        
    protected List<LabelDTO> listaLabels = new ArrayList<LabelDTO>();
    
    protected boolean terminarHiloButton = false;
    
    /**
     * Metodo especifico para cambiar el tamaño de una fuente
     * @param style Tipo de estilo, negrita, cursiva, etc
     * @param sizeActual Tamaño que tiene actualmente la fuente
     * @param suma Cantidad que se quiere agrandar el tamaño de la fuente actual
     * @return Devuelve una fuente con el tamaño cambiado
     */
    protected java.awt.Font cambiarSize(int style, int sizeActual, int suma){
        return new java.awt.Font("Tahoma", style, sizeActual + suma);
    }
    
    /**
     * Creacion de un hilo en segundo plano encargado de modificar la imagen de los botones deslizantes
     * @param imagenButtonDeslizante JLabel al que se le cambiara la imagen
     */
    protected void crearHiloCambioIconButton(javax.swing.JLabel imagenButtonDeslizante){
        // Creacion de un hilo para cambiar la imagen del correo cuando tengas notificaciones nuevas - Asi llama mas la atencion
        new Thread(new Runnable() {
            public void run() {
                
                //Se controlara el tiempo para que cambie el icono cada segundo
                Calendar proximaEjecucion = Calendar.getInstance();
                proximaEjecucion.setTime(new Date());
                proximaEjecucion.add(Calendar.MILLISECOND, 5);
                
                int contador = 1;
                //Siempre que terminarHiloCorreo este a false se ejecutara el hilo
                while(!terminarHiloButton && contador <19){
                    Calendar fechaActual = Calendar.getInstance();
                    fechaActual.setTime(new Date());
                    
                    if(fechaActual.compareTo(proximaEjecucion) >= 0){
                        imagenButtonDeslizante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/submit" + contador + ".png")));
                        contador++;
                        proximaEjecucion.setTime(new Date());
                        proximaEjecucion.add(Calendar.MILLISECOND, 5);
                    }

                    try {
                        java.lang.Thread.sleep(100);
                    }catch(Exception e) { }
                }
                
                if(contador == 19){
                    imagenButtonDeslizante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/submit19.png")));
                }
            }
        }).start();
    }
    
    /**
     * Metodo encargado de cambiar las fuentes de la lista de labels que tiene una pantalla
     */
    protected void cambiarFuentes(){
        for(LabelDTO label : listaLabels){
            label.getLabel().setFont(cambiarSize(label.getLabel().getFont().getStyle(), label.getSize(), PInfUI.getSizeFuente()));
            if(label.getIdTexto() != null) {
            	label.getLabel().setText(PInfUI.getBundle().getString(label.getIdTexto()));
            }
        }
    }    
    
}
