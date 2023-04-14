/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Domain.Jugador;
import Utility.MyUtil;
import Utility.Ruta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Diego
 */
public class JugadorData {
    
    private Document document;
    private Element raiz;
    private String ruta;
    
    public JugadorData() throws JDOMException, IOException {
        this.ruta = Ruta.RutaJugadores;
        File file = new File(this.ruta);
        if (file.exists()) {
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            
            this.document = saxBuilder.build(this.ruta);
            this.raiz = this.document.getRootElement();
        } else {
            this.raiz = new Element("Jugadores");
            this.document = new Document(this.raiz);
            guardarXML();
        }//if-else
    }//constructor

    
    //guarda toda la informacion en el archivo.
    private void guardarXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.document, new PrintWriter(this.ruta));
    }//guardarXML

    
    //registra al jugador en el archivo.
    public boolean registrarJugador(Jugador jugador) throws IOException {
        
        ArrayList<Jugador> jugadores = obtenerJugadores();
        
        if (jugadores.size() > 0) {
            boolean repetido = verificarUsuario(jugador.getNombreUsuario());
            if (repetido) {
                return false;
            }
        }
        
        Element eJugador = new Element("Jugador");
        eJugador.setAttribute("nombreUsuario", jugador.getNombreUsuario());
        
        Element eContrasenia = new Element("contraseña");
        eContrasenia.addContent(jugador.getContrasenia());
        
        Element eLingotes = new Element("lingotes");
        eLingotes.addContent("" + jugador.getLingotesOro());
        
        Element eImagenPerfil = new Element("imagenPerfil");
        eImagenPerfil.addContent(String.valueOf(jugador.getNumImagenPerfil()));
        
        Element eAmigos = new Element("amigos");
        
        eJugador.addContent(eContrasenia);
        eJugador.addContent(eLingotes);
        eJugador.addContent(eImagenPerfil);
        eJugador.addContent(eAmigos);
        
        this.raiz.addContent(eJugador);
        this.guardarXML();
        return true;
        
    }//registrarJugador

    
    //obtiene la lista de jugadores guardados en el archivo
    public ArrayList<Jugador> obtenerJugadores() {
        
        ArrayList<Jugador> jugadores = new ArrayList<>();
        
        List elementList = this.raiz.getChildren();
        
        for (Object object : elementList) {
            Element eJugadorActual = (Element) object;
            jugadores.add(new Jugador(eJugadorActual.getAttributeValue("nombreUsuario"),
                    eJugadorActual.getChild("contraseña").getValue(),
                    Integer.parseInt(eJugadorActual.getChild("lingotes").getValue()),
                    Integer.parseInt(eJugadorActual.getChild("imagenPerfil").getValue()))
            );
            
        }//for 

        return jugadores;
        
    }//obtenerEstudiantes
    
    
    //obtiene la lista de amigos de un jugador designado.
    public ArrayList<String> obtenerAmigos(String nombreJugador) {
        
        ArrayList<String> amigos = new ArrayList<>();
        
        List elementList = this.raiz.getChildren();
        
        for (Object object : elementList) {
            Element eJugadorActual = (Element) object;
            if (eJugadorActual.getAttributeValue("nombreUsuario").equals(nombreJugador)) {
                
                List amigosList = eJugadorActual.getChild("amigos").getChildren();
                
                for (Object object1 : amigosList) {
                    Element eAmigoActual = (Element) object1;
                    amigos.add(eAmigoActual.getAttributeValue("nombre"));
                    
                }
                
            }
            
        }//for 

        return amigos;
        
    }//obtenerEstudiantes

    //obtiene toda la informacion del jugador designado.
    public Jugador obtenerJugador(String nombreUsuario, String contrasenia) {
        
        List elementList = this.raiz.getChildren();
        
        for (Object object : elementList) {
            Element eJugadorActual = (Element) object;
            Jugador jugador = new Jugador(eJugadorActual.getAttributeValue("nombreUsuario"),
                    eJugadorActual.getChild("contraseña").getValue(),
                    Integer.parseInt(eJugadorActual.getChild("lingotes").getValue()),
                    Integer.parseInt(eJugadorActual.getChild("imagenPerfil").getValue()));
            if (jugador.getNombreUsuario().equals(nombreUsuario) && jugador.getContrasenia().equals(contrasenia)) {
                return jugador;
            }
            
        }//for 

        return null;
        
    }//obtenerEstudiantes

    //verifica si un usuario está registrado o no en el archivo.
    public boolean verificarUsuario(String nombreUsuario) {
        List elementList = this.raiz.getChildren();
        
        for (Object object : elementList) {
            Element eJugadorActual = (Element) object;
            Jugador jugador = new Jugador(eJugadorActual.getAttributeValue("nombreUsuario"),
                    eJugadorActual.getChild("contraseña").getValue(),
                    Integer.parseInt(eJugadorActual.getChild("lingotes").getValue()),
                    Integer.parseInt(eJugadorActual.getChild("imagenPerfil").getValue()));
            if (jugador.getNombreUsuario().equals(nombreUsuario)) {
                return true;
            }
            
        }
        
        return false;
    }
    
    //agrega a los amigos a las personas designada en cada uno de sus apartados.
    public void agregarAmigos(String nombreJugador, String amigo) throws IOException {
        boolean hecho = false;
        List jugadoresLIst = this.raiz.getChildren();
        
        for (Object object : jugadoresLIst) {
            Element eJugadorActual = (Element) object;
            if (eJugadorActual.getAttributeValue("nombreUsuario").equals(nombreJugador)) {
                Element eAmigos = eJugadorActual.getChild("amigos");
                
                Element eAmigo = new Element("amigo");
                eAmigo.setAttribute("nombre", amigo);
                
                eAmigos.addContent(eAmigo);
                
                hecho = true;
            }
        }
        
        if (hecho) {
            this.guardarXML();
        }
        
    }//agregarAmigos
    
     

}//fin clase
