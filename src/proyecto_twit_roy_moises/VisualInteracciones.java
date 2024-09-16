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
        InicioDeInteracciones();
        mostrarInteracciones();
    }

    private void InicioDeInteracciones() {
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
                twitPane.setText(twit.getUsername() + " te menciono : \" " + twit.getContenido() + " \"  \nPublicado el " + twit.getFechapublicacion() + ".");

                StyledDocument doc = twitPane.getStyledDocument();
                Style hastagEstilo = twitPane.addStyle("hastagEstilo", null);
                Style mencionEstilo = twitPane.addStyle("mencionEstilo", null);

                StyleConstants.setForeground(hastagEstilo, new Color(30, 144, 255)); // azulito
                StyleConstants.setBold(mencionEstilo, true);
                StyleConstants.setForeground(mencionEstilo, Color.BLACK);

                String text = twitPane.getText();

                // Resaltar hashtags primero
                int index = 0;
                while (index < text.length()) {
                    int hashtagInicio = text.indexOf("#", index);
                    if (hashtagInicio == -1) {
                        break;
                    }

                    int hashtagFin = text.indexOf(" ", hashtagInicio);
                    if (hashtagFin == -1) {
                        hashtagFin = text.length();
                    }

                    doc.setCharacterAttributes(hashtagInicio, hashtagFin - hashtagInicio, hastagEstilo, false);
                    index = hashtagFin;
                }

                // Resaltar menciones despues
                // aqui se Reinicia el indx para resaltar menciones siuuu
                index = 0;
                while (index < text.length()) {
                    // resaltar menciones aqui xd
                    int MencionInicio = text.indexOf("@", index);
                    if (MencionInicio != -1) {
                        int MencionFin = text.indexOf(" ", MencionInicio);
                        if (MencionFin == -1) {
                            MencionFin = text.length();
                        }
                        String mencionadoExiste = text.substring(MencionInicio + 1, MencionFin); // substring para obtener el username del usuario.
                        if (UsuarioManager.usuarioExiste(mencionadoExiste)) {
                            doc.setCharacterAttributes(MencionInicio, MencionFin - MencionInicio, mencionEstilo, false); // aqui condicional para que si el usuario exite este se resaltara
                        }
                        index = MencionFin;
                    } else {
                        index = text.length();
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