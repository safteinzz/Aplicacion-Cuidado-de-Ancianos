/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pinfui.dto.LabelDTO;
import pinfui.dto.TipoSensor;
import pinfui.dto.TipoVentana;
import pinfui.entidades.Usuario;

/**
 * Plantilla para las ventanas de diferentes roles
 * @author ITCOM
 */
public class PantallaMedicoAbrePaciente extends PlantillaPantallas {    
    
    private Usuario user;
    private TipoSensor tSensor;
	/**
	 * Creates new form PantallaUsuario
	 * @param usuario Usuario logeado
	 */
    public PantallaMedicoAbrePaciente(Usuario usuario) {
        super(usuario);
    	initComponents();
        myInitComponents();
        this.setTitle("Ficha del paciente: " + usuario.getNombre() + " " + usuario.getApellidos());
        user = usuario;
        
        //Handler de salida de la ventana
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);        
        this.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
                int choose = JOptionPane.showConfirmDialog(null,
                        "Seguro que quiere cerrar la ventana ?",
                        "Cerrar ventana", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (choose == JOptionPane.YES_OPTION) {
                    e.getWindow().dispose();
                    //PInfUI.abrirVentana(null, null, TipoVentana.LOGIN);
                    System.out.println("close");
                  
                } else {
                    System.out.println("do nothing");
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelVentana = new javax.swing.JPanel();
        panelBarraIzquierda = new javax.swing.JPanel();
        panelUsuario = new javax.swing.JPanel();
        labelNombreUser = new javax.swing.JLabel();
        panelOtrasVentanas = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        panelMenuInicio = new javax.swing.JPanel();
        labelMenuInicio = new javax.swing.JLabel();
        panelMenuCardiaco = new javax.swing.JPanel();
        labelMenuCardiaco = new javax.swing.JLabel();
        panelMenuMensajeria = new javax.swing.JPanel();
        labelMenuMensajeria = new javax.swing.JLabel();
        iconCorreo = new javax.swing.JLabel();
        panelDatosVentana = new javax.swing.JPanel();
        panelInicio = new javax.swing.JPanel();
        labelTituloVentana = new javax.swing.JLabel();
        panelVentanaCardiaco = new javax.swing.JPanel();
        panelVentanaMensajeria = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelVentana.setBackground(new java.awt.Color(255, 255, 255));

        panelBarraIzquierda.setBackground(new java.awt.Color(0, 153, 255));

        panelUsuario.setBackground(new java.awt.Color(0, 153, 255));

        labelNombreUser.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        labelNombreUser.setText("nombreUsuario");
        labelNombreUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout panelUsuarioLayout = new javax.swing.GroupLayout(panelUsuario);
        panelUsuario.setLayout(panelUsuarioLayout);
        panelUsuarioLayout.setHorizontalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNombreUser)
                .addContainerGap(186, Short.MAX_VALUE))
        );
        panelUsuarioLayout.setVerticalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNombreUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelOtrasVentanas.setBackground(new java.awt.Color(0, 153, 255));

        panelMenuInicio.setBackground(new java.awt.Color(0, 153, 255));
        panelMenuInicio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelMenuInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelMenuInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMenuInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMenuInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMenuInicioMouseExited(evt);
            }
        });

        labelMenuInicio.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        labelMenuInicio.setText("Inicio");

        javax.swing.GroupLayout panelMenuInicioLayout = new javax.swing.GroupLayout(panelMenuInicio);
        panelMenuInicio.setLayout(panelMenuInicioLayout);
        panelMenuInicioLayout.setHorizontalGroup(
            panelMenuInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMenuInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMenuInicioLayout.setVerticalGroup(
            panelMenuInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMenuInicio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMenuCardiaco.setBackground(new java.awt.Color(0, 153, 255));
        panelMenuCardiaco.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelMenuCardiaco.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelMenuCardiaco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMenuCardiacoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMenuCardiacoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMenuCardiacoMouseExited(evt);
            }
        });

        labelMenuCardiaco.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        labelMenuCardiaco.setText("Sensor de ritmo cardiaco");

        javax.swing.GroupLayout panelMenuCardiacoLayout = new javax.swing.GroupLayout(panelMenuCardiaco);
        panelMenuCardiaco.setLayout(panelMenuCardiacoLayout);
        panelMenuCardiacoLayout.setHorizontalGroup(
            panelMenuCardiacoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuCardiacoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMenuCardiaco, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMenuCardiacoLayout.setVerticalGroup(
            panelMenuCardiacoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuCardiacoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMenuCardiaco)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMenuMensajeria.setBackground(new java.awt.Color(0, 153, 255));
        panelMenuMensajeria.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelMenuMensajeria.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelMenuMensajeria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMenuMensajeriaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMenuMensajeriaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMenuMensajeriaMouseExited(evt);
            }
        });

        labelMenuMensajeria.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        labelMenuMensajeria.setText("Mensajería");

        iconCorreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/correo.png"))); // NOI18N
        iconCorreo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout panelMenuMensajeriaLayout = new javax.swing.GroupLayout(panelMenuMensajeria);
        panelMenuMensajeria.setLayout(panelMenuMensajeriaLayout);
        panelMenuMensajeriaLayout.setHorizontalGroup(
            panelMenuMensajeriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuMensajeriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMenuMensajeria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconCorreo)
                .addContainerGap())
        );
        panelMenuMensajeriaLayout.setVerticalGroup(
            panelMenuMensajeriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuMensajeriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuMensajeriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(iconCorreo)
                    .addComponent(labelMenuMensajeria))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelOtrasVentanasLayout = new javax.swing.GroupLayout(panelOtrasVentanas);
        panelOtrasVentanas.setLayout(panelOtrasVentanasLayout);
        panelOtrasVentanasLayout.setHorizontalGroup(
            panelOtrasVentanasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenuCardiaco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMenuMensajeria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMenuInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelOtrasVentanasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
        );
        panelOtrasVentanasLayout.setVerticalGroup(
            panelOtrasVentanasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOtrasVentanasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMenuInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMenuCardiaco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelMenuMensajeria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelOtrasVentanasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelOtrasVentanasLayout.createSequentialGroup()
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 342, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelBarraIzquierdaLayout = new javax.swing.GroupLayout(panelBarraIzquierda);
        panelBarraIzquierda.setLayout(panelBarraIzquierdaLayout);
        panelBarraIzquierdaLayout.setHorizontalGroup(
            panelBarraIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelOtrasVentanas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBarraIzquierdaLayout.setVerticalGroup(
            panelBarraIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraIzquierdaLayout.createSequentialGroup()
                .addComponent(panelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOtrasVentanas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDatosVentana.setBackground(new java.awt.Color(255, 255, 255));

        panelInicio.setBackground(new java.awt.Color(102, 255, 255));

        labelTituloVentana.setBackground(new java.awt.Color(255, 255, 255));
        labelTituloVentana.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTituloVentana.setForeground(new java.awt.Color(0, 153, 255));
        labelTituloVentana.setText("Título de la ventana");

        javax.swing.GroupLayout panelInicioLayout = new javax.swing.GroupLayout(panelInicio);
        panelInicio.setLayout(panelInicioLayout);
        panelInicioLayout.setHorizontalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 938, Short.MAX_VALUE)
            .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelInicioLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelTituloVentana, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelInicioLayout.setVerticalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
            .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelInicioLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelTituloVentana)
                    .addContainerGap(487, Short.MAX_VALUE)))
        );

        panelVentanaCardiaco.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelVentanaCardiacoLayout = new javax.swing.GroupLayout(panelVentanaCardiaco);
        panelVentanaCardiaco.setLayout(panelVentanaCardiacoLayout);
        panelVentanaCardiacoLayout.setHorizontalGroup(
            panelVentanaCardiacoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 918, Short.MAX_VALUE)
        );
        panelVentanaCardiacoLayout.setVerticalGroup(
            panelVentanaCardiacoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        panelVentanaMensajeria.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelVentanaMensajeriaLayout = new javax.swing.GroupLayout(panelVentanaMensajeria);
        panelVentanaMensajeria.setLayout(panelVentanaMensajeriaLayout);
        panelVentanaMensajeriaLayout.setHorizontalGroup(
            panelVentanaMensajeriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 918, Short.MAX_VALUE)
        );
        panelVentanaMensajeriaLayout.setVerticalGroup(
            panelVentanaMensajeriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelDatosVentanaLayout = new javax.swing.GroupLayout(panelDatosVentana);
        panelDatosVentana.setLayout(panelDatosVentanaLayout);
        panelDatosVentanaLayout.setHorizontalGroup(
            panelDatosVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosVentanaLayout.createSequentialGroup()
                .addComponent(panelInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelDatosVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDatosVentanaLayout.createSequentialGroup()
                    .addComponent(panelVentanaCardiaco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(panelDatosVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDatosVentanaLayout.createSequentialGroup()
                    .addComponent(panelVentanaMensajeria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelDatosVentanaLayout.setVerticalGroup(
            panelDatosVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelDatosVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelVentanaCardiaco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelDatosVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelVentanaMensajeria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelVentanaLayout = new javax.swing.GroupLayout(panelVentana);
        panelVentana.setLayout(panelVentanaLayout);
        panelVentanaLayout.setHorizontalGroup(
            panelVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentanaLayout.createSequentialGroup()
                .addComponent(panelBarraIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatosVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelVentanaLayout.setVerticalGroup(
            panelVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBarraIzquierda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelDatosVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelMenuCardiacoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuCardiacoMouseEntered
        panelMenuCardiaco.setBackground(new java.awt.Color(0, 102, 204));
        panelMenuCardiaco.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    }//GEN-LAST:event_panelMenuCardiacoMouseEntered

    private void panelMenuCardiacoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuCardiacoMouseExited
        panelMenuCardiaco.setBackground(new java.awt.Color(0, 153, 255));
        panelMenuCardiaco.setBorder(null);
    }//GEN-LAST:event_panelMenuCardiacoMouseExited

    private void panelMenuMensajeriaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuMensajeriaMouseEntered
        panelMenuMensajeria.setBackground(new java.awt.Color(0, 102, 204));
        panelMenuMensajeria.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    }//GEN-LAST:event_panelMenuMensajeriaMouseEntered

    private void panelMenuMensajeriaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuMensajeriaMouseExited
        panelMenuMensajeria.setBackground(new java.awt.Color(0, 153, 255));
        panelMenuMensajeria.setBorder(null);
    }//GEN-LAST:event_panelMenuMensajeriaMouseExited

    private void panelMenuCardiacoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuCardiacoMouseClicked
        panelInicio.setVisible(false);
        panelVentanaCardiaco.setVisible(true);
        panelVentanaMensajeria.setVisible(false);
        tSensor = TipoSensor.RITMOCARDIACO;

        pintarGrafica();
    }//GEN-LAST:event_panelMenuCardiacoMouseClicked

    private void panelMenuInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuInicioMouseClicked
        panelInicio.setVisible(true);
        panelVentanaCardiaco.setVisible(false);

        panelVentanaMensajeria.setVisible(false);
    }//GEN-LAST:event_panelMenuInicioMouseClicked

    private void panelMenuInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuInicioMouseEntered
        panelMenuInicio.setBackground(new java.awt.Color(0, 102, 204));
        panelMenuInicio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    }//GEN-LAST:event_panelMenuInicioMouseEntered

    private void panelMenuInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuInicioMouseExited
        panelMenuInicio.setBackground(new java.awt.Color(0, 153, 255));
        panelMenuInicio.setBorder(null);
    }//GEN-LAST:event_panelMenuInicioMouseExited

    private void panelMenuMensajeriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuMensajeriaMouseClicked
        panelInicio.setVisible(false);
        panelVentanaCardiaco.setVisible(false);
        panelVentanaMensajeria.setVisible(true);
    }//GEN-LAST:event_panelMenuMensajeriaMouseClicked

    //metodo para añadir los cambios que queramos nosotros por codigo
    private void myInitComponents(){
        panelMenuInicio.setBorder(null);
        panelMenuCardiaco.setBorder(null);
        panelMenuMensajeria.setBorder(null);
        terminarHiloCorreo = false;
        
        //cambiar size de las letras 
        labelNombreUser.setText(usuario.getNombre());
        listaLabels.add(new LabelDTO(labelNombreUser,null, labelNombreUser.getFont().getSize()));
        listaLabels.add(new LabelDTO(labelTituloVentana,"tituloInicio", labelTituloVentana.getFont().getSize()));
        listaLabels.add(new LabelDTO(labelMenuMensajeria, "menuMensajeria", labelMenuMensajeria.getFont().getSize()));
        listaLabels.add(new LabelDTO(labelMenuInicio, "tituloInicio", labelMenuInicio.getFont().getSize()));
        listaLabels.add(new LabelDTO(labelMenuCardiaco, "menuRitmoCardiaco", labelMenuCardiaco.getFont().getSize()));
        
        crearHiloCambioIconCorreo(iconCorreo);
    }
    
    public void cambiarFuentes(){
        for(LabelDTO label : listaLabels){
            label.getLabel().setFont(cambiarSize(label.getLabel().getFont().getStyle(), label.getSize(), PInfUI.getSizeFuente()));
        }
        
//        List<javax.swing.JButton> listaBotones = new ArrayList<javax.swing.JButton>();
//        listaBotones.add(jButton);
//        
//        for(javax.swing.JButton boton : listaBotones){
//            boton.setFont(cambiarSize(boton.getFont().getSize(), 10));
//        }
    }  
    
    /**
     * Metodo encargado de pintar la grafica
     */
    private void pintarGrafica() {   	
        panelVentanaCardiaco.setLayout(new java.awt.BorderLayout());
        panelVentanaCardiaco.removeAll();
        panelVentanaCardiaco.add(new JPanelRitmoCardiaco(user.getDni(), user.getNombre()));
        panelVentanaCardiaco.revalidate();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconCorreo;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelMenuCardiaco;
    private javax.swing.JLabel labelMenuInicio;
    private javax.swing.JLabel labelMenuMensajeria;
    private javax.swing.JLabel labelNombreUser;
    private javax.swing.JLabel labelTituloVentana;
    private javax.swing.JPanel panelBarraIzquierda;
    private javax.swing.JPanel panelDatosVentana;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelMenuCardiaco;
    private javax.swing.JPanel panelMenuInicio;
    private javax.swing.JPanel panelMenuMensajeria;
    private javax.swing.JPanel panelOtrasVentanas;
    private javax.swing.JPanel panelUsuario;
    private javax.swing.JPanel panelVentana;
    private javax.swing.JPanel panelVentanaCardiaco;
    private javax.swing.JPanel panelVentanaMensajeria;
    // End of variables declaration//GEN-END:variables
}
