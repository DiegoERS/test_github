/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Diego
 */

//Se crea clase abstracta para poder usar de las posiciones y las imagenes en otras clases
public abstract class Sprite {
    
    //Se crean de tipo protegido para poder usarlos en otras clases
    protected BufferedImage imagen;
    protected double posX;
    protected double posY;

    public Sprite() {
    }
    
    
    
    
    public Sprite(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }//constructor

    /**
     * @return the imagen
     */
    public BufferedImage getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the posX
     */
    public double getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(double posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public double getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(double posY) {
        this.posY = posY;
    }
    
    //Se crea el metodo abstrato dibujar para usarlo en otras clases
    public abstract void dibujar(Graphics g);
    
    
}//fin clase
