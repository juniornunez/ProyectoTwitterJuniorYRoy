package proyecto_twit_roy_moises;

import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author royum
 */
public class PerfilVisual extends javax.swing.JFrame {

    private String usuarioActual;
    private String username;  // El usuario cuyo perfil estamos viendo
    manejoPerfil mane;
    UsuarioManager user;
    private MenuPrincipal menu;
    private MiPerfil miPerfil;
    /**
     * Creates new form PerfilVisual
     *
     * @param usuarioActual El nombre del usuario que está usando la aplicación
     * @param username El nombre del usuario cuyo perfil estamos viendo
     */
    public PerfilVisual(String usuarioActual, String username, MiPerfil miPerfil) {
    this.usuarioActual = usuarioActual;
    this.username = username;
    this.miPerfil = miPerfil;  // Guardar la referencia de MiPerfil para poder actualizarla
    initComponents();
    this.setLocationRelativeTo(null);
    cargarDatosPerfil(); // Llamar al método para cargar los datos del perfil
}

    /**
     * Método para cargar los datos del perfil del usuario seleccionado
     */
    private void cargarDatosPerfil() {
        // Obtener el índice del usuario cuyo perfil estamos viendo
        int index = UsuarioManager.obtenerIndiceUsuario(username);

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Error: El usuario no fue encontrado.");
            return;
        }

        // Obtener datos del usuario
        String nombreCompleto = UsuarioManager.getNombres()[index];
        String edad = UsuarioManager.getEdades()[index];
        int numeroSeguidores = manejoPerfil.getNumFollowers()[index];
        int numeroSiguiendo = manejoPerfil.getNumFollowing()[index];
        String fechaIngreso = UsuarioManager.getFechasIngreso()[index].getTime().toString();

        // Mostrar los datos en los labels correspondientes
        NombrePerfil.setText("Nombre: " + nombreCompleto);
        UsernamePerfil.setText("@" + username);
        Edad.setText(edad + " años");
        Numero_Twetts.setText(String.valueOf(UsuarioManager.obtenerTwitsUsuario(username).getNumeroTwits()));
        Numero_Seguidores.setText(String.valueOf(numeroSeguidores));
        Numero_Siguiendo.setText(String.valueOf(numeroSiguiendo));
        Fecha_Ingreso.setText("Fecha de Ingreso: " + fechaIngreso);

        // Cargar la imagen del perfil según el género del usuario
        cargarImagenPerfil(username);

        // Aquí obtenemos y mostramos los tweets del usuario
        String tweetsUsuario = UsuarioManager.verTwettsUsuario(username);  // Este método debería devolver los tweets del usuario
        Tweets_Usuario.setText(tweetsUsuario);  // Mostramos los tweets en el área de texto
        Tweets_Usuario.setEditable(false);

