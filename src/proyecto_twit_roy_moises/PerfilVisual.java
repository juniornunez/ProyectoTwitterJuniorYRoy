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

    /**
     * Creates new form PerfilVisual
     *
     * @param usuarioActual El nombre del usuario que está usando la aplicación
     * @param username El nombre del usuario cuyo perfil estamos viendo
     */
    public PerfilVisual(String usuarioActual,String username) {
        this.usuarioActual = usuarioActual;
        this.username = username;
        
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

    // Ver si el usuario actual sigue al usuario del perfil
    if (manejoPerfil.sigueUsuario(usuarioActual, username)) {
        SEGUIR_NOSEGUIR.setText("Dejar de seguir");
        TESIGUE_NOTESIGUE.setText("Te sigue");
    } else {
        SEGUIR_NOSEGUIR.setText("Seguir");
        TESIGUE_NOTESIGUE.setText("No te sigue");
    }

    // Aquí obtenemos y mostramos los tweets del usuario
    String tweetsUsuario = UsuarioManager.verTwettsUsuario(username);  // Este método debería devolver los tweets del usuario
    Tweets_Usuario.setText(tweetsUsuario);  // Mostramos los tweets en el área de texto

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
        TESIGUE_NOTESIGUE = new javax.swing.JLabel();
        Edad = new javax.swing.JLabel();
        Fecha_Ingreso = new javax.swing.JLabel();
        REGRESARMENU = new javax.swing.JButton();
        imagen_perfil = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtTweets.setText("Twits del usuario");

        NombrePerfil.setText("AQUI NOMBRE COMPLETO");

        txtxtwits.setForeground(new java.awt.Color(102, 102, 102));
        txtxtwits.setText("Tweets");

        Numero_Twetts.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Numero_Twetts.setForeground(new java.awt.Color(0, 255, 255));
        Numero_Twetts.setText("0");

        SIGUIENDO.setForeground(new java.awt.Color(102, 102, 102));
        SIGUIENDO.setText("Siguiendo");

        Numero_Siguiendo.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Numero_Siguiendo.setForeground(new java.awt.Color(0, 255, 255));
        Numero_Siguiendo.setText("0");

        SEGUIDORES.setForeground(new java.awt.Color(102, 102, 102));
        SEGUIDORES.setText("Seguidores");

        Numero_Seguidores.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Numero_Seguidores.setForeground(new java.awt.Color(0, 255, 255));
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

        TESIGUE_NOTESIGUE.setBackground(new java.awt.Color(102, 102, 102));
        TESIGUE_NOTESIGUE.setForeground(new java.awt.Color(102, 102, 102));
        TESIGUE_NOTESIGUE.setText("No te sigue");

        Edad.setText("18");

        Fecha_Ingreso.setText("jLabel1");

        REGRESARMENU.setText("Regresar Menu Principal");
        REGRESARMENU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGRESARMENUActionPerformed(evt);
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
                            .addComponent(UsernamePerfil)
                            .addComponent(Edad)
                            .addComponent(Fecha_Ingreso)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTweets)
                                    .addComponent(NombrePerfil)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(TESIGUE_NOTESIGUE)))
                                .addGap(77, 77, 77)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(Numero_Twetts, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtxtwits))
                                .addGap(68, 68, 68)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SIGUIENDO)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(Numero_Siguiendo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(Numero_Seguidores, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(SEGUIDORES)))
                            .addComponent(imagen_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(REGRESARMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SEGUIR_NOSEGUIR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(imagen_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtxtwits)
                    .addComponent(SIGUIENDO)
                    .addComponent(SEGUIDORES)
                    .addComponent(SEGUIR_NOSEGUIR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Numero_Twetts)
                    .addComponent(Numero_Siguiendo)
                    .addComponent(Numero_Seguidores)
                    .addComponent(TESIGUE_NOTESIGUE))
                .addGap(18, 18, 18)
                .addComponent(NombrePerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UsernamePerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Edad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Fecha_Ingreso)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTweets, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(REGRESARMENU, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30)
                .addComponent(Tweets_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void SEGUIR_NOSEGUIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEGUIR_NOSEGUIRActionPerformed

        if (manejoPerfil.sigueUsuario(usuarioActual, username)) {
            // Dejar de seguir al usuario
            manejoPerfil.dejarDeSeguirUsuario(usuarioActual, username);
            SEGUIR_NOSEGUIR.setText("Seguir");
        } else {
            // Seguir al usuario
            manejoPerfil.seguirUsuario(usuarioActual, username);
            SEGUIR_NOSEGUIR.setText("Dejar de seguir");
        }

        // Actualizar si el usuario sigue al perfil visto
        TESIGUE_NOTESIGUE.setText(manejoPerfil.sigueUsuario(username, usuarioActual) ? "Te sigue" : "No te sigue");

        // Actualizar el número de seguidores y seguidos
        int index = UsuarioManager.obtenerIndiceUsuario(username);
        Numero_Seguidores.setText(String.valueOf(manejoPerfil.getNumFollowers()[index]));
        Numero_Siguiendo.setText(String.valueOf(manejoPerfil.getNumFollowing()[index]));
      
    }//GEN-LAST:event_SEGUIR_NOSEGUIRActionPerformed

    private void REGRESARMENUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REGRESARMENUActionPerformed
        if(menu != null){
            menu.mostrarMenu();
        }
        this.dispose();
        
    }//GEN-LAST:event_REGRESARMENUActionPerformed

    public void imagen_perfil(String username, JLabel label) {
        int index = UsuarioManager.obtenerIndiceUsuario(username);

        // Verifica si el indice es valido
        if (index != -1) {
            // Obten el género del usuario desde el arreglo 'generos'
            String genero = UsuarioManager.getGeneros()[index];

            // Carga la imagen adecuada segun el genero
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
            Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);

            // Establecer la imagen en el JLabel
            label.setIcon(scaledIcon);
        } else {
            // Mensaje de error en caso de que el usuario no exista
            System.out.println("Usuario no encontrado");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
    private javax.swing.JLabel TESIGUE_NOTESIGUE;
    private java.awt.TextArea Tweets_Usuario;
    private javax.swing.JLabel UsernamePerfil;
    private javax.swing.JLabel imagen_perfil;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtTweets;
    private javax.swing.JLabel txtxtwits;
    // End of variables declaration//GEN-END:variables
}
