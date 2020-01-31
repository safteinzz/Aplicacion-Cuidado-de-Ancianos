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
public class JPanelPresencia extends PlantillaJPanel {

    private PresenciaController presenciaController = new PresenciaController();
    
    private String dniPaciente;
    private String nombrePaciente;
    
    private DefaultTableModel model;
    
    /**
     * Constructor
     * @param dniPaciente DNI del paciente a buscar por el panel
     * @param nombrePaciente Nombre del paciente a buscar por el panel
     */
    public JPanelPresencia(String dniPaciente, String nombrePaciente) {
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
        buttonExportarExcel
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/exportar.png")));
        buttonBuscar
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/buscar.png")));
        
        jTable1.setModel(model);
        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.getTableHeader().setDefaultRenderer(new EstiloCabeceraJTable());
        jTable1.setDefaultRenderer(Object.class, new EstiloCotenidoJTable());
        
        jScrollPane1.setViewportView(jTable1);
        jScrollPane1.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        
    	comboTipoPresencia.addItem(new Item(null, PInfUI.getBundle().getString("seleccionar")));
        for(TipoPresencia tipoPresencia : PInfUI.gestorDatos.getTiposPresencias()){
            comboTipoPresencia.addItem(new Item(tipoPresencia, PInfUI.getBundle().getString("tipoPresencia" + tipoPresencia.getId())));
        }
        
        comboTipoPresencia.setRenderer( new ItemRenderer() );
        
        rellenarFechaDesde(fechaDesde);
        rellenarFechaHasta(fechaHasta);
        
        listaLabels.add(new LabelDTO(jLabel1, "lugar", jLabel1.getFont().getSize()));
        listaLabels.add(new LabelDTO(jLabel6, "fechaDesde", jLabel6.getFont().getSize()));
        listaLabels.add(new LabelDTO(jLabel10, "fechaHasta", jLabel10.getFont().getSize()));
        
        cambiarFuentes();
    }
    
    /**
     * Rellena los JTextField de la pantalla de la fecha desde
     * @param fecha - Fecha desde con la que rellenar los JTextField
     */
    private void rellenarFechaDesde(Calendar fecha) {
    	jTextField2.setText(String.valueOf(fecha.get(Calendar.DAY_OF_MONTH)));
    	jTextField3.setText(String.valueOf(fecha.get(Calendar.MONTH) + 1));
    	jTextField4.setText(String.valueOf(fecha.get(Calendar.YEAR)));
        jTextField8.setText(String.valueOf(fecha.get(Calendar.HOUR_OF_DAY)));
        jTextField9.setText(String.valueOf(fecha.get(Calendar.MINUTE)));
    }
    
    /**
     * Recupera una fecha a partir de los JTextField de fecha desde de la pantalla
     * @return Devuelve la fecha recuperada de los JTextField de fecha desde de la pantalla
     */
    private Calendar recuperarFechaDesde() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	String fechaString = jTextField2.getText() + "/" + jTextField3.getText() + "/" +jTextField4.getText() + " " + jTextField8.getText() + ":" +jTextField9.getText();
    	try {
	    	Calendar fechaDesde = Calendar.getInstance();
	    	fechaDesde.setTime(sdf.parse(fechaString));
	    	
	    	return fechaDesde;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * Rellena los JTextField de la pantalla de la fecha hasta
     * @param fecha - Fecha hasta con la que rellenar los JTextField
     */
    private void rellenarFechaHasta(Calendar fecha) {
    	jTextField5.setText(String.valueOf(fecha.get(Calendar.DAY_OF_MONTH)));
    	jTextField6.setText(String.valueOf(fecha.get(Calendar.MONTH) + 1));
    	jTextField7.setText(String.valueOf(fecha.get(Calendar.YEAR)));
        jTextField10.setText(String.valueOf(fecha.get(Calendar.HOUR_OF_DAY)));
        jTextField11.setText(String.valueOf(fecha.get(Calendar.MINUTE)));
    }
    
    /**
     * Recupera una fecha a partir de los JTextField de fecha hasta de la pantalla
     * @return Devuelve la fecha recuperada de los JTextField de fecha hasta de la pantalla
     */
    private Calendar recuperarFechaHasta() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	String fechaString = jTextField5.getText() + "/" + jTextField6.getText() + "/" +jTextField7.getText() + " " + jTextField10.getText() + ":" +jTextField11.getText();;
    	try {
	    	Calendar fechaHasta = Calendar.getInstance();
	    	fechaHasta.setTime(sdf.parse(fechaString));
	    	
	    	return fechaHasta;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboTipoPresencia = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonBuscar = new javax.swing.JLabel();
        buttonExportarExcel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Lugar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 93, Short.MAX_VALUE))
                    .addComponent(comboTipoPresencia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboTipoPresencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(248, 64));

