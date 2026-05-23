import java.util.Scanner;

public class Consola {
    private Scanner sc;

    public Consola() {
        this.sc = new Scanner(System.in);
    }

    public String leerComando() {
        return sc.nextLine().trim();
    }

    public void mostrarTablero(Tablero tablero) {
        tablero.imprimir();
    }

    public void mostrarMenu() { // Imprimir menu
        System.out.println("                                ");
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
        System.out.print("Elige una opcion: "); // Remite a juego
        System.out.println("                                ");

    }

    // Muestra las acciones disponibles en cada turno
    public void mostrarAcciones() {
        System.out.println("                       ");
        System.out.println("¿Qué deseas hacer?");
        System.out.println("====================");
        System.out.println("1. Descubrir celda  ");
        System.out.println("2. Poner/quitar     ");
        System.out.println("0. Abandonar partida");
        System.out.println("=====================");
        System.out.print("Opción: ");
        System.out.println("                        ");

    }

    // Pide fila y columna por separado y valida que sean numeros validos
    public int[] leerCoordenadas() {
        int fila = -1;
        int col = -1;

        // Pedir fila hasta que sea valida
        boolean filaValida = false;
        while (!filaValida) {
            try {
                System.out.print("Fila: ");
                fila = Integer.parseInt(sc.nextLine().trim());
                filaValida = true;
            } catch (Exception e) {
                System.out.println("Ingresa solo numeros enteros");
            }
        }

        // Pedir columna hasta que sea válida
        boolean colValida = false;
        while (!colValida) {
            try {
                System.out.print("Columna: ");
                col = Integer.parseInt(sc.nextLine().trim());
                colValida = true;
            } catch (Exception e) {
                System.out.println("Ingresa solo numeros enteros");
            }
        }
        return new int[] { fila, col };
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