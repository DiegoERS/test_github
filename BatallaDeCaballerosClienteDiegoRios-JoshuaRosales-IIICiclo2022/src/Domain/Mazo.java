/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;

/**
 *
 * @author Diego
 */
//Se crea la clase mazo que hereda de la clase abstracta Sprite
public class Mazo extends Sprite {
    
    private int cantidadCartas;
    private ArrayList<Carta> cartas; //Se crea el ArrayList tipo carta 

    public Mazo(int cantidadCartas, double posX, double posY) throws IOException {
        super(posX, posY);
        this.cantidadCartas = cantidadCartas;
        this.cartas = crearMazo();
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Imagenes/atrasCarta.png"));
    }//constructor

    public Mazo() throws IOException {
     this.cantidadCartas = 21;
        this.cartas = crearMazo();
        this.imagen = ImageIO.read(getClass().getResourceAsStream("/Imagenes/atrasCarta.png"));
    }
    

    @Override
    public void dibujar(Graphics g) {

        g.drawImage(this.imagen, 648, 466, null);
        g.setFont(new Font("gg", Font.BOLD, 40));
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(this.cartas.size()), 665, 535);
    }//dibujo

    public ArrayList<Carta> getCartas() {
        return cartas;
    }//getCartas

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }//setCartas
    
    //Este metodo es donde se crea el mazo con las 21 cartas que tendrá cada mazo de cada jugador
    public ArrayList<Carta> crearMazo() throws IOException{
        ArrayList<Carta> cartas=new ArrayList<>();
        //Se empiezan a añadir las cartas que apareceran 2 veces en el juego al arrayList 
        for (int i = 0; i < 2; i++) {
            //cartas ataque
            cartas.add(new Espada(100, 0));
            cartas.add(new Arco( 200, 0));
            cartas.add(new Hacha( 300, 0));
            
            //cartas defensa
            cartas.add(new EscudoMadera( 400, 0));
            cartas.add(new EscudoMetalico( 500, 0));
            cartas.add(new Armadura( 600, 0));
            
            //comportamiento
            cartas.add(new RecuperarVida( 700, 0));
        }
        //Se empiezan a añadir las cartas que apareceran 4 veces en el juego al arrayList 
        for (int i = 0; i < 4; i++) {
            cartas.add(new RecargarEnergia( 0, 120));
        }
        //Se empiezan a añadir las cartas que apareceran 3 veces en el juego al arrayList 
        for (int i = 0; i < 3; i++) {
             cartas.add(new CartaModo( 100, 120));
        }
        
        Collections.shuffle(cartas); //Desordena el mazo para que siempre salga de una manera diferente
        
        return cartas; //Retorna el mazo
    }//crearMazo
   
   
    
}//fin clase
