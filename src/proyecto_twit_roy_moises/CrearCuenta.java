/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_twit_roy_moises;

import javax.swing.*; // libreria de swing
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 *
 * @author royum
 */
public class CrearCuenta {

    private static CrearCuenta instancia2;
    private JFrame frame;
    private JTextField nombreField;
    private JComboBox generoComboBox;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField edadField;
    private JTextField fechaIngresoField; // Campo para mostrar la fecha de ingreso
    private JButton generarFechaButton; // Botón para generar la fecha de ingreso
    LogIn log;

    private String[] usernames;

    public CrearCuenta() {
        usernames = new String[1000]; 
        initUI();
    }
    
    // instancia creada
    public static CrearCuenta getInstancia2(){
        if(instancia2 == null){
            instancia2 = new CrearCuenta();
        }
        return instancia2;
    }
    
    // mostrarframe de crear cuenta
    public void MostrarCrearCuenta(){
        if(frame !=null){
            frame.setVisible(true);
        }
    }

    // ocultar frame de crearCuenta
    public void OcultarCrearCuenta() {
        frame.setVisible(false);
    }

    private void initUI() {
        frame = new JFrame("Crea tu cuenta");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Panel para contener los campos de entrada
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.white);
        frame.add(panel);

        // Configuracion de las etiquetas y campos de entrada
        panel.add(crearCampo("Nombre:", nombreField = new JTextField()));
        panel.add(crearCampo("Genero:", generoComboBox = new JComboBox(new String[]{"Masculino", "Femenino"})));
        panel.add(crearCampo("Username:", usernameField = new JTextField()));
        panel.add(crearCampo("Contraseña:", passwordField = new JPasswordField()));
        panel.add(crearCampo("Edad:", edadField = new JTextField()));
        
        fechaIngresoField = new JTextField();
        fechaIngresoField.setEditable(false);
        panel.add(crearCampo("Fecha Ingreso: ",fechaIngresoField));
        
        generarFechaButton = new JButton("Generar Fecha");
        generarFechaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(generarFechaButton);
        
        generarFechaButton.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                Calendar fechaActual = Calendar.getInstance(); 
                fechaIngresoField.setText(fechaActual.get(Calendar.DAY_OF_MONTH) + "/"
                        + (fechaActual.get(Calendar.MONTH)+1 )+ "/"
                        + fechaActual.get(Calendar.YEAR)+ " "
                        + fechaActual.get(Calendar.HOUR_OF_DAY)+ ":"
                        + fechaActual.get(Calendar.MINUTE)+ ":"
                        + fechaActual.get(Calendar.SECOND));
            }
            
        });

        JButton registerButton = new JButton("Crear Cuenta");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10)); // Espacio vertical
        panel.add(registerButton);

        JButton regresar = new JButton("Regresar Log");
        regresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(15));
        panel.add(regresar);

        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogIn abrirLog = LogIn.getInstancia();
                abrirLog.mostrarLog();
                frame.dispose();
            }
        });

        // Accion del boton Registrar
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton1ActionPerformed();
            }
        });

        frame.setVisible(true);
    }

    private JPanel crearCampo(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.white);
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.black);
        label.setPreferredSize(new Dimension(150, 30));
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(label, BorderLayout.WEST);

        field.setPreferredSize(new Dimension(150, 30));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(Color.black);
        field.setBackground(Color.white);
        field.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2)); // Borde azul claro
        panel.add(field, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Espacio alrededor

        return panel;
    }

    private void jButton1ActionPerformed() {
        String nombre = nombreField.getText().trim();
        String genero = (String) generoComboBox.getSelectedItem();
        String nombreUsuario = usernameField.getText().trim();
        String contrasena = new String(passwordField.getPassword()).trim();
        String edadText = edadField.getText().trim();
        String fechaIngreso  = fechaIngresoField.getText().trim();

        // Validación de campos vacíos
        if (nombre.isEmpty() || genero == null || nombreUsuario.isEmpty() || contrasena.isEmpty() || edadText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // aqui Validacion de la edad   
        int edad;
        try {
            edad = Integer.parseInt(edadText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La edad debe ser un numero entero valido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (edad < 18 || edad > 90) {
            JOptionPane.showMessageDialog(null, "La edad que ingreso no es valida por ser menor de edad (+18) o ingreso un numero no valido, limite de 90.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Este return hace que no se registre con exito si se excede el número
        }

         if (!UsuarioManager.usuarioExiste(nombreUsuario)) {
            // Agregar nuevo usuario
            Calendar fechaIngresoCalendario = Calendar.getInstance();
            try {
                String[] partesFecha = fechaIngreso.split("[/ :]");
                int dia = Integer.parseInt(partesFecha[0]);
                int mes = Integer.parseInt(partesFecha[1])- 1;
                int anio = Integer.parseInt(partesFecha[2]);
                int hora = Integer.parseInt(partesFecha[3]);
                int minuto = Integer.parseInt(partesFecha[4]);
                int segundors = Integer.parseInt(partesFecha[5]);
                
                fechaIngresoCalendario.set(anio, mes, dia, hora, minuto);
                
                if (UsuarioManager.agregarUsuario(nombre, nombreUsuario, contrasena, genero, String.valueOf(edad), fechaIngresoCalendario)) {
                    JOptionPane.showMessageDialog(null, "Usuario registrado con exito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    LogIn log = LogIn.getInstancia();
                    frame.dispose();
                    new LogIn();
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario no pudo ser registrado. Intentalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al procesar la fecha de ingreso.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El usuario ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
