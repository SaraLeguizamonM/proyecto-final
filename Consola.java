import java.util.Scanner;

public class Consola {
    private Scanner scanner;

    public Consola() {
        this.scanner = new Scanner(System.in);
    }

    public String leerComando() {
        return scanner.nextLine().trim();
    }

    public void mostrarTablero(Tablero tablero) {
        tablero.imprimir();
    }

    public void mostrarMenu() { // Imprimir menu
        System.out.println("================================");
        System.out.println("||         BUSCAMINAS          ||");
        System.out.println("================================");
        System.out.println("||  1. Principiante: (9x9)     ||");
        System.out.println("||  2. Intermedio:   (16x16)   ||");
        System.out.println("||  3. Experto:      (16x30)   ||");
        System.out.println("||  4. Personalizado           ||");
        System.out.println("||  5. Ver historial(Score)    ||");
        System.out.println("||  0. Salir                   ||");
        System.out.println("================================");
        System.out.print("Elige una opción: "); // Remite a juego
    }

    // Verifca el estado de la partida
    public void mostrarResultado(String resultado) {
        if (resultado.equals("VICTORIA")) {
            System.out.println("Felicidades, ¡Ganaste! Todas las celdas descubiertas :)");
        } else if (resultado.equals("DERROTA")) {
            System.out.println("Lo lamento, ¡Perdiste! Pisaste una mina");
        } else {
            System.out.println("Partida abandonada");
        }
    }
}