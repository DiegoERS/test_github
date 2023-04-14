package GUI;

import Domain.ClienteSingleton;
import Domain.Jugador;
import Utility.GestionXML;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JFchat extends javax.swing.JFrame implements Runnable {

    private Jugador jugador;
    private int jugadorIndice;
    private String jugadorENviar;
    private ArrayList<Jugador> jugadores;

    public JFchat(Jugador jugador) {

        this.jugadores=new ArrayList<>();
        this.jugador = jugador;
        this.jugadorIndice = -1;
        this.jugadorENviar = "";

        initComponents();

        this.getRootPane().setDefaultButton(this.btnEnviar);
        Thread t = new Thread(this);
        t.start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaTexto = new javax.swing.JTextArea();
        jtfTextoEnviar = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaTextoPrivado = new javax.swing.JTextArea();
        jtfTextoPrivado = new javax.swing.JTextField();
        btnEnviarPrivado = new javax.swing.JButton();
        jcbJugadores = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Chat");

        jtaTexto.setEditable(false);
        jtaTexto.setColumns(20);
        jtaTexto.setRows(5);
        jScrollPane1.setViewportView(jtaTexto);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jtfTextoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfTextoEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Chat General", jPanel1);

        jtaTextoPrivado.setEditable(false);
        jtaTextoPrivado.setColumns(20);
        jtaTextoPrivado.setRows(5);
        jScrollPane2.setViewportView(jtaTextoPrivado);

        btnEnviarPrivado.setText("Enviar");
        btnEnviarPrivado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarPrivadoActionPerformed(evt);
            }
        });

        jcbJugadores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbJugadoresItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jtfTextoPrivado, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviarPrivado, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfTextoPrivado, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnEnviarPrivado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Chat privado", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
    //envia mensajes al servidor para que los envie a todos y funcione como chat general.
        ClienteSingleton cs = ClienteSingleton.getInstance();
        GestionXML gestionXML = new GestionXML();

        String mensaje = this.jugador.getNombreUsuario() + ": " + this.jtfTextoEnviar.getText() + "\n";
        String envio = gestionXML.xmlToString(gestionXML.agregarAccionSimple("chat", mensaje));

        cs.enviarDatos(envio);
        this.jtfTextoEnviar.setText("");


    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnEnviarPrivadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarPrivadoActionPerformed
       //envia un mensaje al servidor para que envie el mensaje deseado a la persona seleccionada mediante el chat privado.
        ClienteSingleton cs = ClienteSingleton.getInstance();
        GestionXML gestionXML = new GestionXML();

        String mensaje = this.jugador.getNombreUsuario() + ": " + this.jtfTextoPrivado.getText() + "\n";
        String envio = gestionXML.xmlToString(gestionXML.agregarChatPrivado("chatPrivado", mensaje, this.jugadorENviar));
        this.jtaTextoPrivado.append(this.jugador.getNombreUsuario()+": "+this.jtfTextoPrivado.getText() + "\n");
        cs.enviarDatos(envio);
        this.jtfTextoPrivado.setText("");
    }//GEN-LAST:event_btnEnviarPrivadoActionPerformed

    private void jcbJugadoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbJugadoresItemStateChanged
        if (evt.getSource() == this.jcbJugadores) {
            this.jugadorENviar = (String) this.jcbJugadores.getSelectedItem();
        }
    }//GEN-LAST:event_jcbJugadoresItemStateChanged

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnEnviarPrivado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcbJugadores;
    private javax.swing.JTextArea jtaTexto;
    private javax.swing.JTextArea jtaTextoPrivado;
    private javax.swing.JTextField jtfTextoEnviar;
    private javax.swing.JTextField jtfTextoPrivado;
    // End of variables declaration//GEN-END:variables

    
    //Este hilo se encarga de actualizar el are de texto cada vez que se recibe un mensaje de otro jugador.
    @Override
    public void run() {
        ClienteSingleton cs = ClienteSingleton.getInstance();
        while (true) {
            try {
                if (cs.isPrueba()) {

                    this.jtaTexto.append(cs.getChat() + "\n");
                    cs.setPrueba(false);
                }
                if (cs.isMensajePrivado()) {
                    this.jtaTextoPrivado.append(cs.getChatPrivado()+"\n");
                    cs.setMensajePrivado(false);
                }
                
                if (cs.isCombobox()) {
                    this.jcbJugadores.removeAllItems();
                    this.jugadores.clear();
                    this.jugadores = cs.getEspectadores();
                    for (int i = 0; i < cs.getEspectadores().size(); i++) {
                        if (cs.getJugador().getNombreUsuario().equals(this.jugadores.get(i).getNombreUsuario())) {

                            this.jugadorIndice = i;
                        }
                    }
                    for (int i = 0; i < this.jugadores.size(); i++) {
                        if (i != this.jugadorIndice) {
                            this.jcbJugadores.addItem(this.jugadores.get(i).getNombreUsuario());
                        }

                    }
                    cs.setCombobox(false);
                }

                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JFchat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
