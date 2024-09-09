/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_twit_roy_moises;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author royum
 */
public class BuscarHashtag {

    private JTextField campoHashtag;
    private JButton botonBuscar;
    private JButton botonRegresar;
    private JPanel panelResultados;
    private Twits todosLosTwits;
    private MenuPrincipal menu;

    public BuscarHashtag(Twits twits, MenuPrincipal menu) {
        this.todosLosTwits = twits;
        this.menu = menu;
        initUI();
    }

    private void initUI() {
        JFrame frame = new JFrame("Buscar Hashtag");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);

        campoHashtag = new JTextField(20);
        botonBuscar = new JButton("Buscar");
        panelResultados = new JPanel();
        panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS)); // Cambiar a Y_AXIS

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarHashtag();
            }
        });

        botonRegresar = new JButton("Regresar");

        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                menu.mostrarMenu();
            }
        });

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.add(new JLabel("Hashtag:"));
        panelBusqueda.add(campoHashtag);
        panelBusqueda.add(botonBuscar);
        panelBusqueda.add(botonRegresar);

        frame.getContentPane().add(panelBusqueda, "North");
        frame.getContentPane().add(new JScrollPane(panelResultados), "Center");

        frame.setVisible(true);
    }

    private void buscarHashtag() {
        String hashtagBuscado = campoHashtag.getText().trim();
        if (!hashtagBuscado.isEmpty()) {
            HashTag buscador = new HashTag(hashtagBuscado);
            Twit[] resultados = buscador.buscarTwitsConHashTag();

            panelResultados.removeAll();

            for (Twit twit : resultados) {
                if (twit != null) {
                    JTextPane tweetPane = new JTextPane();
                    tweetPane.setEditable(false);
                    tweetPane.setText(twit.getUsername() + " escribio: “ " + twit.getContenido() + " ” el " + twit.getFechapublicacion());
                    tweetPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    StyledDocument doc = tweetPane.getStyledDocument();
                    Style hashtagEstilo = tweetPane.addStyle("hastagEstilo", null);
                    Style MencionesEstilo = tweetPane.addStyle("MencionEstilo", null);

                    StyleConstants.setForeground(hashtagEstilo, new Color(30, 144, 255));// azulito
                    StyleConstants.setBold(MencionesEstilo, true);
                    StyleConstants.setForeground(MencionesEstilo, Color.BLACK);

                    // Resaltar hashtags
                    String text = tweetPane.getText();
                    int index = 0;
                    while (index < text.length()) {
                        int hashTagInicio = text.indexOf("#", index);
                        if (hashTagInicio != -1) {
                            int hashTagFin = text.indexOf(" ", hashTagInicio);
                            if (hashTagFin == -1) {
                                hashTagFin = text.length();
                            }
                            doc.setCharacterAttributes(hashTagInicio, hashTagFin - hashTagInicio, hashtagEstilo, false);
                            index = hashTagFin;
                        } else {
                            index = text.length();
                        }
                    }

                    // Resaltar menciones (@)
                    index = 0;
                    while (index < text.length()) {
                        int mencionInicio = text.indexOf("@", index);
                        if (mencionInicio != -1) {
                            int mencionFin = text.indexOf(" ", mencionInicio);
                            if (mencionFin == -1) {
                                mencionFin = text.length();
                            }
                            doc.setCharacterAttributes(mencionInicio, mencionFin - mencionInicio, MencionesEstilo, false);
                            index = mencionFin;
                        } else {
                            index = text.length();
                        }
                    }

                    panelResultados.add(tweetPane);
                }
            }

            panelResultados.revalidate();
            panelResultados.repaint();
        }
    }

}
