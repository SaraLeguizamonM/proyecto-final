// Nivel especial donde el usuario elige el tamaño de las filas y las columnas a su gusto, al
// igual que elige la cantidad de minas a su gusto (la cantidad de minas no puede ser superior
//a la mitad de las celdas totales)
public class Personalizado extends NivelDificultad {

    public Personalizado() {
        this.nombre = "Personalizado";
    }

    // Recibe los datos ingresados por usuario, y lanza excepciones si estos
    // no cumplen con los rangos permitidos
    public void setDimensiones(int filas, int columnas, int minas) {
        
        // Validamos el valor de las filas antes de asignarlas, y se lanza la excepcion
        // en caso de no cumplir con la restriccion
        if (filas < 3 || filas > 30) {
            throw new IllegalArgumentException("El valor de las filas no puede ser menor a 3 o mayor a 30, reingrese nuevamente un valor válido entre 3 y 30.");
        }
        this.filas = filas;

        // Validamos el valor de las columnas antes de asignarlas, y se lanza la excepcion
        // en caso de no cumplir con la restriccion
        if (columnas < 3 || columnas > 30) {
            throw new IllegalArgumentException("El valor de las columnas no puede ser menor a 3 o mayor a 30, reingrese nuevamente un valor válido entre 3 y 30.");
        }
        this.columnas = columnas;

        // Calculamos la cantidad total de celdas para controlar el límite de bombas
        // según el tamaño de la cuadrícula
        int totalCeldas = this.filas * this.columnas;
        int maximoMinasPermitido = (totalCeldas / 2);

        // Validamos que la cantidad de minas sea congruente con el tamaño del tablero,
        // es decir, que cumpla con la condicion
        if (minas <= 0 || minas > maximoMinasPermitido) {
            throw new IllegalArgumentException("La cantidad de minas debe ser mayor a 0 y no puede superar la mitad del tablero, que es(" + maximoMinasPermitido + "), reingrese nuevamente el valor.");
        }
        this.minas = minas;
    }
}