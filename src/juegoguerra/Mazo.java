/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoguerra;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Programarcelo
 */

public class Mazo {

       private ArrayList<Carta> cartas;

    public Mazo() {
        this.cartas = new ArrayList<>();
        crearMazo(); 
        barajar();   
    }


    private void crearMazo() {
        String[] palos = {"Oros", "Copas", "Espadas", "Bastos"};
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12}; 

        
        for (String palo : palos) {
            for (int numero : numeros) {
                this.cartas.add(new Carta(palo, numero));
            }
        }
    }

    public void barajar() {
        
        Collections.shuffle(this.cartas);
    }

    public Carta sacarCarta() {
        if (!quedanCartas()) {
            System.out.println("¡No quedan cartas en el mazo!");
            return null; 
        }
        
        return this.cartas.remove(0);
    }

    public boolean quedanCartas() {
        
        return !this.cartas.isEmpty();
    }
}
