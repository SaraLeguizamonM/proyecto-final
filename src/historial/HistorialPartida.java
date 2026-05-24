package src.historial;

import java.util.ArrayList;

public class HistorialPartida {
    private ArrayList<Partida> partidas;

    // Constructor
    public HistorialPartida() {
        this.partidas = new ArrayList<>();
    }

    // Agrega una nueva partida al historial al terminar cada partida
    public void agregar(Partida p) {
        partidas.add(p);
    }

    // Ordena el historial de menor a mayor tiempo
    public void ordenarPorTiempo() {
        bubbleSort(); // metodo de ordenamiento de burbuja
    }

    // Busca una partida segun el nivel usando BinarySearch, pero primero se ordena
    // alfabeticamente,
    // pero al terminar se restaura el orden por tiempo
    public Partida buscarPorDificultad(String dificultad) {
        ordenarPorDificultad(); // ordenar alfabeticamente antes de buscar
        int indice = binarySearch(dificultad);

        Partida resultado;
        if (indice == -1) {
            System.out.println("No se encontro partida con dificultad: " + dificultad);
            resultado = null;
        } else {
            resultado = partidas.get(indice);
        }
        bubbleSort(); // restaura el orden por tiempo despues
        return resultado;

    }

    // Se ordena alfabetifcamente segun los requerimientos donde se pide que la
    // lista este ordenada
    // antes de buscar
    private void ordenarPorDificultad() {
        if (partidas == null || partidas.size() < 2)
            return;
        for (int i = 0; i < partidas.size() - 1; i++) {
            for (int j = 0; j < partidas.size() - i - 1; j++) {
                int cmp = partidas.get(j).getDificultad().compareToIgnoreCase(partidas.get(j + 1).getDificultad());
                if (cmp > 0) {
                    Partida tmp = partidas.get(j);
                    partidas.set(j, partidas.get(j + 1));
                    partidas.set(j + 1, tmp);
                }
            }
        }
    }

    public void mostrar() {
        if (partidas.isEmpty()) {
            System.out.println("No hay partidas registradas");
        } else {
            System.out.println("====================================================================================");
            System.out.println("||                               HISTORIAL DE PARTIDAS                             ||");
            System.out.println("====================================================================================");
            for (int i = 0; i < partidas.size(); i++) {
                System.out.println("|| " + (i + 1) + ". " + partidas.get(i) + "  ||");
            }
            System.out.println("====================================================================================");
        }
    }

    // Metodos de ordenamiento y busqueda

    private void bubbleSort() {
        if (partidas == null || partidas.size() < 2) {
            return; // nada que ordenar
        }
        // se ordena segun el score por medio del metodo de la burbuja
        for (int i = 0; i < partidas.size() - 1; i++) {
            for (int j = 0; j < partidas.size() - i - 1; j++) {
                if (partidas.get(j).getTiempo() > partidas.get(j + 1).getTiempo()) {
                    Partida tmp = partidas.get(j);
                    partidas.set(j, partidas.get(j + 1));
                    partidas.set(j + 1, tmp);
                }
            }
        }
    }

    private int binarySearch(String dificultad) {
        if (partidas == null || partidas.isEmpty()) {
            return -1; // nada que buscar
        }
        int inicio = 0;
        int fin = partidas.size() - 1;
        int indice = -1;

        while (inicio <= fin && indice == -1) {
            int mid = (inicio + fin) / 2;
            int cmp = partidas.get(mid).getDificultad().compareToIgnoreCase(dificultad);
            if (cmp == 0) {
                indice = mid;
            } else if (cmp < 0) {
                inicio = mid + 1;
            } else {
                fin = mid - 1;
            }
        }
        return indice;
    }
}