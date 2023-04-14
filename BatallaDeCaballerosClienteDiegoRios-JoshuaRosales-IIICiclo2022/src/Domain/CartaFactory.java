/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.io.IOException;

/**
 *
 * @author catas
 */
public class CartaFactory {

    public CartaFactory() {
    }//constructor

    
    
    //Este metodo recibe el identificador de la carta y retorna la carta correspondiente.
    public static Carta crearCarta(int tipo, double posX, double posY) throws IOException {
        switch (tipo) {
            case 1:
                return new Espada(posX, posY);
            case 2:

                return new Arco(posX, posY);
            case 3:

                return new Hacha(posX, posY);
            case 4:

                return new EscudoMadera(posX, posY);
            case 5:

                 return new EscudoMetalico(posX, posY);
            case 6:

                return new Armadura(posX, posY);
            case 7:

                return new RecargarEnergia(posX, posY);

            case 8:

                return new RecuperarVida(posX, posY);
            case 9:

                return new CartaModo(posX, posY);

            default:
                return null;
        }

    }//crearCarta

}
