public class Partida {
    private String dificultad;
    private int filas;
    private int columnas;
    private int minas;
    private long tiempoSegundos;
    private String resultado;

    public Partida(String dificultad, int filas, int columnas, int minas, long tiempoSegundos, String resultado) {
        this.dificultad = dificultad;
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
        this.tiempoSegundos = tiempoSegundos;
        this.resultado = resultado;
    }

    public long getTiempo() {
        return tiempoSegundos;
    }

    public String getDificultad() {
        return dificultad;
    }

    public String getResultado() {
        return resultado;
    }

    private String formatearTiempo() {
        long minutos = tiempoSegundos / 60;
        long segundos = tiempoSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos); // Como en el juego MM:SS
    }

    // El score es el mismo tiempo, y normalmente tiene sus categorias para sacar
    // marcas
    private String calcularRanking() {
        String ranking;

        if (resultado.equals("DERROTA") || resultado.equals("ABANDONADA")) {
            ranking = "Sin ranking";
        } else {
            switch (dificultad) { // Diferentes mediciones del ranking segun el nivel
                case "Principiante":
                    if (tiempoSegundos < 30)
                        ranking = "Excelente";
                    else if (tiempoSegundos < 60)
                        ranking = "Bueno";
                    else if (tiempoSegundos < 120)
                        ranking = "Regular";
                    else
                        ranking = "Malo";
                    break;

                case "Intermedio":
                    if (tiempoSegundos < 90)
                        ranking = "Excelente";
                    else if (tiempoSegundos < 180)
                        ranking = "Bueno";
                    else if (tiempoSegundos < 300)
                        ranking = "Regular";
                    else
                        ranking = "Malo";
                    break;

                case "Experto":
                    if (tiempoSegundos < 180)
                        ranking = "Excelente";
                    else if (tiempoSegundos < 360)
                        ranking = "Bueno";
                    else if (tiempoSegundos < 600)
                        ranking = "Regular";
                    else
                        ranking = "Malo";
                    break;

                default: // Nivel personalizado aplica en este
                    // Mientras más celdas y minas es necesario más tiempo
                    int totalCeldas = filas * columnas;
                    double porcentaje = (double) minas / totalCeldas;
                    // Base de tiempo según tamaño + dificultad por cantidad de minas
                    long limiteExcelente = (long) (totalCeldas * 0.5 * (1 + porcentaje));
                    long limiteBueno = (long) (totalCeldas * 1.0 * (1 + porcentaje));
                    long limiteRegular = (long) (totalCeldas * 2.0 * (1 + porcentaje));

                    if (tiempoSegundos < limiteExcelente)
                        ranking = "Excelente";
                    else if (tiempoSegundos < limiteBueno)
                        ranking = "Bueno";
                    else if (tiempoSegundos < limiteRegular)
                        ranking = "Regular";
                    else
                        ranking = "Malo";
                    break;
            }
        }

        return ranking;
    }

    @Override
    public String toString() {
        return dificultad + " | " +
                filas + "x" + columnas + " | " +
                "Minas: " + minas + " | " +
                "Tiempo: " + formatearTiempo() + " | " +
                resultado + " | " +
                calcularRanking();
    }
}