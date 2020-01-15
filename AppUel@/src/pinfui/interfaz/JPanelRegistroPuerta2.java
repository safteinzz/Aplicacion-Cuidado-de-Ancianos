/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import pinfui.controller.PresenciaController;
import pinfui.dto.EstiloCabeceraJTable;
import pinfui.dto.EstiloCotenidoJTable;
import pinfui.dto.Item;
import pinfui.dto.ItemRenderer;
import pinfui.dto.LabelDTO;
import pinfui.entidades.TipoPresencia;

/**
 * Frame del panel de ritmo cardiaco
 * @author ITCOM
 */
public class JPanelRegistroPuerta2 extends PlantillaJPanel {

    private PresenciaController presenciaController = new PresenciaController();
    
    private String dniPaciente;
    private String nombrePaciente;
    
    private DefaultTableModel model;
    
    /**
     * Constructor
     * @param dniPaciente DNI del paciente a buscar por el panel
     * @param nombrePaciente Nombre del paciente a buscar por el panel
     */
    public JPanelRegistroPuerta2(String dniPaciente, String nombrePaciente) {
        this.dniPaciente = dniPaciente;
        this.nombrePaciente = nombrePaciente;
        model = new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    PInfUI.getBundle().getString("lugar"), PInfUI.getBundle().getString("fecha")
                }
            ) {
        	public boolean isCellEditable(int row, int column)
            {
              return false;//This causes all cells to be not editable
            }
        };
        
        Calendar fechaHasta = presenciaController.getFechaActual();
        Calendar fechaDesde = presenciaController.getDiaAnterior(fechaHasta);
        
        rellenarTabla(fechaDesde, fechaHasta, null);
//        this.nombrePaciente = nombrePaciente;
    	initComponents();
        jTable1.setModel(model);
        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.getTableHeader().setDefaultRenderer(new EstiloCabeceraJTable());
        jTable1.setDefaultRenderer(Object.class, new EstiloCotenidoJTable());
        
        jScrollPane1.setViewportView(jTable1);
        jScrollPane1.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        
        iconExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/excel.png")));
        iconExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        cambiarFuentes();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        iconExcel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        iconExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconExcelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(841, 841, 841)
                        .addComponent(iconExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(iconExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void iconExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconExcelMouseClicked

    }//GEN-LAST:event_iconExcelMouseClicked
    
    private void rellenarTabla(Calendar fechaDesde, Calendar fechaHasta, TipoPresencia tipoPresencia) {
    	//Cambiar el null por el DefaultModel de la tabla
    	if (model.getRowCount() > 0) {
    	    for (int i = model.getRowCount() - 1; i > -1; i--) {
    	    	model.removeRow(i);
    	    }
    	}
    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
//    	presenciaController.convertListPresencia(model, dniPaciente, fechaDesde, fechaHasta, tipoPresencia);
    	
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconExcel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
