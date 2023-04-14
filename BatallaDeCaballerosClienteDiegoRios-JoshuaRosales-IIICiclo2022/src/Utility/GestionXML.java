/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;


import Domain.Jugador;
import java.awt.List;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.ListenerNotFoundException;
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

    //convierte un String a xml.
    public Element stringToXML(String stringMensaje) throws JDOMException, IOException{
        SAXBuilder saxBuilder= new SAXBuilder();
        StringReader stringReader=new StringReader(stringMensaje);
        Document doc=saxBuilder.build(stringReader);
        return doc.getRootElement();
    } // stringToXML   
    
    //convierte un xml a String.
    public String xmlToString(Element element) {
        XMLOutputter outputter = new XMLOutputter(Format.getCompactFormat());
        String xmlStringElement = outputter.outputString(element);
        xmlStringElement = xmlStringElement.replace("\n", "");
        return xmlStringElement;
    } // xmlToString
    
    //convierte un xml a jugador.
    public Jugador xmlAJugador(Element elementoActual){
        try {
            Element eJugador=elementoActual.getChild("dato").getChild("jugador");
            Jugador jugadorActual=new Jugador(eJugador.getAttributeValue("nombreUsuario"),
                    eJugador.getChild("contraseña").getValue(),
                    Integer.parseInt(eJugador.getChild("lingotes").getValue()),
                    Integer.parseInt(eJugador.getChild("imagenPerfil").getValue()));
            
            
            return jugadorActual;
        } // xmlAEstudiante // xmlAEstudiante // xmlAEstudiante // xmlAEstudiante
        catch (IOException ex) {
            Logger.getLogger(GestionXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //convierte un xml en una lista de jugadores recibidos.
     public ArrayList<Jugador> xmlAJugadores(Element elementoActual){
       
            java.util.List jugadorList=elementoActual.getChild("dato").getChildren();
            ArrayList<Jugador> jugadores= new ArrayList<>();
            for (Object object : jugadorList) {
                try {
                    Element eJugadorActual=(Element) object;
                    jugadores.add(new Jugador(eJugadorActual.getAttributeValue("nombreUsuario"),
                            eJugadorActual.getChild("contraseña").getValue(),
                            Integer.parseInt(eJugadorActual.getChild("lingotes").getValue()),
                            Integer.parseInt(eJugadorActual.getChild("imagenPerfil").getValue())));
                } catch (IOException ex) {
                    Logger.getLogger(GestionXML.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
           
            for (int i = 0; i < jugadores.size(); i++) {
                System.out.println(jugadores.get(i).getLingotesOro());
             
         }
            
            return jugadores;
       
       
       
    }
     
     //convierte un xml a una lista de los nombres de los amigos de cada jugador.
     public ArrayList<String> xmlAAmigos(Element elementoActual){
       
            java.util.List amigoList=elementoActual.getChild("dato").getChildren();
            ArrayList<String> amigos= new ArrayList<>();
            for (Object object : amigoList) {
                    Element eAmigoActual=(Element) object;
                    amigos.add(eAmigoActual.getAttributeValue("nombre"));
            }
            
           
            
            
            return amigos;
       
       
       
    }
    
     //crea el protocolo para establecer una accion
       public Element crearMensajeProtocolo(String accion){
        
        Element eProtocolo=new Element("Protocolo");
        eProtocolo.setAttribute("accion", accion);
        
        return eProtocolo;
    }//crearMensajeProtocolo
       
       
       //establece la accion del protocolo.
       public Element agregarAccionSimple(String accion, String dato){
           Element eDato= new Element("dato");
           eDato.addContent(dato);
           
           Element eProtocolo=crearMensajeProtocolo(accion);
           eProtocolo.addContent(eDato);
           return eProtocolo;
       }
       
       //convierte en xml la informacion del chat privado para enviarlo a la persona deseada.
        public Element agregarChatPrivado(String accion, String dato,String personaENviar){
           Element eDato= new Element("dato");
           eDato.setAttribute("nombre",personaENviar);
           eDato.addContent(dato);
           
           Element eProtocolo=crearMensajeProtocolo(accion);
           eProtocolo.addContent(eDato);
           return eProtocolo;
       }
       
        
        //convierte un jugador y toda su informacion a xml
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
    
} // fin clase
