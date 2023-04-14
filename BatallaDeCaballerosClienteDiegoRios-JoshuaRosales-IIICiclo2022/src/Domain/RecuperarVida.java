/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Diego
 */

//Se crea la clase de la carta Recuperar Vida que extienda de la clase abstracta Carta Comportamiento
public class RecuperarVida extends CartaComportamiento{

    public RecuperarVida( double posX, double posY) throws IOException {
        super( posX, posY);
         this.poderAtaque=0;
        this.poderDefensa=0;
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Imagenes/recargaVidaCarta.png"));
        this.numCarta=8; //Identificador de la carta
    }//constructor

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(this.imagen, (int) this.posX, (int) this.posY, null);
    }//dibujar

    
    
    @Override
    //Se hereda el metodo de CartaComportamiento en el cual se realiza el comportamiento de la carta Recuperar Vida
      public double accionCarta(Jugador jugador,Jugador jugado2,int indice) {
          //Se analiza las cartas que posee el jugador en la zona de estrategia utilizando el getCartaSeleccionadas,
          //obteniendo el indice ya que cada carta posee un identificador numero unico el cual nos ayuda a diferenciarlas,
          //en este caso estamos analizando si el jugador posee carta espada, arco o hacha
          if (jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 1 || jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 2
                || jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 3 ) {

              
            double multiplicador = jugador.getEnergia() * 0.25; //Si el jugador posee enegería se le multiplica por 0.25
            double resultado = jugador.getCartasSeleccionadas().get(indice).getPoderAtaque() * multiplicador;
            //En el resultado se guarda el daño de la carta seleccionada y se le multiplica el multiplicador 
            jugador.setEnergia(0); //Se setea al jugador la energia 
            
            return (10-(jugador.getCartasSeleccionadas().get(indice).getPoderAtaque() + resultado)) ;
            //Se retorna los 10 de vida extra pero si el jugador fue atacado por una carta de daño se le resta el daño causado por
            //dicha carta a los 10 de vida extra
        }//if
        return 10; //Si ninguna carta validadas anteriormente la lanzo el jugador se retorna los 10 de vida extra al jugador
    }//accionCarta
    
}//fin clase
