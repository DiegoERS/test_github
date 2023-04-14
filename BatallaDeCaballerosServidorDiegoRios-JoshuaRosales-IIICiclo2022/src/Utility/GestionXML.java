/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;


import Domain.Cliente;
import Domain.Jugador;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Nelson
 */
public class GestionXML {

    // convierte un String en un xml.
    public Element stringToXML(String stringMensaje) throws JDOMException, IOException{
        SAXBuilder saxBuilder= new SAXBuilder();
        StringReader stringReader=new StringReader(stringMensaje);
        Document doc=saxBuilder.build(stringReader);
        return doc.getRootElement();
    } // stringToXML   
    
    //convierte un xml en un String.
    public String xmlToString(Element element) {
        XMLOutputter outputter = new XMLOutputter(Format.getCompactFormat());
        String xmlStringElement = outputter.outputString(element);
        xmlStringElement = xmlStringElement.replace("\n", "");
        return xmlStringElement;
    } // xmlToString
    
    
    
    //convierte un xml a jugador.
    public Jugador xmlAJugador(Element elementoActual){
        Element eJugador=elementoActual.getChild("dato").getChild("jugador");
        Jugador jugadorActual=new Jugador(eJugador.getAttributeValue("nombreUsuario"),
                eJugador.getChild("contraseña").getValue(),
                Integer.parseInt(eJugador.getChild("lingotes").getValue()), 
                Integer.parseInt(eJugador.getChild("imagenPerfil").getValue()));
             
        
        return jugadorActual;
        
    } // xmlAEstudiante
    
      //crea el mensaje principal de los protocolos
       public Element crearMensajeProtocolo(String accion){
        
        Element eProtocolo=new Element("Protocolo");
        eProtocolo.setAttribute("accion", accion);
        
        return eProtocolo;
    }//crearMensajeProtocolo
       
       //agrega una accion al protocolo
        public Element agregarAccionSimple(String accion,String dato){
        Element edato= new Element("dato");
        edato.addContent(dato);
      
        Element eProtocolo=crearMensajeProtocolo(accion);
        eProtocolo.addContent(edato);
        return eProtocolo;
    }//agregarAccionSimple
       
        //agrega un jugador al protocolo.
       public Element agregarJugador(String accion,Jugador jugador){
        Element edato= new Element("dato");
        
        Element eJugador=new Element("jugador");
        eJugador.setAttribute("nombreUsuario",jugador.getNombreUsuario());
        
        Element eContrasenia=new Element("contraseña");
        eContrasenia.addContent(jugador.getContrasenia());
        
        Element eLingotes=new Element("lingotes");
        eLingotes.addContent( String.valueOf(jugador.getLingotesOro()));
        
        Element eImagenPerfil=new Element("imagenPerfil");
        eImagenPerfil.addContent(String.valueOf(jugador.getNumImagenPerfil()));
        
        eJugador.addContent(eContrasenia);
        eJugador.addContent(eLingotes);
        eJugador.addContent(eImagenPerfil);
        edato.addContent(eJugador);
      
        Element eProtocolo=crearMensajeProtocolo(accion);
        eProtocolo.addContent(edato);
        return eProtocolo;
    }//agregarPersona
       
       //agrega una lista de jugadores al protocolo.
        public Element agregarJugadores(String accion,ArrayList<Jugador> jugadores){
        Element edato= new Element("dato");
        
            for (int i = 0; i < jugadores.size(); i++) {
                 Element eJugador=new Element("jugador");
        eJugador.setAttribute("nombreUsuario",jugadores.get(i).getNombreUsuario());
        
        Element eContrasenia=new Element("contraseña");
        eContrasenia.addContent(jugadores.get(i).getContrasenia());
        
        Element eLingotes=new Element("lingotes");
        eLingotes.addContent( String.valueOf(jugadores.get(i).getLingotesOro()));
        
        Element eImagenPerfil=new Element("imagenPerfil");
        eImagenPerfil.addContent(String.valueOf(jugadores.get(i).getNumImagenPerfil()));
        
        eJugador.addContent(eContrasenia);
        eJugador.addContent(eLingotes);
        eJugador.addContent(eImagenPerfil);
        edato.addContent(eJugador);
                
            }
       
      
        Element eProtocolo=crearMensajeProtocolo(accion);
        eProtocolo.addContent(edato);
        return eProtocolo;
    }//agregarPersona
        
        //agrega una lista de Strings al protocolo para presentarlos como amigos.
        public Element agregarAmigos(String accion,ArrayList<String> amigos){
        Element edato= new Element("dato");
        
            for (int i = 0; i < amigos.size(); i++) {
                 Element eAmigo=new Element("amigo");
        eAmigo.setAttribute("nombre",amigos.get(i));
        
        edato.addContent(eAmigo);
                
            }
       
      
        Element eProtocolo=crearMensajeProtocolo(accion);
        eProtocolo.addContent(edato);
        return eProtocolo;
    }//agregarPersona
        
        
        //procesa los jugadores de cada cliente.
        public ArrayList<Jugador> clientesAJugador(ArrayList<Cliente> clientes){
        ArrayList<Jugador> jugadores=new ArrayList<>();
        
        for (int i = 0; i < clientes.size(); i++) {
            jugadores.add(clientes.get(i).getJugadorCliente());
            
        }
        
        return jugadores;
    }
       
} // fin clase
