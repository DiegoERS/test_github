/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Data.JugadorData;
import Domain.Jugador;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom.JDOMException;

/**
 *
 * @author Diego
 */
public class JugadorBusiness {
    
    private JugadorData jugadorData;
    
    public JugadorBusiness() throws JDOMException, IOException {
    this.jugadorData=new JugadorData();
    }//constructor
    
    public boolean registrarJugador(Jugador jugador) throws IOException{
        return this.jugadorData.registrarJugador(jugador);
    }//registrarJugador
    
    public ArrayList<Jugador> obtenerJugadores(){
        return this.jugadorData.obtenerJugadores();
    }//obtenerJugadores
    
     public Jugador obtenerJugador(String nombreUsuario, String contrasenia){
         return this.jugadorData.obtenerJugador(nombreUsuario, contrasenia);
     }//obtenerJugador
    
     public void agregarAmigos(String nombreJugador, String amigo) throws IOException {
         this.jugadorData.agregarAmigos(nombreJugador, amigo);
     }//agregarAmigos
     
     public ArrayList<String> obtenerAmigos(String nombreJugador) {
         return this.jugadorData.obtenerAmigos(nombreJugador);
     }//obtenerAmigos
}//fin clase
