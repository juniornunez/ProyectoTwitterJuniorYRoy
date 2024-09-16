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
    private String[] hashtagExistente = new String[100]; // solo se podran guardar 100 hashtag
    private int numHashatgExistente = 0;

    public MenuPrincipal(String UsuarioActual) {
        this.UsuarioActual = UsuarioActual;
        this.twits = UsuarioManager.obtenerTwitsUsuario(UsuarioActual);
        if (this.twits == null) {
            this.twits = new Twits();
        }

        inicioDelUsuario();
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

    private void inicioDelUsuario() {
        frame = new JFrame("Menu Principal");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        // Panel de botones de navegacion
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1, 6, 10, 10)); // Seis botones en una fila
        navPanel.setBackground(Color.lightGray);

        JButton InteraccionesBoton = new JButton("Interacciones");
        JButton Editar = new JButton("Editar Perfil");
        JButton BuscarHash = new JButton("Buscar Hashtags");
        JButton logoutButton = new JButton("Cerrar Sesion");

        // Añadir botones al panel de navegacion
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
        JButton publicarboton = new JButton("Publicar");
        buttonPanel.add(publicarboton);

        bottomPanel.add(publicarboton, BorderLayout.EAST);

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
        Editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana MiPerfil con los datos del usuario actual
                MiPerfil miPerfilVentana = new MiPerfil(UsuarioActual);  // Pasar el nombre de usuario actual
                miPerfilVentana.setVisible(true);
                frame.setVisible(false);  // Cerrar la ventana actual

            }
        });

        BuscarHash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BuscarHashtag buscarHashtag = new BuscarHashtag(twits, MenuPrincipal.this);
            }
        });

        InteraccionesBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana de VisualInteracciones
                visual = new VisualInteracciones(UsuarioActual);
            }
        });

        publicarboton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contenido = tweetArea.getText().trim();
                if (!contenido.isEmpty() && contenido.length() <= 140) {
                    // al publicar el tweet, se agrega el hashtag al arreglo existente
                    agregarHashtagsExistente(contenido);

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
                resaltarHashtags();
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
                resaltarHashtags(); // Resaltar hashtags 
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

    private void agregarHashtagsExistente(String contenido) {
        int longitud = contenido.length();
        StringBuilder palabraActual = new StringBuilder();
        boolean esHashtag = false;

        for (int i = 0; i < longitud; i++) {
            char caracterActual = contenido.charAt(i);

            if (caracterActual == '#') {
                esHashtag = true;
            } else if (esHashtag && (caracterActual == ' ' || i == longitud - 1)) {
                if (i == longitud - 1 && caracterActual != ' ') {
                    palabraActual.append(caracterActual);
                }

                String hashtag = palabraActual.toString();
                if (!hashtagYaExiste(hashtag) && !hashtag.isEmpty()) {
                    if (numHashatgExistente < hashtagExistente.length) {
                        hashtagExistente[numHashatgExistente] = hashtag;
                        numHashatgExistente++;
                        System.out.println("Hashtag agregado : " + hashtag);
                    }
                }
                esHashtag = false;
                palabraActual.setLength(0);
            } else if (esHashtag) {
                palabraActual.append(caracterActual);
            }
        }
    }

    // funcion para verificar el si hashtagYaExiste
    private boolean hashtagYaExiste(String hashtag) {
        for (int buscarHash = 0; buscarHash < numHashatgExistente; buscarHash++) {
            // Verificar si el hashtag existe sin el símbolo '#'
            if (hashtagExistente[buscarHash] != null && hashtagExistente[buscarHash].equals(hashtag)) {
                return true;
            }
        }
        return false;
    }

    private void resaltarHashtags() {
        StyledDocument doc = tweetArea.getStyledDocument();

        // Crear un estilo por defecto si no existe
        Style defaultStyle = tweetArea.getStyle("default");
        if (defaultStyle == null) {
            defaultStyle = tweetArea.addStyle("default", null);
        }

        // Crear el estilo para los hashtags
        Style hashtagStyle = tweetArea.getStyle("HashtagStyle");
        if (hashtagStyle == null) {
            hashtagStyle = tweetArea.addStyle("HashtagStyle", null);
            StyleConstants.setBold(hashtagStyle, true);
            StyleConstants.setForeground(hashtagStyle, new Color(30, 144, 255)); // Azulito
        }

        String text = tweetArea.getText();

        // Limpia estilos anteriores (esto es importante para que no se acumulen los estilos)
        doc.setCharacterAttributes(0, text.length(), defaultStyle, true);

        int index = 0;
        int longitud = text.length();

        while (index < longitud) {
            int hashtagInicia = text.indexOf("#", index);
            if (hashtagInicia == -1) {
                break; // No hay más hashtags en el texto
            }

            // Encontrar el final del hashtag (espacio o fin del texto)
            int hashtagTermina = hashtagInicia + 1;
            while (hashtagTermina < longitud && !Character.isWhitespace(text.charAt(hashtagTermina))) {
                hashtagTermina++;
            }

            // Extraer la palabra del hashtag sin el #
            String hashtag = text.substring(hashtagInicia + 1, hashtagTermina);

            // Verificar si la palabra existe en el arreglo sin el símbolo #
            if (hashtagYaExiste(hashtag)) {
                // Resaltar con azul si el hashtag existe
                doc.setCharacterAttributes(hashtagInicia, hashtagTermina - hashtagInicia, hashtagStyle, false);
            }

            // Mover al siguiente índice después del hashtag actual
            index = hashtagTermina;
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

            String username = text.substring(arrobaInicia + 1, arrobaTermina); // aqui exraemos el nombre del usuario, se le suma 1 para que extraiga desde su nombre y no cuenta el @ y comienze desde el caracter.

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

    public void actualizarTimeline() {
        espacioPanel.removeAll();

        //espacioPanel.setLayout(new BoxLayout(espacioPanel,BoxLayout.Y_AXIS)); // borrar si es necesario
        for (int twi = 0; twi < twits.getNumeroTwits(); twi++) {
            Twit twit = twits.getTwits()[twi];

            // Crear JTextPane para el tweet
            JTextPane tweetPane = new JTextPane();
            tweetPane.setEditable(false);
            tweetPane.setText(twit.getUsername() + " escribio: “ " + twit.getContenido() + " ” \npublicado el " + twit.getFechapublicacion() + ".\n-------------------------------------------------------------------------------------------------------");
            //-------------------------------------------------
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

                    // aqui extraemos el hashtag
                    String CreaHashtag = text.substring(HashTagInicio + 1, HashTagFin);

                    // y aqui se añade
                    agregarHashtagsExistente(CreaHashtag);

                    // con esto se resaltara
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
                    String mencionadoExiste = text.substring(MencionInicio + 1, MencionFin); // substring para obtener el username del usuario.
                    if (UsuarioManager.usuarioExiste(mencionadoExiste)) {
                        doc.setCharacterAttributes(MencionInicio, MencionFin - MencionInicio, mencionEstilo, false); // aqui condicional para que si el usuario exite este se resaltara
                    }
                    index = MencionFin;
                } else {
                    index = text.length();
                }
            }

            tweetPane.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            espacioPanel.add(tweetPane);

            // espacioPanel.add(Box.createRigidArea(new Dimension(0,5)));// borrar si es necesario
        }

        espacioPanel.revalidate();
        espacioPanel.repaint();
    }

    private void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(frame, "¿Estas seguro de que deseas cerrar sesion?",
                "Confirmar cierre de sesión", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            LogIn logIn = new LogIn();
            frame.dispose();
        }
    }

}