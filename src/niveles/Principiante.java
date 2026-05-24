package src.niveles;

// Configuración predefinida para el nivel más fácil, es decir, principiante
public class Principiante extends NivelDificultad {
    public Principiante() {
        // Según las reglas del buscaminas clásico, el tablero
        // para el nivel principiante debe ser de 9x9 con 10 bombas,
        // así que se definen estos valores por defecto para este nivel

        this.nombre = "Principiante";
        this.filas = 9;
        this.columnas = 9;
        this.minas = 10;
    }
}