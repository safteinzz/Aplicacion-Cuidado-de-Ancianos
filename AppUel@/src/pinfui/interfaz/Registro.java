/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pinfui.dto.TipoVentana;
import pinfui.entidades.Asignacion;
import pinfui.entidades.Municipio;
import pinfui.entidades.Provincia;
import pinfui.entidades.Rol;
import pinfui.entidades.Usuario;
/**
 *
 * @author ITCOM
 */
public class Registro extends javax.swing.JFrame {

    List<Provincia> provinciasLista;
    List<Municipio> municipiosLista;
    List<Rol> rolesLista;
    Usuario medicoAsignado = null;
    Usuario familiarAsignado = null;
    Usuario aEditar = null;
    boolean editar = false;
    boolean passCambiada = false;
    
    /**
     * Constructor de la clase Registro
     * En caso de que se le pase un usuario (cuando se editan) el constructor rellenará los campos con el usuario introducido
     * @param user el usuario del cual se cargarán los datos
     */
    public Registro(Usuario user) {
        initComponents();
        
        if (user != null)
        {
            editar = true;
        }
        
        //EXTRACCION DE DATOS
        try {
            municipiosLista = PInfUI.gestorDatos.getMunicipios();
            provinciasLista = PInfUI.gestorDatos.getProvincias();
            rolesLista = PInfUI.gestorDatos.getRoles();
        } catch (SQLException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //RELLENAR ROLES
        rolesLista.forEach((rol) -> {
            jCBRol.addItem(rol);
        });
        
        //SETEAR RENDERER DE ROLES
        jCBRol.setRenderer(new RenderComboBox());
        
        //RELLENAR PROVINCIA (los municipios se rellenan en base a la provincia escogida)
        provinciasLista.forEach((provincia) -> {
            jCBProvincia.addItem(provincia);
        });
        
        //SETEAR RENDERERES
        jCBProvincia.setRenderer(new RenderComboBox());
        jCBMunicipio.setRenderer(new RenderComboBox());

        
        //Handler de salida de la ventana
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);        
        this.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
                int choose = JOptionPane.showConfirmDialog(null,
                        "Seguro que quiere cancelar el formulario ?",
                        "Cancelar formulario", JOptionPane.YES_NO_OPTION,
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
        jPanelSeleccionAsignacion.setVisible(false);
        
        //SI ESTA EN MODO EDITAR
        //RELLENAR CON LOS DATOS
        if (editar)        
        {
            aEditar = user;
            cargarDatosUsuario(user);
        }
            
    }
    
    /**
     * Metodo que se encarga de extraer y establecer los valores de los campos con los valores del usuario que ha sido recibido como parametro
     * Las asignaciones se rellenan haciendo llamadas adicionales
     * @param user Usuario recibido con el cual se rellenaran los campos
     */
    private void cargarDatosUsuario(Usuario user) {
    	

        //RELLENAR TEXTFIELDS
        jTFEmail.setText(user.getEmail());
        jTFDNI.setText(user.getDni());        
        jTFNombre.setText(user.getNombre());
        jTFApellidos.setText(user.getApellidos());
        jTFMovil.setText(user.getTlf_Movil());
        jTFFijo.setText(user.getTlf_Fijo());
        jTFDireccion.setText(user.getDireccion());
        
        //Estas dos se rellenan falsamente porque no se pueden traer los datos tal cual
        //ES UNA CONTRASEÑA RARA PARA QUE EL USUARIO NO LA PONGA SIN QUERER Y NO SE LE UPDATEE LA PASS
        jPFContrasena.setText("nosepuede"); 
        jPFRepiteContrasena.setText("nosepuede");


        //RELLENAR COMBOBOXES
        jCBProvincia.getModel().setSelectedItem(user.getMunicipio().getProvincia());
        jCBMunicipio.getModel().setSelectedItem(user.getMunicipio());
        jCBRol.getModel().setSelectedItem(user.getRol());    
        


        //RELLENAR ASIGNACIONES
//            (1,'Medico - Paciente / Paciente - Medico'),
//            (2,'Familiar - Paciente / Paciente - Familiar');
        if(user.getRol().getId_Rol() == 4)
        {
            for (Asignacion asig : user.getListaAsignacion())
            {
                if (asig.getId_Tipo() == 1) 
                {
                    this.medicoAsignado = asig.getUsuarioAsignado();
                    jTFMedicoAsignado.setText
                    (
                            this.medicoAsignado.getApellidos() +
                            " " +
                            this.medicoAsignado.getNombre()
                    );
                }                    
                else if (asig.getId_Tipo() == 2)
                {
                    this.familiarAsignado = asig.getUsuarioAsignado();
                    jTFFamiliarAsignado.setText
                    (
                            this.familiarAsignado.getApellidos() +
                            " " +
                            this.familiarAsignado.getNombre()
                    );
                }                    
            }                
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

        jLabelHeaderWarning = new javax.swing.JLabel();
        jLabelHeader1 = new javax.swing.JLabel();
        jLabelHeader2 = new javax.swing.JLabel();
        jLabelHeader3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelNombre = new javax.swing.JLabel();
        jTFNombre = new javax.swing.JTextField();
        jLabelApellidos = new javax.swing.JLabel();
        jTFApellidos = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jTFEmail = new javax.swing.JTextField();
        jLabelProvincia = new javax.swing.JLabel();
        jCBProvincia = new javax.swing.JComboBox<>();
        jLabelMunicipio = new javax.swing.JLabel();
        jCBMunicipio = new javax.swing.JComboBox<>();
        jLabelDireccion = new javax.swing.JLabel();
        jTFDireccion = new javax.swing.JTextField();
        jLabelMovil = new javax.swing.JLabel();
        jTFMovil = new javax.swing.JTextField();
        jLabelFijo = new javax.swing.JLabel();
        jTFFijo = new javax.swing.JTextField();
        jLabelDni = new javax.swing.JLabel();
        jTFDNI = new javax.swing.JTextField();
        jLabelPass1 = new javax.swing.JLabel();
        jPFContrasena = new javax.swing.JPasswordField();
        jLabelPass2 = new javax.swing.JLabel();
        jPFRepiteContrasena = new javax.swing.JPasswordField();
        jLabelRol = new javax.swing.JLabel();
        jCBRol = new javax.swing.JComboBox<>();
        jPanelSeleccionAsignacion = new javax.swing.JPanel();
        jLabelFamiliar = new javax.swing.JLabel();
        jTFFamiliarAsignado = new javax.swing.JTextField();
        jBFamiliarAsignado = new javax.swing.JButton();
        jLabelMedico = new javax.swing.JLabel();
        jTFMedicoAsignado = new javax.swing.JTextField();
        jBMedicoAsignado = new javax.swing.JButton();
        jBRegistrarse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro");
        setResizable(false);

        jLabelHeaderWarning.setText("Las casillas marcadas con (*) son imprescindibles para el registro");

        jLabelHeader1.setText("Datos de usuario");

        jLabelHeader2.setText("Datos de contacto");

        jLabelHeader3.setText("Información de la cuenta");

        jLabelNombre.setText("Nombre: *");

        jTFNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(173, 173, 255), null));

        jLabelApellidos.setText("Apellidos: *");

        jTFApellidos.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(173, 173, 255), null));

        jLabelEmail.setText("Email:");

        jTFEmail.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(173, 173, 255), null));

        jLabelProvincia.setText("Provincia: *");

        jCBProvincia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCBProvincia.setMaximumSize(new java.awt.Dimension(112, 112));
        jCBProvincia.setPreferredSize(new java.awt.Dimension(112, 22));
        jCBProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBProvinciaActionPerformed(evt);
            }
        });

        jLabelMunicipio.setText("Municipio *");

        jCBMunicipio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCBMunicipio.setMaximumSize(new java.awt.Dimension(112, 112));
        jCBMunicipio.setPreferredSize(new java.awt.Dimension(112, 22));

        jLabelDireccion.setText("Dirección: *");

        jTFDireccion.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(173, 173, 255), null));

        jLabelMovil.setText("Telefono Movil: *");

        jTFMovil.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(173, 173, 255), null));

        jLabelFijo.setText("Telefono Fijo:");

        jTFFijo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(173, 173, 255), null));

        jLabelDni.setText("DNI: *");

        jTFDNI.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(173, 173, 255), null));

        jLabelPass1.setText("Contraseña: *");

        jPFContrasena.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(173, 173, 255), null));
        jPFContrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPFContrasena.setText("");
                jPFRepiteContrasena.setText("");
                passCambiada = true;
            }
        });

        jLabelPass2.setText("Repita Contraseña: *");

        jPFRepiteContrasena.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(173, 173, 255), null));
        jPFRepiteContrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPFRepiteContrasena.setText("");
            }
        });

        jLabelRol.setText("Rol: *");

        jCBRol.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCBRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBRolActionPerformed(evt);
            }
        });

        jLabelFamiliar.setText("Familiar Asignado: *");

        jTFFamiliarAsignado.setEditable(false);

        jBFamiliarAsignado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/lupaBuscar.png"))); // NOI18N
        jBFamiliarAsignado.setBorder(null);
        jBFamiliarAsignado.setBorderPainted(false);
        jBFamiliarAsignado.setContentAreaFilled(false);
        jBFamiliarAsignado.setFocusPainted(false);
        jBFamiliarAsignado.setOpaque(false);
        jBFamiliarAsignado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFamiliarAsignadoActionPerformed(evt);
            }
        });

        jLabelMedico.setText("Medico Asignado: *");

        jTFMedicoAsignado.setEditable(false);

        jBMedicoAsignado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/lupaBuscar.png"))); // NOI18N
        jBMedicoAsignado.setBorder(null);
        jBMedicoAsignado.setBorderPainted(false);
        jBMedicoAsignado.setContentAreaFilled(false);
        jBMedicoAsignado.setFocusPainted(false);
        jBMedicoAsignado.setOpaque(false);
        jBMedicoAsignado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMedicoAsignadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSeleccionAsignacionLayout = new javax.swing.GroupLayout(jPanelSeleccionAsignacion);
        jPanelSeleccionAsignacion.setLayout(jPanelSeleccionAsignacionLayout);
        jPanelSeleccionAsignacionLayout.setHorizontalGroup(
            jPanelSeleccionAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelSeleccionAsignacionLayout.createSequentialGroup()
                .addGroup(jPanelSeleccionAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFamiliar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSeleccionAsignacionLayout.createSequentialGroup()
                        .addGroup(jPanelSeleccionAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSeleccionAsignacionLayout.createSequentialGroup()
                                .addComponent(jTFMedicoAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBMedicoAsignado))
                            .addGroup(jPanelSeleccionAsignacionLayout.createSequentialGroup()
                                .addComponent(jTFFamiliarAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBFamiliarAsignado)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSeleccionAsignacionLayout.setVerticalGroup(
            jPanelSeleccionAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSeleccionAsignacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMedico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSeleccionAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBMedicoAsignado)
                    .addComponent(jTFMedicoAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabelFamiliar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSeleccionAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTFFamiliarAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBFamiliarAsignado))
                .addGap(3, 3, 3))
        );

        jBRegistrarse.setText("GUARDAR");
        jBRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistrarseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelHeaderWarning)
                            .addComponent(jPFContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanelSeleccionAsignacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(52, 52, 52)
                                    .addComponent(jBRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabelPass2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                        .addComponent(jLabelHeader1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTFDNI, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPFRepiteContrasena, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTFEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCBRol, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelPass1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelDni, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelRol, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(42, 42, 42)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(62, 62, 62)
                                            .addComponent(jLabelNombre))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCBProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabelMunicipio)
                                                .addComponent(jCBMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(62, 62, 62)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabelApellidos)
                                                .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabelFijo)
                                        .addComponent(jLabelMovil)
                                        .addComponent(jLabelDireccion)
                                        .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTFMovil, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTFFijo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelHeader2)
                                            .addGap(70, 70, 70)
                                            .addComponent(jLabelHeader3))))))
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabelHeaderWarning)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelHeader2)
                            .addComponent(jLabelHeader3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelHeader1))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelProvincia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelEmail)
                        .addComponent(jLabelNombre)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCBProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelDni)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelMunicipio)
                        .addComponent(jLabelApellidos)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTFDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCBMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPass1)
                    .addComponent(jLabelMovil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPFContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPass2)
                    .addComponent(jLabelFijo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFFijo)
                    .addComponent(jPFRepiteContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRol)
                    .addComponent(jLabelDireccion))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelSeleccionAsignacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );

        this.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    /**
     * Acción del boton de registro
     * @param evt 
     */
    private void jBRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistrarseActionPerformed
        
        //Comprobar que las casillas obligatorias esten rellenadas
        // LA ULTIMA PARTE DEL IF CUMPRUEBA SI EL ROL ES 4 (OSEA PACIENTE) Y SI ES 4 DEBE RELLENAR LAS CASILLAS DE ASOCIACION
        if (jTFNombre.getText().isEmpty() || 
                jTFApellidos.getText().isEmpty() ||
                jTFDireccion.getText().isEmpty() ||
                jTFMovil.getText().isEmpty() || 
                jTFDNI.getText().isEmpty() ||
                jPFContrasena.getText().isEmpty() || 
                jPFRepiteContrasena.getText().isEmpty() || 
                ( 
                    ((Rol)jCBRol.getSelectedItem()).getId_Rol() == 4 && 
                    (
                        jTFFamiliarAsignado.getText().isEmpty() ||
                        jTFMedicoAsignado.getText().isEmpty()
                    ) 
                ) 
            )
            JOptionPane.showMessageDialog(null ,"Tienes que rellenar todas las casillas marcadas con (*)", "Error", ERROR_MESSAGE);
        
        else
        {
            //Comprobar que la contraseña sea igual en ambos campos
            if (!Arrays.equals(jPFContrasena.getPassword(), jPFRepiteContrasena.getPassword())) 
                JOptionPane.showMessageDialog(null ,"Has puesto contraseñas diferentes", "Error", ERROR_MESSAGE);
            else
            {
                Usuario user = null;
                String telefonoFijo = null;
                String email = null;
                
                if (!jTFFijo.getText().isEmpty())
                    telefonoFijo = jTFFijo.getText();
                if (!jTFEmail.getText().isEmpty())
                    email = jTFEmail.getText();
                
                //Crear usuario y llamar al registro
                user = new Usuario
                (
                        jTFDNI.getText(),
                        jTFNombre.getText(),
                        jTFApellidos.getText(),
                        email,
                        jTFMovil.getText(),
                        (Municipio)jCBMunicipio.getSelectedItem(),
                        (Provincia)jCBProvincia.getSelectedItem(),
                        jTFDireccion.getText(),
                        (Rol)jCBRol.getSelectedItem(),
                        telefonoFijo
                );                
              
                try {                    
                    //Si el usuario es paciente hay que crear asignaciones
                    //El paciente siempre debera asignar un familiar
                    //1 = medico - paciente
                    //2 = familiar - paciente
                    if (((Rol)jCBRol.getSelectedItem()).getId_Rol() == 4)
                    {
                        Asignacion pacienteMedico = new Asignacion(user, this.medicoAsignado, 1);
                        Asignacion pacienteFamiliar = new Asignacion(user, this.familiarAsignado, 2);
                        List<Asignacion> listaAsignacion = new ArrayList<>();
                        listaAsignacion.add(pacienteMedico);
                        listaAsignacion.add(pacienteFamiliar);
                        PInfUI.gestorDatos.registroAsignacion(editar, listaAsignacion);
                    }
                    //Llamar al registro pasandole el usuario y la contraseña
                    if(PInfUI.gestorDatos.registroUsuario(editar, passCambiada, user, jPFContrasena.getText(), aEditar))
                    {
                        JOptionPane.showMessageDialog(null, "Guardado con exito", "Information", INFORMATION_MESSAGE);
                        PantallaAdministrador.updatePanel();
                    }
                        
                    else
                        JOptionPane.showMessageDialog(null, "El DNI introducido ya existe", "Error", ERROR_MESSAGE);
                } 
                catch (SQLException | NoSuchAlgorithmException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la creación de la cuenta", "Error", ERROR_MESSAGE);
                }
                
                //CERRAR VENTANA
                this.dispose();
            }
        }
    }//GEN-LAST:event_jBRegistrarseActionPerformed

    /**
     * Metodo para rellenar combobox municipio basandonos en provincia escogida
     * @param evt 
     */
    private void jCBProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBProvinciaActionPerformed
        jCBMunicipio.removeAllItems();
        Provincia aux = (Provincia)jCBProvincia.getSelectedItem();

        for (Municipio municipio : municipiosLista) {
            if (municipio.getId_Provincia() == aux.getId_Provincia())
            {
                jCBMunicipio.addItem(municipio);
            }
        }       
    }//GEN-LAST:event_jCBProvinciaActionPerformed

    private void jBMedicoAsignadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMedicoAsignadoActionPerformed
        JPanelFiltro jPF = new JPanelFiltro(2, null, false, false);
        this.medicoAsignado= getUser(jPF);
        //System.out.println(this.medicoAsignado.getDni());
        if (this.medicoAsignado != null) 
            jTFMedicoAsignado.setText
            (
                    this.medicoAsignado.getApellidos() +
                    " " +
                    this.medicoAsignado.getNombre()
            );
    }//GEN-LAST:event_jBMedicoAsignadoActionPerformed

    private void jBFamiliarAsignadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFamiliarAsignadoActionPerformed
        JPanelFiltro jPF = new JPanelFiltro(3, null, false, false);
        this.familiarAsignado = getUser(jPF);
        //System.out.println(this.familiarAsociado.getDni());
        if (this.familiarAsignado != null)
            jTFFamiliarAsignado.setText
            (
                    this.familiarAsignado.getApellidos() +
                    " " +
                    this.familiarAsignado.getNombre()
            );
    }//GEN-LAST:event_jBFamiliarAsignadoActionPerformed

    private void jCBRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBRolActionPerformed
        if (((Rol)jCBRol.getSelectedItem()).getId_Rol() == 4)
            jPanelSeleccionAsignacion.setVisible(true);
        else
            jPanelSeleccionAsignacion.setVisible(false);
    }//GEN-LAST:event_jCBRolActionPerformed

    
    private Usuario getUser(JPanelFiltro jPF){
        int result = JOptionPane.showConfirmDialog(null, jPF, "Busqueda de Usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION)
            return jPF.getUserSelected();
        return null;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBFamiliarAsignado;
    private javax.swing.JButton jBMedicoAsignado;
    private javax.swing.JButton jBRegistrarse;
    private javax.swing.JComboBox<Municipio> jCBMunicipio;
    private javax.swing.JComboBox<Provincia> jCBProvincia;
    private javax.swing.JComboBox<Rol> jCBRol;
    private javax.swing.JLabel jLabelApellidos;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelDni;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelFamiliar;
    private javax.swing.JLabel jLabelFijo;
    private javax.swing.JLabel jLabelHeader1;
    private javax.swing.JLabel jLabelHeader2;
    private javax.swing.JLabel jLabelHeader3;
    private javax.swing.JLabel jLabelHeaderWarning;
    private javax.swing.JLabel jLabelMedico;
    private javax.swing.JLabel jLabelMovil;
    private javax.swing.JLabel jLabelMunicipio;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPass1;
    private javax.swing.JLabel jLabelPass2;
    private javax.swing.JLabel jLabelProvincia;
    private javax.swing.JLabel jLabelRol;
    private javax.swing.JPasswordField jPFContrasena;
    private javax.swing.JPasswordField jPFRepiteContrasena;
    private javax.swing.JPanel jPanelSeleccionAsignacion;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTFApellidos;
    private javax.swing.JTextField jTFDNI;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFEmail;
    private javax.swing.JTextField jTFFamiliarAsignado;
    private javax.swing.JTextField jTFFijo;
    private javax.swing.JTextField jTFMedicoAsignado;
    private javax.swing.JTextField jTFMovil;
    private javax.swing.JTextField jTFNombre;
    // End of variables declaration//GEN-END:variables
}
