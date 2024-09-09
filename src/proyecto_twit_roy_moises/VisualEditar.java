
 
package proyecto_twit_roy_moises;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static proyecto_twit_roy_moises.manejoPerfil.BuscarUsuario;

/**
 *
 * @author royum
 */
public class VisualEditar extends javax.swing.JFrame {

    private String usuarioActual;
    private DefaultListModel<String> listModel;

    manejoPerfil mane;
    UsuarioManager user;
    private MenuPrincipal menu;

    /**
     * Creates new form VisualEditarPerfil
     *
     * @param usuarioActual
     */
    public VisualEditar(String usuarioActual) {
        this.usuarioActual = usuarioActual;
        this.menu = MenuPrincipal.getMenu(usuarioActual);
        initComponents();
        this.setLocationRelativeTo(null);
        configurarLista(); // Configurar la lista y el evento de doble clic
    }

    private void configurarLista() {
        listModel = new DefaultListModel<>();
        resultado.setModel(listModel);

        resultado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Detectar doble clic
                    int index = resultado.locationToIndex(evt.getPoint()); // Obtener el índice del elemento seleccionado
                    if (index >= 0) {
                        String seleccion = listModel.getElementAt(index); // Obtener el usuario seleccionado
                        String username = seleccion.split(" \\| ")[0]; // Obtener solo el nombre del usuario
                        abrirPerfil(username); // Llamar a la función para abrir el perfil
                    }
                }
            }
        });
    }

    private void abrirPerfil(String username) {
        PerfilVisual perfil = new PerfilVisual(username); // Pasar el username al constructor de PerfilVisual
        perfil.setVisible(true); // Mostrar la nueva ventana del perfil
        this.dispose(); // Cerrar la ventana actual
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        ImagenNombre = new javax.swing.JPanel();
        Busc = new javax.swing.JLabel();
        EsBucarUser = new javax.swing.JTextField();
        BuscarEnter = new javax.swing.JButton();
        EntrarPerfil = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        RegresarMenu = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultado = new javax.swing.JList<>();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Busc.setText("Buscar Personas: ");

        EsBucarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EsBucarUserActionPerformed(evt);
            }
        });

        BuscarEnter.setText("Buscar");
        BuscarEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarEnterActionPerformed(evt);
            }
        });

        EntrarPerfil.setText("Entrar a un perfil");
        EntrarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntrarPerfilActionPerformed(evt);
            }
        });

        RegresarMenu.setText("Regresar Menu");
        RegresarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarMenuActionPerformed(evt);
            }
        });

        resultado.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(resultado);

        javax.swing.GroupLayout ImagenNombreLayout = new javax.swing.GroupLayout(ImagenNombre);
        ImagenNombre.setLayout(ImagenNombreLayout);
        ImagenNombreLayout.setHorizontalGroup(
            ImagenNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImagenNombreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ImagenNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ImagenNombreLayout.createSequentialGroup()
                        .addComponent(RegresarMenu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EntrarPerfil))
                    .addGroup(ImagenNombreLayout.createSequentialGroup()
                        .addComponent(Busc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EsBucarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BuscarEnter)
                        .addGap(0, 100, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(ImagenNombreLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ImagenNombreLayout.setVerticalGroup(
            ImagenNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ImagenNombreLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(ImagenNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Busc)
                    .addComponent(EsBucarUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuscarEnter))
                .addGap(32, 32, 32)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(ImagenNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EntrarPerfil)
                    .addComponent(RegresarMenu))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImagenNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImagenNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EsBucarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EsBucarUserActionPerformed


    }//GEN-LAST:event_EsBucarUserActionPerformed

    private void BuscarEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarEnterActionPerformed
 if (EsBucarUser.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "no puedes buscar a alguien sin haber ingresado texto");
            return;
        }

        String nombreUsuario = EsBucarUser.getText().trim();

        String[] usuariosEncontrados = BuscarUsuario(nombreUsuario);

        listModel.clear(); // Limpiar los resultados previos

        if (usuariosEncontrados.length > 0) {
            for (String usuario : usuariosEncontrados) {
                String estadoSeguimiento = manejoPerfil.sigueUsuario(usuarioActual, usuario) ? "LO SIGO" : "NO LO SIGUES";
                listModel.addElement(usuario + " | " + estadoSeguimiento);
            }
        } else {
            listModel.addElement("No se encontraron usuarios.");
        }
    }//GEN-LAST:event_BuscarEnterActionPerformed

    private void RegresarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarMenuActionPerformed
        
      MenuPrincipal.getMenu(usuarioActual);

        if (menu != null) {
            menu.mostrarMenu();
        }

        this.setVisible(false);
    }//GEN-LAST:event_RegresarMenuActionPerformed

    private void EntrarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntrarPerfilActionPerformed
            PerfilVisual perfil = new PerfilVisual(usuarioActual);
        perfil.setVisible(true);
        this.dispose();


    }//GEN-LAST:event_EntrarPerfilActionPerformed

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
            java.util.logging.Logger.getLogger(VisualEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Busc;
    private javax.swing.JButton BuscarEnter;
    private javax.swing.JButton EntrarPerfil;
    private javax.swing.JTextField EsBucarUser;
    private javax.swing.JPanel ImagenNombre;
    private javax.swing.JButton RegresarMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JList<String> resultado;
    // End of variables declaration//GEN-END:variables
}
