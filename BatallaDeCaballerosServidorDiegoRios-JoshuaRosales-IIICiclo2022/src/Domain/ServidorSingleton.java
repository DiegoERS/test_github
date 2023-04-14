/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Utility.GestionXML;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ServidorSingleton extends Thread {

    private static ServidorSingleton servidor;
    private ArrayList<Cliente> clientes;
    private int numeroPuerto;

    private ServidorSingleton() {

        this.clientes = new ArrayList<>();
        this.numeroPuerto = 5025;
    }//constructor

    public static ServidorSingleton getInstance() {
        if (servidor == null) {
            servidor = new ServidorSingleton();
            servidor.start();
        }

        return servidor;
    }

    //el hilo se usa para crear los clientes del servidor y guardarlos con su respectivo socket cuando establecen conexion.
    @Override
    public void run() {
        try {
            ServerSocket serverSocket
                    = new ServerSocket(this.numeroPuerto);

            while (true) {

                Socket socket = serverSocket.accept();
                Cliente cliente = new Cliente(socket);
                cliente.start();
                this.clientes.add(cliente);

                if (this.clientes.size() > 0) {
                    for (int i = 0; i < clientes.size(); i++) {
                        if (this.clientes.get(i).getSocket().isClosed()) {
                            this.clientes.remove(this.clientes.get(i));
                        }

                    }
                }

            }
        } //run
        catch (IOException ex) {
            Logger.getLogger(ServidorSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//run

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

 
  //envia la solicitud de amistad al cliente que se desea.
    public boolean enviarSolicitud(String nombre, String remitente) {
        GestionXML gestionXML = new GestionXML();
        for (int i = 0; i < this.clientes.size(); i++) {
            if (this.clientes.get(i).getJugadorCliente() != null) {
                if (this.clientes.get(i).getJugadorCliente().getNombreUsuario().equals(nombre)) {

                    String mensaje = gestionXML.xmlToString(gestionXML.agregarAccionSimple("amistad", remitente));
                    System.out.println(mensaje);
                    this.clientes.get(i).enviarDatos(mensaje);
                    return true;
                }

            }

        }
        return false;
    }

    //crea la partida y agrega al cliente host a la misma partida.
    public void crearPartida(String nombreUsuario) {
        ArrayList<Cliente> clientesAñadir = new ArrayList<>();
        for (int i = 0; i < this.clientes.size(); i++) {
            if (this.clientes.get(i).getJugadorCliente().getNombreUsuario() != null) {
                if (this.clientes.get(i).getJugadorCliente().getNombreUsuario().equals(nombreUsuario)) {
                    clientesAñadir.add(this.clientes.get(i));
                    this.clientes.get(i).setPartida(new Partida(clientesAñadir));
                }
            }
        }

    }

    //agrega a la persona invitada a la partida designada
    public void invitarPartida(String anfitrion, String invitado) {
        int cliente = -1;
        for (int i = 0; i < this.clientes.size(); i++) {
            if (this.clientes.get(i).getJugadorCliente().getNombreUsuario() != null) {
                if (this.clientes.get(i).getJugadorCliente().getNombreUsuario().equals(invitado)) {
                    cliente = i;
                }
            }
        }

        if (cliente != -1) {
            for (int j = 0; j < this.clientes.size(); j++) {
                if (this.clientes.get(j).getJugadorCliente().getNombreUsuario() != null) {
                    if (this.clientes.get(j).getJugadorCliente().getNombreUsuario().equals(anfitrion)) {
                        this.clientes.get(j).getPartida().getClientes().add(this.clientes.get(cliente));
                        this.clientes.get(cliente).setPartida(this.clientes.get(j).getPartida());
                    }
                }
            }

        }

    }

    //encuentra al jugador invitado y le notifica que está siendo invitado a una partida.
    public boolean enviarInvitacion(String nombre, String remitente) {
        GestionXML gestionXML = new GestionXML();
        for (int i = 0; i < this.clientes.size(); i++) {
            if (this.clientes.get(i).getJugadorCliente().getNombreUsuario() != null) {
                if (this.clientes.get(i).getJugadorCliente().getNombreUsuario().equals(nombre)) {

                    String mensaje = gestionXML.xmlToString(gestionXML.agregarAccionSimple("invitacion", remitente));
                    System.out.println(mensaje);
                    this.clientes.get(i).enviarDatos(mensaje);
                    return true;
                }
            }

        }
        return false;
    }

}//fin clase