        // Actualizar el texto del botón según si seguimos o no al usuario
        actualizarBotonSeguir(username);
    }
    /**
     * Método para cargar la imagen del perfil basado en el género
     */
    private void cargarImagenPerfil(String username) {
        int index = UsuarioManager.obtenerIndiceUsuario(username);

        // Verifica si el índice es válido
        if (index != -1) {
            // Obtener el género del usuario desde el arreglo 'generos'
            String genero = UsuarioManager.getGeneros()[index];

            // Cargar la imagen adecuada según el género
            String imagePath;
            if ("masculino".equalsIgnoreCase(genero)) {
                imagePath = "/imagenes/Masculino.jpg"; // Ruta relativa para imagen masculina
            } else if ("femenino".equalsIgnoreCase(genero)) {
                imagePath = "/imagenes/Femenino.jpg"; // Ruta relativa para imagen femenina
            } else {
                imagePath = "/imagenes/mini.png"; // Ruta relativa para imagen genérica
            }

            // Cargar la imagen y ajustarla al tamaño del JLabel
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(imagen_perfil.getWidth(), imagen_perfil.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            
            // Establecer la imagen en el JLabel
            imagen_perfil.setIcon(scaledIcon);
        } else {
            // Mensaje de error en caso de que el usuario no exista
            System.out.println("Usuario no encontrado");
        }
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtTweets = new javax.swing.JLabel();
        Tweets_Usuario = new java.awt.TextArea();
        NombrePerfil = new javax.swing.JLabel();
        txtxtwits = new javax.swing.JLabel();
        Numero_Twetts = new javax.swing.JLabel();
        SIGUIENDO = new javax.swing.JLabel();
        Numero_Siguiendo = new javax.swing.JLabel();
        SEGUIDORES = new javax.swing.JLabel();
        Numero_Seguidores = new javax.swing.JLabel();
        SEGUIR_NOSEGUIR = new javax.swing.JButton();
        UsernamePerfil = new javax.swing.JLabel();
        Edad = new javax.swing.JLabel();
        Fecha_Ingreso = new javax.swing.JLabel();
        REGRESARMENU = new javax.swing.JButton();
        imagen_perfil = new javax.swing.JLabel();
        volverperfilbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtTweets.setText("Twits del usuario");

        NombrePerfil.setText("AQUI NOMBRE COMPLETO");

        txtxtwits.setForeground(new java.awt.Color(102, 102, 102));
        txtxtwits.setText("Tweets");

        Numero_Twetts.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Numero_Twetts.setText("0");

        SIGUIENDO.setForeground(new java.awt.Color(102, 102, 102));
        SIGUIENDO.setText("Siguiendo");

        Numero_Siguiendo.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Numero_Siguiendo.setText("0");

        SEGUIDORES.setForeground(new java.awt.Color(102, 102, 102));
        SEGUIDORES.setText("Seguidores");

        Numero_Seguidores.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Numero_Seguidores.setText("0");

        SEGUIR_NOSEGUIR.setBackground(new java.awt.Color(0, 204, 153));
        SEGUIR_NOSEGUIR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        SEGUIR_NOSEGUIR.setForeground(new java.awt.Color(255, 255, 255));
        SEGUIR_NOSEGUIR.setText("Seguir");
        SEGUIR_NOSEGUIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEGUIR_NOSEGUIRActionPerformed(evt);
            }
        });

        UsernamePerfil.setForeground(new java.awt.Color(102, 102, 102));
        UsernamePerfil.setText("@AQUI NOMBRE USURIO");

        Edad.setText("18");

        Fecha_Ingreso.setText("jLabel1");

        REGRESARMENU.setText("Regresar Menu Principal");
        REGRESARMENU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGRESARMENUActionPerformed(evt);
            }
        });

        volverperfilbtn.setText("Volver a tu perfil");
        volverperfilbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverperfilbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Tweets_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(UsernamePerfil)
                                    .addComponent(NombrePerfil)
                                    .addComponent(imagen_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addComponent(Numero_Twetts, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Numero_Siguiendo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(90, 90, 90)
                                        .addComponent(Numero_Seguidores, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(txtxtwits, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(SIGUIENDO)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SEGUIDORES)))
                                .addGap(28, 28, 28))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Fecha_Ingreso)
                                    .addComponent(Edad)
                                    .addComponent(txtTweets))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(SEGUIR_NOSEGUIR, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(REGRESARMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(volverperfilbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(28, 28, 28)))))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtxtwits, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SIGUIENDO)
                                    .addComponent(SEGUIDORES))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Numero_Siguiendo)
                                        .addComponent(Numero_Seguidores))
                                    .addComponent(Numero_Twetts, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(imagen_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(NombrePerfil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UsernamePerfil)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(SEGUIR_NOSEGUIR)
                        .addGap(99, 99, 99)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Edad)
                        .addGap(18, 18, 18)
                        .addComponent(Fecha_Ingreso)
                        .addGap(33, 33, 33)
                        .addComponent(txtTweets)
                        .addGap(22, 22, 22)
                        .addComponent(Tweets_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(volverperfilbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(REGRESARMENU)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 private void actualizarBotonSeguir(String username) {
        if (manejoPerfil.sigueUsuario(usuarioActual, username)) {
            SEGUIR_NOSEGUIR.setText("Dejar de seguir");
        } else {
            SEGUIR_NOSEGUIR.setText("Seguir");
        }
    }
 public void actualizarSeguidoresYSeguidos() {
    // Obtener el índice del usuario cuyo perfil estamos viendo
    int index = UsuarioManager.obtenerIndiceUsuario(username);

    if (index != -1) {
        // Obtener el número de seguidores y seguidos
        int numSeguidores = manejoPerfil.getNumFollowers()[index];
        int numSiguiendo = manejoPerfil.getNumFollowing()[index];

        // Actualizar los JLabel en la interfaz
        Numero_Seguidores.setText("" + numSeguidores);  // Convertir a String usando concatenación
        Numero_Siguiendo.setText("" + numSiguiendo);    // Convertir a String usando concatenación
    } else {
        // Manejar el caso donde el índice no es válido
        JOptionPane.showMessageDialog(this, "Error al obtener los datos del usuario.");
    }
}
    private void SEGUIR_NOSEGUIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEGUIR_NOSEGUIRActionPerformed
        // Actualizar el perfil una vez que el usuario sigue o deja de seguir a otro
    miPerfil.actualizarSeguidoresYSeguidosMiPerfil();  // Asegúrate de actualizar los contadores visuales

    if (manejoPerfil.sigueUsuario(usuarioActual, username)) {
        // Confirmación antes de dejar de seguir
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Quieres dejar de seguir a " + username + "?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Dejar de seguir al usuario
            manejoPerfil.dejarDeSeguirUsuario(usuarioActual, username);

            // *** Eliminar los tweets del usuario dejado de seguir ***
            Twits twitsSeguido = UsuarioManager.obtenerTwitsUsuario(username);
            if (twitsSeguido != null) {
                UsuarioManager.obtenerTwitsUsuario(usuarioActual).eliminarTwitsDeSeguido(username);
            }

            // Actualizar el timeline para reflejar los cambios
            MenuPrincipal menu = MenuPrincipal.getMenu(usuarioActual);
            menu.actualizarTimeline();  // Refrescar el timeline después de dejar de seguir

            // Actualizar botones y contadores visuales
            actualizarBotonSeguir(username);
            miPerfil.actualizarListaUsuariosSeguidos(); // Actualizar lista de seguidos en MiPerfil
            actualizarSeguidoresYSeguidos();  // Actualizar seguidores y seguidos en PerfilVisual
        }
    } else {
        // Seguir al usuario
        manejoPerfil.seguirUsuario(usuarioActual, username);

        // Agregar los tweets del usuario seguido al timeline
        Twits twitsSeguido = UsuarioManager.obtenerTwitsUsuario(username);
        if (twitsSeguido != null) {
            UsuarioManager.obtenerTwitsUsuario(usuarioActual).agregarTwitsDeSeguido(twitsSeguido);
        }

        // Actualizar el timeline para reflejar los cambios
        MenuPrincipal menu = MenuPrincipal.getMenu(usuarioActual);
        menu.actualizarTimeline();  // Refrescar el timeline después de seguir

        // Actualizar botones y contadores visuales
        actualizarBotonSeguir(username);
        miPerfil.actualizarListaUsuariosSeguidos(); // Actualizar lista de seguidos en MiPerfil
        actualizarSeguidoresYSeguidos();  // Actualizar seguidores y seguidos en PerfilVisual
    }
    }//GEN-LAST:event_SEGUIR_NOSEGUIRActionPerformed

    private void REGRESARMENUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REGRESARMENUActionPerformed
       
       // Asegurarse de usar la misma instancia del menú principal
    MenuPrincipal menu = MenuPrincipal.getMenu(usuarioActual);
    menu.mostrarMenu();  // Mostrar la ventana del menú principal
    this.dispose();  // Cerrar solo la ventana actual
        
      
    }//GEN-LAST:event_REGRESARMENUActionPerformed

    private void volverperfilbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverperfilbtnActionPerformed

        
        MiPerfil perfilUsuario = new MiPerfil(usuarioActual); 
        perfilUsuario.setVisible(true); // 

        dispose();
    }//GEN-LAST:event_volverperfilbtnActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PerfilVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PerfilVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PerfilVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PerfilVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Edad;
    private javax.swing.JLabel Fecha_Ingreso;
    private javax.swing.JLabel NombrePerfil;
    private javax.swing.JLabel Numero_Seguidores;
    private javax.swing.JLabel Numero_Siguiendo;
    private javax.swing.JLabel Numero_Twetts;
    private javax.swing.JButton REGRESARMENU;
    private javax.swing.JLabel SEGUIDORES;
    private javax.swing.JButton SEGUIR_NOSEGUIR;
    private javax.swing.JLabel SIGUIENDO;
    private java.awt.TextArea Tweets_Usuario;
    private javax.swing.JLabel UsernamePerfil;
    private javax.swing.JLabel imagen_perfil;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtTweets;
    private javax.swing.JLabel txtxtwits;
    private javax.swing.JButton volverperfilbtn;
    // End of variables declaration//GEN-END:variables
}
//hola inge lo quiero mucho