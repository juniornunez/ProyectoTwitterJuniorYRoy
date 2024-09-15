package proyecto_twit_roy_moises;

import java.util.Calendar;

public class UsuarioManager {

    private static final int MAX_USUARIOS = 500;
    private static String[] nombres = new String[MAX_USUARIOS];
    private static String[] usernames = new String[MAX_USUARIOS];
    private static String[] passwords = new String[MAX_USUARIOS];
    private static String[] generos = new String[MAX_USUARIOS];
    private static String[] edades = new String[MAX_USUARIOS];
    private static Calendar[] FechasIngreso = new Calendar[MAX_USUARIOS];
    private static Twits[] twitsPorUsuario = new Twits[MAX_USUARIOS];
    private static Twits[] twitsUsuarios = new Twits[MAX_USUARIOS];
    private static boolean[] estadosActivacion = new boolean[MAX_USUARIOS];

    // Arrays para almacenar seguidores y seguidos
    private static String[][] seguidores = new String[MAX_USUARIOS][MAX_USUARIOS];
    private static String[][] seguidos = new String[MAX_USUARIOS][MAX_USUARIOS];
    private static int[] numSeguidores = new int[MAX_USUARIOS];
    private static int[] numSeguidos = new int[MAX_USUARIOS];

    private static int contador = 0;

