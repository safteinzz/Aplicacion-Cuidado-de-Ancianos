/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.interfaz;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import pinfui.controller.GestorDatos;
import pinfui.dto.Idioma;
import pinfui.dto.TipoVentana;
import pinfui.entidades.Usuario;

/**
 * Main principal de la aplicacion
 * @author ITCOM
 */
public class PInfUI {

    public static ResourceBundle bundle;

    public static GestorDatos gestorDatos = new GestorDatos();

    private static int sizeFuente = 0;

    /**
     * Main principal para ejecutar la aplicacion
     * @param args
     * @throws java.sql.SQLException
     * @throws java.security.NoSuchAlgorithmException
     */
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
    	bundle = ResourceBundle.getBundle("resources.mensajes.Mensajes", new Locale("es", "ES"));

        //CHECKEAR SI HAY ADMIN EN LA BASE DE DATOS
        //SI ENCONTRAMOS UNA FORMA DE METER UN ARRAY DE BYTES COMO INSERT DIRECTAMENTE EN WORKBENCH ESTO NO ES NECESARIO
        //SOLO HACE FALTA RUNEARLO UNA VEZ, LUEGO BORRAMOS EL METODO PORQUE EL ADMIN PRINCIPAL YA ESTARIA EN BASE DE DATOS
        //DE MOMENTO SE QUEDA PORQUE CADA VEZ QUE RENUEVO LA BASE DE DATOS TENGO QUE CREAR EL USUARIO ADMIN PARA PRUEBAS
        if (PInfUI.gestorDatos.isModeDB() && PInfUI.gestorDatos.login("root","admin") == null)
        {
            Usuario user = new Usuario(1, -1, "root",null,null,null,null,null,null,null,null);
            PInfUI.gestorDatos.registroUsuario(false, true,user, "admin", null);
        }
        abrirVentana(null, null, TipoVentana.LOGIN);
        
        
        //entrar roteado
//        Usuario usuario = PInfUI.gestorDatos.login("14523658F", "123456789");
//        abrirVentana(usuario, null, TipoVentana.MEDICOABREPACIENTE);

        //Creacion de un usuario medico
//        try {
//			gestorDatos.registro(new Usuario("48569758X", "Medico", "Pruebas", "medico@gmail.com", "458697586", new Municipio(1, 1, 123, 1, "Madrid"), new Provincia(1, "Comunidad de Madrid"), "calle", new Rol(2, "Medico"), "589368539"), "123456789");
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

    /**
     * Metodo encargado de abrir una nueva ventana y cerrar la ventana anterior
     * @param usuario Usuario logeado
     * @param ventanaCerrar Frame de la ventana que se desea cerrar
     * @param tipoVentanaAbrir Ventana que se desea abrir
     */
    public static void abrirVentana(Usuario usuario, javax.swing.JFrame ventanaCerrar, TipoVentana tipoVentanaAbrir){
        if(ventanaCerrar != null) {
        	ventanaCerrar.dispose();
        }

        if(tipoVentanaAbrir.equals(TipoVentana.LOGIN))
        {
            LogIn ventanaLogIn = new LogIn();
            ventanaLogIn.setVisible(true);
        } 
        else if(tipoVentanaAbrir.equals(TipoVentana.FAMILIAR)){
            PantallaFamiliar ventanaFamiliar = new PantallaFamiliar(usuario);
            ventanaFamiliar.setVisible(true);
        } 
        else if(tipoVentanaAbrir.equals(TipoVentana.REGISTRO)){
            Registro ventanaRegistro = new Registro(usuario);
            ventanaRegistro.setVisible(true);
        }
        else if(tipoVentanaAbrir.equals(TipoVentana.ADMIN)){
            PantallaAdministrador ventanaAdmin = new PantallaAdministrador(usuario);
            ventanaAdmin.setVisible(true);
        }
        else if(tipoVentanaAbrir.equals(TipoVentana.MEDICO)){
            PantallaMedico ventanaMedico = new PantallaMedico(usuario);
            ventanaMedico.setVisible(true);
        }
        else if(tipoVentanaAbrir.equals(TipoVentana.MEDICOABREPACIENTE)){
            PantallaMedicoAbrePaciente ventanaMedicoAbrePaciente = new PantallaMedicoAbrePaciente(usuario);
            ventanaMedicoAbrePaciente.setVisible(true);
        }
        else if(tipoVentanaAbrir.equals(TipoVentana.PACIENTE)){
            PantallaPaciente ventanaPaciente = new PantallaPaciente(usuario);
            ventanaPaciente.setVisible(true);
        }
    }

    public static ResourceBundle getBundle() {
    	return bundle;
    }

    /**
     * Cambia el idioma de la aplicacion segun el idioma elegido
     * @param idioma Idioma al que cambiar la aplicacion
     */
    public static void cambiarIdioma(Idioma idioma) {
    	if(idioma.equals(Idioma.CASTELLANO)) {
    		bundle = ResourceBundle.getBundle("resources.mensajes.Mensajes", new Locale("es", "ES"));
    	} else if(idioma.equals(Idioma.INGLES)) {
    		bundle = ResourceBundle.getBundle("resources.mensajes.Mensajes", new Locale("en", "US"));
    	} else if(idioma.equals(Idioma.FRANCES)) {
    		bundle = ResourceBundle.getBundle("resources.mensajes.Mensajes", new Locale("fr", "FR"));
    	}
    }

    public static void setSizeFuente(int sizeFuente) {
        PInfUI.sizeFuente = sizeFuente;
    }

    public static int getSizeFuente() {
        return sizeFuente;
    }
}
