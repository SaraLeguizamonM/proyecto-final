import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private int filas;
    private int columnas;
    private int totalMinas;
    private Celda[][] celdas;
    private int descubiertasCont;

    public Tablero(int filas, int columnas, int totalMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.totalMinas = totalMinas;
        this.descubiertasCont = 0;
    }

    public void inicializar() {
        celdas = new Celda[filas][columnas]; // Se crea la matriz
        for (int i = 0; i < filas; i++) { // Se llena de celdas, con su respectivo tamaño
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new CeldaSegura(i, j); // Se llena de celdas seguras
            }
        }
    }

    public void colocarMinas() {
        Random rand = new Random();
        int cont = 0;
        int fila, columna;
        while (cont <= totalMinas) {
            fila = rand.nextInt(0, filas - 1);
            columna = rand.nextInt(0, columnas - 1);
            // verifica si hay una mina o no, en caso que si busca en otra posicion
            if (celdas[fila][columna] instanceof CeldaMina) { // el instanceof permite ver si el objeto pertenece a esa
                                                              // clase
                continue; // en caso que no, remplaza
            }
            celdas[fila][columna] = new CeldaMina(fila, columna); // Remplaza celdaSegura por celdaMina
            cont++;
        }
    }

    public void calcularAdyacentes() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // el instanceof permite ver si el objeto pertenece a esa clase
                if (!(celdas[i][j] instanceof CeldaSegura)) {
                    continue;
                }
                int cont = 0; // se reinicia por cada celda

                // Asignacion de los valores de las celdas adyacentes para mejor busqueda
                int[] externosFila = { -1, -1, -1, 0, 0, +1, +1, +1 };
                int[] externosCol = { -1, 0, +1, -1, +1, -1, 0, +1 };

                for (int k = 0; k < 8; k++) { // Va posicionandose en cada celda adyacente
                    int ni = i + externosFila[k];
                    int nj = j + externosCol[k];

                    if (ni >= 0 && ni < filas && nj >= 0 && nj < columnas) {
                        // el instanceof permite ver si el objeto pertenece a esa clase
                        if (celdas[ni][nj] instanceof CeldaMina) {
                            cont++;
                        }
                    }
                }
                ((CeldaSegura) celdas[i][j]).setMinasCercanas(cont); // asigna el resultado a la celda
            }
        }
    }

    public boolean descubrir(int fila, int columna) {
        Celda celda = celdas[fila][columna];
        // Verifica si ya esta descubierta o si hay una bandera, en caso que si no hace
        // nada
        if (celda.getDescubierta() || celda.bandera) {
            return false;
        }
        celda.descubrir();

        // En caso de que haya una mina, pierde
        if (celda instanceof CeldaMina) {
            return true;
        }

        descubiertasCont++; // se va sumando las que estan descubiertas que son seguras
        // Si no hay minas alrededor, se expande en cascada
        if (((CeldaSegura) celda).getMinasCercanas() == 0) {
            descubrirCascada(fila, columna);
        }
        return false;
    }

    private void descubrirCascada(int fila, int columna) {
        // Asignacion de los valores de las celdas adyacentes para mejor busqueda
        int[] externosFila = { -1, -1, -1, 0, 0, +1, +1, +1 };
        int[] externosCol = { -1, 0, +1, -1, +1, -1, 0, +1 };

        ArrayList<int[]> pendientes = new ArrayList<>();
        pendientes.add(new int[] { fila, columna }); // Crea una lista de celdas que aun no se ha revisado

        while (!pendientes.isEmpty()) { // revisa hasta que termine de procesar
            // Saca el ultimo elemento del arreglo
            int[] actual = pendientes.get(pendientes.size() - 1);
            pendientes.remove(pendientes.size() - 1);

            int f = actual[0];
            int c = actual[1];

            // recorrde las 8 celdas adyacentes (como en el metodo)
            for (int k = 0; k < 8; k++) {
                int ni = f + externosFila[k];
                int nj = c + externosCol[k];

                // Verificaciones
                if (ni < 0 || ni >= filas || nj < 0 || nj >= columnas) { // si existe una adyacente
                    continue;
                }
                Celda vecino = celdas[ni][nj];

                if (vecino.getDescubierta()) { // revisar si esta descubierto
                    continue;
                }
                if (vecino.bandera) { // revisar si tiene bandera
                    continue;
                }
                if (vecino instanceof CeldaMina) { // revisar si es una mina
                    continue;
                }

                // si pasa los filtros se cuenta como descubierta y se cuenta
                vecino.descubrir();
                descubiertasCont++;

                // si es vacia se guarda como pendiente para revisar despues
                if (((CeldaSegura) vecino).getMinasCercanas() == 0) {
                    pendientes.add(new int[] { ni, nj });
                }
            }
        }
    }

    public void cambiarBandera(int fila, int columna) {
        // verificacion para evitar coordenadas invalidas
        if ((fila >= 0) && (fila < filas) && (columna >= 0) && (columna < columnas)) {
            celdas[fila][columna].cambiarBandera(); // toda la logica esta en celda
        }
    }

    public boolean Victoria() {
        // verifica si descubrio todas las celdas seguras, y ver si gano
        int celdasSeguras = (filas * columnas) - totalMinas;
        return descubiertasCont == celdasSeguras;
    }

    public void revelarTodo() {
        // cuando el jugador pierde, muestra todo el tablero y minas
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j].descubrir();
            }
        }
    }

    public void imprimir() {
        // Encabezado de columnas
        System.out.print("    ");
        for (int j = 0; j < columnas; j++) {
            System.out.printf("%2d ", j); // %2d el espacio + int
        }
        System.out.println();

        // Línea de separacion
        System.out.print("   ");
        for (int j = 0; j < columnas; j++) {
            System.out.print("---");
        }
        System.out.println();

        // Filas del tablero
        for (int i = 0; i < filas; i++) {
            System.out.printf("%2d |", i); // número de fila
            for (int j = 0; j < columnas; j++) {
                Celda celda = celdas[i][j];
                char simbolo;

                if (!celda.getDescubierta() && celda.bandera) {
                    simbolo = 'P'; // tiene bandera
                } else if (!celda.getDescubierta()) {
                    simbolo = '■'; // celda oculta
                } else {
                    simbolo = celda.getSimbolo(); // descubierta
                }

                System.out.printf("%2c ", simbolo);// %2c el espacio + caracter
            }
            System.out.println();
        }
    }
}
