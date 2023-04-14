/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
//Se crea la clase jugador que hereda de la clase abstracta Sprite
public class Jugador extends Sprite {

    //Se crean las variables que necesitaremos
    private String nombreUsuario;
    private String contrasenia;
    private double vida;
    private int turno;
    private int lingotesOro;
    private int numImagenPerfil;
    private Mazo mazo;
    private ArrayList<Carta> cartasActuales;
    private ArrayList<Carta> cartasSeleccionadas;
    private int energia;
    private boolean accion;

    public Jugador(String nombreUsuario, String contrasenia, double posX, double posY) throws IOException {
        super(posX, posY);
        this.energia=0;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.vida = 100;
        this.turno = 0;
        this.lingotesOro = 0;
        this.numImagenPerfil = 0;
        this.mazo = new Mazo();
        this.cartasActuales = new ArrayList<>();
        rellenarCartasActuales();
        this.cartasSeleccionadas = new ArrayList<>();
        this.accion=false;

    }//constructor
    
    public Jugador(String nombreUsuario, String contrasenia, int lingotesOro, int numImagenPerfil) throws IOException {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.lingotesOro = lingotesOro;
        this.numImagenPerfil = numImagenPerfil;
        this.energia=0;
        this.vida = 100;
        this.turno = 0;
        this.mazo = new Mazo();
        this.cartasActuales = new ArrayList<>();
        rellenarCartasActuales();
        this.cartasSeleccionadas = new ArrayList<>();
        this.accion=false;
        
    }//constructor

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }
    
    public boolean isAccion() {
        return accion;
    }

    public void setAccion(boolean accion) {
        this.accion = accion;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return the vida
     */
    public double getVida() {
        return vida;
    }

    /**
     * @param vida the vida to set
     */
    public void setVida(double vida) {
        this.vida = vida;
    }

    /**
     * @return the turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    /**
     * @return the lingotesOro
     */
    public int getLingotesOro() {
        return lingotesOro;
    }

    /**
     * @param lingotesOro the lingotesOro to set
     */
    public void setLingotesOro(int lingotesOro) {
        this.lingotesOro = lingotesOro;
    }

    /**
     * @return the numImagenPerfil
     */
    public int getNumImagenPerfil() {
        return numImagenPerfil;
    }

    /**
     * @param numImagenPerfil the numImagenPerfil to set
     */
    public void setNumImagenPerfil(int numImagenPerfil) {
        this.numImagenPerfil = numImagenPerfil;
    }

    /**
     * @return the mazo
     */
    public Mazo getMazo() {
        return mazo;
    }

    /**
     * @param mazo the mazo to set
     */
    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public ArrayList<Carta> getCartasSeleccionadas() {
        return cartasSeleccionadas;
    }

    public void setCartasSeleccionadas(ArrayList<Carta> cartasSeleccionadas) {
        this.cartasSeleccionadas = cartasSeleccionadas;
    }

    public ArrayList<Carta> getCartasActuales() {
        return cartasActuales;
    }

    public void setCartasActuales(ArrayList<Carta> cartasActuales) {
        this.cartasActuales = cartasActuales;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombreUsuario=" + nombreUsuario + ", contrasenia=" + contrasenia + ", vida=" + vida + ", turno=" + turno + ", lingotesOro=" + lingotesOro + ", numImagenPerfil=" + numImagenPerfil + '}';
    }



    @Override
    public void dibujar(Graphics g) {
        this.mazo.dibujar(g);
        //Dibujaremos las cartas que posea en la mano el jugador
        for (int i = 0; i < cartasActuales.size(); i++) {
            this.cartasActuales.get(i).setPosX(5);
            this.cartasActuales.get(i).setPosY(465);

            this.cartasActuales.get(i).setPosX(i * 30 + i * 80 + 20);
            this.cartasActuales.get(i).dibujar(g);

        }//For
        //Dibujaremos las cartas que posea en la zona de estrategia el jugador
        for (int i = 0; i < this.cartasSeleccionadas.size(); i++) {
            this.cartasSeleccionadas.get(i).setPosX(25);
            this.cartasSeleccionadas.get(i).setPosY(300);

            this.cartasSeleccionadas.get(i).setPosX(i * 130 + i * 80 + 20);
            this.cartasSeleccionadas.get(i).dibujar(g);

        }//For

    }//Dibujar

    //Metodo que se encargará de darle al jugador las primeras 5 cartas las cuales seran las cartas que tendrá
    //en la mano
    public void rellenarCartasActuales() {

        //Le damos las 5 cartas 
        for (int i = 0; i < 5; i++) {

            //Si en el mazo ya no hay cartas no se le dará nada
            if (this.mazo.getCartas().size() == 0) {

                break;
            }//if
            //Si ya posee las 5 cartas en la mano no se le darán mas 
            if (this.cartasActuales.size() == 5) {
                break;
            }//if
            //Se le darán las 5 cartas del mazo y se remueven del mazo para que no vuelvas a aparecer
            this.cartasActuales.add(this.mazo.getCartas().remove(0));

        }//For

    }//rellenarCartasActuales

    //Metodo para seleccionar las cartas y agregarlas a la zona de estrategia
    public void tocarCarta(int posiX, int posiY) {
        Rectangle rectangulo = new Rectangle();
        //Se recorre las cartas de la mano
        for (int i = 0; i < this.cartasActuales.size(); i++) {
            //Se obtiene el limite de las cartas
            
          
            
            rectangulo.setBounds((int) this.cartasActuales.get(i).getPosX(), (int) this.cartasActuales.get(i).getPosY(), 80, 120);
            if (rectangulo.contains(posiX, posiY)) {
                //Si las cartas de la zona de estrategia son menores a 3
                
                if (this.cartasSeleccionadas.size() < 3) {
                    //Se agregan 3 cartas a la zona de estrategia de las 5 cartas que tenemos en la mano y se remueven
                    //de la mano
                    this.cartasSeleccionadas.add(this.cartasActuales.remove(i));
                }//if

            }//if

        }//for

    }//tocarCarta

    //Metodo que se encargara de borrar la formacion de la zona de estrategia del jugador
    public void limpiarFormacion() {

        //Se recorren las 3 cartas que estan en la zona de estrategia
        for (int i = 0; i < 3; i++) {
            //Se vuelven a agregar a las cartas de la mano y se remueven de la zona de estrategia
            this.cartasActuales.add(this.cartasSeleccionadas.remove(0));

        }//for

    }//limpiarFormacion

}//fin clase
