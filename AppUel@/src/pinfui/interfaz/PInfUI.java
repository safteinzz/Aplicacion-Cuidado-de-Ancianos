/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import pinfui.controller.GestorDatos;

/**
 *
 * @author ITCOM
 */
public class PInfUI {

    public static LogIn ventanaLogIn = new LogIn();
    public static Registro ventanaRegistro = new Registro();
    public static PantallaUsuario ventanaUser = new PantallaUsuario();
    
    
    public static GestorDatos gestorDatos = new GestorDatos();
    
    private static int sizeFuente = 0;
    
    public static void main(String[] args) {
        if (!PInfUI.gestorDatos.checkLogin("root","pass"))
            PInfUI.gestorDatos.registro("root", "admin", "1", "NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA");
        ventanaLogIn.setVisible(true);
    }
    
    
    
    
    
    public static void setSizeFuente(int sizeFuente) {
        PInfUI.sizeFuente = sizeFuente;
    }

    public static int getSizeFuente() {
        return sizeFuente;
    }
}
