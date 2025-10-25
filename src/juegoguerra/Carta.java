/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Programarcelo
 */
package juegoguerra; 

public class Carta {

    
    private String palo;
    private int numero; 

  
    public Carta(String palo, int numero) {
        this.palo = palo;
        this.numero = numero;
    }

    
    public int getNumero() {
        return numero;
    }

    public String getPalo() {
        return palo;
    }

    private String getNombreNumero() {
        switch (this.numero) {
            case 1:
                return "As";
            case 10:
                return "Sota";
            case 11:
                return "Caballo";
            case 12:
                return "Rey";
            default:
                return String.valueOf(this.numero); 
        }
    }

    
    @Override
    public String toString() {
        return getNombreNumero() + " de " + this.palo;
    }
}
