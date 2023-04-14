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
public class ZonaCarta {
    
    private ArrayList<Integer> cartasEscogidas;
    private int energia;

    public ZonaCarta(ArrayList<Integer> cartasEscogidas, int energia) {
        this.cartasEscogidas = cartasEscogidas;
        this.energia = energia;
    }//constructor

    public ArrayList<Integer> getCartasEscogidas() {
        return cartasEscogidas;
    }

    public void setCartasEscogidas(ArrayList<Integer> cartasEscogidas) {
        this.cartasEscogidas = cartasEscogidas;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    @Override
    public String toString() {
        return "ZonaCarta{" + "cartasEscogidas=" + cartasEscogidas + ", energia=" + energia + '}';
    }
    
    
    
    
}//fin clase
