package proyecto_twit_roy_moises;

import javax.swing.*;
import java.awt.Image;

/**
 * Clase MiPerfil: Muestra los detalles del perfil actual y permite buscar otros usuarios.
 */
public class MiPerfil extends javax.swing.JFrame {

    private String usuarioActual;
    private DefaultListModel<String> listModel; // Mover la declaración aquí

    public MiPerfil(String usuarioActual) {
        this.usuarioActual = usuarioActual;
        initComponents();

        // Inicializar listModel aquí
        listModel = new DefaultListModel<>();
        usersxd.setModel(listModel);
        jTextArea1.setEditable(false);

        // Cargar otros datos del perfil
        cargarDatosPerfil(usuarioActual);
        cargarImagenPerfil(usuarioActual);
        configurarLista();
    }

    /**
     * Cargar los datos del perfil del usuario actual.
     */
    private void cargarDatosPerfil(String username) {
        int index = UsuarioManager.obtenerIndiceUsuario(username);

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Error: El usuario no fue encontrado.");
            return;
        }

        // Cargar datos del usuario desde UsuarioManager
        String nombre = UsuarioManager.getNombres()[index];
        String edad = UsuarioManager.getEdades()[index];
        String genero = UsuarioManager.getGeneros()[index]; // Obtener el género del usuario
        String tweets = UsuarioManager.verTwettsUsuario(username);

