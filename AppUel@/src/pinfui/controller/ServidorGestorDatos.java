package pinfui.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Semaphore;

import pinfui.dto.ConstantesAplicacion;
import pinfui.entidades.Mensaje;

/**
 * Servidor para el guardado de mensajes de forma simultanea entre diferentes
 * usuarios conectados a la vez, de esta forma se evita que se machaquen los
 * datos
 *
 * @author ITCOM
 *
 */
public class ServidorGestorDatos extends Thread {

    private static ServerSocket ss;
    private Socket socket;

    private Semaphore semaforoMensaje;

    public ServidorGestorDatos(Socket socket, Semaphore semaforoMensaje) {
        this.socket = socket;
        this.semaforoMensaje = semaforoMensaje;
    }

    /**
     * Metodo que ejecutara el hilo creado por la conexion
     */
    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(input);

            //Recupera el mensaje que es enviado desde la otra parte de la conexion
            Mensaje mensaje = (Mensaje) objectInputStream.readObject();

            //Coger permisos del semaforo
            semaforoMensaje.acquire();

            GestorDatos gestorDatos = new GestorDatos();

            //Recuperamos todos los mensajes actuales 
//            List<Mensaje> listaMensajes = gestorDatos.getMensajes();
//            listaMensajes.add(mensaje);
//
//            //Guardamos toda la lista de mensajes con el nuevo mensaje en el archivo JSON
//            gestorDatos.ObjecttoJson(listaMensajes, gestorDatos.MENSAJE_JSON);

            //Devolver el permiso al semaforo
            semaforoMensaje.release();
            System.out.println("Objeto guardado");
        } catch (IOException e) {
            System.out.println("La conexión ha sido cerrada de improvisto");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            //Cerrar la conexión
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Abriendo servidor");
            ss = new ServerSocket(ConstantesAplicacion.PORT_SERVER);
            System.out.println("Servidor abierto");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //Creacion del semaforo mensaje con un unico permiso, solo un hilo puede modificar el archivo JSON
        Semaphore semaforoMensaje = new Semaphore(1);

        while (true) {
            try {
                System.out.println("A la espera de una conexión");
                Socket conexao = ss.accept();
                System.out.println("Conexión abierta");
                new ServidorGestorDatos(conexao, semaforoMensaje).start();
                System.out.println("Hilo lanzado");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
