/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoguerra;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author Programarcelo
 */

public class Jugador {
    private String nombre;
    private Queue<Carta> mano;
    
   
    private ArrayList<Carta> ganadas;

    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new LinkedList<>();
        this.ganadas = new ArrayList<>();
    }


    public Carta jugarCarta() {
       
        return this.mano.poll();
    }

    public void recibirCartaInicial(Carta carta) {
        this.mano.add(carta);
    }

    public void recibirCartasGanadas(ArrayList<Carta> cartasDeLaMesa) {
        this.ganadas.addAll(cartasDeLaMesa);
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadCartasEnMano() {
        return this.mano.size();
    }
    
    public int getCantidadCartasGanadas() {
        return this.ganadas.size();
    }
}
