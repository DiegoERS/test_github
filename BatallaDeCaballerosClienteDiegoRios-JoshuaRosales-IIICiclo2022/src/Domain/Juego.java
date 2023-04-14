/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Utility.GestionXML;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
//Se crea la clase juego en donde se realizara toda la funcionalidad del juego
public class Juego extends Thread {

    private ClienteSingleton cliente;
    private ArrayList<Jugador> jugadores;
    private int turnoJugadores;
    private boolean turnoTerminado;
    private int rondas;

    private boolean interaccion;
    private int cartaPresentada;
    private boolean guerraActiva;
    private boolean envioCartas;

    public static boolean botones;

    //Constructor el cual recibe el cliente y los jugadores
    public Juego(ArrayList<Jugador> jugadores) {
        this.cliente = ClienteSingleton.getInstance();
        this.jugadores = jugadores;
        this.turnoJugadores = 0;
        this.turnoTerminado = false;
        this.interaccion = false;
        this.cartaPresentada = 0;

        this.envioCartas = false;
        this.rondas = 0;
        this.guerraActiva = false;
        botones = true;

    }//Constructor

    //Este hilo se encarga de todo el funcionamiento del juego dentro del cliente a base de la informacion que envia el servidor.
    @Override
    public void run() {
        GestionXML gestionXML = new GestionXML();
        while (true) {
            try {
                if (this.turnoTerminado) {
                    botones = false;
                    if (this.envioCartas) {
                        //this.cliente.enviarDatos(/*enviar cartas y a quien va dirigido el ataque*/);
                        this.envioCartas = false;
                    }
                    
                    if (this.cliente.isReciboCartas()) {
                        
                        tirarBasura();
                        this.turnoTerminado = false;
                        this.cliente.setReciboCartas(false);
                        this.jugadores.get(this.turnoJugadores).rellenarCartasActuales();
                        
                    }
                } else {
                    botones = true;
                    
                }
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    public int getCartaPresentada() {
        return cartaPresentada;
    }//getCartaPresentada

    public void setCartaPresentada(int cartaPresentada) {
        this.cartaPresentada = cartaPresentada;
    }//setCartaPresentada

    public boolean isInteraccion() {
        return interaccion;
    }//isInteraccion

    public void setInteraccion(boolean interaccion) {
        this.interaccion = interaccion;
    }//setInteraccion

    public ClienteSingleton getCliente() {
        return cliente;
    }//getCliente

    public void setCliente(ClienteSingleton cliente) {
        this.cliente = cliente;
    }//setCliente

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }//getJugadores

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }//setJugadores

    public int getTurnoJugadores() {
        return turnoJugadores;
    }//getTurnoJugadores

    public void setTurnoJugadores(int turnoJugadores) {
        this.turnoJugadores = turnoJugadores;
    }//setTurnoJugadores

    public boolean isTurnoTerminado() {
        return turnoTerminado;
    }//isTurnoTerminado

    public void setTurnoTerminado(boolean turnoTerminado) {
        this.turnoTerminado = turnoTerminado;
    }//setTurnoTerminado

    public boolean isEnvioCartas() {
        return envioCartas;
    }

    public void setEnvioCartas(boolean envioCartas) {
        this.envioCartas = envioCartas;
    }

    //Metodo dibujar
    public void dibujar(Graphics g) {

        if (this.turnoTerminado) {
            this.jugadores.get(this.turnoJugadores).setAccion(true);

            //metodo interaccion;
        }//if
        if (this.interaccion) {
            guerra();
            if (this.jugadores.get(0).getCartasSeleccionadas().size() > 0 && this.jugadores.get(1).getCartasSeleccionadas().size() > 0) {

            }
        } else {

            this.jugadores.get(this.turnoJugadores).dibujar(g);
        }//else

    }//dibujar

    //Metodo que se encarga de la interaccion de las cartas cuando ya ambos jugadores hayan
    //formado su estrategia
    public void guerra() {
        if (this.interaccion) {

            if (this.guerraActiva) {
                for (int i = 0; i < 3; i++) {
                    this.setCartaPresentada(i);

                    this.jugadores.get(0).setVida(
                            this.jugadores.get(0).getVida()
                            + this.jugadores.get(0).getCartasSeleccionadas().get(i).accionCarta(this.jugadores.get(1), this.jugadores.get(0), i));

                    //jugador 1
                    this.jugadores.get(1).setVida(
                            this.jugadores.get(1).getVida()
                            + this.jugadores.get(1).getCartasSeleccionadas().get(i).accionCarta(this.jugadores.get(0), this.jugadores.get(1), i));

                    //System.out.println(this.jugadores.get(0).getCartasSeleccionadas().get(i).numCarta + " " + this.jugadores.get(1).getCartasSeleccionadas().get(i).numCarta);
                }

                this.guerraActiva = false;
            }

        }//if

    }//guerra

    //Metodo para limpiar las cartas de la zona de estrategia de los jugadores
    public void tirarBasura() {

        this.jugadores.get(this.turnoJugadores).getCartasSeleccionadas().clear();

//           this.basura.clear();
    }//tirarBasura

    
    //este metodo se usa para verificar si alguien ha muerto o se acabaron las cartas del mazo.
    public Jugador finalJuego() {

        if (this.jugadores.get(0).getVida() <= 0) {
            this.jugadores.get(0).setVida(0);
            return this.jugadores.get(1);
        }

        if (this.jugadores.get(1).getVida() <= 0) {
            this.jugadores.get(1).setVida(0);
            return this.jugadores.get(0);
        }

        if (this.rondas == 7) {
            if (this.jugadores.get(0).getVida() < this.jugadores.get(1).getVida()) {
                return this.jugadores.get(1);
            } else {
                return this.jugadores.get(0);
            }
        }

        return null;
    }

   

}//fin clase
