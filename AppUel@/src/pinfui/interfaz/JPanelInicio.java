/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import javax.swing.table.DefaultTableModel;
import pinfui.controller.InicioController;
import pinfui.dto.ConstantesAplicacion;
import pinfui.dto.EstiloCabeceraJTable;
import pinfui.dto.EstiloCotenidoJTable;
import pinfui.dto.LabelDTO;
import pinfui.dto.TipoVentana;
import pinfui.entidades.Mensaje;
import pinfui.entidades.Usuario;

/**
 * Frame del panel de ritmo cardiaco
 * @author ITCOM
 */
public class JPanelInicio extends PlantillaJPanel {

    private InicioController inicioController = new InicioController();
	
    private DefaultTableModel modelNotas;
    private DefaultTableModel modelMensajeria;
    private DefaultTableModel modelAlertas;
    
    private Usuario usuario;
    
    private JPanelMEnsajeVisor1 mo = null;
   
    public JPanelInicio(Usuario usuario) {
    	this.usuario = usuario;
    	
    	initComponents();
    	
        modelNotas = new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                    PInfUI.getBundle().getString("fecha"), PInfUI.getBundle().getString("nota")
                }
            ) {
        	public boolean isCellEditable(int row, int column)
            {
              return false;//This causes all cells to be not editable
            }
        };
        
        tablaNotas.setModel(modelNotas);
        tablaNotas.setBackground(new java.awt.Color(255, 255, 255));
        tablaNotas.getTableHeader().setDefaultRenderer(new EstiloCabeceraJTable());
        tablaNotas.setDefaultRenderer(Object.class, new EstiloCotenidoJTable());
        
        jScrollPane1.setViewportView(tablaNotas);
        jScrollPane1.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        
        
        
        modelMensajeria = new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                    PInfUI.getBundle().getString("nombre")
                }
            ) {
        	public boolean isCellEditable(int row, int column)
            {
              return false;//This causes all cells to be not editable
            }
        };
        
        
        tablaMensajeriaRapida.setModel(modelMensajeria);
        tablaMensajeriaRapida.setBackground(new java.awt.Color(255, 255, 255));
        tablaMensajeriaRapida.getTableHeader().setDefaultRenderer(new EstiloCabeceraJTable());
        tablaMensajeriaRapida.setDefaultRenderer(Object.class, new EstiloCotenidoJTable());
        
        jScrollPane2.setViewportView(tablaMensajeriaRapida);
        jScrollPane2.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        
        
        
        
        modelAlertas = new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                    PInfUI.getBundle().getString("fecha"), PInfUI.getBundle().getString("sensor"), PInfUI.getBundle().getString("valor")
                }
            ) {
        	public boolean isCellEditable(int row, int column)
            {
              return false;//This causes all cells to be not editable
            }
        };
        
        tablaAlertas.setModel(modelAlertas);
        tablaAlertas.setBackground(new java.awt.Color(255, 255, 255));
        tablaAlertas.getTableHeader().setDefaultRenderer(new EstiloCabeceraJTable());
        tablaAlertas.setDefaultRenderer(Object.class, new EstiloCotenidoJTable());
        
        jScrollPane3.setViewportView(tablaAlertas);
        jScrollPane3.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        
        listaLabels.add(new LabelDTO(labelMensajeriaRapida1, "mensajeriaRapida", labelMensajeriaRapida1.getFont().getSize()));
        listaLabels.add(new LabelDTO(labelMensajeriaRapida2, "alertas", labelMensajeriaRapida2.getFont().getSize()));
        listaLabels.add(new LabelDTO(labelNotas, "notas", labelNotas.getFont().getSize()));
        
        cambiarFuentes();
        
        inicioController.cargarTablaAlertas(modelAlertas, usuario);
    
    	inicioController.cargarTablaNotas(modelNotas, usuario);
    	
    	inicioController.cargarTablaMensajeria(modelMensajeria, usuario);
        
        buttonBorrar
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/borrar.png")));
        buttonBorrar1
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/borrar.png")));
        buttonNuevaNota
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/nuevaNota.png")));
    }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNotas = new javax.swing.JTable();
        labelNotas = new javax.swing.JLabel();
        buttonBorrar = new javax.swing.JLabel();
        buttonNuevaNota = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelMensajeriaRapida1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaMensajeriaRapida = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        labelMensajeriaRapida2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAlertas = new javax.swing.JTable();
        buttonBorrar1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tablaNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaNotas);

        labelNotas.setBackground(new java.awt.Color(255, 255, 255));
        labelNotas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNotas.setForeground(new java.awt.Color(0, 153, 255));
        labelNotas.setText("Notas");

        buttonBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/es_ES/borrar.png"))); // NOI18N
        buttonBorrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonBorrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonBorrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonBorrarMouseExited(evt);
            }
        });

        buttonNuevaNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/es_ES/nuevaNota.png"))); // NOI18N
        buttonNuevaNota.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonNuevaNota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonNuevaNotaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonNuevaNotaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonNuevaNotaMouseExited(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        labelMensajeriaRapida1.setBackground(new java.awt.Color(255, 255, 255));
        labelMensajeriaRapida1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelMensajeriaRapida1.setForeground(new java.awt.Color(0, 153, 255));
        labelMensajeriaRapida1.setText("Mensajería rápida");

        tablaMensajeriaRapida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre"
            }
        ));
        tablaMensajeriaRapida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMensajeriaRapidaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaMensajeriaRapida);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMensajeriaRapida1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(labelMensajeriaRapida1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        labelMensajeriaRapida2.setBackground(new java.awt.Color(255, 255, 255));
        labelMensajeriaRapida2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelMensajeriaRapida2.setForeground(new java.awt.Color(0, 153, 255));
        labelMensajeriaRapida2.setText("Alertas");

        tablaAlertas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Alerta"
            }
        ));
        jScrollPane3.setViewportView(tablaAlertas);

        buttonBorrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/es_ES/borrar.png"))); // NOI18N
        buttonBorrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonBorrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonBorrar1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonBorrar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonBorrar1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(labelMensajeriaRapida2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonBorrar1))
            .addComponent(jScrollPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonBorrar1)
                    .addComponent(labelMensajeriaRapida2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonNuevaNota)
                        .addGap(18, 18, 18)
                        .addComponent(buttonBorrar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonNuevaNota))
                    .addComponent(buttonBorrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(75, 75, 75))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBorrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBorrarMouseEntered
        buttonBorrar
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/borrar_hover.png")));
    }//GEN-LAST:event_buttonBorrarMouseEntered

    private void buttonBorrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBorrarMouseExited
        buttonBorrar
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/borrar.png")));
    }//GEN-LAST:event_buttonBorrarMouseExited

    public void refrescarTablaNotas(){
        if (modelNotas.getRowCount() > 0) {
    	    for (int i = modelNotas.getRowCount() - 1; i > -1; i--) {
    	    	modelNotas.removeRow(i);
    	    }
    	}
        inicioController.cargarTablaNotas(modelNotas, usuario);
    }
    
    private void buttonBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBorrarMouseClicked
        inicioController.borrarNotas(tablaNotas.getSelectedRows(), usuario); 
        //Cambiar el null por el DefaultModel de la tabla
    	refrescarTablaNotas();
    }//GEN-LAST:event_buttonBorrarMouseClicked

    private void buttonNuevaNotaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonNuevaNotaMouseEntered
        buttonNuevaNota
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/nuevaNota_hover.png")));
    }//GEN-LAST:event_buttonNuevaNotaMouseEntered

    private void buttonNuevaNotaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonNuevaNotaMouseExited
        buttonNuevaNota
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/nuevaNota.png")));
    }//GEN-LAST:event_buttonNuevaNotaMouseExited

    private void buttonNuevaNotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonNuevaNotaMouseClicked
       
        this.setEnabled(false);
        PantallaNuevaNota ventanaNuevaNota = new PantallaNuevaNota(usuario, this);
        ventanaNuevaNota.setVisible(true);
        this.setEnabled(true);
    }//GEN-LAST:event_buttonNuevaNotaMouseClicked

    private void buttonBorrar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBorrar1MouseClicked
        inicioController.borrarAlerta(tablaAlertas.getSelectedRows(), usuario); 
        //Cambiar el null por el DefaultModel de la tabla
    	if (modelAlertas.getRowCount() > 0) {
    	    for (int i = modelAlertas.getRowCount() - 1; i > -1; i--) {
    	    	modelAlertas.removeRow(i);
    	    }
    	}
        inicioController.cargarTablaAlertas(modelAlertas, usuario);
    }//GEN-LAST:event_buttonBorrar1MouseClicked

    private void buttonBorrar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBorrar1MouseEntered
        buttonBorrar1
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/borrar_hover.png")));
    }//GEN-LAST:event_buttonBorrar1MouseEntered

    private void buttonBorrar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBorrar1MouseExited
       buttonBorrar1
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/borrar.png")));
    }//GEN-LAST:event_buttonBorrar1MouseExited

    private void tablaMensajeriaRapidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMensajeriaRapidaMouseClicked
        crearMensaje();
    }//GEN-LAST:event_tablaMensajeriaRapidaMouseClicked
    
    private void crearMensaje() {
    	int filaSeleccionada = tablaMensajeriaRapida.getSelectedRow();
        if(mo != null){
            mo.dispose();
        }
        Mensaje mensaje = new Mensaje();
        
        mensaje.setDni_Emisor(usuario.getDni());
        
        String dni;
        if(usuario.getId_Rol() == ConstantesAplicacion.ROL_PACIENTE) {
        	dni = usuario.getListaAsignacion().get(filaSeleccionada).getDni_Asignado();
        } else {
        	dni = usuario.getListaAsignacion().get(filaSeleccionada).getDni_Asociado();
        }
        mensaje.setDni_Receptor(dni);
        
        mo = new JPanelMEnsajeVisor1(new JPanelMensajeriaNuevo(usuario, mensaje));
        mo.setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buttonBorrar;
    private javax.swing.JLabel buttonBorrar1;
    private javax.swing.JLabel buttonNuevaNota;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelMensajeriaRapida1;
    private javax.swing.JLabel labelMensajeriaRapida2;
    private javax.swing.JLabel labelNotas;
    private javax.swing.JTable tablaAlertas;
    private javax.swing.JTable tablaMensajeriaRapida;
    private javax.swing.JTable tablaNotas;
    // End of variables declaration//GEN-END:variables
}
