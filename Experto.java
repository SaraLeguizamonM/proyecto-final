// Configuración predefinida para el nivel más difícil del juego, es decir, experto
public class Experto extends NivelDificultad {
    public Experto() {
        // Según las reglas del buscaminas clásico, el tablero
        // para el nivel Experto debe ser de 16x30, es decir,
        // un tablero rectangular alargado, con 99 bombas,
        // así que se definen estos valores por defecto para este nivel
        this.nombre = "Experto";
        this.filas = 16;
        this.columnas = 30;
        this.minas = 99;
    }
}