/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package batalladecaballerosclientediegorios.joshuarosales.iiiciclo2022;


import Domain.ClienteSingleton;
import Domain.Jugador;
import GUI.JFVentanaPrincipal;
import GUI.JPAreaJuego;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


/**
 *
 * @author Diego
 */
public class BatallaDeCaballerosClienteDiegoRiosJoshuaRosalesIIICiclo2022 {

    /**
     * @param args the command line arguments
     */
    //Se instancia la el JFrame del package GUI para que se haga visible
    public static void main(String[] args)   {
       
                    ClienteSingleton cliente=ClienteSingleton.getInstance();
        if (cliente!=null) {
             JFVentanaPrincipal jFVentanaPrincipal = new JFVentanaPrincipal();
        jFVentanaPrincipal.setLocation(250, 70);
        jFVentanaPrincipal.setVisible(true);
        }

    }
        

    

}//BatallaDeCaballerosClienteDiegoRiosJoshuaRosalesIIICiclo2022
