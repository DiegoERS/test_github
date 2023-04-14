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
//Se crea la clase CartaModo que hereda de la clase abstracta CartaComportamiento
public class CartaModo extends CartaComportamiento {

    public CartaModo( double posX, double posY) throws IOException {
        super( posX, posY);
         this.poderAtaque=0;
        this.poderDefensa=0;
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Imagenes/modoCarta.png"));
        this.numCarta=9; //Identificador
    }//constructor

    @Override
    public void dibujar(Graphics g) {
         g.drawImage(this.imagen, (int) this.posX, (int) this.posY, null);
    }//dibujar

    @Override
    //Se hereda el metodo de accionCarta en el cual se realiza el comportamiento de la carta CartaModo
     public double accionCarta(Jugador jugador,Jugador jugador2,int indice) {
           //Se analiza las cartas que posee el jugador en la zona de estrategia utilizando el getCartaSeleccionadas,
        //obteniendo el indice ya que cada carta posee un identificador numero unico el cual nos ayuda a diferenciarlas,
        //en este caso estamos analizando si el jugador posee carta espada, arco o hacha
         if (jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 1 || jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 2
                || jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 3 ) {
             try {
                 Armadura armadura=new Armadura(0, 0);//Se instancia la armadura 
                 return armadura.accionCarta(jugador, jugador2, indice);//Retorna la accion de la carta armadura
             } catch (IOException ex) {
                 Logger.getLogger(CartaModo.class.getName()).log(Level.SEVERE, null, ex);
             }//Catch
                 
             //Si el jugador ataca la carta de modo defiende
         }//if

        //Se analiza las cartas que posee el jugador en la zona de estrategia utilizando el getCartaSeleccionadas,
        //obteniendo el indice ya que cada carta posee un identificador numero unico el cual nos ayuda a diferenciarlas,
        //en este caso estamos analizando si el jugador posee carta escudoMetalico, escudoMadera o armadura
          if (jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 4 || jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 5
                || jugador.getCartasSeleccionadas().get(indice).getNumCarta() == 6 ) {
             try {
                 RecargarEnergia recargarEnergia=new RecargarEnergia(0, 0);//Se instancia recargaEnergia
                 return recargarEnergia.accionCarta(jugador, jugador2, indice);//Retorna la accion de carga de energia
             } catch (IOException ex) {
                 Logger.getLogger(CartaModo.class.getName()).log(Level.SEVERE, null, ex);
             }
          }//Si el jugador defiende la carta de modo recarga energia

        return 0;
    }//accionCarta
    
    
    
}//fin clase
