/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import pinfui.dto.EstiloCabeceraJTable;
import pinfui.dto.EstiloCotenidoJTable;
import pinfui.dto.Item;
import pinfui.dto.ItemRenderer;
import pinfui.dto.LabelDTO;
import pinfui.entidades.Mensaje;
import pinfui.entidades.TipoPresencia;
import pinfui.entidades.Usuario;

/**
 *
 * @author Usuario DAM
 */
public class JPanelMensajeria extends PlantillaJPanel{
    
    private DefaultTableModel model;
    private Usuario usuario;
    private List<Mensaje> mensajes;
    private JPanelMensajeriaNuevo visorMensaje;
    private JPanelMEnsajeVisor1 mo = null;
    private JPanelMensajeriaResponder visorMensajeRespuesta;
    

    /**
     * Creates new form JPanelMensajeria1
     */
    public JPanelMensajeria(Usuario usuario) {
        initComponents();
        buttonCrear
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/nuevoMensaje.png")));
        buttonBuscar
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/buscar.png")));
        
        
        this.usuario = usuario;
        crearTabla();
        
        //Agregar elementos en el combo
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("todo"), PInfUI.getBundle().getString("todo")));
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("dni"), PInfUI.getBundle().getString("dni")));
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("remitente"), PInfUI.getBundle().getString("remitente")));
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("titulo"), PInfUI.getBundle().getString("titulo")));
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("etiqueta"), PInfUI.getBundle().getString("etiqueta")));
        
        //Estilo para el combo
        jCBFiltrarPor.setRenderer( new ItemRenderer() );
        
        listaLabels.add(new LabelDTO(labelTitulo, "tituloMensajeria", labelTitulo.getFont().getSize()));
        listaLabels.add(new LabelDTO(jLabel2, "filtro", jLabel2.getFont().getSize()));
        listaLabels.add(new LabelDTO(jLabel1, "tipoBusqueda", jLabel1.getFont().getSize()));
        
        cambiarFuentes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        panelFiltro = new javax.swing.JPanel();
        jTFCasillaBusqueda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCBFiltrarPor = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        panelNuevoMensaje = new javax.swing.JPanel();
        buttonCrear = new javax.swing.JLabel();
        panelBuscar = new javax.swing.JPanel();
        buttonBuscar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        labelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        labelTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(0, 153, 255));
        labelTitulo.setText("Mensajería");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        panelFiltro.setBackground(new java.awt.Color(255, 255, 255));

        jTFCasillaBusqueda.setFont(new java.awt.Font("Tahoma", 0, 24));
        jTFCasillaBusqueda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFCasillaBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTFCasillaBusqueda.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jTFCasillaBusqueda.setText("");
            }
        });

        jTFCasillaBusqueda.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    jBFiltrarPress();
                }
            }
        });

        jLabel2.setText("Filtro");

        jCBFiltrarPor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCBFiltrarPor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jCBFiltrarPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBFiltrarPorActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo de búsqueda");

        javax.swing.GroupLayout panelFiltroLayout = new javax.swing.GroupLayout(panelFiltro);
        panelFiltro.setLayout(panelFiltroLayout);
        panelFiltroLayout.setHorizontalGroup(
            panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltroLayout.createSequentialGroup()
                .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFCasillaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jCBFiltrarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        panelFiltroLayout.setVerticalGroup(
            panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltroLayout.createSequentialGroup()
                .addGap(0, 38, Short.MAX_VALUE)
                .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFCasillaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBFiltrarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelBotones.setBackground(new java.awt.Color(255, 255, 255));

        panelNuevoMensaje.setBackground(new java.awt.Color(255, 255, 255));

        buttonCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/es_ES/nuevoMensaje.png"))); // NOI18N
        buttonCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCrearMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonCrearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonCrearMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelNuevoMensajeLayout = new javax.swing.GroupLayout(panelNuevoMensaje);
        panelNuevoMensaje.setLayout(panelNuevoMensajeLayout);
        panelNuevoMensajeLayout.setHorizontalGroup(
            panelNuevoMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoMensajeLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(buttonCrear)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        panelNuevoMensajeLayout.setVerticalGroup(
            panelNuevoMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoMensajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonCrear)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelBuscar.setBackground(new java.awt.Color(255, 255, 255));

        buttonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/es_ES/buscar.png"))); // NOI18N
        buttonBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonBuscarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(buttonBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonBuscar)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNuevoMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addComponent(panelNuevoMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTitulo)
                            .addComponent(panelFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 161, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int filaSeleccionada = jTable2.getSelectedRow();
        visorMensajeRespuesta = new JPanelMensajeriaResponder(usuario, mensajes.get(filaSeleccionada));
        if(mo != null){
            mo.dispose();
        }
        mo = new JPanelMEnsajeVisor1(visorMensajeRespuesta);
        mo.setVisible(true);
    }//GEN-LAST:event_jTable2MouseClicked

    private void buttonBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBuscarMouseEntered
        buttonBuscar
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/buscar_hover.png")));
    }//GEN-LAST:event_buttonBuscarMouseEntered

    private void buttonBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBuscarMouseExited
        buttonBuscar
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/buscar.png")));
    }//GEN-LAST:event_buttonBuscarMouseExited

    private void buttonCrearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCrearMouseEntered
        buttonCrear
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/nuevoMensaje_hover.png")));
    }//GEN-LAST:event_buttonCrearMouseEntered

    private void buttonCrearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCrearMouseExited
        buttonCrear
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/nuevoMensaje.png")));
    }//GEN-LAST:event_buttonCrearMouseExited

    private void jCBFiltrarPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBFiltrarPorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBFiltrarPorActionPerformed

    private void buttonCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCrearMouseClicked
        // TODO add your handling code here:
        Mensaje mensaje = new Mensaje();
        JPanelMensajeriaNuevo mensajeNuevo = new JPanelMensajeriaNuevo(usuario, mensaje);
        
        mo = new JPanelMEnsajeVisor1(mensajeNuevo);
        mo.setVisible(true);
    }//GEN-LAST:event_buttonCrearMouseClicked

    private void jBFiltrarPress()
    {
        //accion del filtro
    }
    
    private void crearTabla(){
        //Creacion de las columnas de la tabla
        final String [] nombreColumnas = {PInfUI.getBundle().getString("remitente"),PInfUI.getBundle().getString("titulo"),PInfUI.getBundle().getString("etiqueta")};
        
        Object [][] listaRegistrosPP = {};
        
        //cargar estilos a la tabla
        model = new javax.swing.table.DefaultTableModel(listaRegistrosPP, nombreColumnas)
                {
        	public boolean isCellEditable(int row, int column)
            {
              return false;//This causes all cells to be not editable
            }};
        cargarListaRegistros();
        jTable2.setModel(model);
        jTable2.setBackground(new java.awt.Color(255, 255, 255));
        jTable2.getTableHeader().setDefaultRenderer(new EstiloCabeceraJTable());
        jTable2.setDefaultRenderer(Object.class, new EstiloCotenidoJTable());
    }
    
    private void cargarListaRegistros(){
        
        mensajes = new ArrayList<>();
        
        for(Mensaje mensaje : usuario.getListaMensajes()){
            Object [] mensajito = {
                mensaje.getDni_Emisor(), mensaje.getAsunto(), mensaje.getEtiqueta()
            };
            model.addRow(mensajito);
            mensajes.add(mensaje);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buttonBuscar;
    private javax.swing.JLabel buttonCrear;
    private javax.swing.JComboBox<Item> jCBFiltrarPor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCasillaBusqueda;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelFiltro;
    private javax.swing.JPanel panelNuevoMensaje;
    // End of variables declaration//GEN-END:variables

}
