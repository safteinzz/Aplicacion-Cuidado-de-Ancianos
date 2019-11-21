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

/**
 *
 * @author ITCOM
 */
public class PlantillaPantallas extends javax.swing.JFrame {
        
    protected List<LabelDTO> listaLabels = new ArrayList<LabelDTO>();
    
    protected boolean terminarHiloButton = false;
    protected boolean terminarHiloCorreo = false;
    
    protected java.awt.Font cambiarSize(int style, int sizeActual, int suma){
        return new java.awt.Font("Tahoma", style, sizeActual + suma);
    }
    
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
    
    protected void crearHiloCambioIconCorreo(javax.swing.JLabel iconCorreo){
        // Creacion de un hilo para cambiar la imagen del correo cuando tengas notificaciones nuevas - Asi llama mas la atencion
        new Thread(new Runnable() {
            public void run() {
                
                //Se controlara el tiempo para que cambie el icono cada segundo
                Calendar proximaEjecucion = Calendar.getInstance();
                proximaEjecucion.setTime(new Date());
                proximaEjecucion.add(Calendar.SECOND, 1);
                
                boolean cambioAlert = true;
                
                //Siempre que terminarHiloCorreo este a false se ejecutara el hilo
                while(!terminarHiloCorreo){
                    Calendar fechaActual = Calendar.getInstance();
                    fechaActual.setTime(new Date());
                    
                    if(fechaActual.compareTo(proximaEjecucion) >= 0){
                        if(cambioAlert){
                            
                            //Esta parte es la que se encarga de ejecutarse como un hilo
                            SwingUtilities.invokeLater(new Runnable() {

                                public void run() {
                                    iconCorreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/correo-alert.png")));
                                }
                            });
                            cambioAlert = false;
                        } else {
                            SwingUtilities.invokeLater(new Runnable() {

                                public void run() {
                                    iconCorreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/correo.png")));
                                }
                            });
                            cambioAlert = true;
                        }
                        
                        proximaEjecucion.setTime(new Date());
                        proximaEjecucion.add(Calendar.SECOND, 2);
                    }

                    try {
                        java.lang.Thread.sleep(100);
                    }catch(Exception e) { }
                }
            }
        }).start();
    }
}
