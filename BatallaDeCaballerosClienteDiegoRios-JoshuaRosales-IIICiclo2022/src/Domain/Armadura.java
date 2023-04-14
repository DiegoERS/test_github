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
//Se crea la clase Armadura que hereda de la clase abstracta CartaDefensa
public class Armadura extends CartaDefensa {

    public Armadura(double posX, double posY) throws IOException {
        super(posX, posY);
        this.poderAtaque = 0;
        this.poderDefensa = 5;
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Imagenes/armaduraCarta.png"));
        this.numCarta = 6;//Identificador
    }//constructor

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(this.imagen, (int) this.posX, (int) this.posY, null);
    }//dibujar

    @Override
    //Se hereda el metodo de CartaDefensa en el cual se realiza el comportamiento de la carta Armadura
    public double accionCarta(Jugador jugador, Jugador jugado2, int indice) {
        //Se analiza las cartas que posee el jugador en la zona de estrategia utilizando el getCartaSeleccionadas,
        //obteniendo el indice ya que cada carta posee un identificador numero unico el cual nos ayuda a diferenciarlas,
        //en este caso estamos analizando si el jugador posee carta espada, arco o hacha
        if (jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 1 || jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 2
                || jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 3) {
            //Si el jugador tiene enegia mayor a 0
            if (jugador.getEnergia() > 0) {
                double multiplicador = jugador.getEnergia() * 0.25;//Si el jugador posee enegería se le multiplica por 0.25
                double resultado = jugador.getCartasSeleccionadas().get(indice).getPoderAtaque() * multiplicador;
                //En el resultado se guarda el daño de la carta seleccionada y se le multiplica el multiplicador 
                jugador.setEnergia(0);//Se setea al jugador la energia
                double ataqueComp = (jugador.getCartasSeleccionadas().get(indice).getPoderAtaque() + resultado);
                //En ataqueComp se guarda el daño de las cartas de ataque y se le suma lo de la variable resultado
                if (ataqueComp > this.poderDefensa) {//Si el daño es mayor a la defensa

                    return (ataqueComp - this.poderDefensa) * -1;//Se le resta el daño a la defensa
                } else {
                    return 0;//Si es mayor la defensa no recibe daño
                }

            } else {
                //Si el jugador poseia energia menor a 0
                if (jugador.getCartasSeleccionadas().get(indice).getPoderAtaque() > this.poderDefensa) {
                    //Entraria al if diciendo si las cartas de ataque es mayor a la defensa

                    return (jugador.getCartasSeleccionadas().get(indice).getPoderAtaque() - this.poderDefensa) * -1;
                    //Retorne el daño del ataque menos la defensa

                } else {

                    return 0;//Si es mayor la defensa no recibe daño
                }

            }

        }//if de cartas ataque
        return 0;
    }

}//fin clase