    // Métodos getter
    public static Calendar[] getFechasIngreso() {
        return FechasIngreso;
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

    public static String[] getUsernames() {
        return usernames;
    }

    public static int getContador() {
        return contador;
    }

    // Cambiar el estado de la cuenta (activa o inactiva)
    public static void Cambiarestadocuenta(int index, boolean estado) {
        estadosActivacion[index] = estado; // Cambia el estado del usuario (activo/inactivo)
}

    public static boolean UsuarioActivo(String username) {
    int index = obtenerIndiceUsuario(username);
    return index != -1 && estadosActivacion[index]; // Verifica si la cuenta está activa
}

    // Buscar el índice del usuario en el arreglo
    public static int obtenerIndiceUsuario(String username) {
        for (int i = 0; i < contador; i++) {
            if (usernames[i] != null && usernames[i].equals(username)) {
                return i;
            }
        }
        return -1;
    }

    // Agregar un nuevo usuario
    public static boolean agregarUsuario(String nombre, String username, String password, String genero, String edad, Calendar FechaIngreso) {
        if (contador >= MAX_USUARIOS) {
            return false;
        }
        for (int i = 0; i < contador; i++) {
            if (usernames[i] != null && usernames[i].equals(username)) {
                return false;
            }
        }
        nombres[contador] = nombre;
        usernames[contador] = username;
        passwords[contador] = password;
        generos[contador] = genero;
        edades[contador] = edad;
        FechasIngreso[contador] = FechaIngreso;
        twitsUsuarios[contador] = new Twits();
        numSeguidores[contador] = 0;
        numSeguidos[contador] = 0;
        estadosActivacion[contador] = true;  // La cuenta empieza como activa
        contador++;
        return true;
    }

    // Verificar si un usuario existe
    public static boolean usuarioExiste(String username) {
        for (int i = 0; i < contador; i++) {
            if (usernames[i] != null && usernames[i].equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Autenticar un usuario
    public static boolean autenticar(String username, String password) {
        for (int i = 0; i < contador; i++) {
            if (usernames[i] != null && usernames[i].equals(username) && passwords[i] != null && passwords[i].equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Obtener los twits de un usuario
    public static Twits getTwitsDeUsuario(String username) {
        for (int i = 0; i < contador; i++) {
            if (usernames[i] != null && usernames[i].equals(username)) {
                return twitsPorUsuario[i];
            }
        }
        return null;
    }

    public static Twits obtenerTwitsUsuario(String username) {
        for (int i = 0; i < contador; i++) {
            if (usernames[i] != null && usernames[i].equals(username)) {
                return twitsUsuarios[i];
            }
        }
        return null;
    }

    public static String verTwettsUsuario(String username) {
        Twits twitsUsuario = obtenerTwitsUsuario(username);
        if (twitsUsuario == null) {
            return "No hay tweets.";
        }

        Twit[] listaTwits = twitsUsuario.getTwits();
        StringBuilder resultado = new StringBuilder();
        for (int i = twitsUsuario.getNumeroTwits() - 1; i >= 0; i--) {
            Twit twit = listaTwits[i];
            if (twit != null) {
                resultado.append(twit.getFechapublicacion()).append(" - ").append(twit.getContenido()).append("\n");
            }
        }
        return resultado.toString();
    }

    // Métodos para seguir y dejar de seguir usuarios
   public static boolean seguirUsuario(String usuarioActual, String usuarioASeguir) {
    int indiceActual = obtenerIndiceUsuario(usuarioActual);
    int indiceObjetivo = obtenerIndiceUsuario(usuarioASeguir);

    if (indiceActual == -1 || indiceObjetivo == -1) {
        return false; // Si alguno de los usuarios no existe
    }

    // Verificar si ya sigue al usuario
    for (int i = 0; i < numSeguidos[indiceActual]; i++) {
        if (seguidos[indiceActual][i] != null && seguidos[indiceActual][i].equals(usuarioASeguir)) {
            return false; // Ya sigue a este usuario
        }
    }

    // Si no lo sigue, agregarlo a la lista de seguidos y actualizar el contador
    seguidos[indiceActual][numSeguidos[indiceActual]] = usuarioASeguir;
    numSeguidos[indiceActual]++;

    // También incrementar el número de seguidores del usuario objetivo
    seguidores[indiceObjetivo][numSeguidores[indiceObjetivo]] = usuarioActual;
    numSeguidores[indiceObjetivo]++;

    return true;
}

public static boolean dejarDeSeguirUsuario(String usuarioActual, String usuarioADejar) {
    int indiceActual = obtenerIndiceUsuario(usuarioActual);
    int indiceObjetivo = obtenerIndiceUsuario(usuarioADejar);

    if (indiceActual == -1 || indiceObjetivo == -1) {
        return false; // Si alguno de los usuarios no existe
    }

    // Buscar y eliminar de la lista de seguidos
    for (int i = 0; i < numSeguidos[indiceActual]; i++) {
        if (seguidos[indiceActual][i] != null && seguidos[indiceActual][i].equals(usuarioADejar)) {
            // Mover el último elemento al lugar del eliminado
            seguidos[indiceActual][i] = seguidos[indiceActual][numSeguidos[indiceActual] - 1];
            seguidos[indiceActual][numSeguidos[indiceActual] - 1] = null;
            numSeguidos[indiceActual]--;

            // También disminuir el número de seguidores del usuario objetivo
            for (int j = 0; j < numSeguidores[indiceObjetivo]; j++) {
                if (seguidores[indiceObjetivo][j].equals(usuarioActual)) {
                    seguidores[indiceObjetivo][j] = seguidores[indiceObjetivo][numSeguidores[indiceObjetivo] - 1];
                    seguidores[indiceObjetivo][numSeguidores[indiceObjetivo] - 1] = null;
                    numSeguidores[indiceObjetivo]--;
                    break;
                }
            }
            return true;
        }
    }
    return false;
}
    public static boolean sigueUsuario(String usuarioActual, String usuarioObjetivo) {
    int indiceActual = obtenerIndiceUsuario(usuarioActual);
    int indiceObjetivo = obtenerIndiceUsuario(usuarioObjetivo);

    if (indiceActual == -1 || indiceObjetivo == -1) {
        return false; // Si alguno de los usuarios no existe
    }

    // Verificar si el usuarioActual sigue al usuarioObjetivo
    for (int i = 0; i < numSeguidos[indiceActual]; i++) {
        if (seguidos[indiceActual][i] != null && seguidos[indiceActual][i].equals(usuarioObjetivo)) {
            return true; // Ya sigue al usuario
        }
    }
    return false; // No sigue al usuario
}

    public static int obtenerNumSeguidores(String username) {
        int index = obtenerIndiceUsuario(username);
        return numSeguidores[index];
    }

    public static int obtenerNumSeguidos(String username) {
        int index = obtenerIndiceUsuario(username);
        return numSeguidos[index];
    }

    // Obtener la lista de usuarios seguidos
    public static String[] obtenerUsuariosSeguidos(String usuarioActual) {
        int indiceActual = obtenerIndiceUsuario(usuarioActual);
        if (indiceActual == -1) {
            return new String[0]; // Retorna un arreglo vacío si el usuario no existe
        }

        // Retornar solo los usuarios seguidos, eliminando las entradas nulas
        String[] usuariosSeguidos = new String[numSeguidos[indiceActual]];
        System.arraycopy(seguidos[indiceActual], 0, usuariosSeguidos, 0, numSeguidos[indiceActual]);
        return usuariosSeguidos;
    }

    // Eliminar todos los seguidos y seguidores cuando se desactiva la cuenta
    public static void eliminarSeguidosYSeguidores(String username) {
        int indiceActual = obtenerIndiceUsuario(username);

        if (indiceActual == -1) {
            return;
        }

        // Eliminar de la lista de seguidores
        for (int i = 0; i < numSeguidores[indiceActual]; i++) {
            String seguidor = seguidores[indiceActual][i];
            int indiceSeguidor = obtenerIndiceUsuario(seguidor);

            if (indiceSeguidor != -1) {
                for (int j = 0; j < numSeguidos[indiceSeguidor]; j++) {
                    if (seguidos[indiceSeguidor][j].equals(username)) {
                        seguidos[indiceSeguidor][j] = seguidos[indiceSeguidor][numSeguidos[indiceSeguidor] - 1];
                        seguidos[indiceSeguidor][numSeguidos[indiceSeguidor] - 1] = null;
                        numSeguidos[indiceSeguidor]--;
                    }
                }
            }
        }

        // Eliminar la lista de seguidos de este usuario
        for (int i = 0; i < numSeguidos[indiceActual]; i++) {
            String seguido = seguidos[indiceActual][i];
            int indiceSeguido = obtenerIndiceUsuario(seguido);

            if (indiceSeguido != -1) {
                for (int j = 0; j < numSeguidores[indiceSeguido]; j++) {
                    if (seguidores[indiceSeguido][j].equals(username)) {
                        seguidores[indiceSeguido][j] = seguidores[indiceSeguido][numSeguidores[indiceSeguido] - 1];
                        seguidores[indiceSeguido][numSeguidores[indiceSeguido] - 1] = null;
                        numSeguidores[indiceSeguido]--;
                    }
                }
            }
        }
        // Limpiar las listas del usuario actual
        for (int i = 0; i < numSeguidores[indiceActual]; i++) {
            seguidores[indiceActual][i] = null;
        }
        numSeguidores[indiceActual] = 0;

        for (int i = 0; i < numSeguidos[indiceActual]; i++) {
            seguidos[indiceActual][i] = null;
        }
        numSeguidos[indiceActual] = 0;
    }
}