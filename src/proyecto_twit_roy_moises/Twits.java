/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_twit_roy_moises;

// esta clasew sirve para administar los twits del usuario
/**
 *
 * @author royum
 */
public class Twits {

    private Twit[] twits;
    private int numeroTwits;
    private HashTag[] hash;

    // constructos de twitsss
    public Twits() {

        this.twits = new Twit[1000]; // mil tuwtsss
        this.numeroTwits = 0;

    }

    // funcion para public twitsssss]
    public void Publicartwit(String nombreUsuario, String contenido) {

        if (numeroTwits < 1000) {
            Twit nuevoTwit = new Twit(nombreUsuario, contenido);
            if (nuevoTwit.isContenidovalido()) {
                twits[numeroTwits] = nuevoTwit;
                numeroTwits++;
                GestorDeTwits.agregarTwit(nuevoTwit);

            } else {
                System.out.println(nuevoTwit.getContenido()); // aqui muestra el mensaje de un error
            }
        } else {
            System.out.println("No pudes publicar mas twits. se ha alcanzado el limite");
        }

    }

    public Twit[] getTwits() {
        return twits;
    }

    public int getNumeroTwits() {
        return numeroTwits;
    }

    
    public HashTag[] getHash() {
        return hash;
    }
    
     public void eliminarTwitsDeUsuario(String seguido) {
        for (int i = 0; i < numeroTwits; i++) {
            if (twits[i] != null && twits[i].getUsername().equals(seguido)) {
                // Mover los tweets hacia la izquierda para sobreescribir el eliminado
                for (int j = i; j < numeroTwits - 1; j++) {
                    twits[j] = twits[j + 1];
                }
                twits[numeroTwits - 1] = null;  // Eliminar el último tweet
                numeroTwits--;  // Reducir el contador de tweets
                i--;  // Volver a verificar el nuevo tweet en la misma posición
            }
        }
    }

    public void agregarTwitsDeUsuario(Twits twitsSeguido) {
        for (int i = 0; i < twitsSeguido.getNumeroTwits(); i++) {
            if (twitsSeguido.getTwits()[i] != null) {
                // Solo agregamos si hay espacio en el arreglo
                if (numeroTwits < twits.length) {
                    twits[numeroTwits] = twitsSeguido.getTwits()[i];
                    numeroTwits++;
                }
            }
        }
    }
    
    public void agregarTwitsDeSeguido(Twits twitsSeguido) {
        // Asegurarnos de no sobrepasar el límite de tweets
        for (int i = 0; i < twitsSeguido.getNumeroTwits() && numeroTwits < twits.length; i++) {
            twits[numeroTwits] = twitsSeguido.getTwits()[i];
            numeroTwits++;
        }
    }

    public void eliminarTwitsDeSeguido(String usernameSeguido) {
        int nuevoIndice = 0;

        // Recorre los tweets actuales del usuario
        for (int i = 0; i < numeroTwits; i++) {
            Twit twit = twits[i];
            // Si el tweet no pertenece al usuario que seguimos, lo mantenemos
            if (twit != null && !twit.getUsername().equals(usernameSeguido)) {
                twits[nuevoIndice] = twits[i];
                nuevoIndice++;
            }
        }

        // Actualiza el número de tweets
        numeroTwits = nuevoIndice;

        // Limpia el espacio restante en el arreglo
        for (int i = nuevoIndice; i < twits.length; i++) {
            twits[i] = null;
        }
    }

}
