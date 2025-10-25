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
        int turno = 1;       
        while (usuario.tieneCartas() && pc.tieneCartas()) {
            siguienteTurno(turno++); 
        }
        
        mostrarGanador();
    }

    private void siguienteTurno(int turno) {
        System.out.println("\n--- Turno " + turno + " ---");
        
        
        System.out.println("Tus cartas (Total): " + usuario.getCantidadTotalCartas());
        System.out.println("Cartas PC (Total): " + pc.getCantidadTotalCartas());
        System.out.println("(Cartas en tu mano: " + usuario.getCantidadCartasEnMano() + ")");
        
        System.out.print("Presiona [Enter] para jugar tu carta...");
        scanner.nextLine();
        
        Carta cUsuario = usuario.jugarCarta();
        Carta cPC = pc.jugarCarta();
        
        mesa.clear();
        mesa.add(cUsuario);
        mesa.add(cPC);

        // 3. Se muestran las cartas
        System.out.println("Tú juegas: \t" + cUsuario);
        System.out.println("PC juega: \t" + cPC);

        // 4. Se comparan las cartas (Lógica sin cambios)
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
    
    /**
     * ¡¡¡ 3. MÉTODO MODIFICADO !!!
     * Se llama en un empate. Ahora comprueba si los jugadores
     * se quedan sin cartas DURANTE la guerra, al intentar jugar.
     */
    private void resolverGuerra() {
        System.out.println("¡¡¡ G U E R R A !!!");

        // 1. Carta boca abajo
        System.out.println("Ambos ponen 1 carta boca abajo...");
        Carta cUsuarioBocaAbajo = usuario.jugarCarta();
        Carta cPCBocaAbajo = pc.jugarCarta();

        // --- Chequeo de fin de juego (Boca Abajo) ---
        // Si el usuario no pudo jugar (se quedó sin cartas)
        if (cUsuarioBocaAbajo == null) {
            System.out.println("¡No tienes cartas para la guerra! PC gana todo.");
            if (cPCBocaAbajo != null) mesa.add(cPCBocaAbajo); // Añadir la carta que el PC sí jugó
            pc.recibirCartasGanadas(mesa);
            return; // Salir de la guerra (esto terminará el juego)
        }
        // Si el PC no pudo jugar
        if (cPCBocaAbajo == null) {
            System.out.println("¡PC no tiene cartas para la guerra! Ganas todo.");
            mesa.add(cUsuarioBocaAbajo); // Añadir la carta que tú sí jugaste
            usuario.recibirCartasGanadas(mesa);
            return;
        }

        // Si ambos pudieron, se añaden a la mesa
        mesa.add(cUsuarioBocaAbajo);
        mesa.add(cPCBocaAbajo);

        // 2. Carta boca arriba
        System.out.println("Y 1 carta boca arriba...");
        Carta cUsuarioBocaArriba = usuario.jugarCarta();
        Carta cPCBocaArriba = pc.jugarCarta();

        // --- Chequeo de fin de juego (Boca Arriba) ---
        if (cUsuarioBocaArriba == null) {
            System.out.println("¡No tienes segunda carta para la guerra! PC gana todo.");
            if (cPCBocaArriba != null) mesa.add(cPCBocaArriba);
            pc.recibirCartasGanadas(mesa);
            return;
        }
        if (cPCBocaArriba == null) {
            System.out.println("¡PC no tiene segunda carta para la guerra! Ganas todo.");
            mesa.add(cUsuarioBocaArriba);
            usuario.recibirCartasGanadas(mesa);
            return;
        }

        // Si ambos pudieron, se añaden a la mesa
        mesa.add(cUsuarioBocaArriba);
        mesa.add(cPCBocaArriba);
        
        System.out.println("Tú juegas (boca arriba): \t" + cUsuarioBocaArriba);
        System.out.println("PC juega (boca arriba): \t" + cPCBocaArriba);

        // 3. Comparar de nuevo
        if (cUsuarioBocaArriba.getNumero() > cPCBocaArriba.getNumero()) {
            System.out.println("¡Ganas la guerra! Te llevas " + mesa.size() + " cartas.");
            usuario.recibirCartasGanadas(mesa);
        } else if (cPCBocaArriba.getNumero() > cUsuarioBocaArriba.getNumero()) {
            System.out.println("PC gana la guerra. Se lleva " + mesa.size() + " cartas.");
            pc.recibirCartasGanadas(mesa);
        } else {
            System.out.println("¡¡¡ OTRO EMPATE !!! La mesa acumula " + mesa.size() + " cartas.");
            resolverGuerra(); // Se repite la lógica (Recursividad)
        }
    }
    
    /**
     * ¡¡¡ 4. MÉTODO MODIFICADO !!!
     * Se llama cuando el bucle 'jugar()' termina.
     * El ganador es quien todavía tiene cartas.
     */
    private void mostrarGanador() {
        System.out.println("\n--- ¡JUEGO TERMINADO! ---");
        
        int cartasUsuario = usuario.getCantidadTotalCartas();
        int cartasPC = pc.getCantidadTotalCartas();

        System.out.println("Conteo final:");
        System.out.println("Cartas " + usuario.getNombre() + ": " + cartasUsuario);
        System.out.println("Cartas " + pc.getNombre() + ": " + cartasPC);

        // El ganador es simplemente quien tiene más cartas (el otro tendrá 0)
        if (cartasUsuario > cartasPC) {
            System.out.println("\n¡¡¡ FELICIDADES, " + usuario.getNombre() + ", HAS GANADO !!! 🥳");
        } else if (cartasPC > cartasUsuario) {
            System.out.println("\n¡Oh no! Ha ganado " + pc.getNombre() + ". 🤖");
        } else {
            System.out.println("\n¡Es un EMPATE! 🤝");
        }
    }
}