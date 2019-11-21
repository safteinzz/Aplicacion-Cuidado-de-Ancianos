/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.controller;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import pinfui.interfaz.PInfUI;
import pinfui.interfaz.Registro;
/**
 *
 * @author ITCOM
 */
public class GestorDatos {
    //DATOS BD
    private static Connection conn;
    private static String myDriver = "com.mysql.cj.jdbc.Driver";
    private static String myUrl = "jdbc:mysql://localhost/DBPInf";
    private static String userBD = "root";
    private static String passBD = "123456789";

    private boolean modeDB = true; 
    //True = Se usa base de datos
    //False = Se usa JSON
    
    public static List<List <String>> getObjects(String rol){
        String query = "select * from USUARIO where"; //ROL
        Statement statement;
        ResultSet rs;
        List<List<String>> listaDeObjetos = new ArrayList<List<String>>();
        List<String> objeto = new ArrayList<String> ();
        
        try {
            conn = DriverManager.getConnection(myUrl, userBD, passBD);
            statement = conn.createStatement();
            rs = statement.executeQuery(query); 
            while (rs.next())
            {
                objeto.add(rs.getString("NOMBRE").toString());
                listaDeObjetos.add(objeto);
            }
            conn.close();        
        } catch (SQLException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return listaDeObjetos;
    }
    
    public boolean checkLogin(String user, String pass)
    {  
        boolean logCheck = false;
        if (modeDB) // MODO BDATOS
        {     
            String query = "select * from usuario where usuario.NOMBRE_USUARIO = '" + user + "';";
            Statement statement;    
            try {
                conn = DriverManager.getConnection(myUrl, userBD, passBD);
            
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next())
                {
                    byte[] sal = rs.getBytes("SAL");
                    String contrasenahashed = "";
                    contrasenahashed = getHash(pass, sal);
                    if (contrasenahashed.equals(rs.getString("CONTRASENA_USUARIO")))
                    {
                        logCheck = true;                        
                    }
                    //DEBUG
                    //JOptionPane.showMessageDialog(null ,"\nHash1: " + contrasenahashed + "\nHash2: " + rs.getString("CONTRASENA_USUARIO"), "Error", ERROR_MESSAGE);
                }
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else //MODO JSON
        {
            //...
        }
        return logCheck;
    }
    
    public void registro(String user, String pass, String rol, String nombre, String apellidos, String email, String telMovil, String telFijo, String ciudad, String pais, String direccion){
        byte[] salt = null;
        try {
            salt = getSalt();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GestorDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        String contrasenahashed = getHash(pass, salt);
        //check el hash (debug)
        //JOptionPane.showMessageDialog(null ,contrasenahashed, "Error", ERROR_MESSAGE);        
        String fijoFix = "N/A";
        if (!telFijo.isEmpty())
            fijoFix = telFijo;
        
        if (modeDB) // MODO BDATOS
        {       
            String query = "insert into USUARIO(NOMBRE_USUARIO,"
                    + " CONTRASENA_USUARIO,"
                    + " ID_ROL, "
                    + " NOMBRE,"
                    + " APELLIDOS,"
                    + " EMAIL,"
                    + " TLF_MOVIL,"
                    + " TLF_FIJO,"
                    + " LOCALIDAD,"
                    + " PAIS,"
                    + " DIRECCION,"
                    + " SAL)" + " values ('" + user + "','"
                    + contrasenahashed + "','"
                    + rol + "','"
                    + nombre + "','"
                    + apellidos + "','"
                    + email + "','"
                    + telMovil + "','"
                    + fijoFix + "','"
                    + ciudad + "','"
                    + pais + "','"
                    + direccion + "',?);";
            try {    
                // Preparar el statement
                conn = DriverManager.getConnection(myUrl, userBD, passBD);
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setBytes(1, salt);
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();                
            } catch (SQLException ex) {
                Logger.getLogger(GestorDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null ,"Cuenta creada con exito", "Error", INFORMATION_MESSAGE);
            PInfUI.ventanaRegistro.hide();
            PInfUI.ventanaLogIn.setVisible(true);
        }
        else //MODO JSON
        {
            
        }
    }
    
    public static List<String> getPaises()
    {
        String query = "select * from paises";
        Statement statement;
        ResultSet rs;
        List<String> paisesLista = new ArrayList<String> ();
        try {
            conn = DriverManager.getConnection(myUrl, userBD, passBD);
            statement = conn.createStatement();
            rs = statement.executeQuery(query); 
            while (rs.next())
            {
                paisesLista.add(rs.getString("NOMBRE").toString());
            }
            conn.close();        
        } catch (SQLException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return paisesLista;
    }
    
    private String getHash(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    private byte[] getSalt() throws NoSuchAlgorithmException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
}
