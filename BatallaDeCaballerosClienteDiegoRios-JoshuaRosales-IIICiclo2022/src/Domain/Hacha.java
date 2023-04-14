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
//Se crea la clase hacha que hereda de la clase abstracta CartaAtaque
public class Hacha extends CartaAtaque {

    public Hacha(double posX, double posY) throws IOException {
        super(posX, posY);
        this.poderAtaque = 15;
        this.poderDefensa = 0;
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Imagenes/hachaCarta.png"));
        this.numCarta = 3; //Identificador
    }//constructor

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(this.imagen, (int) this.posX, (int) this.posY, null);
    }//dibujar

    @Override
    //Se hereda el metodo de accionCarta en el cual se realiza el comportamiento de la carta Hacha
    public double accionCarta(Jugador jugador, Jugador jugador2, int indice) {
        //Se analiza las cartas que posee el jugador en la zona de estrategia utilizando el getCartaSeleccionadas,
        //obteniendo el indice ya que cada carta posee un identificador numero unico el cual nos ayuda a diferenciarlas,
        //en este caso estamos analizando si el jugador posee carta espada, arco o hacha
        if (jugador.getCartasSeleccionadas().get(indice).numCarta == 1 || jugador.getCartasSeleccionadas().get(indice).numCarta == 2
                || jugador.getCartasSeleccionadas().get(indice).numCarta == 3 ) {

            double multiplicador = jugador.getEnergia() * 0.25; //Si el jugador posee enegería se le multiplica por 0.25
            double resultado = jugador.getCartasSeleccionadas().get(indice).getPoderAtaque() * multiplicador;
            //En el resultado se guarda el daño de la carta seleccionada y se le multiplica el multiplicador 
            jugador.setEnergia(0); //Se setea al jugador la energia 
           
            return (jugador.getCartasSeleccionadas().get(indice).getPoderAtaque() + resultado) * -1;
            //Retorna el poder de ataque mas el resultado * -1
        }
        return 0;
    }//accionCarta

}//fin clase
