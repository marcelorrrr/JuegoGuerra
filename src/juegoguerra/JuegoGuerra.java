/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoguerra;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Programarcelo
 */
public class JuegoGuerra {

    private Mazo mazo;
    private Jugador usuario;
    private Jugador pc;
    private ArrayList<Carta> mesa; 
    private Scanner scanner;

    public static void main(String[] args) {
        
        JuegoGuerra miJuego = new JuegoGuerra();
        miJuego.jugar();
    }

    public JuegoGuerra() {
        iniciarJuego();
    }

    private void iniciarJuego() {
        System.out.println("--- ¡Bienvenido a La Guerra! ---");
        
        mazo = new Mazo(); 
        usuario = new Jugador("Usuario");
        pc = new Jugador("PC");
        mesa = new ArrayList<>();
        scanner = new Scanner(System.in);

        for (int i = 0; i < 20; i++) {
            usuario.recibirCartaInicial(mazo.sacarCarta());
            pc.recibirCartaInicial(mazo.sacarCarta());
        }
        
        System.out.println("Se han repartido las cartas. ¡Listos para empezar!");
    }

    
    
    public void jugar() {
        while (usuario.getCantidadCartasEnMano() > 0) {
            siguienteTurno();
        }
        
        mostrarGanador();
    }

    
    private void siguienteTurno() {
        System.out.println("\n--- Nuevo Turno ---");
        System.out.println("Tus cartas ganadas: " + usuario.getCantidadCartasGanadas());
        System.out.println("Cartas ganadas PC: " + pc.getCantidadCartasGanadas());
        System.out.println("Te quedan " + usuario.getCantidadCartasEnMano() + " cartas en la mano.");
        System.out.print("Presiona [Enter] para jugar tu carta...");
        scanner.nextLine(); // Espera la acción del usuario

        
        Carta cUsuario = usuario.jugarCarta();
        Carta cPC = pc.jugarCarta();

      
        if (cUsuario == null || cPC == null) {
            return; // 
        }
        
        
        mesa.clear(); 
        mesa.add(cUsuario);
        mesa.add(cPC);

        
        System.out.println("Tú juegas: \t" + cUsuario);
        System.out.println("PC juega: \t" + cPC);

        
        if (cUsuario.getNumero() > cPC.getNumero()) {
            
            System.out.println("¡Ganas la mano! Te llevas 2 cartas.");
            usuario.recibirCartasGanadas(mesa);
        } else if (cPC.getNumero() > cUsuario.getNumero()) {
            
            System.out.println("PC gana la mano. Se lleva 2 cartas.");
            pc.recibirCartasGanadas(mesa);
        } else {
            
            resolverGuerra();
        }
    }
    
    
    private void resolverGuerra() {
        System.out.println("¡¡¡ G U E R R A !!!");

       
        
        boolean usuarioPuedeGuerra = usuario.getCantidadCartasEnMano() >= 2;
        boolean pcPuedeGuerra = pc.getCantidadCartasEnMano() >= 2;

        if (!usuarioPuedeGuerra && !pcPuedeGuerra) {
            System.out.println("¡Ninguno puede ir a la guerra! Las cartas se descartan.");
            mesa.clear(); 
            return;
        }
        if (!usuarioPuedeGuerra) {
            System.out.println("No tienes cartas suficientes para la guerra. ¡PC gana todo!");
            pc.recibirCartasGanadas(mesa);
            return;
        }
        if (!pcPuedeGuerra) {
            System.out.println("PC no tiene cartas suficientes para la guerra. ¡Ganas todo!");
            usuario.recibirCartasGanadas(mesa);
            return;
        }
        
        
        
        System.out.println("Ambos ponen 1 carta boca abajo...");
        mesa.add(usuario.jugarCarta());
        mesa.add(pc.jugarCarta());

        
        System.out.println("Y 1 carta boca arriba...");
        Carta cUsuario = usuario.jugarCarta();
        Carta cPC = pc.jugarCarta();

        mesa.add(cUsuario);
        mesa.add(cPC);

        System.out.println("Tú juegas: \t" + cUsuario);
        System.out.println("PC juega: \t" + cPC);

        
        if (cUsuario.getNumero() > cPC.getNumero()) {
            System.out.println("¡Ganas la guerra! Te llevas " + mesa.size() + " cartas.");
            usuario.recibirCartasGanadas(mesa);
        } else if (cPC.getNumero() > cUsuario.getNumero()) {
            System.out.println("PC gana la guerra. Se lleva " + mesa.size() + " cartas.");
            pc.recibirCartasGanadas(mesa);
        } else {
            
            System.out.println("¡¡¡ OTRO EMPATE !!!");
            resolverGuerra(); 
        }
    }
    
   
    private void mostrarGanador() {
        System.out.println("\n--- ¡JUEGO TERMINADO! ---");
        System.out.println("Se han jugado todas las cartas de la mano inicial.");
        
        int cartasUsuario = usuario.getCantidadCartasGanadas();
        int cartasPC = pc.getCantidadCartasGanadas();

        System.out.println("Conteo final:");
        System.out.println("Cartas " + usuario.getNombre() + ": " + cartasUsuario);
        System.out.println("Cartas " + pc.getNombre() + ": " + cartasPC);

        if (cartasUsuario > cartasPC) {
            System.out.println("\n¡¡¡ FELICIDADES, " + usuario.getNombre() + ", HAS GANADO !!! 🥳");
        } else if (cartasPC > cartasUsuario) {
            System.out.println("\n¡Oh no! Ha ganado " + pc.getNombre() + ". 🤖");
        } else {
            System.out.println("\n¡Es un EMPATE! 🤝");
        }
    }
}
