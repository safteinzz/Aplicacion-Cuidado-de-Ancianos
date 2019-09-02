/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui;

/**
 *
 * @author SaFteiNZz
 */
public class PInfUI {

    public static LogIn ventanaLogIn = new LogIn();
    public static Registro ventanaRegistro = new Registro();
    public static InterfazPrincipal ventanaMainUI = new InterfazPrincipal();
    
    public static void main(String[] args) {
        ventanaLogIn.setVisible(true);
        ventanaLogIn.setResizable(false);
    }
    
}
