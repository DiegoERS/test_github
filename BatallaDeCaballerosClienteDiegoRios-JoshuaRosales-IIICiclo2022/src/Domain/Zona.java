/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.util.ArrayList;

/**
 *
 * @author Diego
 */

public class Zona {
    private int cartaJuego;
    private int energia;
    private ArrayList<Carta> cartasEscogidas;

    public Zona(int cartaJuego, int energia) {
        this.cartaJuego = cartaJuego;
        this.energia = energia;
        this.cartasEscogidas=new ArrayList<>();
    }//constructor

    public int getCartaJuego() {
        return cartaJuego;
    }

    public void setCartaJuego(int cartaJuego) {
        this.cartaJuego = cartaJuego;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public ArrayList<Carta> getCartasEscogidas() {
        return cartasEscogidas;
    }

    public void setCartasEscogidas(ArrayList<Carta> cartasEscogidas) {
        this.cartasEscogidas = cartasEscogidas;
    }
    
    
    
    
    
    
}//Zona
