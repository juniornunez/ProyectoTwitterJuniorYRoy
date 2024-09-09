/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_twit_roy_moises;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author royum
 */
public class VisualInteracciones {

    private JFrame frame;
    private JPanel panelInteracciones;
    private String UsuarioActual;

    public VisualInteracciones(String UsuarioActual) {
        this.UsuarioActual = UsuarioActual;
        initUI();
        mostrarInteracciones();
    }

    private void initUI() {
        frame = new JFrame("Interacciones");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panelInteracciones = new JPanel();
        panelInteracciones.setLayout(new BoxLayout(panelInteracciones, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(panelInteracciones);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton regresarButton = new JButton("Regresar");
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar la ventana actual
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(regresarButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void mostrarInteracciones() {
        boolean hayMenciones = false;
        Twit[] todosLosTwits = GestorDeTwits.obtenerTodoLosTwits();

        for (int mostrar = 0; mostrar < todosLosTwits.length; mostrar++) {
            Twit twit = todosLosTwits[mostrar];
            if (contieneMencion(twit.getContenido(), UsuarioActual)) {
                hayMenciones = true;
                
                JTextPane twitPane = new JTextPane();
                twitPane.setEditable(false);
                twitPane.setText(twit.getUsername() + " te menciono : \"" + twit.getContenido() + "\" | Publicado el " + twit.getFechapublicacion() + ".");
                
                StyledDocument doc  =twitPane.getStyledDocument();
                Style hastagEstilo = twitPane.addStyle("hastagEstilo", null);
                Style MencionEstilo = twitPane.addStyle("MencionEstilo", null);
                
                StyleConstants.setForeground(hastagEstilo, new Color(30,144,255));// azulito
                StyleConstants.setBold(MencionEstilo, true);
                StyleConstants.setForeground(MencionEstilo, Color.BLACK);
                
                
                
                String text = twitPane.getText();
                int index = 0;
                
                // aqui se resaltaran los hashtags
                    while(index < text.length()){
                        int HashtagInicia  = text.indexOf("#",index);
                        if(HashtagInicia == -1){
                            break;
                        }
                        
                        int HashTagFin = text.indexOf(" ",HashtagInicia);
                        if(HashTagFin == -1){
                            HashTagFin = text.length();
                        }
                        doc.setCharacterAttributes(HashtagInicia , HashTagFin - HashtagInicia, hastagEstilo, false);
                        index = HashTagFin;
                        
                        
                        // aqui resaltamos para las menciones
                        index = 0;
                        while(index < text.length()){
                            int MencionesInicia = text.indexOf("@",index);
                            if(MencionesInicia == -1){
                                break;
                            }
                            
                            int MencionFinaliza = text.indexOf(" ",MencionesInicia);
                            if(MencionFinaliza == -1){
                                MencionFinaliza = text.length();
                            }
                            doc.setCharacterAttributes(MencionesInicia, MencionFinaliza - MencionesInicia, MencionEstilo, false);
                            index = MencionFinaliza;
                        }
                    }
                
                twitPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panelInteracciones.add(twitPane);
            }
            panelInteracciones.revalidate();
            panelInteracciones.repaint();
        }

        if (!hayMenciones) {
            JLabel mensaje = new JLabel("Lo sentimos, aun no has sido mencionado.");
            mensaje.setHorizontalAlignment(SwingConstants.CENTER);
            panelInteracciones.add(mensaje);
        }

        panelInteracciones.revalidate();
        panelInteracciones.repaint();
    }

    private boolean contieneMencion(String contenido, String usuario) {
        String mencion = "@" + usuario;
        return contenido.contains(mencion);
    }

}
