/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
/**
 *
 * @author SaFteiNZz
 */
public class Registro extends javax.swing.JFrame {

    //DATOS BD
    private static Connection conn;
    private static String myDriver = "com.mysql.cj.jdbc.Driver";
    private static String myUrl = "jdbc:mysql://localhost/DBPInf";
    private static String userBD = "safteinzz";
    private static String passBD = "21505519";
    
    public Registro() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFApellidos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTFDireccion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTFMovil = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTFFijo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTFUsuario = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPFContrasena = new javax.swing.JPasswordField();
        jPFRepiteContrasena = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jBRegistrarse = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jCBPaisResidencia = new javax.swing.JComboBox<>();
        jCBLocalidad = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Tus datos personales");

        jLabel2.setText("Tus datos de contacto");

        jLabel3.setText("Información de la cuenta");

        jLabel4.setText("Nombre: *");

        jLabel5.setText("Apellidos: *");

        jLabel6.setText("Email: *");

        jLabel7.setText("Pais de Residencia: *");

        jLabel9.setText("Pueblo/Ciudad: *");

        jLabel10.setText("Dirección: *");

        jLabel11.setText("Telefono Movil: *");

        jLabel12.setText("Telefono Fijo:");

        jLabel13.setText("Usuario: *");

        jLabel14.setText("Contraseña: *");

        jLabel15.setText("Repita su Contraseña: *");

        jLabel16.setText("Las casillas marcadas con (*) son imprescindibles para el registro");

        jBRegistrarse.setText("REGISTRARSE");
        jBRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistrarseActionPerformed(evt);
            }
        });

        jBCancelar.setText("CANCELAR");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        jCBPaisResidencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCBLocalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jTFNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(jTFApellidos))
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel9)
                                            .addComponent(jTFEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCBPaisResidencia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCBLocalidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(75, 75, 75)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel13)
                                            .addComponent(jTFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)
                                            .addComponent(jPFContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPFRepiteContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTFFijo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                        .addComponent(jTFMovil, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTFDireccion, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jBRegistrarse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7))))
                            .addComponent(jLabel16))
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel16)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(32, 32, 32)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCBPaisResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(31, 31, 31)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jPFContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jPFRepiteContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCBLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel15))))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBRegistrarse)
                    .addComponent(jBCancelar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistrarseActionPerformed
        //CHECKEAR SI SE HA RELLENADO TODO LO IMPRESCINDIBLE
        if (jTFNombre.getText().isEmpty() || jTFApellidos.getText().isEmpty() || jTFEmail.getText().isEmpty() || jCBPaisResidencia.getSelectedIndex() == -1 || jCBLocalidad.getSelectedIndex() == -1 || jTFDireccion.getText().isEmpty() || jTFMovil.getText().isEmpty() || jTFUsuario.getText().isEmpty() || jPFContrasena.getText().isEmpty() || jPFRepiteContrasena.getText().isEmpty())
            JOptionPane.showMessageDialog(null ,"Tienes que rellenar todas las casillas marcadas con (*)", "Error", ERROR_MESSAGE);
        else 
        {
            //CHECKEAR SI LA CONTRASEÑA ESTA BIEN
            if (!Arrays.equals(jPFContrasena.getPassword(), jPFRepiteContrasena.getPassword()))
                JOptionPane.showMessageDialog(null ,"Has puesto contraseñas diferentes", "Error", ERROR_MESSAGE);
            else
            {
                String algorithm = "SHA-256";
                String contrasenahashed = "";
                byte[] salt = createSalt();
                try {
                    contrasenahashed = generateHash(jPFContrasena.getText(), algorithm, salt);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
                }
                //check el hash (debug)
                //JOptionPane.showMessageDialog(null ,contrasenahashed, "Error", ERROR_MESSAGE);
                // Crear query                
                String fijo = "N/A";
                if (!jTFFijo.getText().isEmpty()) fijo = jTFFijo.getText();
                String query = "insert into USUARIO(NOMBRE_USUARIO,"
                        + " CONTRASENA_USUARIO,"
                        + " NOMBRE,"
                        + " APELLIDOS,"
                        + " EMAIL,"
                        + " TLF_MOVIL,"
                        + " TLF_FIJO,"
                        + " LOCALIDAD,"
                        + " PAIS,"
                        + " DIRECCION,"
                        + " SAL)" + " values ('" + jTFUsuario.getText() + "','"
                        + contrasenahashed + "','"
                        + jTFNombre.getText() + "','"
                        + jTFApellidos.getText() + "','"
                        + jTFEmail.getText() + "','"
                        + jTFMovil.getText() + "','"
                        + fijo + "','"
                        + jCBLocalidad.getSelectedItem().toString() + "','"
                        + jCBPaisResidencia.getSelectedItem().toString() + "','"
                        + jTFDireccion.getText() + "','"
                        + salt + "');";
                // Preparar el statement
                PreparedStatement statement;
                try {
                    conn = DriverManager.getConnection(myUrl, userBD, passBD);
                    statement = conn.prepareStatement(query);
                    statement.execute();
                    conn.close();
                    JOptionPane.showMessageDialog(null ,"Cuenta creada con exito", "Error", INFORMATION_MESSAGE);
                    PInfUI.ventanaRegistro.hide();
                    PInfUI.ventanaLogIn.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
    }//GEN-LAST:event_jBRegistrarseActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        this.hide();
        PInfUI.ventanaLogIn.setVisible(true);
    }//GEN-LAST:event_jBCancelarActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }
    
    private static String generateHash(String data, String algorithm, byte[] salt) throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        digest.update(salt);
        byte[] hash = digest.digest(data.getBytes());
        return bytesToStringHex(hash);
    } 
    
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    
    public static String bytesToStringHex(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length;j++)
        {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    public static byte[] createSalt()
    {
        byte[] bytes = new byte[5];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBRegistrarse;
    private javax.swing.JComboBox<String> jCBLocalidad;
    private javax.swing.JComboBox<String> jCBPaisResidencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPFContrasena;
    private javax.swing.JPasswordField jPFRepiteContrasena;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTFApellidos;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFEmail;
    private javax.swing.JTextField jTFFijo;
    private javax.swing.JTextField jTFMovil;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTextField jTFUsuario;
    // End of variables declaration//GEN-END:variables
}