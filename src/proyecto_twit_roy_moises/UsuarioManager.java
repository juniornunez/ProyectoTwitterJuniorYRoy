/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_twit_roy_moises;

import java.util.Date;
import java.util.Calendar;

//  sirve para la revision que gestione la lista de usuarios
// esta clase se encargara de almacenar y verificar los nombres de usuario y la constrasena.
/**
 *
 * @author royum
 */
public class UsuarioManager {

    private static final int MAX_USUARIOS = 500;
    private static String[] nombres = new String[MAX_USUARIOS];
    private static String[] usernames = new String[MAX_USUARIOS];
    private static String[] passwords = new String[MAX_USUARIOS];
    private static String[] generos = new String[MAX_USUARIOS];
    private static String[] edades = new String[MAX_USUARIOS];
    private static Calendar[] FechasIngreso =  new Calendar[MAX_USUARIOS];
    private static Twits[] twitsPorUsuario = new Twits[MAX_USUARIOS]; // Array para almacenar los twits por usuario
    private static Twits[] twitsUsuarios = new Twits[MAX_USUARIOS]; // Nuevo arreglo para manejar los tweets de cada usuario
    private static boolean[] estadosActivacion = new boolean[MAX_USUARIOS]; // Estado de activación: true = activo, false = desactivado

    public static Calendar[] getFechasIngreso() {
        return FechasIngreso;
    }

    private static int contador = 0;

    public static void Cambiarestadocuenta(int index, boolean estado) {
        estadosActivacion[index] = estado;
    }

    public static boolean UsuarioActivo(String username) {
        int index = obtenerIndiceUsuario(username);
        return index != -1 && estadosActivacion[index];
    }

    // Metodo para obtener el indice de un usuario basado en su username
    public static int obtenerIndiceUsuario(String username) {
        for (int obten = 0; obten < contador; obten++) {
            if (usernames[obten] != null && usernames[obten].equals(username)) {
                return obten;
            }
        }
        return -1;
    }

    // Agrega un nuevo usuario con una contraseña y las guarda
    public static boolean agregarUsuario(String nombre, String username, String password, String genero, String edad, Calendar FechaIngreso) {
        if (contador >= MAX_USUARIOS) {
            return false; // No hay espacio para mas usuarios
        }
        for (int exis = 0; exis < contador; exis++) {
            if (usernames[exis] != null && usernames[exis].equals(username)) {
                return false; // El usuario ya existe
            }
        }
        nombres[contador] = nombre;
        usernames[contador] = username;
        passwords[contador] = password; // Guardar la contraseña
        generos[contador] = genero;
        edades[contador] = edad;
        FechasIngreso[contador] = FechaIngreso;
        twitsUsuarios[contador] = new Twits(); // Inicializar el objeto Twits para este usuario
        contador++;
        return true;
    }

    // Verifica si el nombre de usuario existe
    public static boolean usuarioExiste(String username) {
        for (int no = 0; no < contador; no++) {
            if (usernames[no] != null && usernames[no].equals(username)) {
                return true; // El usuario existe
            }
        }
        return false; // El usuario no existe
    }

    // Verifica si el nombre de usuario y la contraseña coinciden
    public static boolean autenticar(String username, String password) {
        for (int au = 0; au < contador; au++) {
            if (usernames[au] != null && usernames[au].equals(username) && passwords[au] != null && passwords[au].equals(password)) {
                return true; // Autenticacion exitosa
            }
        }
        return false; // Autenticacion fallida
    }

    public static String[] getEdades() {
        return edades;
    }

    public static String[] getNombres() {
        return nombres;
    }

    public static String[] getGeneros() {
        return generos;
    }

    // Obtiene los usernames de los usuarios
    public static String[] getUsernames() {
        return usernames;
    }

    // Obtiene el contador de usuarios
    public static int getContador() {
        return contador;
    }

