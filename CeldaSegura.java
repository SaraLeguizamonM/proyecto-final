// Tipo de celda que representa a las celdas normales que muestran
// los números de la cantidad de minas alrededor
public class CeldaSegura extends Celda {
    private int minasCercanas; // Guarda la cantidad de minas alrededor de la celda (valor de 0 a 8)

    // Constructor que se encarga de pasar las coordenadas a la clase madre Celda
    public CeldaSegura(int fila, int columna) {
        super(fila, columna);

        // La cantidad de minas cercanas inicia en cero hasta que el tablero
        // calcule la cantidad de minas alrededor de la celda
        this.minasCercanas = 0;
    }

    // Método para get obtener el valor de las minas cercanas,
    // y método set modificar el contador de minas vecinas
    public int getMinasCercanas() {
        return minasCercanas;
    }

    public void setMinasCercanas(int minas) {
        if (minas >= 0 && minas <= 8) {
            this.minasCercanas = minas;
        }
    }

    // El @override indica que se está reescribiendo el método abstracto de la clase
    // madre (Celda)
    // Se reescribe para decirle a esta clase hija que simbolos mostrar según el
    // caso
    @Override
    public char getSimbolo() {

        // Si tiene la celda tiene bandera pero el tablero la descubrió al perder,
        // es decir, que la bandera estaba mal puesta, muestra una X indicando
        // que en la celda había una bandera mal puesta
        if (bandera && descubierta) {
            return 'X'; // Muestra la X de bandera incorrecta
        }

        if (bandera) {
            // Si la celda tiene bandera y sigue oculta durante la partida normal, es decir,
            // sin perder
            return 'P';
        }

        // Si la casilla ya fue descubierta por el jugador o la cascada,
        // y el valor de minas cercanas de esa celda es 0
        if (descubierta) {
            if (minasCercanas == 0) {
                return '0'; // Muestra el cero explícito para las zonas vacías donde no hay mina
            }

            // Mediente un switch (que intercambie de enteros a char), el número
            // correspondiente
            // a minas cercanas se covierte de un entero a un caracter para que el método
            // abstracto
            // pueda funcionar
            switch (minasCercanas) {
                case 1:
                    return '1';
                case 2:
                    return '2';
                case 3:
                    return '3';
                case 4:
                    return '4';
                case 5:
                    return '5';
                case 6:
                    return '6';
                case 7:
                    return '7';
                case 8:
                    return '8';
                default:
                    return '0';
            }
        }

        return '■'; // Si la celda está oculta y no tiene bandera, se muestra vacía
    }
}