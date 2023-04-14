/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import GUI.JPanelDado;
import GUI.JPpanelRegistro;
import Utility.GestionXML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author Diego
 */
//Se crea la calse cliente 
public class ClienteSingleton extends Observable implements Runnable {

    private static ClienteSingleton clienteSingleton;
    private Socket socket;
    private InetAddress direccion;
    private PrintStream enviar;
    private BufferedReader leer;
    private ArrayList<Jugador> jugadores;
    private boolean prueba;
    private String registrado;
    private boolean verificado;
    private String chat;
    private boolean solicitudAmistad;
    private String solicitud;
    private ArrayList<String> amigos;
    private boolean amigosLista;
    private boolean solicitudPartida;
    private String invitacion;
    private ArrayList<Jugador> espectadores;
    private boolean cambioEspera;
    private int numDado;
    private JDesktopPane jDesktopPane;
    private Jugador jugador;
    private boolean reciboCartas;
    private boolean abierto;
    private boolean mensajePrivado;
    private String chatPrivado;
    private boolean combobox;

    private ClienteSingleton() throws IOException {
        this.direccion = InetAddress.getByName("192.168.100.44");
        this.socket = new Socket(this.direccion, 5025);
        this.enviar = new PrintStream(this.socket.getOutputStream());
        this.leer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.jugadores=new ArrayList<>();
        this.jugadores.add(new Jugador("", "", 0, 0));
        System.out.println(this.jugadores.size());
        this.prueba=false;
        this.registrado="no";
        this.verificado=false;
        this.chat="";
        this.chatPrivado="";
        this.solicitudAmistad=false;
        this.solicitud="";
        this.amigos=new ArrayList<>();
        this.amigosLista=false;
        this.invitacion="";
        this.solicitudPartida=false;
        this.espectadores=new ArrayList<>();
        this.cambioEspera=false;
        this.mensajePrivado=false;
        this.numDado=-1;
        this.jDesktopPane=null;
        this.jugador=null;
        this.abierto=true;
        this.combobox=true;
        this.reciboCartas=false;

    }//constructor
     //Este hilo escucha la informacion que envia el servidor y realiza acciones mediante el protocolo establecido.
    @Override
    public void run() {
        try {
            GestionXML gestionXML = new GestionXML();
            while (this.abierto) {
                
                try {
                    String Peticion = this.leerDatos();
                    System.out.println(Peticion);
                    try {
                        if (Peticion.startsWith("<Protocolo")) {
                            
                            
                            Element eProtocolo = gestionXML.stringToXML(Peticion);
                            String accion = eProtocolo.getAttributeValue("accion");
                            
                            System.out.println(eProtocolo);
                            if (accion.equals("validado")) {
                                this.setChanged();
                                System.out.println(eProtocolo);
                                this.notifyObservers(eProtocolo);
                                this.clearChanged();
                            }
                            if (accion.equals("ranking")) {
                                this.jugadores.clear();
                                this.jugadores=gestionXML.xmlAJugadores(eProtocolo);
                                this.prueba=true;
                                 
                                
                            }
                            
                            if (accion.equals("registrado")) {
                                this.registrado=eProtocolo.getChild("dato").getValue();
                                this.verificado=true;
                                
                            }
                            
                            if (accion.equals("chat")) {
                                this.chat=eProtocolo.getChild("dato").getValue();
                                this.prueba=true;
                                
                            }
                            if (accion.equals("chatPrivado")) {
                                this.chatPrivado=eProtocolo.getChild("dato").getValue();
                                this.mensajePrivado=true;
                                
                            }
                            
                            if (accion.equals("confirmacionEnvio")) {
                                this.registrado=eProtocolo.getChild("dato").getValue();
                                this.verificado=true;
                                
                            }
                            
                            if (accion.equals("amistad")) {
                                this.solicitud=eProtocolo.getChild("dato").getValue();
                                this.solicitudAmistad=true;
                                
                            }
                            if (accion.equals("listaAmigos")) {
                                this.amigos.clear();
                                this.amigos=gestionXML.xmlAAmigos(eProtocolo);
                                this.amigosLista=true;
                                
                                
                            }
                            if (accion.equals("invitacion")) {
                                this.invitacion=eProtocolo.getChild("dato").getValue();
                                this.solicitudPartida=true;
                                
                            }
                            
                            if (accion.equals("salaEspera")) {
                                this.espectadores.clear();
                                this.espectadores=gestionXML.xmlAJugadores(eProtocolo);
                                this.cambioEspera=true;
                                this.combobox=true;
                                
                                
                            }
                            
                            if (accion.equals("dado")) {
                                this.numDado=Integer.parseInt(eProtocolo.getChild("dato").getValue());
                                JPanelDado jPanelDado=new JPanelDado(this.jugador, this.numDado);
                                JInternalFrame jInternalFrame=new JInternalFrame("Dado");
                                jInternalFrame.add(jPanelDado);
                                jInternalFrame.pack();
                                jInternalFrame.setClosable(true);
                                this.jDesktopPane.add(jInternalFrame);
                                jInternalFrame.setVisible(true);
                                
                                
                            }
                            if (accion.equals("cerrar")) {
                                this.abierto=false;
                            }
                            
                            if (accion.equals("cartass")) {
                                System.out.println("entra");
                                this.reciboCartas=true;
                            }
                            
                            
                        }
                        
                    } catch (JDOMException ex) {
                        Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } catch (IOException ex) {
                    Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            this.socket.close();
            this.enviar.close();
            this.leer.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ClienteSingleton getInstance() {
        if (clienteSingleton == null) {
            try {
                clienteSingleton = new ClienteSingleton();
            } catch (IOException ex) {
                Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);

            }
            Thread thread = new Thread(clienteSingleton);
            thread.start();
        }
        return clienteSingleton;
    }

    public boolean isMensajePrivado() {
        return mensajePrivado;
    }

    public void setMensajePrivado(boolean mensajePrivado) {
        this.mensajePrivado = mensajePrivado;
    }

    public String getChatPrivado() {
        return chatPrivado;
    }

    public void setChatPrivado(String chatPrivado) {
        this.chatPrivado = chatPrivado;
    }

    public boolean isCombobox() {
        return combobox;
    }

    public void setCombobox(boolean combobox) {
        this.combobox = combobox;
    }

    
    
    public void enviarDatos(String datos) {
        this.enviar.println(datos);
    }

    public String leerDatos() throws IOException {
        return this.leer.readLine();
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public boolean isPrueba() {
        return prueba;
    }

    public void setPrueba(boolean prueba) {
        this.prueba = prueba;
    }

    public String getRegistrado() {
        return registrado;
    }

    public void setRegistrado(String registrado) {
        this.registrado = registrado;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public boolean isSolicitudAmistad() {
        return solicitudAmistad;
    }

    public void setSolicitudAmistad(boolean solicitudAmistad) {
        this.solicitudAmistad = solicitudAmistad;
    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

    public ArrayList<String> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<String> amigos) {
        this.amigos = amigos;
    }

    public boolean isAmigosLista() {
        return amigosLista;
    }

    public void setAmigosLista(boolean amigosLista) {
        this.amigosLista = amigosLista;
    }

    public boolean isSolicitudPartida() {
        return solicitudPartida;
    }

    public void setSolicitudPartida(boolean solicitudPartida) {
        this.solicitudPartida = solicitudPartida;
    }

    public String getInvitacion() {
        return invitacion;
    }

    public void setInvitacion(String invitacion) {
        this.invitacion = invitacion;
    }

    public ArrayList<Jugador> getEspectadores() {
        return espectadores;
    }

    public void setEspectadores(ArrayList<Jugador> espectadores) {
        this.espectadores = espectadores;
    }

    public boolean isCambioEspera() {
        return cambioEspera;
    }

    public void setCambioEspera(boolean cambioEspera) {
        this.cambioEspera = cambioEspera;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public InetAddress getDireccion() {
        return direccion;
    }

    public void setDireccion(InetAddress direccion) {
        this.direccion = direccion;
    }

    public PrintStream getEnviar() {
        return enviar;
    }

    public void setEnviar(PrintStream enviar) {
        this.enviar = enviar;
    }

    public BufferedReader getLeer() {
        return leer;
    }

    public void setLeer(BufferedReader leer) {
        this.leer = leer;
    }

    public int getNumDado() {
        return numDado;
    }

    public void setNumDado(int numDado) {
        this.numDado = numDado;
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public void setjDesktopPane(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }

    public boolean isReciboCartas() {
        return reciboCartas;
    }

    public void setReciboCartas(boolean reciboCartas) {
        this.reciboCartas = reciboCartas;
    }

    public boolean isAbierto() {
        return abierto;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }
    
    
    
    

}//fin clase
 