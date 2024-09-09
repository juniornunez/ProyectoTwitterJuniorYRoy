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

}
