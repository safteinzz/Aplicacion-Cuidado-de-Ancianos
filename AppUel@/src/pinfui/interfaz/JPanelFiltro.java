/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import pinfui.dto.TipoVentana;
import pinfui.entidades.Asignacion;
import pinfui.entidades.Usuario;

/**
 * Frame del filtro de usuarios
 * @author ITCOM
 */
public class JPanelFiltro extends PlantillaJPanel {

    
    //LISTA DE USUARIOS ( SE RELLENA SEGUN LO QUE LE PASES )
    private List<Usuario> listaUsuarios; 
    
    //USUARIO ENCONTRADO
    private Usuario userSelected; 
    
    //VARIABLE PARA ABRIR VENTANAS DE USUARIO O NO
    private boolean adminAbrirUsuarios;   
    private boolean medicoAbrirUsuarios;  
    
    //SORTER PARA FILTRAR LA TABLA
    private TableRowSorter<TableModel> sorter;
    
    private final float[] columnWidthPercentage = {0.1f, 0.25f, 0.2f, 0.125f, 0.125f, 0.1f, 0.1f};
    
    
    
    /**
     * Creates new form JPanelFiltro
     * @param rol Para basar la tabla de usuarios en un rol (usado en registro)
     * @param user Para basar la tabla de usuarios en asignaciones (usado en tabla medico)
     * Si ambos valores son null la tabla de usuarios sera de todos los usuarios de la base datos/JSON (usado en la tabla administrador)
     * @param adminAbrirUsers Si es true, se abren los usuarios en el registro para editar
     * @param medicoAbrirUsers Si es true, se abren los usuarios como nuevas Pantallas Usuario 
     */
    public JPanelFiltro(Integer rol, Usuario user, boolean adminAbrirUsers, boolean medicoAbrirUsers) 
    {
        
        
        initComponents();
        
        this.adminAbrirUsuarios = adminAbrirUsers;
        this.medicoAbrirUsuarios = medicoAbrirUsers;
        
        
        //1 admin, 2 medico, 3 familiar, 4 paciente       
        //Si trae rol cargas usuarios por rol
        if(rol != null) {
            try {            
                listaUsuarios = PInfUI.gestorDatos.getUsuarios(null, rol, false, false);
            } catch (SQLException ex) {
                Logger.getLogger(JPanelFiltro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        //Si no trae rol cargas los usuarios asociados al usuario entregado
        else if (user != null) {
            listaUsuarios = user.getUsuariosAsociados();   
        }
        //Si no trae nada rellenas tabla con todos los usuarios (y traes asignaciones) esto es ventana admin !
        else {
            try {
                listaUsuarios = PInfUI.gestorDatos.getUsuarios(null,null, true, false);
            } catch (SQLException ex) {
                Logger.getLogger(JPanelFiltro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        //CREAR EL MODELO
        DefaultTableModel model = (DefaultTableModel) jTFiltro.getModel();  
        
        //METER LAS COLUMNAS
        model.addColumn("DNI");
	model.addColumn("Nombre y Apellidos");
	model.addColumn("Email");
	model.addColumn("Municipio");
	model.addColumn("Provincia");
	model.addColumn("Tlf Movil");
	model.addColumn("Tlf Fijo");       
        
        
        //METER LOS USUARIOS EN LAS FILAS DE LA TABLA
        listaUsuarios.forEach((usuario) -> {
            String municipio = " ";
            String provincia = " ";
            if (usuario.getMunicipio() != null)
            {
                municipio = usuario.getMunicipio().getNombre();
                provincia = usuario.getMunicipio().getProvincia().getProvincia();
            }
            model.addRow(
                    new Object[]{
                        usuario.getDni(),
                        usuario.getNombre() + " " + usuario.getApellidos(),
                        usuario.getEmail(),
                        municipio,
                        provincia,
                        usuario.getTlf_Movil(),
                        usuario.getTlf_Fijo()
                    }
            );
        });      
        
        //CREAR UN ORGANIZADOR
        sorter = new TableRowSorter<>(jTFiltro.getModel());
        
        //SETEAR EL ORGANIZADOR A LA TABLA
        jTFiltro.setRowSorter(sorter);  

        
        //LISTENER PARA SABER CUANDO EL USUARIO CLICA
        jTFiltro.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                //Point point = mouseEvent.getPoint();
                //int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    //System.out.println(jTFiltro.getValueAt(jTFiltro.getSelectedRow(), 0).toString());
                    setUserSelected((jTFiltro.getValueAt(jTFiltro.getSelectedRow(), 0).toString()).trim());                    
                }
            }
        }); 
        
        
//        jTFiltro.setRowSelectionAllowed(true);
//        jTFiltro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        jTFiltro.setRowSelectionInterval(0, 0);
        jTFiltro.setFocusable(false);
//        jTFiltro.setRowSelectionAllowed(true);   

        resizeColumns();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeColumns();
            }
        });
        
        
    }

    /**
     * Metodo para setear el ancho de las columnas de la tabla en tanto por cierto
     */
    private void resizeColumns() {        
        //Use TableColumnModel.getTotalColumnWidth() if your table is included in a JScrollPane
        int tW = jTFiltro.getWidth();
        TableColumn column;
        TableColumnModel jTableColumnModel = jTFiltro.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }
    
    
    
    
    /**
     * Metodo para setear el usuario seleccionado como atributo.
     * Ademas este metodo se encargará de abrir ventanas con el usuario si  esta en modo ventanas
     * En modo vetanas se abrira una ventana de usuario con la información del usuario 
     * @param dni 
     */
    private void setUserSelected(String dni) {
        listaUsuarios.stream().filter((u) -> (u.getDni().equals(dni))).forEachOrdered((u) -> {
            this.userSelected = u;
        });
        
        //SI ESTA EN MODO ABRIR USUARIO
        //MODO ADMIN
        if (this.adminAbrirUsuarios)
        {
            PInfUI.abrirVentana(this.userSelected, null, TipoVentana.REGISTRO);
        }
        //MODO MEDICO
        else if (this.medicoAbrirUsuarios)
        {
            PInfUI.abrirVentana(this.userSelected, null, TipoVentana.MEDICOABREPACIENTE);
        }
    }

    public Usuario getUserSelected() {
        return userSelected;
    }   
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTFCasillaBusqueda = new javax.swing.JTextField();
        jCBFiltrarPor = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTFiltro = new javax.swing.JTable();
        jBFiltrar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(850, 391));
        setLayout(new java.awt.GridBagLayout());

        jTFCasillaBusqueda.setFont(new java.awt.Font("Tahoma", 0, 24));
        jTFCasillaBusqueda.setText("Buscar");
        jTFCasillaBusqueda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFCasillaBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 339;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 0, 0, 0);
        add(jTFCasillaBusqueda, gridBagConstraints);

        jCBFiltrarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "DNI", "Nombre y Apellidos", "Email", "Municipio", "Provincia", "Telefono Movil", "Telefono Fijo" }));
        jCBFiltrarPor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCBFiltrarPor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 0, 0);
        add(jCBFiltrarPor, gridBagConstraints);

        jLabel1.setText("Buscar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 18, 0, 0);
        add(jLabel1, gridBagConstraints);

        jTFiltro.setModel(new javax.swing.table.DefaultTableModel(

        )
        {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int row, int column) {return false;}
        }
    );
    jTFiltro.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
    jScrollPane1.setViewportView(jTFiltro);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridwidth = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 826;
    gridBagConstraints.ipady = 284;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
    add(jScrollPane1, gridBagConstraints);

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
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.ipadx = 22;
    gridBagConstraints.ipady = 9;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(11, 59, 0, 0);
    add(jBFiltrar, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Metodo para filtrar por columnas
     * @param filtroInput Lo que hay que buscar
     * @param numColumna En que columna hay que buscarlo
     */
    private void filtro(String filtroInput, int numColumna) {
        
//        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() 
//        {
//            @Override
//            public boolean include(Entry<?, ?> entry) 
//            {
//                int numberOfColumn = numColumna; 
//                String value= String.valueOf(entry.getValue(numberOfColumn));
//                return value.equalsIgnoreCase(filtroInput);
//            }
//        };
        
        
        RowFilter<TableModel, Object> filter = null;
        try {
            filter = RowFilter.regexFilter("(?i)" + filtroInput,numColumna);
        } catch (PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(filter);
    }

    
    
    
    
    /**
     * Metodo Accion filtro
     * El metodo en base a la opcion escogida hace una de dos cosas
     *  1º Filtra por fila en base a lo introducido en el textfield
     *  2º Filtra en toda la tabla en baso a lo introducido en el textfield
     * El metodo sabe el modo a usar en base al index de la combobox de busqueda, siendo 0 para TODO y 1,2,3.. para lo demas
     * @param evt 
     */
    private void jBFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFiltrarActionPerformed
        jBFiltrarPress();
    }//GEN-LAST:event_jBFiltrarActionPerformed

    private void jBFiltrarPress()
    {
        //Si ha elegido una accion que no sea TODO
        if (jCBFiltrarPor.getSelectedIndex() != 0) 
        {
            int fila = jCBFiltrarPor.getSelectedIndex() - 1;
            filtro(jTFCasillaBusqueda.getText(), fila);
        }
        //Si ha elegido la accion todo
        else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + jTFCasillaBusqueda.getText()));
        }    
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBFiltrar;
    private javax.swing.JComboBox<String> jCBFiltrarPor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCasillaBusqueda;
    private javax.swing.JTable jTFiltro;
    // End of variables declaration//GEN-END:variables
}
