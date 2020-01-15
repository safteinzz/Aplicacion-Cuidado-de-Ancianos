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
 * Plantilla para las diferentes ventanas de usuarios
 * @author ITCOM
 */
public class PlantillaPantallas extends javax.swing.JFrame {
        
    protected List<LabelDTO> listaLabels = new ArrayList<LabelDTO>();
    
    protected boolean terminarHiloButton = false;
    protected boolean terminarHiloCorreo = false;
    
    protected Usuario usuario;
    
    /**
     * Constructor 
     * @param usuario Usuario logeado
     */
    public PlantillaPantallas(Usuario usuario) {
    	this.usuario = usuario;
    }
    
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
     * Creacion de un hilo en segundo plano encargado de modificar la imagen de correo para notificar que hay uno nuevo sin leer
     * @param iconCorreo JLabel a modificar
     */
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
        
//        List<javax.swing.JButton> listaBotones = new ArrayList<javax.swing.JButton>();
//        listaBotones.add(jButton);
//        
//        for(javax.swing.JButton boton : listaBotones){
//            boton.setFont(cambiarSize(boton.getFont().getSize(), 10));
//        }
    }    

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
}
