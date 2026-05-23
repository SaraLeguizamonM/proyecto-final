public class Juego {
    private Tablero tablero;
    private NivelDificultad nivel;
    private HistorialPartida historial;
    private long tiempoInicio;
    private Consola consola;

    public Juego() {
        this.historial = new HistorialPartida();
        this.consola = new Consola();
    }

    public void iniciar() { // inicio el juego con la creacion segun su tipo de dificultades
        boolean corriendo = true;
        while (corriendo) {
            consola.mostrarMenu();
            String opcion = consola.leerComando();
            switch (opcion) {
                case "1":
                    iniciarPartida(new Principiante());
                    break;
                case "2":
                    iniciarPartida(new Intermedio());
                    break;
                case "3":
                    iniciarPartida(new Experto());
                    break;
                case "4":
                    iniciarPartidaPersonalizada();
                    break;
                case "5":
                    mostrarHistorial();
                    break;
                case "0":
                    corriendo = false;
                    break;
                default:
                    System.out.println("Opcion invalida, intenta de nuevo");
            }
        }
    }

    public void iniciarPartida(NivelDificultad nivel) {
        this.nivel = nivel;
        this.tablero = new Tablero(
                nivel.getFilas(),
                nivel.getColumnas(),
                nivel.getMinas());
        tablero.inicializar();
        tablero.colocarMinas();
        tablero.calcularAdyacentes();
        this.tiempoInicio = System.currentTimeMillis();

        boolean jugando = true;
        while (jugando) {
            consola.mostrarTablero(tablero); // (desde consola)
            consola.mostrarAcciones(); // muestra el menu de acciones (desde consola)
            String opcion = consola.leerComando();
            jugando = procesarTurno(opcion);
        }
    }

    public boolean procesarTurno(String opcion) {
        boolean sigue = true;
        switch (opcion) {
            case "1":
                // Descubrir celda
                int[] coordD = consola.leerCoordenadas();
                int filaD = coordD[0];
                int colD = coordD[1];
                boolean esMina = tablero.descubrir(filaD, colD);
                if (esMina) {
                    tablero.revelarTodo();
                    consola.mostrarTablero(tablero);
                    finalizarPartida("DERROTA");
                    sigue = false;
                } else if (tablero.Victoria()) {
                    consola.mostrarTablero(tablero);
                    finalizarPartida("VICTORIA");
                    sigue = false;
                }
                break;

            case "2":
                // Poner o quitar bandera
                int[] coordB = consola.leerCoordenadas();
                tablero.cambiarBandera(coordB[0], coordB[1]);
                break;

            case "0":
                // Salir
                finalizarPartida("ABANDONADA");
                sigue = false;
                break;

            default:
                System.out.println("Opcion invalida, elige 1, 2 o 0");
        }
        return sigue;
    }

    // Se acaba la partida y se manda la partida al historial
    public void finalizarPartida(String resultado) {
        long tiempoSegundos = (System.currentTimeMillis() - tiempoInicio) / 1000;
        consola.mostrarResultado(resultado);
        Partida partida = new Partida(
                nivel.getNombre(),
                nivel.getFilas(),
                nivel.getColumnas(),
                nivel.getMinas(),
                tiempoSegundos,
                resultado);
        historial.agregar(partida);
    }

    // Metodo para mostrar el historial
    public void mostrarHistorial() {
        historial.ordenarPorTiempo();
        historial.mostrar();
    }

    // Partida personalizada con sus respectivas restrincciones
    private void iniciarPartidaPersonalizada() {
        Personalizado p = new Personalizado();
        try {
            System.out.print("Filas (3-30): ");
            int f = Integer.parseInt(consola.leerComando());
            System.out.print("Columnas (3-30): ");
            int c = Integer.parseInt(consola.leerComando());
            System.out.print("Minas: ");
            int m = Integer.parseInt(consola.leerComando());
            p.setDimensiones(f, c, m);
            iniciarPartida(p);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Valor invalido, ingresa solo numeros.");
        }
    }
}
