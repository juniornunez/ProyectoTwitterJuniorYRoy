/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_twit_roy_moises;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.text.*;

/**
 *
 * @author royum
 */
public class MenuPrincipal {

    VisualInteracciones visual;
    UsuarioManager user;
    private static MenuPrincipal menu;
    private JFrame frame;
    private JTextPane tweetArea;
    private JLabel letrasContadasLabel;
    private JPanel espacioPanel;
    private Twits twits;
    private String UsuarioActual;

    public MenuPrincipal(String UsuarioActual) {
        this.UsuarioActual = UsuarioActual;
        this.twits = UsuarioManager.obtenerTwitsUsuario(UsuarioActual);
        if (this.twits == null) {
            this.twits = new Twits();
        }

        initUI();
        actualizarTimeline();
    }

    public static MenuPrincipal getMenu(String UsuarioActual) {
        if (menu == null) {
            menu = new MenuPrincipal(UsuarioActual);
        }
        return menu;
    }

    public void mostrarMenu() {
        if (frame != null) {
            frame.setVisible(true);
        }
    }

    private void initUI() {
        frame = new JFrame("Menu Principal");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        // Panel de botones de navegación
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1, 6, 10, 10)); // Seis botones en una fila
        navPanel.setBackground(Color.lightGray);

        JButton timelineButton = new JButton("Timeline");
        JButton tweetButton = new JButton("Mandar Tweet");
        JButton InteraccionesBoton = new JButton("Interacciones");
        JButton Editar = new JButton("Editar Perfil");
        JButton BuscarHash = new JButton("Buscar Hashtags");
        JButton logoutButton = new JButton("Cerrar Sesion");

        // Añadir botones al panel de navegación
        navPanel.add(timelineButton);
        navPanel.add(tweetButton);
        navPanel.add(InteraccionesBoton);
        navPanel.add(Editar);
        navPanel.add(BuscarHash);
        navPanel.add(logoutButton);

        // Panel de contenido
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.white);

        // Panel para escribir tweets
        JPanel tweetPanel = new JPanel(new BorderLayout());
        tweetPanel.setBorder(BorderFactory.createTitledBorder("Hola " + UsuarioActual + "! ¿En que estas pensando?"));
        tweetArea = new JTextPane(); // Cambiado a JTextPane
        tweetArea.setPreferredSize(new Dimension(400, 100));
        tweetPanel.add(new JScrollPane(tweetArea), BorderLayout.CENTER);

        // Contador de caracteres
        JPanel bottomPanel = new JPanel(new BorderLayout());
        letrasContadasLabel = new JLabel("0/140 caracteres");
        bottomPanel.add(letrasContadasLabel, BorderLayout.WEST);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton publishButton = new JButton("Publicar");
        buttonPanel.add(publishButton);

        bottomPanel.add(publishButton, BorderLayout.EAST);

        tweetPanel.add(bottomPanel, BorderLayout.SOUTH);

        contentPanel.add(tweetPanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Panel para mostrar los tweets
        espacioPanel = new JPanel();
        espacioPanel.setLayout(new BoxLayout(espacioPanel, BoxLayout.Y_AXIS));
        espacioPanel.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2));  // borde azulito
        JScrollPane timelineScrollPane = new JScrollPane(espacioPanel);
        contentPanel.add(timelineScrollPane, BorderLayout.CENTER);

        // Añadir paneles al panel principal
        mainPanel.add(navPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Configuracion de los botones de navegacin
        timelineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTimeline();
            }
        });

        tweetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mandarTweet();
            }
        });

        Editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                VisualEditar ventanaEditar = new VisualEditar(UsuarioActual);
                ventanaEditar.setVisible(true);

            }
        });

        BuscarHash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new BuscarHashtag(twits, MenuPrincipal.this);
            }
        });

        InteraccionesBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana de VisualInteracciones
                visual = new VisualInteracciones(UsuarioActual);
            }
        });

        publishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contenido = tweetArea.getText().trim();
                if (!contenido.isEmpty() && contenido.length() <= 140) {
                    twits.Publicartwit(UsuarioActual, contenido); // Se usa el nombre del usuario actual
                    JOptionPane.showMessageDialog(frame, "Tweet publicado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTimeline();
                    tweetArea.setText("");
                    letrasContadasLabel.setText("0/140 caracteres");
                } else if (contenido.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "El tweet no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "El tweet excede los 140 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                resaltarHashtags(); // Llamar al metodo para resaltar hashtags
                resaltarArroba(); // aqui llamamos a la funcion para que resalte el arroba
            }
        });

        tweetArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int contadorLetras = tweetArea.getText().length();
                letrasContadasLabel.setText(contadorLetras + "/140 caracteres");
                if (contadorLetras > 140) {
                    letrasContadasLabel.setForeground(Color.RED);
                } else {
                    letrasContadasLabel.setForeground(Color.BLACK);
                }
                resaltarHashtags(); // Resaltar hashtags en tiempo real
                resaltarArroba();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        frame.setVisible(true);
    }

    private void resaltarHashtags() {
        StyledDocument doc = tweetArea.getStyledDocument();
        Style style = tweetArea.addStyle("HashtagStyle", null);
        StyleConstants.setForeground(style, new Color(30, 144, 255)); // Azulito

        String text = tweetArea.getText();

        // Limpiar estilos anteriores
        doc.setCharacterAttributes(0, text.length(), tweetArea.getStyle("default"), true);

        // Resaltar hashtags con contains
        int index = 0;
        while (index < text.length()) {
            int hashtagStart = text.indexOf("#", index);
            if (hashtagStart == -1) {
                break;
            }

            int hashtagEnd = text.indexOf(" ", hashtagStart);
            if (hashtagEnd == -1) {
                hashtagEnd = text.length();
            }

            doc.setCharacterAttributes(hashtagStart, hashtagEnd - hashtagStart, style, false);
            index = hashtagEnd;
        }
    }

    private void resaltarArroba() {
        StyledDocument arro = tweetArea.getStyledDocument();
        Style negro = tweetArea.addStyle("default", null); // estilo normal
        Style boldStyle = tweetArea.addStyle("boldStyle", null); // aqui estilo en negritd
        StyleConstants.setBold(boldStyle, true); // aqui aplica negrita al estilo
        StyleConstants.setForeground(boldStyle, Color.BLACK);

        String text = tweetArea.getText();
        arro.setCharacterAttributes(0, text.length(), tweetArea.getStyle("default"), true); // aqui limpia esitlos anteriores

        int indice = 0;
        while (indice < text.length()) {
            int arrobaInicia = text.indexOf("@", indice);
            if (arrobaInicia == -1) {
                break;
            }

            int arrobaTermina = text.indexOf(" ", arrobaInicia);
            if (arrobaTermina == -1) {
                arrobaTermina = text.length();
            }

            String username = text.substring(arrobaInicia + 1, arrobaTermina); // aqui exraemos el nombre del usuario.

            // aqui llamamos a la clase de UsuarioMananger y buscar la funcion del UsuarioExiste para verificar si existe
            if (UsuarioManager.usuarioExiste(username)) {
                arro.setCharacterAttributes(arrobaInicia, arrobaTermina - arrobaInicia, boldStyle, false); // aqui aplicamos el negrita si el usuarioExiste
            }

            indice = arrobaTermina;

        }

    }

    private void mostrarTimeline() {
        actualizarTimeline();
    }

    private void mandarTweet() {
        tweetArea.requestFocus();
    }

    private void actualizarTimeline() {
        espacioPanel.removeAll();
        
        //espacioPanel.setLayout(new BoxLayout(espacioPanel,BoxLayout.Y_AXIS)); // borrar si es necesario

        for (int twi = 0; twi < twits.getNumeroTwits(); twi++) {
            Twit twit = twits.getTwits()[twi];

            // Crear JTextPane para el tweet
            JTextPane tweetPane = new JTextPane();
            tweetPane.setEditable(false);
            tweetPane.setText(twit.getUsername() + " escribio: “ " + twit.getContenido() + " ” |publicado el " + twit.getFechapublicacion() + "|.");

            // Establecer el estilo de documento para hashtags
            StyledDocument doc = tweetPane.getStyledDocument();
            Style hashtagEstilo = tweetPane.addStyle("hashtagEstilo", null);
            Style mencionEstilo = tweetPane.addStyle("mencionEstilo", null);

            StyleConstants.setForeground(hashtagEstilo, new Color(30, 144, 255)); // Azulito
            StyleConstants.setBold(mencionEstilo, true);
            StyleConstants.setForeground(mencionEstilo, Color.BLACK);

            // aqui resaltar hashtags en el tweet y las menciones el timeLine
            String text = tweetPane.getText();
            int index = 0;
            while (index < text.length()) {
                int HashTagInicio = text.indexOf("#", index);
                if (HashTagInicio != -1) {
                    int HashTagFin = text.indexOf(" ", HashTagInicio);
                    if (HashTagFin == -1) {
                        HashTagFin = text.length();
                    }
                    doc.setCharacterAttributes(HashTagInicio, HashTagFin - HashTagInicio, hashtagEstilo, false);
                    index = HashTagFin;
                } else {
                    index = text.length();
                }
            }

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
                    doc.setCharacterAttributes(MencionInicio, MencionFin - MencionInicio, mencionEstilo, false);
                    index = MencionFin;
                } else {
                    index = text.length();
                }
            }

            tweetPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            espacioPanel.add(tweetPane);
            
           // espacioPanel.add(Box.createRigidArea(new Dimension(0,5)));// borrar si es necesario
        }

        espacioPanel.revalidate();
        espacioPanel.repaint();
    }

    private void cerrarSesion() { 
        new LogIn();
        frame.dispose();
    }

    

}
