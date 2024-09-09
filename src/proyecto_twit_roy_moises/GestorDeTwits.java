/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_twit_roy_moises;

// aqui se alamacenaran todos los twits de todos los usuarios

/**
 *
 * @author royum
 */
public class GestorDeTwits {
    private static Twit[] todosLosTwits = new Twit[500];
    private static int numeroTwitsGlobal = 0;
    
    // aqui funcion para agregar un tweet al almacenamiento central
    public static void agregarTwit(Twit nuevoTwit){
        if(numeroTwitsGlobal < todosLosTwits.length){
            todosLosTwits[numeroTwitsGlobal] = nuevoTwit;
            numeroTwitsGlobal++;
        } else {
            System.out.println("no se pueden agregar mas twits");   
        }
    }
    
    // funcion para obtener todos los twits almacenados de cualquier cuenta.
    public static Twit[] obtenerTodoLosTwits(){
        Twit[] resultado = new Twit[numeroTwitsGlobal];
        System.arraycopy(todosLosTwits, 0, resultado, 0, numeroTwitsGlobal);
        return resultado;
    }
    
    
}
