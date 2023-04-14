/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Business.JugadorBusiness;
import Utility.GestionXML;
import Utility.MyUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author Diego
 */
public class Cliente extends Thread {

    private ArrayList<Jugador> jugadores;
    private Socket socket;
    private PrintStream enviar;
    private BufferedReader leer;
    private Partida partida;
    private boolean activo;
    private Jugador jugadorCliente;
   
    
    public Cliente(Socket socket) throws IOException {
        this.partida = null;
        this.socket = socket;
        this.enviar = new PrintStream(this.socket.getOutputStream());
        this.leer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.activo = true;

        this.jugadorCliente = null;

    }
    //este hilo se encarga de escuchar lo que pide el cliente y resuelve sus peticiones mediante acciones estabblecidas.

    public void run() {

        try {
            enviarDatos("hola soy el servidor");
            GestionXML gestionXML = new GestionXML();
            while (this.activo) {
                try {
                    
                    String peticion = leerDatos();
                    Element eProtocolo;
                    try {
                        eProtocolo = gestionXML.stringToXML(peticion);
                        String accion = eProtocolo.getAttributeValue("accion");
                        
                             //PROTOCOLOSS
                        if (accion.equals("autenticar")) {
                            JugadorBusiness jugadorBusiness = new JugadorBusiness();
                            Jugador jugador = gestionXML.xmlAJugador(eProtocolo);
                            System.out.println(jugador.toString());
                            String mensaje = "";
                            Jugador verificado = jugadorBusiness.obtenerJugador(jugador.getNombreUsuario(), jugador.getContrasenia());
                            this.jugadorCliente = jugador;
                            if (verificado != null) {
                                mensaje = gestionXML.xmlToString(gestionXML.agregarJugador("validado", verificado));
                                
                                System.out.println(this.jugadorCliente);
                                
                            } else {
                                mensaje = gestionXML.xmlToString(gestionXML.agregarAccionSimple("validado", "null"));
                            }
                            
//                    System.out.println(eDato.getValue());
System.out.println(mensaje);
enviarDatos(mensaje);

                        }
                   
                        if (accion.equals("ranking")) {
                            
                            JugadorBusiness jugadorBusiness = new JugadorBusiness();
                            this.jugadores = jugadorBusiness.obtenerJugadores();
                           
                            String mensaje = "";
                            mensaje = gestionXML.xmlToString(gestionXML.agregarJugadores("ranking", this.jugadores));
                            System.out.println(mensaje);
                            enviarDatos(mensaje);
                            
                        }
                        if (accion.equals("guardar")) {
                            
                            JugadorBusiness jugadorBusiness = new JugadorBusiness();
                            System.out.println(gestionXML.xmlAJugador(eProtocolo).toString());
                            boolean registrado = jugadorBusiness.registrarJugador(gestionXML.xmlAJugador(eProtocolo));
                            String mensaje = "";
                            if (registrado) {
                                mensaje = gestionXML.xmlToString(gestionXML.agregarAccionSimple("registrado", "si"));
                            } else {
                                mensaje = gestionXML.xmlToString(gestionXML.agregarAccionSimple("registrado", "no"));
                            }
                            this.enviarDatos(mensaje);
                            
                        }
                        
                        if (accion.equals("chat")) {
                            
                            String mensaje = gestionXML.xmlToString(eProtocolo);
                            this.partida.notificarAtodos(mensaje);
                            
                        }
                         if (accion.equals("chatPrivado")) {
                            String personaEnviar=eProtocolo.getChild("dato").getAttributeValue("nombre");
                            String mensaje = eProtocolo.getChild("dato").getValue();
                             System.out.println(personaEnviar + mensaje);
                            this.partida.notificadorEspecifico( personaEnviar, mensaje);
                            
                        }
                        if (accion.equals("agregarAmigo")) {
                            
                            String nombre = eProtocolo.getChild("dato").getValue();
                            System.out.println(nombre + this.jugadorCliente.getNombreUsuario());
                            ServidorSingleton ss = ServidorSingleton.getInstance();
                            boolean encontrado = ss.enviarSolicitud(nombre, this.jugadorCliente.getNombreUsuario());
                            String mensaje = "";
                            if (encontrado) {
                                mensaje = gestionXML.xmlToString(gestionXML.agregarAccionSimple("confirmacionEnvio", "si"));
                            } else {
                                mensaje = gestionXML.xmlToString(gestionXML.agregarAccionSimple("confirmacionEnvio", "no"));
                            }
                            
                            this.enviarDatos(mensaje);
                            
                        }
                        
                        if (accion.equals("guardarAmigo")) {
                            
                            JugadorBusiness jugadorBusiness = new JugadorBusiness();
                            jugadorBusiness.agregarAmigos(this.jugadorCliente.getNombreUsuario(), eProtocolo.getChild("dato").getValue());
                            jugadorBusiness.agregarAmigos(eProtocolo.getChild("dato").getValue(), this.jugadorCliente.getNombreUsuario());
                            
                        }
                        
                        if (accion.equals("listaAmigos")) {
                            
                            JugadorBusiness jugadorBusiness = new JugadorBusiness();
                            ArrayList<String> amigos = jugadorBusiness.obtenerAmigos(this.jugadorCliente.getNombreUsuario());
                            String mensaje = "";
                            
                            if (amigos.size() > 0) {
                                mensaje = gestionXML.xmlToString(gestionXML.agregarAmigos("listaAmigos", amigos));
                                System.out.println(mensaje);
                                enviarDatos(mensaje);
                            }
                            
                        }
                        if (accion.equals("crearPartida")) {
                            ServidorSingleton ss = ServidorSingleton.getInstance();
                            ss.crearPartida(this.jugadorCliente.getNombreUsuario());
                            String mensaje = gestionXML.xmlToString(gestionXML.agregarJugadores("salaEspera",gestionXML.clientesAJugador(this.partida.getClientes()) ));
                            enviarDatos(mensaje);
                            this.partida.start();
                        }
                        if (accion.equals("invitarPartida")) {
                            String nombre = eProtocolo.getChild("dato").getValue();
                            System.out.println(nombre + this.jugadorCliente.getNombreUsuario());
                            ServidorSingleton ss = ServidorSingleton.getInstance();
                            boolean encontrado = ss.enviarInvitacion(nombre, this.jugadorCliente.getNombreUsuario());
                       

                        }

                        if (accion.equals("agregarPartida")) {
                            
                            
                            String anfitrion=eProtocolo.getChild("dato").getValue();
                            ServidorSingleton  ss=ServidorSingleton.getInstance();
                            ss.invitarPartida(anfitrion, this.jugadorCliente.getNombreUsuario());
                            this.partida.datosSalaEspera();
                            
                        }
                        
                        if (accion.equals("esperaListo")) {
                            
                            this.jugadorCliente.setJugadorListo(true);
                        }
                        
                        if (accion.equals("cerrar")) {
                            this.activo=false;
                            System.out.println("cliente cerrado");
                            this.enviarDatos(gestionXML.xmlToString(gestionXML.crearMensajeProtocolo("cerrar")));
                        }
                        
                        if (accion.equals("jugadorListo")) {
                            this.jugadorCliente.setCartasEscogidas(true);
                        }
                        
                    } catch (JDOMException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }//while
            
            this.socket.close();
            this.enviar.close();
            this.leer.close();
        } //run
        catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
   //este metodo se encarga de enviar datos al cliente
    public void enviarDatos(String datos) {
        this.enviar.println(datos);
    }
    //se encarga de leer las peticiones del cliente.
    public String leerDatos() throws IOException {
        return this.leer.readLine();
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Jugador getJugadorCliente() {
        return jugadorCliente;
    }

    public void setJugadorCliente(Jugador jugadorCliente) {
        this.jugadorCliente = jugadorCliente;
    }

}//fin clase