    // obtenemos los twits del usuario
    public static Twits getTwitsDeUsuario(String username) {
        for (int twits = 0; twits < contador; twits++) {
            if (usernames[twits] != null && usernames[twits].equals(username)) {
                return twitsPorUsuario[twits];
            }

        }
        return null;
    }

    // NO BORRAR ESTA FUNCIONNNN!!!!!!!!!!!!!!!!!!!!!
    // Metodo para obtener los tweets de un usuario especifico
    public static Twits obtenerTwitsUsuario(String username) {
        for (int obtenertwi = 0; obtenertwi < contador; obtenertwi++) {
            if (usernames[obtenertwi] != null && usernames[obtenertwi].equals(username)) {
                return twitsUsuarios[obtenertwi];
            }

        }
        return null; // Usuario no encontrado o no tiene tweets
    }

    public static String verTwettsUsuario(String username) {
        Twits twitsUsuario = getTwitsDeUsuario(username);
        if (twitsUsuario == null) {
            return " no hay twetts";
        }

        Twit[] listaTwits = twitsUsuario.getTwits();
        StringBuilder resultado = new StringBuilder();
        for (int resul = twitsUsuario.getNumeroTwits() - 1; resul >= 0; resul++) {
            Twit twit = listaTwits[resul];
            if (twit != null) {
                resultado.append(twit.getFechapublicacion()).append(" - ").append(twit.getContenido()).append("\n");
            }
        }
        return resultado.toString();
    }
    
    public static Twit[] obtenerTwitsQueMencionan(String username){
        Twit[] twitsQueMencionan = new Twit[500];
        int contadorMenciones = 0;
        
        for (int Mencion = 0; Mencion < contador; Mencion++) {
            if(twitsUsuarios[Mencion] != null){
                for (int mencionJOTA = 0; mencionJOTA < twitsUsuarios[Mencion].getNumeroTwits(); mencionJOTA++) {
                    Twit twit = twitsUsuarios[Mencion].getTwits()[mencionJOTA];
                    if(twit != null && twit.MencionUsuario(username)){
                        twitsQueMencionan[contadorMenciones] = twit;
                        contadorMenciones++;
                    }
                    
                }
            }
        }
        
        
        
        Twit[] resultado = new Twit[contadorMenciones];
        System.arraycopy(twitsQueMencionan, 0, resultado, 0, contadorMenciones);
        return resultado;
    }

//AGREGANDO DESDE AQUI 5 DE SEP JUNIOR NUÑEZ
  
    // Método para agregar usuarios (solo para ejemplos de prueba)
    public static void agregarUsuario(String username) {
        if (contador < MAX_USUARIOS) {
            usernames[contador] = username;
            contador++;
        } else {
            System.out.println("No se pueden agregar más usuarios.");
        }
    }

    // Método para buscar usuarios en el arreglo de usernames
    public static String[] buscarUsuario(String termino) {
        // Array temporal para almacenar los resultados
        String[] resultados = new String[MAX_USUARIOS];
        int contadorResultados = 0;

        // Recorremos el array de usernames para buscar coincidencias
        for (int i = 0; i < contador; i++) {
            if (usernames[i] != null && usernames[i].toLowerCase().contains(termino.toLowerCase())) {
                // Si hay coincidencia, lo agregamos a los resultados
                resultados[contadorResultados] = usernames[i];
                contadorResultados++;
            }
        }

        // Devolvemos solo los resultados no nulos
        return java.util.Arrays.copyOf(resultados, contadorResultados);
    }

    // Método para mostrar resultados
    public static void mostrarResultados(String termino) {
        String[] resultados = buscarUsuario(termino);
        if (resultados.length > 0) {
            System.out.println("Usuarios encontrados:");
            for (String usuario : resultados) {
                System.out.println(usuario);
            }
        } else {
            System.out.println("No se encontraron usuarios con el término: " + termino);
        }
        // En tu clase UsuarioManager, ya tendrás la lista de usuarios guardados
    UsuarioManager.mostrarResultados("Roy");

    }


}
