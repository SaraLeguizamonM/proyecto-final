package src.niveles;

// Clase base abstracta para definir las variables de cualquier nivel de juego
public abstract class NivelDificultad {
    protected String nombre;
    protected int filas;
    protected int columnas;
    protected int minas;

    // Métodos get para que otras clases puedan obtener-consultar los datos del
    // nivel
    public String getNombre() {
        return nombre;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getMinas() {
        return minas;
    }
}