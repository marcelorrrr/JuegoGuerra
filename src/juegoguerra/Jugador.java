/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoguerra;

import java.util.ArrayList;
import java.util.Collections;
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
        reponerManoDesdeGanadas();       
        return this.mano.poll();
    }
   
   private void reponerManoDesdeGanadas() {
        
        if (this.mano.isEmpty() && !this.ganadas.isEmpty()) {
            
            System.out.println("\n--- (" + this.nombre + " está barajando sus " + this.ganadas.size() + " cartas ganadas) ---");                        
            Collections.shuffle(this.ganadas);            
            this.mano.addAll(this.ganadas);      
            this.ganadas.clear();
        }
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
    
    public int getCantidadTotalCartas() {
        return this.mano.size() + this.ganadas.size();
    }
        
    public boolean tieneCartas() {
        return !this.mano.isEmpty() || !this.ganadas.isEmpty();
    }
}
