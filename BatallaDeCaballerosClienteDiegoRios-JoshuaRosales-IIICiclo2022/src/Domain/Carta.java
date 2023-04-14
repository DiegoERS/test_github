/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Graphics;

/**
 *
 * @author Diego
 */
//Se crea la clase carta que hereda de Sprite
public abstract class Carta extends Sprite {

    protected int numCarta;
    protected double poderAtaque;
    protected double poderDefensa;
    
    
    public Carta(double posX, double posY) {
        super(posX, posY);
        this.numCarta=0;
        this.poderAtaque=0;
        this.poderDefensa=0;
    }//constructor

    public int getNumCarta() {
        return numCarta;
    }//getNumCarta

    public void setNumCarta(int numCarta) {
        this.numCarta = numCarta;
    }//setNumCarta

    public double getPoderAtaque() {
        return poderAtaque;
    }//getPoderAtaque

    public void setPoderAtaque(double poderAtaque) {
        this.poderAtaque = poderAtaque;
    }//setPoderAtaque

    public double getPoderDefensa() {
        return poderDefensa;
    }//getPoderDefensa

    public void setPoderDefensa(double poderDefensa) {
        this.poderDefensa = poderDefensa;
    }//setPoderDefensa

    @Override
    public abstract void dibujar(Graphics g); //Metodo de dibujar
    
    public abstract double accionCarta(Jugador jugador,Jugador jugador2, int indice); //Metodo de accion de las cartas
    
    
}//fin clase
