/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Diego
 */

//Se crea la clase de la carta Recuperar Energía que extienda de la clase abstracta Carta Comportamiento
public class RecargarEnergia extends CartaComportamiento{

    
    public RecargarEnergia( double posX, double posY) throws IOException {
        super( posX, posY);
          this.poderAtaque=0;
        this.poderDefensa=0;
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Imagenes/recargaEnergiaCarta.png"));
        this.numCarta=7; //Identificador de la carta
    }//constructor

    @Override
    public void dibujar(Graphics g) {
         g.drawImage(this.imagen, (int) this.posX, (int) this.posY, null);
    }//dibujar


    @Override
     //Se hereda el metodo de CartaComportamiento en el cual se realiza el comportamiento de la carta Recargar Enería
     public double accionCarta(Jugador jugador, Jugador jugador2,int indice) {
         jugador2.setEnergia(jugador2.getEnergia()+1);
         //System.out.println(jugador2.getNombreUsuario()+ " " +jugador2.getEnergia());
          //Se analiza las cartas que posee el jugador en la zona de estrategia utilizando el getCartaSeleccionadas,
          //obteniendo el indice ya que cada carta posee un identificador numero unico el cual nos ayuda a diferenciarlas,
          //en este caso estamos analizando si el jugador posee carta espada, arco o hacha
         if (jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 1 || jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 2
                || jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 3) {
             
             
            return (jugador.getCartasSeleccionadas().get(indice).getPoderAtaque())*-1;
        }//If
         //Analizamos si el jugador posee una carta de recuperar vida en la zona de estrategia
         if (jugador.getCartasSeleccionadas().get(indice).getNumCarta()==9) {
             try {
                 //Si sí posee se instancia a la clase hacha
                 Hacha hacha=new Hacha(0, 0);
                  double multiplicador = jugador.getEnergia() * 0.25; //Se obtendra la energía y se le sumara el 25% de daño extra
            double resultado = hacha.getPoderAtaque() * multiplicador; //Se le agregará el daño extra al daño original del hacha
            jugador.setEnergia(0);
            return (hacha.getPoderAtaque()+resultado)*-1; //Se retornará el poder del hacha mas el daño extra
             } catch (IOException ex) {
                 Logger.getLogger(RecargarEnergia.class.getName()).log(Level.SEVERE, null, ex);
             }//Catch
             
        }//if
        return 0; //Si no entra a ningun if se retorna 0
    }//accionCarta
    
        

    
}//fin clase
