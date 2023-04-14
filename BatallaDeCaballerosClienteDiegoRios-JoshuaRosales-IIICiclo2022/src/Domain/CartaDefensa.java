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
//Se crea la clase abstracta CartaDefensa que hereda de Carta
public abstract class CartaDefensa extends Carta{
    
  

    public CartaDefensa( double posX, double posY) {
        super(posX, posY);
    
    }//constructor

   

    @Override
    public abstract void dibujar(Graphics g);//metodo de dibujar

    @Override
    public abstract double accionCarta(Jugador jugador,Jugador jugador2,int indice); //Metodo de accion de cartas
    
    
    
}//fin clase
