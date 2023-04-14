/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;


import GUI.JPAreaJuego;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
//Se crea la clase dado que herda de Hilo
public class Dado extends Thread{
    private BufferedImage[] dado;
    private BufferedImage imagenActual;
    private boolean ejecucion;
    private Jugador jugador;
    private int numDado;

    public Dado(Jugador jugador, int numDado) throws IOException {
        this.jugador = jugador;
        this.numDado=numDado;
        this.dado = new BufferedImage[6]; //Creamos las 6 caras del dado
        this.dado[0] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/dado1.png"));
        this.dado[1] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/dado2.png"));
        this.dado[2] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/dado3.png"));
        this.dado[3] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/dado4.png"));
        this.dado[4] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/dado5.png"));
        this.dado[5] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/dado6.png"));
        this.imagenActual = ImageIO.read(getClass().getResourceAsStream("/Imagenes/dado1.png"));
        this.ejecucion = true; //ejecucion Verdadera
    }//Constructor

    @Override
    public void run() {
        try {
            int j = 0;
            while (this.ejecucion) {//Entra al ciclo si la ejecucion es verdadera
                try {
                    this.imagenActual = this.dado[j];//Al princio la cara del dado es 1
                    j++; //Se suma
                    if (j == 6) {
                        j = 0;
                    }//Si j es excatamente igual a 0 la j vuelve a ser 0 para volver mostrar los 6 numeros del dado
                    Thread.sleep(100); //!00 milisegundos
                } catch (InterruptedException ex) {
                    Logger.getLogger(Dado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            int resultado = this.numDado; //Se setea el numero enviado desde el servidor
            this.imagenActual = this.dado[resultado - 1]; //Se cambia la imagen del dado por la que le haya tocado al jugador
            JOptionPane.showMessageDialog(null, "su numero en el dado es: " + resultado);
            
            ClienteSingleton cs=ClienteSingleton.getInstance();
            JInternalFrame internalFrame=new JInternalFrame("partida");
            internalFrame.add(new JPAreaJuego(cs.getEspectadores()));
            internalFrame.pack();
            cs.getjDesktopPane().add(internalFrame);
            internalFrame.setVisible(true);
        } //run
        catch (IOException ex) {
            Logger.getLogger(Dado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void dibujar(Graphics g) {
        g.drawImage(this.imagenActual, 160, 110, null);
    }//dibujar

    public BufferedImage getImagenActual() {
        return imagenActual;
    }//getImagenActual

    public void setImagenActual(BufferedImage imagenActual) {
        this.imagenActual = imagenActual;
    }//setImagenActual

    public boolean isEjecucion() {
        return ejecucion;
    }//isEjecucion

    public void setEjecucion(boolean ejecucion) {
        this.ejecucion = ejecucion;
    }//setEjecucion

}//fin clase
