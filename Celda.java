// Estructura base abstracta para cualquier celda del tablero
public abstract class Celda {
    protected int fila;
    protected int columna;
    protected boolean descubierta;
    protected boolean bandera;

    // Se usa un constructor para darle coordenadas a la celda al ser creada, es
    // decir, saber
    // en que número de fila y columna se encuentra
    public Celda(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.descubierta = false; // Cada una de las celdas empieza oculta por defecto
        this.bandera = false; // cada una de las celdas empieza sin bandera
    }

    // Métodos get para obtener-consultar la posición y estado de la celda
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean getDescubierta() {
        return descubierta;
    }

    // Se realiza un toggle, un método que funciona como un interruptor para la
    // bandera.
    // Solo se puede poner bandera si la celda no se ha descubierto,
    // es decir, para esa celda descubierto es false
    public void cambiarBandera() {
        if (!descubierta) {

            // Cambia el estado de la bandera entre true y false.
            // (si la celda tiene bandera, se la quita; si no tiene bandera, se la pone)
            this.bandera = !this.bandera;
        }
    }

    // Cambia el estado de la celda a descubierta cuando el jugador hace clic en
    // esta
    public void descubrir() {
        this.descubierta = true;
    }

    // Método abstracto donde cada clase hija de celda decide qué caracter mostrar
    // al imprimirse en consola, es decir, decide si mostrar una bandera, una bomba,
    // etc.
    public abstract char getSimbolo();
}