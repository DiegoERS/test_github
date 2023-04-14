/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Utility.GestionXML;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

/**
 *
 * @author Diego
 */
public class Partida extends Thread {

    private String codigoUnico;
    private int turnoJugadores;
    private boolean rondaTerminada;
    private int rondas;
    private boolean guerraActiva;
    private ArrayList<Cliente> clientes;

    public Partida(ArrayList<Cliente> clientes) {
        this.codigoUnico = UUID.randomUUID().toString().toUpperCase().substring(0, 8);
        this.clientes = clientes;
        this.turnoJugadores = 0;
        this.rondaTerminada = false;
        this.rondas = 0;
        this.guerraActiva = false;
    }//default

    
    //el hilo se usa para controlar todo el funcionamiento de la partida entre clientes.
    @Override
    public void run() {
        while (true) {
            todosListos();
            datosInteraccion();
        }

    }

    //cuando todos los clientes dan listo, este metodo los introduce a la partida en juego y los ordena con su respectivo turno.
    public void todosListos() {
        boolean listos = true;
        for (int i = 0; i < this.clientes.size(); i++) {
            if (!this.clientes.get(i).getJugadorCliente().isJugadorListo()) {
                listos = false;
            }

        }

        if (listos) {
            boolean repetidos = true;

            int[] numeros = new int[4];

            while (repetidos) {

                repetidos = false;

                for (int i = 0; i < numeros.length; i++) {
                    numeros[i] = (int) (Math.random() * 6) + 1;

                }

                for (int i = 0; i < numeros.length; i++) {
                    for (int j = i + 1; j < numeros.length; j++) {
                        if (numeros[i] == numeros[j]) {
                            repetidos = true;
                        }

                    }
                }

            }

            GestionXML gestionXML = new GestionXML();

            for (int i = 0; i < this.clientes.size(); i++) {
                this.clientes.get(i).getJugadorCliente().setJugadorListo(false);
                this.clientes.get(i).enviarDatos(gestionXML.xmlToString(gestionXML.agregarAccionSimple("dado", "" + numeros[i])));
                System.out.println(this.clientes.get(i).getJugadorCliente().getNombreUsuario() + " " + numeros[i]);
                this.clientes.get(i).getJugadorCliente().setTurno(numeros[i]);

            }

            Collections.sort(this.clientes, new Comparator<Cliente>() {
                @Override
                public int compare(Cliente o1, Cliente o2) {
                    int dato1 = o1.getJugadorCliente().getTurno();
                    int dato2 = o2.getJugadorCliente().getTurno();
                    return new Integer(dato2).compareTo(new Integer(dato1));
                }
            });

            for (int i = 0; i < clientes.size(); i++) {
                System.out.println(this.clientes.get(i).getJugadorCliente().getNombreUsuario() + "  " + this.clientes.get(i).getJugadorCliente().getTurno());

            }

        }
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCOdigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public int getTurnoJugadores() {
        return turnoJugadores;
    }

    public void setTurnoJugadores(int turnoJugadores) {
        this.turnoJugadores = turnoJugadores;
    }

    public boolean isRondaTerminada() {
        return rondaTerminada;
    }

    public void setRondaTerminada(boolean rondaTerminada) {
        this.rondaTerminada = rondaTerminada;
    }

    public int getRondas() {
        return rondas;
    }

    public void setRondas(int rondas) {
        this.rondas = rondas;
    }

    public boolean isGuerraActiva() {
        return guerraActiva;
    }

    public void setGuerraActiva(boolean guerraActiva) {
        this.guerraActiva = guerraActiva;
    }

    @Override
    public String toString() {
        return "Partida{" + "turnoJugadores=" + turnoJugadores + ", rondaTerminada=" + rondaTerminada + ", rondas=" + rondas + ", guerraActiva=" + guerraActiva + '}';
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    //este metodo es el que envia mensajes al chat general de las partidas.
    public void notificarAtodos(String mensaje) {

        for (int i = 0; i < this.clientes.size(); i++) {

            this.clientes.get(i).enviarDatos(mensaje);

        }
    }
   // este metodo es el encargado de enviar un mensaje privado a la persona designada.
    public void notificadorEspecifico(String destinatario, String mensaje) {
        GestionXML gestionXML = new GestionXML();
        for (int i = 0; i < this.clientes.size(); i++) {
            if (this.clientes.get(i).getJugadorCliente().getNombreUsuario().equals(destinatario)) {
                this.clientes.get(i).enviarDatos(gestionXML.xmlToString(gestionXML.agregarAccionSimple("chatPrivado", mensaje)));
            }

        }
    }

    //este metodo pasa actualizando la informacion cada vez que alguien se une a la partida despues de ser invitado en la sala de espera.
    public void datosSalaEspera() {
        GestionXML gestionXML = new GestionXML();
        String mensaje = gestionXML.xmlToString(gestionXML.agregarJugadores("salaEspera", gestionXML.clientesAJugador(this.clientes)));
        for (int i = 0; i < this.clientes.size(); i++) {
            this.clientes.get(i).enviarDatos(mensaje);

        }
    }

    //este meotodo envia toda la informacion de los jugadores con sus respectivos turnos a todos los clientes.
    public void datosArraylistOrdenado() {
        GestionXML gestionXML = new GestionXML();
        String mensaje = gestionXML.xmlToString(gestionXML.agregarJugadores("ordenado", gestionXML.clientesAJugador(this.clientes)));
        for (int i = 0; i < this.clientes.size(); i++) {
            this.clientes.get(i).enviarDatos(mensaje);

        }
    }

    
    //Este metodo se encarga de enviar las cartas de todos los jugadores a los clientes para que empiece la batalla.
    public void datosInteraccion() {
        GestionXML gestionXML = new GestionXML();
        boolean listos = true;
        for (int i = 0; i < this.clientes.size(); i++) {
            if (!this.clientes.get(i).getJugadorCliente().isCartasEscogidas()) {
                listos = false;
            }

        }
        if (listos) {
            for (int i = 0; i < this.clientes.size(); i++) {
                this.clientes.get(i).getJugadorCliente().setCartasEscogidas(false);
                this.clientes.get(i).enviarDatos(gestionXML.xmlToString(gestionXML.crearMensajeProtocolo("cartass")));

            }
            //metodo para enviar cartas

        }
    }

}//fin clase