        jLabel10.setText("Fecha hasta");

        jLabel11.setText("/");

        jLabel12.setText("/");

        jLabel4.setText(":");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(248, 64));

        jLabel6.setText("Fecha desde");

        jLabel8.setText("/");

        jLabel9.setText("/");

        jLabel3.setText(":");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

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

        buttonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/es_ES/buscar.png"))); // NOI18N
        buttonBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonBuscarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonBuscarMouseExited(evt);
            }
        });

        buttonExportarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/es_ES/exportar.png"))); // NOI18N
        buttonExportarExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonExportarExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonExportarExcelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonExportarExcelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonExportarExcelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonBuscar)
                        .addGap(31, 31, 31)
                        .addComponent(buttonExportarExcel)
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonBuscar)
                    .addComponent(buttonExportarExcel))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBuscarMouseEntered
        buttonBuscar
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/buscar_hover.png")));
    }//GEN-LAST:event_buttonBuscarMouseEntered

    private void buttonBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBuscarMouseExited
        buttonBuscar
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/buscar.png")));
    }//GEN-LAST:event_buttonBuscarMouseExited

    private void buttonBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBuscarMouseClicked
        Item objeto = (Item) comboTipoPresencia.getSelectedItem();

        Calendar fechaDesde = recuperarFechaDesde();
        Calendar fechaHasta = recuperarFechaHasta();

        if(comprobarFiltros(fechaDesde, fechaHasta)) {
            rellenarTabla(fechaDesde, fechaHasta, (TipoPresencia) objeto.getTipo());
        }
    }//GEN-LAST:event_buttonBuscarMouseClicked

    private void buttonExportarExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExportarExcelMouseClicked
        Item objeto = (Item) comboTipoPresencia.getSelectedItem();

        Calendar fechaDesde = recuperarFechaDesde();
        Calendar fechaHasta = recuperarFechaHasta();

        if(comprobarFiltros(fechaDesde, fechaHasta)) {
            //llamada al metodo encargado de abrir un Excel
            presenciaController.createExcel(dniPaciente, fechaDesde, fechaHasta, (TipoPresencia) objeto.getTipo(), new String [] {
                PInfUI.getBundle().getString("lugar"), PInfUI.getBundle().getString("fecha")
            }, nombrePaciente);
        }
    }//GEN-LAST:event_buttonExportarExcelMouseClicked

    private void buttonExportarExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExportarExcelMouseEntered
        buttonExportarExcel
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/exportar_hover.png")));
    }//GEN-LAST:event_buttonExportarExcelMouseEntered

    private void buttonExportarExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExportarExcelMouseExited
        buttonExportarExcel
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/exportar.png")));
    }//GEN-LAST:event_buttonExportarExcelMouseExited

    private boolean comprobarFiltros(Calendar fechaDesde, Calendar fechaHasta) {
    	if((fechaDesde == null && fechaHasta == null) || (fechaDesde != null && fechaHasta == null) || (fechaDesde != null && fechaHasta != null && fechaDesde.compareTo(fechaHasta) < 0)) {
        	return true;
        } else {
        	JOptionPane.showMessageDialog(null, PInfUI.getBundle().getString("error.rangoFechas"),
					"Error", ERROR_MESSAGE);
        	return false;
        }
    }
    
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
    private javax.swing.JLabel buttonBuscar;
    private javax.swing.JLabel buttonExportarExcel;
    private javax.swing.JComboBox<Item> comboTipoPresencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
