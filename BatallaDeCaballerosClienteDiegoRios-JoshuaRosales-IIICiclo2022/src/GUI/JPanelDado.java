/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Domain.Dado;
import Domain.Jugador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

/**
 *
 * @author Diego
 */
public class JPanelDado extends JPanel implements ActionListener{

    private Dado dado;
    private BufferedImage fondo;
    private JButton jbtnInicio;
    private JButton jbtnparar;
    private Jugador jugador;
    
    
    public JPanelDado(Jugador jugador, int numDado) throws IOException {
       this.jugador=jugador;
         this.setPreferredSize(new Dimension(400, 300));
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
        this.fondo=ImageIO.read(getClass().getResourceAsStream("/Imagenes/fondoDado.png"));
        this.dado=new Dado(this.jugador, numDado);
        init();
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(this.fondo, 0, 0, null);
        g.drawImage(this.jugador.getImagen(), 320, 0, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("andale", Font.CENTER_BASELINE, 26));
        g.drawString(this.jugador.getNombreUsuario(), 320, 100);
        this.dado.dibujar(g);
        repaint();
    }

    private void init() {
        this.jbtnInicio=new JButton("Iniciar dado");
        this.jbtnInicio.setBounds(140, 200, 100, 30);
        this.jbtnInicio.addActionListener(this);
        this.add(this.jbtnInicio);
        
         this.jbtnparar=new JButton("Parar dado");
        this.jbtnparar.setBounds(140, 240, 100, 30);
        this.jbtnparar.addActionListener(this);
        this.add(this.jbtnparar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.jbtnInicio) {
            this.dado.start();
            this.jbtnInicio.setEnabled(false);
        }
        
         if (e.getSource()==this.jbtnparar) {
            this.dado.setEjecucion(false);
            this.jbtnparar.setEnabled(false);
             
        }
    }
    
}
