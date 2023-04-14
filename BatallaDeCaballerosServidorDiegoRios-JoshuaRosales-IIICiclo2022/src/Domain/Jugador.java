/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

/**
 *
 * @author Diego
 */
public class Jugador {
    
    private String nombreUsuario;
    private String contrasenia;
    private double vida;
    private int turno;
    private int lingotesOro;
    private int numImagenPerfil;
    private ZonaCarta zonaCarta;
    private boolean jugadorListo;
    private boolean cartasEscogidas;

    public Jugador(String nombreUsuario, String contrasenia, int lingotesOro, int numImagenPerfil) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.lingotesOro = lingotesOro;
        this.numImagenPerfil = numImagenPerfil;
        this.jugadorListo=false;
        this.cartasEscogidas=false;
        
    }//constructor

    public boolean isCartasEscogidas() {
        return cartasEscogidas;
    }

    public void setCartasEscogidas(boolean cartasEscogidas) {
        this.cartasEscogidas = cartasEscogidas;
    }

    
    
    public boolean isJugadorListo() {
        return jugadorListo;
    }

    public void setJugadorListo(boolean jugadorListo) {
        this.jugadorListo = jugadorListo;
    }

    
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getLingotesOro() {
        return lingotesOro;
    }

    public void setLingotesOro(int lingotesOro) {
        this.lingotesOro = lingotesOro;
    }

    public int getNumImagenPerfil() {
        return numImagenPerfil;
    }

    public void setNumImagenPerfil(int numImagenPerfil) {
        this.numImagenPerfil = numImagenPerfil;
    }

    public ZonaCarta getZonaCarta() {
        return zonaCarta;
    }

    public void setZonaCarta(ZonaCarta zonaCarta) {
        this.zonaCarta = zonaCarta;
    }

    
    
    @Override
    public String toString() {
        return "Jugador{" + "nombreUsuario=" + nombreUsuario + ", contrasenia=" + contrasenia + ", vida=" + vida + ", turno=" + turno + ", lingotesOro=" + lingotesOro + ", numImagenPerfil=" + numImagenPerfil + '}';
    }
    
    
    
    
    
    
}//fin clase
