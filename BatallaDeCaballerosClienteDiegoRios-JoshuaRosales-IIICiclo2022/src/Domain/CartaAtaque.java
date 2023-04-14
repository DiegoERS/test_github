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
//Se crea la clase abstracta CartaAtaque que hereda de Carta
public abstract class CartaAtaque extends Carta {
    
    

    public CartaAtaque( double posX, double posY) {
        super(posX, posY);
       
    }//constructor

 

    @Override
    public abstract void dibujar(Graphics g); //Metodo dibujar

    @Override
    public abstract double accionCarta(Jugador jugador,Jugador jugador2,int indice); //Metodo de accion de las cartas
    
    
    
}//fin clase
