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
        mistweets.setEditable(false);

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
        int numeroSeguidores = manejoPerfil.getNumFollowers()[index];
        int numeroSiguiendo = manejoPerfil.getNumFollowing()[index];
        String fechaIngreso = UsuarioManager.getFechasIngreso()[index].getTime().toString();

        
        numfollowers.setText(String.valueOf(numeroSeguidores));
        numfollowing.setText(String.valueOf(numeroSiguiendo));
        jLabel1.setText(nombre);
        FechaIngreso.setText("Fecha de Ingreso: " + fechaIngreso);
        usernamelbl.setText(username);
        Edadlbl.setText(edad + " años");
        mistweets.setText(tweets);
        mistweets.setLineWrap(true);  // Ajustar el texto para que se vea correctamente en líneas
        mistweets.setWrapStyleWord(true);
        
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
 public void actualizarSeguidoresYSeguidosMiPerfil() {
    // Obtener el índice del usuario actual
    int index = UsuarioManager.obtenerIndiceUsuario(usuarioActual);

    if (index != -1) {
        // Obtener el número de seguidores y seguidos
        int numSeguidores = UsuarioManager.obtenerNumSeguidores(usuarioActual);
        int numSiguiendo = UsuarioManager.obtenerNumSeguidos(usuarioActual);

        // Actualizar los JLabel en la interfaz
        numfollowers.setText("" + numSeguidores);  // Convertir a String usando concatenación
        numfollowing.setText("" + numSiguiendo);   // Convertir a String usando concatenación

       
    } else {
        JOptionPane.showMessageDialog(this, "Error al obtener los datos del usuario.");
    }
}

// Actualización de la lista de seguidos
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
        actualizarSeguidoresYSeguidosMiPerfil();  // Actualiza los valores después de seguir
        actualizarListaUsuariosSeguidos();  // Actualiza la lista de usuarios seguidos en la UI
    } else {
        JOptionPane.showMessageDialog(this, "Hubo un error al seguir al usuario.");
    }
}

private void dejarDeSeguirUsuario(String usernameADejar) {
    boolean resultado = UsuarioManager.dejarDeSeguirUsuario(usuarioActual, usernameADejar);
    if (resultado) {
        JOptionPane.showMessageDialog(this, "Has dejado de seguir a " + usernameADejar);
        actualizarSeguidoresYSeguidosMiPerfil();  // Actualiza los valores después de dejar de seguir
        actualizarListaUsuariosSeguidos();  // Actualiza la lista de usuarios seguidos en la UI
    } else {
        JOptionPane.showMessageDialog(this, "Hubo un error al dejar de seguir al usuario.");
    }
}

   

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        usernamelbl = new javax.swing.JLabel();
        Edadlbl = new javax.swing.JLabel();
        Mistweetslbl = new javax.swing.JLabel();
        Volvermenuxd = new javax.swing.JButton();
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
        mistweets = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        FechaIngreso = new javax.swing.JLabel();
        activar_desactivarcuenta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 800));

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

        mistweets.setColumns(20);
        mistweets.setRows(5);
        jScrollPane2.setViewportView(mistweets);

        jLabel5.setText("Edad:");

        FechaIngreso.setText("jLabel6");

        activar_desactivarcuenta.setText("Activar/Desactivar Cuenta");
        activar_desactivarcuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activar_desactivarcuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(pfppersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(Edadlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(FechaIngreso))
                                            .addGap(129, 129, 129)
                                            .addComponent(jLabel2))
                                        .addComponent(jLabel1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(usernamelbl))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(numfollowers)
                                .addGap(19, 19, 19)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(numfollowing))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLabel3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Mistweetslbl)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buscarusuariobtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Ingresaruser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(activar_desactivarcuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Volvermenuxd, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(pfppersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numfollowers)
                            .addComponent(numfollowing))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(usernamelbl))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Edadlbl))
                        .addGap(45, 45, 45)
                        .addComponent(FechaIngreso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Mistweetslbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Ingresaruser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscarusuariobtn)))
                        .addGap(18, 67, Short.MAX_VALUE)
                        .addComponent(Volvermenuxd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(activar_desactivarcuenta)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarusuariobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarusuariobtnActionPerformed
       String nombreUsuario = Ingresaruser.getText().trim(); // Aquí tomamos el nombre desde el campo de texto

    if (!nombreUsuario.isEmpty()) {

        if (nombreUsuario.equals(usuarioActual)){
            JOptionPane.showMessageDialog(this,"No puedes buscarte a ti mismo");
            return;
        }

        // Buscar usuarios que coincidan o tengan similitud con el nombre ingresado
        String[] usuariosSimilares = manejoPerfil.BuscarUsuario(nombreUsuario);

        if (usuariosSimilares.length > 0) {
            DefaultListModel<String> listModel = new DefaultListModel<>();

            for (String usuario : usuariosSimilares) {
                // Verificar si la cuenta está activa antes de mostrar el usuario
                boolean estaActiva = UsuarioManager.UsuarioActivo(usuario);
                if (!estaActiva) {
                    listModel.addElement(usuario + " [No existe :( ]");
                    continue;
                }

                // Verifica si el usuario actual sigue al otro usuario
                boolean loSigo = manejoPerfil.sigueUsuario(usuarioActual, usuario);

                // Usamos operador ternario para determinar si mostrar "LO SIGO" o "NO LO SIGO"
                String estadoSeguimiento = loSigo ? "[LO SIGO]" : "[NO LO SIGO]";

                // Agregar el usuario y su estado de seguimiento a la lista
                listModel.addElement(usuario + " " + estadoSeguimiento);
            }

            // Mostrar los resultados en la lista
            usersxd.setModel(listModel); // Aquí añadimos los usuarios encontrados a la lista

        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron usuarios con ese nombre o coincidencia.");
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

    private void activar_desactivarcuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activar_desactivarcuentaActionPerformed
         // Obtener el estado actual de la cuenta
    boolean cuentaActiva = UsuarioManager.UsuarioActivo(usuarioActual);
    
    if (cuentaActiva) {
        // Desactivar la cuenta
        UsuarioManager.desactivarCuenta(usuarioActual);
        JOptionPane.showMessageDialog(this, "Tu cuenta ha sido desactivada.");
    } else {
        // Activar la cuenta
        UsuarioManager.activarCuenta(usuarioActual);
        JOptionPane.showMessageDialog(this, "Tu cuenta ha sido activada.");
    }

    }//GEN-LAST:event_activar_desactivarcuentaActionPerformed
  
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
    private javax.swing.JLabel Edadlbl;
    private javax.swing.JLabel FechaIngreso;
    private javax.swing.JTextField Ingresaruser;
    private javax.swing.JLabel Mistweetslbl;
    private javax.swing.JButton Volvermenuxd;
    private javax.swing.JButton activar_desactivarcuenta;
    private javax.swing.JButton buscarusuariobtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mistweets;
    private javax.swing.JLabel numfollowers;
    private javax.swing.JLabel numfollowing;
    private javax.swing.JLabel pfppersonal;
    private javax.swing.JLabel usernamelbl;
    private javax.swing.JList<String> usersxd;
    // End of variables declaration//GEN-END:variables
}
