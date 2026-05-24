package src.celdas;

// Tipo de celda que representa una celda con mina.
// Extiende de celda, porque una celda con mina, es de por sí, una celda
public class CeldaMina extends Celda {

    // Constructor que crea la celda mina y le asigna su ubicación
    // en cordendas (número de fila y columna) en el tablero
    public CeldaMina(int fila, int columna) {
        super(fila, columna);
    }

    // Si el juego terminó y perdimos, este método ayuda a saber si esta celda con
    // mina explotó
    public boolean getExplotada() {
        return descubierta;
    }

    // El @override indica que se está reescribiendo el método abstracto de la clase
    // madre (Celda)
    // Se reescribe para decirle a esta clase hija que simbolos mostrar según el
    // caso
    @Override
    public char getSimbolo() {

        if (bandera) {
            // Si la celda tiene bandera, muestra la 'P', simbolo parecido a la forma de una
            // bandera
            return 'P';
        }

        if (descubierta) {
            // Si la mina explotó, es decir, que la celda con mina se descubrió,
            // se muestra un asterisco que simboliza a la mina
            return '*';
        }

        return '■'; // Si está oculta, se muestra vacía para no delatarse en el tablero
    }
}