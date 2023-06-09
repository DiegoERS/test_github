/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Domain.ClienteSingleton;
import Domain.Jugador;
import Utility.GestionXML;
import Utility.MyUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom.Element;

/**
 *
 * @author Diego
 */
public class JIFRegistroUsuario extends javax.swing.JInternalFrame implements Runnable {

    /**
     * Creates new form JIFRegistroUsuario
     */
    public static boolean hilo;
    private ClienteSingleton clienteSingleton;
    public JIFRegistroUsuario() {
        initComponents();
        Thread thread=new Thread(this);
        thread.start();
        this.clienteSingleton=ClienteSingleton.getInstance();
        hilo=true;
    }
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jbtnRegistrar = new javax.swing.JButton();
        jtfContrasenia = new javax.swing.JPasswordField();

        setClosable(true);
        setTitle("Registro de usuario");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nombre de usuario:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Registro de nuevo usuario");

        jtfNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Contraseña:");

        jbtnRegistrar.setText("Registrar");
        jbtnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegistrarActionPerformed(evt);
            }
        });

        jtfContrasenia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jbtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jtfContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jtfContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jbtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRegistrarActionPerformed
        // se encarga de enviar la informacion al servidor de la persona que se desea registrar.
        
        try {
            GestionXML gestionXML = new GestionXML();
            Element eProtocolo;
            try {
                eProtocolo = gestionXML.agregarJugador("guardar", new Jugador(this.jtfNombre.getText(),
                        MyUtil.obtenerContraseniaCifrada(String.valueOf(this.jtfContrasenia.getPassword()), MyUtil.MD2),
                        0,
                        0));
                this.clienteSingleton.enviarDatos(gestionXML.xmlToString(eProtocolo));
            } catch (IOException ex) {
                Logger.getLogger(JIFRegistroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(JIFRegistroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_jbtnRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbtnRegistrar;
    private javax.swing.JPasswordField jtfContrasenia;
    private javax.swing.JTextField jtfNombre;
    // End of variables declaration//GEN-END:variables

    
    //este hilo verifica que no exista nignuna persona guarda en el juego con el mismo nombre de usuario.
    @Override
    public void run() {
        ClienteSingleton cliente=ClienteSingleton.getInstance();
        while(hilo){
            
            try {
                if (cliente.isVerificado()) {
                    if (cliente.getRegistrado().equals("si")) {
                        JOptionPane.showMessageDialog(this, "Usuario registrado");
                        
                    }else{
                        JOptionPane.showMessageDialog(this, "Ya existe un usuario con ese nombre");
                    }
                    this.jtfNombre.setText("");
                    this.jtfContrasenia.setText("");
                    cliente.setRegistrado("no");
                    cliente.setVerificado(false);
                }
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JIFRegistroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
