/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import java.awt.Insets;
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
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import pinfui.dto.EstiloCabeceraJTable;
import pinfui.dto.EstiloCotenidoJTable;
import pinfui.dto.Item;
import pinfui.dto.ItemRenderer;
import pinfui.dto.LabelDTO;
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
        
        buttonBuscar
        .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/buscar.png")));
        
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
//        jTFiltro.setRowSelectionAllowed(true);   
        

        //Setear estilo headers
        jTFiltro.getTableHeader().setDefaultRenderer(new EstiloCabeceraJTable());
        
        //setear renderer de celdas de tabla
        jTFiltro.setDefaultRenderer(Object.class, new EstiloCotenidoJTable());


        jTFiltro.setFocusable(false);
        
        //Quitar tabla que no se usa
        jScrollPaneTabla.getViewport().setBackground(jTFiltro.getBackground());
        jScrollPaneTabla.setBorder(createEmptyBorder());

        

        resizeColumns();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeColumns();
            }
        });
        
        
        
        //Cargar combo
//        Todo, DNI, Nombre y Apellidos, Email, Municipio, Provincia, Telefono Movil, Telefono Fijo
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("todo"), PInfUI.getBundle().getString("todo")));  
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("dni"), PInfUI.getBundle().getString("dni")));
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("nombre"), PInfUI.getBundle().getString("nombre")));
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("email"), PInfUI.getBundle().getString("email")));
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("municipio"), PInfUI.getBundle().getString("municipio")));
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("provincia"), PInfUI.getBundle().getString("provincia")));
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("tlfMovil"), PInfUI.getBundle().getString("tlfMovil")));
        jCBFiltrarPor.addItem(new Item(PInfUI.getBundle().getString("tlfFijo"), PInfUI.getBundle().getString("tlfFijo")));
        
        //Estilo para el combo
        jCBFiltrarPor.setRenderer( new ItemRenderer() );
        
        listaLabels.add(new LabelDTO(jLabel1, "buscar", jLabel1.getFont().getSize()));
        
        cambiarFuentes();
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
        jScrollPaneTabla = new javax.swing.JScrollPane();
        jTFiltro = new javax.swing.JTable();
        buttonBuscar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(850, 391));

        jTFCasillaBusqueda.setFont(new java.awt.Font("Tahoma", 0, 24));
        jTFCasillaBusqueda.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTFCasillaBusqueda.setText("Buscar");
        jTFCasillaBusqueda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFCasillaBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTFCasillaBusqueda.setMaximumSize(new java.awt.Dimension(347, 47));
        jTFCasillaBusqueda.addMouseListener
        (
            new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    jTFCasillaBusqueda.setText("");
                }
            }
        );

        jTFCasillaBusqueda.addKeyListener(
            new KeyAdapter()
            {
                @Override
                public void keyPressed(KeyEvent e)
                {
                    if (e.getKeyCode()==KeyEvent.VK_ENTER)
                    {
                        jBFiltrarPress();
                    }
                }
            }
        );
        jTFCasillaBusqueda.setNextFocusableComponent(jCBFiltrarPor);

        jCBFiltrarPor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCBFiltrarPor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        //Handler enter = guardar
        jCBFiltrarPor.addKeyListener(
            new KeyAdapter()
            {
                @Override
                public void keyPressed(KeyEvent e)
                {
                    if (e.getKeyCode()==KeyEvent.VK_ENTER)
                    {
                        jBFiltrarPress();
                    }
                }
            }
        );
        jCBFiltrarPor.setNextFocusableComponent(buttonBuscar);

        jLabel1.setText("Buscar");

        jScrollPaneTabla.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneTabla.setBorder(null);
        jScrollPaneTabla.setForeground(new java.awt.Color(255, 255, 255));

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
    jTFiltro.setIntercellSpacing(new java.awt.Dimension(0, 0));
    jScrollPaneTabla.setViewportView(jTFiltro);

    buttonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/es_ES/buscar.png"))); // NOI18N
    buttonBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    buttonBuscar.setNextFocusableComponent(jScrollPaneTabla);
    buttonBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            buttonBuscarFocusGained(evt);
        }
        public void focusLost(java.awt.event.FocusEvent evt) {
            buttonBuscarFocusLost(evt);
        }
    });
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
    buttonBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            buttonBuscarKeyPressed(evt);
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
                    .addComponent(jTFCasillaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jCBFiltrarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(buttonBuscar)
                    .addGap(0, 107, Short.MAX_VALUE))
                .addComponent(jScrollPaneTabla))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addGap(8, 8, 8)
                        .addComponent(jCBFiltrarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jTFCasillaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
            .addContainerGap())
    );
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

    
    
    
    
    private void buttonBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBuscarMouseClicked
        jBFiltrarPress();
    }//GEN-LAST:event_buttonBuscarMouseClicked

    private void buttonBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBuscarMouseEntered
        buttonBuscar
        .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/buscar_hover.png")));
    }//GEN-LAST:event_buttonBuscarMouseEntered

    private void buttonBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBuscarMouseExited
        buttonBuscar
        .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/buscar.png")));
    }//GEN-LAST:event_buttonBuscarMouseExited

    private void buttonBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonBuscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE)
            jBFiltrarPress();
        else if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            jBFiltrarPress();
    }//GEN-LAST:event_buttonBuscarKeyPressed

    private void buttonBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buttonBuscarFocusGained
        buttonBuscar
        .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/buscar_hover.png")));
    }//GEN-LAST:event_buttonBuscarFocusGained

    private void buttonBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buttonBuscarFocusLost
        buttonBuscar
        .setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/botones/" + PInfUI.bundle.getLocale() + "/buscar.png")));
    }//GEN-LAST:event_buttonBuscarFocusLost

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
    private javax.swing.JLabel buttonBuscar;
    private javax.swing.JComboBox<Item> jCBFiltrarPor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPaneTabla;
    private javax.swing.JTextField jTFCasillaBusqueda;
    private javax.swing.JTable jTFiltro;
    // End of variables declaration//GEN-END:variables
}
