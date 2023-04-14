/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Domain.ClienteSingleton;
import Domain.Jugador;
import Utility.GestionXML;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;

/**
 *
 * @author catas
 */
public class JPSalaEspera extends javax.swing.JPanel implements Runnable{

    /**
     * Creates new form JPSalaEspera
     */
    private ArrayList<Jugador> jugadores;
    private BufferedImage[] avatars;
   private JDesktopPane jDesktopPane; 
   public static boolean hilo;
   private Jugador jugador;
   private BufferedImage fondo;
   public static boolean botonInvitar;
    public JPSalaEspera(JDesktopPane jDesktopPane, Jugador jugador) throws IOException {
        initComponents();
        botonInvitar=true;
        hilo=true;
        Thread thread=new Thread(this);
        thread.start();
        this.jDesktopPane=jDesktopPane;
        this.jugadores=new ArrayList<>();
         this.fondo=ImageIO.read(getClass().getResourceAsStream("/Imagenes/Sala.png"));
         this.avatars = new BufferedImage[4];
        this.avatars[0] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/Alien.png"));
        this.avatars[1] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/Angel.png"));
        this.avatars[2] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/Soldado.png"));
        this.avatars[3] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/Vikingo.png"));
        ClienteSingleton cs=ClienteSingleton.getInstance();
        this.jugador=jugador;
        this.jugador.setImagen(this.avatars[this.jugador.getNumImagenPerfil()]);
        cs.setJugador(this.jugador);
        JFchat jFchat=new JFchat(jugador);
                        jFchat.setLocation(1100, 70);
                        jFchat.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics  g){
        g.drawImage(this.fondo, 0, 0, null);
        if (this.jugadores.size()==1) {
            g.setFont(new Font("andale", Font.BOLD, 18));
            g.setColor(Color.BLACK);
            g.drawString(this.jugadores.get(0).getNombreUsuario(), 210, 20);
        g.drawImage(this.avatars[this.jugadores.get(0).getNumImagenPerfil()], 210, 30, null);
        }
        if (this.jugadores.size()==2){
            g.setFont(new Font("andale", Font.BOLD, 18));
            g.setColor(Color.BLACK);
            g.drawString(this.jugadores.get(0).getNombreUsuario(), 210, 20);
        g.drawImage(this.avatars[this.jugadores.get(0).getNumImagenPerfil()], 210, 30, null);
        
        g.setFont(new Font("andale", Font.BOLD, 18));
            g.setColor(Color.BLACK);
        g.drawString(this.jugadores.get(1).getNombreUsuario(), 210, 140);
        g.drawImage(this.avatars[this.jugadores.get(1).getNumImagenPerfil()], 210, 150, null);
        }
        
        if (this.jugadores.size()==3){
            g.setFont(new Font("andale", Font.BOLD, 18));
            g.setColor(Color.BLACK);
            g.drawString(this.jugadores.get(0).getNombreUsuario(), 210, 20);
        g.drawImage(this.avatars[this.jugadores.get(0).getNumImagenPerfil()], 210, 30, null);
        
        g.setFont(new Font("andale", Font.BOLD, 18));
            g.setColor(Color.BLACK);
        g.drawString(this.jugadores.get(1).getNombreUsuario(), 10, 140);
        g.drawImage(this.avatars[this.jugadores.get(1).getNumImagenPerfil()], 10, 150, null);
        
        g.setFont(new Font("andale", Font.BOLD, 18));
            g.setColor(Color.BLACK);
        g.drawString(this.jugadores.get(2).getNombreUsuario(), 350, 140);
        g.drawImage(this.avatars[this.jugadores.get(2).getNumImagenPerfil()], 350, 150, null);
        }
        
        if (this.jugadores.size()==4){
            g.setFont(new Font("andale", Font.BOLD, 18));
            g.setColor(Color.BLACK);
            g.drawString(this.jugadores.get(0).getNombreUsuario(), 210, 20);
        g.drawImage(this.avatars[this.jugadores.get(0).getNumImagenPerfil()], 210, 30, null);
        
        g.setFont(new Font("andale", Font.BOLD, 18));
            g.setColor(Color.BLACK);
        g.drawString(this.jugadores.get(1).getNombreUsuario(), 10, 140);
        g.drawImage(this.avatars[this.jugadores.get(1).getNumImagenPerfil()], 10, 150, null);
        
        g.setFont(new Font("andale", Font.BOLD, 18));
            g.setColor(Color.BLACK);
        g.drawString(this.jugadores.get(2).getNombreUsuario(), 350, 140);
        g.drawImage(this.avatars[this.jugadores.get(2).getNumImagenPerfil()], 350, 150, null);
        
        g.setFont(new Font("andale", Font.BOLD, 18));
            g.setColor(Color.BLACK);
        g.drawString(this.jugadores.get(3).getNombreUsuario(), 210, 140);
        g.drawImage(this.avatars[this.jugadores.get(3).getNumImagenPerfil()], 210, 150, null);
        }
            
        repaint();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnListo = new javax.swing.JButton();
        jbtnAmigos = new javax.swing.JButton();

        jbtnListo.setText("Listo");
        jbtnListo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnListoActionPerformed(evt);
            }
        });

        jbtnAmigos.setText("Invitar amigos");
        jbtnAmigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAmigosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jbtnListo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jbtnAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(328, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnListo, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jbtnAmigos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAmigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAmigosActionPerformed
        JIFInvitarAmigo jIFInvitarAmigo=new JIFInvitarAmigo();
        this.jDesktopPane.add(jIFInvitarAmigo);
        
        jIFInvitarAmigo.setVisible(true);
    }//GEN-LAST:event_jbtnAmigosActionPerformed

    private void jbtnListoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnListoActionPerformed
          ClienteSingleton cs=ClienteSingleton.getInstance();
          GestionXML gestionXML=new GestionXML();
          cs.setjDesktopPane(this.jDesktopPane);
          cs.enviarDatos(gestionXML.xmlToString(gestionXML.crearMensajeProtocolo("esperaListo")));
          this.jbtnListo.setEnabled(false);
    }//GEN-LAST:event_jbtnListoActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnAmigos;
    private javax.swing.JButton jbtnListo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        ClienteSingleton cs=ClienteSingleton.getInstance(); 
                
        while (hilo) {            
            
           this.jbtnAmigos.setEnabled(botonInvitar);
                
            try {
                if (cs.isCambioEspera()) {
                    this.jugadores.clear();
                    this.jugadores=cs.getEspectadores();
                    
                    if (this.jugadores.size()==4) {
                        this.jbtnAmigos.setEnabled(false);
                    }
                     
                    cs.setCambioEspera(false);
                }
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JPSalaEspera.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void darImagen(Jugador jugador){
        switch (jugador.getNumImagenPerfil()) {
            case 0:
                 jugador.setImagen(this.avatars[0]);
                break;
                case 1:
                 jugador.setImagen(this.avatars[1]);
                break;
                case 2:
                 jugador.setImagen(this.avatars[2]);
                break;
                case 3:
                 jugador.setImagen(this.avatars[3]);
                break;
            default:
                throw new AssertionError();
        }
    }
}
