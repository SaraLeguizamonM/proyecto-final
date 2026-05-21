// Configuración predefinida para el nivel intermedio
public class Intermedio extends NivelDificultad {
    public Intermedio() {
        // Según las reglas del buscaminas clásico, el tablero
        // para el nivel intermedio debe ser de 16x16 con 40 bombas,
        // así que se definen estos valores por defecto para este nivel
        
        this.nombre = "Intermedio";
        this.filas = 16;
        this.columnas = 16;
        this.minas = 40;
    }
}