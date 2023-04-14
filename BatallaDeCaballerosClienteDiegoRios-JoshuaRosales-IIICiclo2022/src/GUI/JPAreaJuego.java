/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
 
import Domain.ClienteSingleton;
import Domain.Juego;
import Domain.Jugador;
import Utility.GestionXML;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author XPC
 */
public class JPAreaJuego extends JPanel implements MouseListener, ActionListener, ItemListener, Runnable{

    private BufferedImage campoJuego;
    private Juego juego;

    private JButton jbtnLimpiarformacion;
    private JButton jbtnTurno;
    private JComboBox<String> jcbJugadores;
    private String personaAtacar;
    private BufferedImage[] avatars;
    
    private int turnoJugadores;
    
    public JPAreaJuego(ArrayList<Jugador> jugadores) throws IOException {

        ClienteSingleton cs=ClienteSingleton.getInstance();
        this.personaAtacar="";
       this.juego = new Juego(jugadores);
       this.juego.start();
       
        for (int i = 0; i < jugadores.size(); i++) {
            if (cs.getJugador().getNombreUsuario().equals(this.juego.getJugadores().get(i).getNombreUsuario())) {
                this.turnoJugadores = i;
            }
            
            
        }
       this.juego.setTurnoJugadores(this.turnoJugadores);
        
      this.avatars = new BufferedImage[4];
        this.avatars[0] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/Alien.png"));
        this.avatars[1] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/Angel.png"));
        this.avatars[2] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/Soldado.png"));
        this.avatars[3] = ImageIO.read(getClass().getResourceAsStream("/Imagenes/Vikingo.png"));
        
        this.juego.getJugadores().get(this.turnoJugadores).setImagen(this.avatars[this.juego.getJugadores().get(this.turnoJugadores).getNumImagenPerfil()]);

        this.setPreferredSize(new Dimension(800, 600));
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
        this.addMouseListener(this);

        this.campoJuego = ImageIO.read(getClass().getResourceAsStream("/Imagenes/madera.png"));
        init();
        Thread t=new Thread(this);
        t.start();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(this.campoJuego, 0, 0, null);

        //dibujos de jugador actual
        g.drawImage(this.juego.getJugadores().get(this.turnoJugadores).getImagen(), 720, 0, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("andale", Font.CENTER_BASELINE, 26));
        g.drawString(this.juego.getJugadores().get(this.turnoJugadores).getNombreUsuario(), 720, 100);
        g.setColor(Color.BLACK);
        g.setFont(new Font("andale", Font.CENTER_BASELINE, 20));
        g.drawString(String.valueOf(this.juego.getJugadores().get(this.turnoJugadores).getVida()), 720, 125);
        
        int multiplicador=0;
        for (int i = 0; i < this.juego.getJugadores().size(); i++) {
            if (i!=this.turnoJugadores) {
                g.setColor(Color.BLACK);
        g.setFont(new Font("andale", Font.CENTER_BASELINE, 26));
        g.drawString(this.juego.getJugadores().get(i).getNombreUsuario(), 10 + multiplicador * 150, 50);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("andale", Font.CENTER_BASELINE, 20));
        g.drawString(String.valueOf(this.juego.getJugadores().get(i).getVida()), 10 + multiplicador * 150, 75);
        multiplicador++;
            }
            
        }


        this.juego.dibujar(g);
        if (this.juego.getJugadores().get(this.turnoJugadores).getCartasSeleccionadas().size() == 3) {
            if (!this.juego.isTurnoTerminado()) {
                this.jbtnLimpiarformacion.setEnabled(true);
            }
            
        } else {
            this.jbtnLimpiarformacion.setEnabled(false);
        }
        repaint();
        
    }

    private void init() {
        
        this.jcbJugadores=new JComboBox<>();
        this.jcbJugadores.setBounds(600, 296, 150, 30);
        this.add(this.jcbJugadores);
        for (int i = 0; i < this.juego.getJugadores().size(); i++) {
            if (i!=this.turnoJugadores) {
                this.jcbJugadores.addItem(this.juego.getJugadores().get(i).getNombreUsuario());
            }
            
        }
        this.jcbJugadores.addItemListener( this);
        
        
        this.jbtnTurno = new JButton("Terminar turno");
        this.jbtnTurno.setBounds(600, 326, 150, 30);
        this.jbtnTurno.addActionListener(this);
        this.add(this.jbtnTurno);

        this.jbtnLimpiarformacion = new JButton("Limpiar formación");
        this.jbtnLimpiarformacion.setBounds(600, 376, 150, 30);
        this.jbtnLimpiarformacion.addActionListener(this);
        this.add(this.jbtnLimpiarformacion);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    
    //se utiliza para verificar que los clicks toquen las cartas y así darle la simulacion de movimiento.
    @Override
    public void mousePressed(MouseEvent e) {
        if (!this.juego.isTurnoTerminado()) {
            this.juego.getJugadores().get(this.turnoJugadores).tocarCarta(e.getX(), e.getY());
        }
          
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbtnLimpiarformacion) {
            this.juego.getJugadores().get(this.turnoJugadores).limpiarFormacion();
        }

        if (e.getSource() == this.jbtnTurno) {
            if (this.juego.getJugadores().get(this.turnoJugadores).getCartasSeleccionadas().size()<3) {
                JOptionPane.showMessageDialog(this, "debe de escoger las 3 cartas para finalizar su turno");
                return;
            }
            
                this.juego.setTurnoTerminado(true);
                GestionXML gestionXML=new GestionXML();
               ClienteSingleton cs=ClienteSingleton.getInstance();
               cs.enviarDatos(gestionXML.xmlToString(gestionXML.crearMensajeProtocolo("jugadorListo")));
                
             
                
                

            
        }

    }

  

    public void ganador(){
        Jugador jugador=this.juego.finalJuego();
                if (jugador!=null) {
                    JOptionPane.showMessageDialog(this, "WINNER: \n"+ jugador.getNombreUsuario()+"\n Lingotes de oro recibidos: 10");
                }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource()==this.jcbJugadores) {
            this.personaAtacar=(String) this.jcbJugadores.getSelectedItem();
            System.out.println(this.personaAtacar);
        }

    }

    @Override
    public void run() {
        while (true){
            if (this.juego.isTurnoTerminado()) {
                 this.jbtnLimpiarformacion.setEnabled(false);
                this.jbtnTurno.setEnabled(false);
                this.jcbJugadores.setEnabled(false);
            }else{
                this.jbtnTurno.setEnabled(true);
                this.jcbJugadores.setEnabled(true);
            }
        }

    }

    
}