        // Mostrar los datos en los campos correspondientes
        jLabel1.setText(nombre);
        usernamelbl.setText(username);
        Edadlbl.setText(edad + " años");
        jTextArea1.setText(tweets);
        jTextArea1.setLineWrap(true);  // Ajustar el texto para que se vea correctamente en líneas
        jTextArea1.setWrapStyleWord(true);
    }

    private void cargarImagenPerfil(String username) {
        int index = UsuarioManager.obtenerIndiceUsuario(username);

        if (index != -1) {
            // Obtener el género del usuario desde el arreglo de géneros
            String genero = UsuarioManager.getGeneros()[index];

            // Cargar la imagen adecuada según el género
            String imagePath;
            if ("masculino".equalsIgnoreCase(genero)) {
                imagePath = "/imagenes/Masculino.jpg"; // Ruta relativa para imagen masculina
            } else if ("femenino".equalsIgnoreCase(genero)) {
                imagePath = "/imagenes/Femenino.jpg"; // Ruta relativa para imagen femenina
            } else {
                imagePath = "/imagenes/mini.png"; // Imagen genérica en caso de no haber género definido
                System.out.println("Cargando imagen genérica");
            }

            // Verificación de la ruta de la imagen
            if (getClass().getResource(imagePath) == null) {
                System.out.println("Error: Imagen no encontrada en la ruta: " + imagePath);
                return;
            }

            // Cargar la imagen y ajustarla al tamaño del JLabel
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(pfppersonal.getWidth(), pfppersonal.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);

            // Asignar la imagen escalada al JLabel
            pfppersonal.setIcon(scaledIcon);

        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    /**
     * Método para actualizar el número de seguidores (followers) y seguidos (following)
     */
        private void actualizarSeguidoresYSeguidos() {
    int followers = UsuarioManager.obtenerNumSeguidores(usuarioActual);
    int following = UsuarioManager.obtenerNumSeguidos(usuarioActual);

    // Actualiza los campos sin usar String.valueOf()
    numfollowers.setText("" + followers);  // Concatenación con cadena vacía
    numfollowing.setText("" + following);  // Concatenación con cadena vacía
}
        
    public void actualizarListaUsuariosSeguidos() {
     String[] usuariosSeguidos = UsuarioManager.obtenerUsuariosSeguidos(usuarioActual);

    // Limpia la lista antes de actualizarla
    listModel.clear();

    // Añade cada usuario seguido a la lista
    for (int i = 0; i < usuariosSeguidos.length; i++) {
        String usuario = usuariosSeguidos[i];

        // Verifica si el usuario actual sigue al otro usuario
        boolean sigue = UsuarioManager.sigueUsuario(usuarioActual, usuario);
        String estadoSeguimiento = sigue ? "LO SIGO" : "NO LO SIGO";

        // Agrega el usuario y su estado a la lista
        listModel.addElement(usuario + " [" + estadoSeguimiento + "]");
    }

    // Establece el modelo actualizado en el componente de lista
    usersxd.setModel(listModel);
}

   private void seguirUsuario(String usernameASeguir) {
    boolean resultado = UsuarioManager.seguirUsuario(usuarioActual, usernameASeguir);
    if (resultado) {
        JOptionPane.showMessageDialog(this, "Ahora sigues a " + usernameASeguir);
        actualizarSeguidoresYSeguidos();  // Actualiza los valores después de seguir
        actualizarListaUsuariosSeguidos();  // Actualiza la lista de usuarios seguidos en la UI
    } else {
        JOptionPane.showMessageDialog(this, "Hubo un error al seguir al usuario.");
    }
}

private void dejarDeSeguirUsuario(String usernameADejar) {
    boolean resultado = UsuarioManager.dejarDeSeguirUsuario(usuarioActual, usernameADejar);
    if (resultado) {
        JOptionPane.showMessageDialog(this, "Has dejado de seguir a " + usernameADejar);
        actualizarSeguidoresYSeguidos();  // Actualiza los valores después de dejar de seguir
        actualizarListaUsuariosSeguidos();  // Actualiza la lista de usuarios seguidos en la UI
    } else {
        JOptionPane.showMessageDialog(this, "Hubo un error al dejar de seguir al usuario.");
    }
}

    private void desactivarCuenta() {
        int indice = UsuarioManager.obtenerIndiceUsuario(usuarioActual);

        if (indice != -1) {
            UsuarioManager.Cambiarestadocuenta(indice, false);
            UsuarioManager.eliminarSeguidosYSeguidores(usuarioActual);

            JOptionPane.showMessageDialog(null, "Tu cuenta ha sido desactivada.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al desactivar la cuenta.");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        usernamelbl = new javax.swing.JLabel();
        Edadlbl = new javax.swing.JLabel();
        Mistweetslbl = new javax.swing.JLabel();
        Volvermenuxd = new javax.swing.JButton();
        Desactivarcuenta = new javax.swing.JButton();
        buscarusuariobtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersxd = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numfollowers = new javax.swing.JLabel();
        numfollowing = new javax.swing.JLabel();
        Ingresaruser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pfppersonal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombrepropiolbl");

        usernamelbl.setText("username");

        Edadlbl.setText("edad");

        Mistweetslbl.setText("Tweets del usuario");

        Volvermenuxd.setText("Volver al menu principal");
        Volvermenuxd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolvermenuxdActionPerformed(evt);
            }
        });

        Desactivarcuenta.setText("Desactivar");
        Desactivarcuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesactivarcuentaActionPerformed(evt);
            }
        });

        buscarusuariobtn.setText("Buscar usuario");
        buscarusuariobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarusuariobtnActionPerformed(evt);
            }
        });

        usersxd.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(usersxd);

        jLabel2.setText("Followers");

        jLabel3.setText("Following");

        numfollowers.setText("0");

        numfollowing.setText("0");

        jLabel4.setText("Username:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Desactivarcuenta)
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Mistweetslbl)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(232, 232, 232)
                                .addComponent(Volvermenuxd))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(pfppersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(usernamelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Edadlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(97, 97, 97)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(numfollowers)
                                        .addGap(139, 139, 139)
                                        .addComponent(numfollowing)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addComponent(Ingresaruser, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(buscarusuariobtn, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(12, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(225, 225, 225)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(Ingresaruser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(pfppersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(numfollowers)
                                    .addComponent(numfollowing)
                                    .addComponent(buscarusuariobtn))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(usernamelbl))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Edadlbl)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(Desactivarcuenta)
                        .addGap(159, 159, 159)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Mistweetslbl)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(Volvermenuxd))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarusuariobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarusuariobtnActionPerformed
          String nombreUsuario = Ingresaruser.getText().trim(); // Aquí tomamos el nombre desde el campo de texto

    if (!nombreUsuario.isEmpty()) {
        int index = UsuarioManager.obtenerIndiceUsuario(nombreUsuario);

        if (index != -1) {
            // Verificar si la cuenta está activa antes de mostrar el usuario
            boolean estaActiva = UsuarioManager.UsuarioActivo(nombreUsuario);
            if (!estaActiva) {
                JOptionPane.showMessageDialog(this, "La cuenta de " + nombreUsuario + " no esta activa.");
                return; // Salimos del método si la cuenta está desactivada
            }

            // Verifica si el usuario actual sigue al otro usuario
            boolean loSigo = UsuarioManager.sigueUsuario(usuarioActual, nombreUsuario);

            // Si el usuario existe, agregarlo a la lista y mostrar su estado (si lo sigues o no)
            DefaultListModel listModel = new DefaultListModel();
            String estadoSeguimiento = "NO LO SIGO";
            if (loSigo) {
                estadoSeguimiento = "LO SIGO";
            }
            listModel.addElement(nombreUsuario + " [" + estadoSeguimiento + "]");
            usersxd.setModel(listModel); // Aquí añadimos el usuario encontrado a la lista

            // Actualizar el número de personas que el usuario actual sigue
            int numSiguiendo = UsuarioManager.obtenerNumSeguidos(usuarioActual);
            numfollowing.setText("" + numSiguiendo); // Aquí actualizamos el JLabel con el nuevo número de seguidos

        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, introduce un nombre de usuario.");
    }

    }//GEN-LAST:event_buscarusuariobtnActionPerformed

    private void VolvermenuxdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolvermenuxdActionPerformed
       MenuPrincipal menu = MenuPrincipal.getMenu(usuarioActual);
        menu.mostrarMenu();
        this.dispose();
    }//GEN-LAST:event_VolvermenuxdActionPerformed

    private void DesactivarcuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesactivarcuentaActionPerformed
    int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea desactivar su cuenta?",
            "Confirmar Desactivación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

    if (respuesta == JOptionPane.YES_OPTION) {
        // Obtener el índice del usuario actual
        int index = UsuarioManager.obtenerIndiceUsuario(usuarioActual);

        // Desactivar la cuenta
        UsuarioManager.Cambiarestadocuenta(index, false);

        // Eliminar todos los seguidores y seguidos
        UsuarioManager.eliminarSeguidosYSeguidores(usuarioActual);

        JOptionPane.showMessageDialog(this, "Su cuenta ha sido desactivada con éxito.");
        
        // Redirigir a la pantalla de inicio de sesión (LogIn)
        LogIn login = new LogIn();  // Crea una nueva instancia del formulario de LogIn
        login.setVisible(true);     // Muestra la ventana de LogIn
        this.dispose();           
    }
    }//GEN-LAST:event_DesactivarcuentaActionPerformed
  
    /**
     * Acción para buscar un usuario y mostrar su perfil.
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
        java.util.logging.Logger.getLogger(MiPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(MiPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(MiPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(MiPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // Aquí debes pasar el nombre del usuario actual al constructor
            String usuarioActual = "usuarioEjemplo"; // Cambia esto por el valor real
            new MiPerfil(usuarioActual).setVisible(true);
        }
    });
    }

   private void configurarLista() {
        usersxd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Detectar doble clic
                    int index = usersxd.locationToIndex(evt.getPoint()); // Obtener el índice del elemento seleccionado
                    if (index >= 0) {
                        String seleccion = usersxd.getModel().getElementAt(index); // Obtener el usuario seleccionado
                        String username = seleccion.split(" \\[")[0].trim(); // Obtener solo el nombre del usuario sin el estado
                        abrirPerfil(username); // Llamar a la función para abrir el perfil
                    }
                }
            }
        });
    }

    private void abrirPerfil(String username) {
        PerfilVisual perfil = new PerfilVisual(usuarioActual, username, this); // Pasar 'this' como referencia a MiPerfil
        perfil.setVisible(true); // Mostrar la nueva ventana del perfil
        this.dispose(); // Cerrar la ventana actual
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Desactivarcuenta;
    private javax.swing.JLabel Edadlbl;
    private javax.swing.JTextField Ingresaruser;
    private javax.swing.JLabel Mistweetslbl;
    private javax.swing.JButton Volvermenuxd;
    private javax.swing.JButton buscarusuariobtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel numfollowers;
    private javax.swing.JLabel numfollowing;
    private javax.swing.JLabel pfppersonal;
    private javax.swing.JLabel usernamelbl;
    private javax.swing.JList<String> usersxd;
    // End of variables declaration//GEN-END:variables
}
