/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import pinfui.controller.RitmoCardiacoController;
import pinfui.dto.Item;
import pinfui.dto.ItemRenderer;
import pinfui.dto.LabelDTO;
import pinfui.dto.TipoRango;

/**
 * Frame del panel de ritmo cardiaco
 * @author ITCOM
 */
public class JPanelRitmoCardiaco extends PlantillaJPanel {

    private RitmoCardiacoController ritmoCardiacoController = new RitmoCardiacoController();
    
    private String dniPaciente;
    private String nombrePaciente;
    
    /**
     * Constructor
     * @param dniPaciente DNI del paciente a buscar por el panel
     * @param nombrePaciente Nombre del paciente a buscar por el panel
     */
    public JPanelRitmoCardiaco(String dniPaciente, String nombrePaciente) {
        this.dniPaciente = dniPaciente;
        this.nombrePaciente = nombrePaciente;
    	initComponents();
        
        for(TipoRango tipoRango : TipoRango.values()){
            comboTipoRango.addItem(new Item(tipoRango, PInfUI.getBundle().getString("tipoRango" + tipoRango.name())));
        }
        
        comboTipoRango.setRenderer( new ItemRenderer() );
        
        Calendar fechaHasta = ritmoCardiacoController.getFechaActual();
        rellenarFechaHasta(fechaHasta);
        
        Calendar fechaDesde = ritmoCardiacoController.getDiaAnterior(fechaHasta);
        rellenarFechaDesde(fechaDesde);
        
        jTextField1.setText("1");
        
        pintarGrafica(TipoRango.HORA, 1, fechaDesde, fechaHasta);
        
        listaLabels.add(new LabelDTO(jLabel1,"tipoRango", jLabel1.getFont().getSize()));
        listaLabels.add(new LabelDTO(jLabel2, "cantidadRango", jLabel2.getFont().getSize()));
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
        jTextField8.setText(String.valueOf(fecha.get(Calendar.HOUR)));
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
        jTextField10.setText(String.valueOf(fecha.get(Calendar.HOUR)));
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
        comboTipoRango = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        panelGrafica = new javax.swing.JPanel();
        labelGrafica = new javax.swing.JLabel();
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
        iconExcel = new javax.swing.JLabel();
        jBFiltrar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Tipo de rango");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipoRango, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboTipoRango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Cantidad de rango");

        jTextField1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 128, Short.MAX_VALUE))
                    .addComponent(jTextField1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelGrafica.setBackground(new java.awt.Color(255, 255, 255));
        panelGrafica.setPreferredSize(new java.awt.Dimension(1000, 600));

        labelGrafica.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
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
                .addContainerGap(42, Short.MAX_VALUE))
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
                .addContainerGap(28, Short.MAX_VALUE))
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

        iconExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/excel.png"))); // NOI18N
        iconExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconExcelMouseClicked(evt);
            }
        });

        jBFiltrar.setBackground(new java.awt.Color(255, 255, 255));
        jBFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botonBuscar.png"))); // NOI18N
        jBFiltrar.setBorder(null);
        jBFiltrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBFiltrar.setDefaultCapable(false);
        jBFiltrar.setBorderPainted(false);
        jBFiltrar.setContentAreaFilled(false);
        jBFiltrar.setFocusPainted(false);
        jBFiltrar.setOpaque(false);
        jBFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(iconExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jBFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void iconExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconExcelMouseClicked
        boolean permisos = true;
        
        Item objeto = (Item) comboTipoRango.getSelectedItem();
        
        int rango = 1;
        try {
        	rango = Integer.valueOf(jTextField1.getText());
        	
        	if(0 > rango || rango > 60) {
        		JOptionPane.showMessageDialog(null, PInfUI.getBundle().getString("error.cantidadRango"),
    					"Error", ERROR_MESSAGE);
        		permisos = false;
        	}
        } catch(Exception e) {
        	JOptionPane.showMessageDialog(null, PInfUI.getBundle().getString("error.rangoNoNumero"),
					"Error", ERROR_MESSAGE);
        	permisos = false;
        }
        
        Calendar fechaDesde = recuperarFechaDesde();
        Calendar fechaHasta = recuperarFechaHasta();
        
        if(permisos) {
	        if((fechaDesde == null && fechaHasta == null) || (fechaDesde != null && fechaHasta == null) || (fechaDesde != null && fechaHasta != null && fechaDesde.compareTo(fechaHasta) < 0)) {
	        	ritmoCardiacoController.createExcel(dniPaciente, nombrePaciente, (TipoRango) objeto.getTipo(), rango, fechaDesde.getTime(), fechaHasta.getTime());
	        } else {
	        	JOptionPane.showMessageDialog(null, PInfUI.getBundle().getString("error.rangoFechas"),
						"Error", ERROR_MESSAGE);
	        }
        }
    }//GEN-LAST:event_iconExcelMouseClicked

    private void jBFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFiltrarActionPerformed
        boolean permisos = true;
        
        Item objeto = (Item) comboTipoRango.getSelectedItem();
        
        int rango = 1;
        try {
        	rango = Integer.valueOf(jTextField1.getText());
        	
        	if(0 > rango || rango > 60) {
        		JOptionPane.showMessageDialog(null, PInfUI.getBundle().getString("error.cantidadRango"),
    					"Error", ERROR_MESSAGE);
        		permisos = false;
        	}
        } catch(Exception e) {
        	JOptionPane.showMessageDialog(null, PInfUI.getBundle().getString("error.rangoNoNumero"),
					"Error", ERROR_MESSAGE);
        	permisos = false;
        }
        
        Calendar fechaDesde = recuperarFechaDesde();
        Calendar fechaHasta = recuperarFechaHasta();
        
        if(permisos) {
	        if((fechaDesde == null && fechaHasta == null) || (fechaDesde != null && fechaHasta == null) || (fechaDesde != null && fechaHasta != null && fechaDesde.compareTo(fechaHasta) < 0)) {
	        	pintarGrafica((TipoRango) objeto.getTipo(), rango, fechaDesde,fechaHasta);
	        } else {
	        	JOptionPane.showMessageDialog(null, PInfUI.getBundle().getString("error.rangoFechas"),
						"Error", ERROR_MESSAGE);
	        }
        }
    }//GEN-LAST:event_jBFiltrarActionPerformed

    /**
     * Metodo encargado pintar la imagen que devuelve RitmoCardiacoController
     * @param tipoRango Tipo de rango del filtro
     * @param rango Cantidad de rango del filtro
     * @param fechaDesde Fecha desde del filtro
     * @param fechaHasta Fecha hasta del filtro
     */
    private void pintarGrafica(TipoRango tipoRango, int rango, Calendar fechaDesde, Calendar fechaHasta) {
    	Image grafica = ritmoCardiacoController.crearGrafica(dniPaciente, nombrePaciente, tipoRango, rango, fechaDesde.getTime(), fechaHasta.getTime());
    	
    	if(grafica != null) {
    		labelGrafica.setIcon(new ImageIcon(grafica));
    	} else {
    		JOptionPane.showMessageDialog(null, PInfUI.getBundle().getString("ritmoCardiacoNoEncontrado"),
					"Error", ERROR_MESSAGE);
    	}
    	
    	
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Item> comboTipoRango;
    private javax.swing.JLabel iconExcel;
    private javax.swing.JButton jBFiltrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField1;
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
    private javax.swing.JLabel labelGrafica;
    private javax.swing.JPanel panelGrafica;
    // End of variables declaration//GEN-END:variables
}
