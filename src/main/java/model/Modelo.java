package model;

import java.util.*;

public class Modelo {

    // Array que almacena las carreteras generadas
    private ArrayList<Carretera> carreteras;

    // Lista que almacena los cruces generados
    private ArrayList<Cruce> cruces;

    // Tamaño del mapa (20x20, 30x30, 40x40, etc.)
    private int tamano;

    // Matriz utilizada para representar visualmente el mapa
    String[][] pintar;

    /**
     * Mét0d0 para "pintar" las carreteras en la matriz `pintar`.
     * Cada posición ocupada por una carretera se marca con el símbolo "#".
     */
    public void pintarCarreteras() {
        for (int i = 0; i < carreteras.size(); i++) {
            Posicion[] currentCarretera = carreteras.get(i).getPosiciones();

            // Marca las posiciones de la carretera actual con "#"
            for (int j = 0; j < currentCarretera.length; j++) {
                pintar[currentCarretera[j].getX()][currentCarretera[j].getY()] = "#";
            }

            // Imprime la matriz `pintar` después de procesar cada carretera
            for (String[] strings : pintar) {
                System.out.println();
                for (String string : strings) {
                    System.out.print(string);
                }
            }
        }
    }

    /**
     * Constructor de la clase Modelo.
     * Inicializa el tamaño del mapa, las carreteras, los cruces y la matriz `pintar`.
     * @param tamano El tamaño del mapa (20x20, 30x30, etc.).
     */
    public Modelo(int tamano) {
        this.tamano = tamano;
        carreteras = new ArrayList<>(tamano / 2);
        cruces = new ArrayList<>(tamano / 2);
        pintar = new String[15][15]; // NOTA: Aquí hay un problema potencial (ver observaciones).
        for (String[] row : pintar) {
            Arrays.fill(row, " "); // Inicializa todas las celdas con espacios en blanco.
        }
    }

    /**
     * Genera las carreteras y los cruces en el mapa.
     */
    public void generarCarreteras() {
        // Listas enlazadas para almacenar las filas y columnas ocupadas por carreteras verticales y horizontales, respectivamente.
        LinkedList<Integer> yOcupadas = new LinkedList<>(); // Filas ocupadas por carreteras verticales.
        LinkedList<Integer> xOcupadas = new LinkedList<>(); // Columnas ocupadas por carreteras horizontales.

        // Genera `n / 2` carreteras (la mitad del tamaño del mapa/tablero).
        for (int i = 0; i < tamano / 2; i++) {
            Carretera carretera = new Carretera(); // Crea una nueva instancia de Carretera.

            // Alterna entre carreteras verticales y horizontales. Si `i` es par, la carretera será vertical.
            boolean esVertical = i % 2 == 0;

            System.out.println(esVertical); // Imprime si la carretera es vertical o no.

            carretera.setId(i); // Asigna un ID único a la carretera.

            // Generador de números aleatorios para determinar las posiciones iniciales y longitudes de las carreteras.
            Random random = new Random(System.currentTimeMillis());

            // Genera una posición inicial aleatoria dentro del rango permitido.
            int xInicial = random.nextInt(1, tamano / 2 - 1);
            int yInicial = random.nextInt(1, tamano / 2 - 1);

            int longitudCarretera; // Variable para almacenar la longitud de la carretera.

            // Determina la longitud de la carretera según su orientación.
            if (esVertical)
                longitudCarretera = random.nextInt(3, tamano - 1 - yInicial); // Longitud vertical.
            else
                longitudCarretera = random.nextInt(3, tamano - 1 - xInicial); // Longitud horizontal.

            Posicion posicionInicial; // Almacena la posición inicial de la carretera.

            // Si es la primera carretera, simplemente asigna la posición inicial generada.
            if (i == 0) {
                posicionInicial = new Posicion(xInicial, yInicial);
            } else {
                boolean estaEnMismaFilaOColumna = true; // Bandera para evitar que las carreteras se crucen.

                // Bucle para asegurarse de que la nueva carretera no se superponga con otras existentes.
                do {
                    Carretera carreteraRandom; // Carretera seleccionada aleatoriamente como referencia.
                    int indexCarreteraRandom;

                    // Selecciona una carretera aleatoria de las ya generadas.
                    indexCarreteraRandom = random.nextInt(0, carreteras.size());
                    carreteraRandom = carreteras.get(indexCarreteraRandom);

                    // Obtiene las posiciones de la carretera seleccionada.
                    Posicion[] posiciones = carreteraRandom.getPosiciones();
                    int indexPosicionRandom = random.nextInt(posiciones.length);
                    posicionInicial = posiciones[indexPosicionRandom];

                    // Verifica si la carretera es vertical u horizontal para aplicar restricciones específicas.
                    if (esVertical) {
                        for (int j = 0; j < cruces.size(); j++) {
                            // Asegura que la posición inicial no coincida con un cruce existente ni con una columna ocupada.
                            if (!posicionInicial.equals(cruces.get(j).getPosicion()) && !xOcupadas.contains(posicionInicial.getX())) {
                                estaEnMismaFilaOColumna = false;
                                break;
                            }
                        }
                    } else {
                        for (int j = 0; j < cruces.size(); j++) {
                            // Asegura que la posición inicial no coincida con un cruce existente ni con una fila ocupada.
                            if (!posicionInicial.equals(cruces.get(j).getPosicion()) && !yOcupadas.contains(posicionInicial.getY())) {
                                estaEnMismaFilaOColumna = false;
                                break;
                            }
                        }
                    }

                    // Actualiza las coordenadas iniciales basadas en la posición inicial seleccionada.
                    xInicial = posicionInicial.getX();
                    yInicial = posicionInicial.getY();

                } while (estaEnMismaFilaOColumna); // Repite hasta que se encuentre una posición válida.
            }

            int xFinal; // Coordenada X final de la carretera.
            int yFinal; // Coordenada Y final de la carretera.

            // Calcula las coordenadas finales según la orientación de la carretera.
            if (esVertical) {
                carretera.setDireccion(Direccion.VERTICAL); // Establece la dirección como vertical.
                xFinal = xInicial; // La coordenada X permanece igual.
                yFinal = yInicial + longitudCarretera - 1; // Calcula la coordenada Y final.
                yOcupadas.add(yInicial); // Registra la fila ocupada.
            } else {
                carretera.setDireccion(Direccion.HORIZONTAL); // Establece la dirección como horizontal.
                yFinal = yInicial; // La coordenada Y permanece igual.
                xFinal = xInicial + longitudCarretera - 1; // Calcula la coordenada X final.
                xOcupadas.add(xInicial); // Registra la columna ocupada.
            }

            // Agrega la carretera a la lista de carreteras generadas.
            carreteras.add(carretera);

            // Crea la posición final de la carretera.
            Posicion posicionFinal = new Posicion(xFinal, yFinal);

            // Imprime las posiciones inicial y final de la carretera para depuración.
            System.out.println("Las posiciones iniciales son x: " + posicionInicial.getX() + " y: " + posicionInicial.getY());
            System.out.println("Las posiciones finales son x: " + posicionFinal.getX() + " y: " + posicionFinal.getY());

            // Establece las posiciones inicial y final en la carretera.
            carretera.setPosiciones(posicionInicial, posicionFinal);

            // Crea un nuevo cruce en la posición inicial de la carretera.
            cruces.add(new Cruce(new Posicion(xInicial, yInicial)));

            // Guarda la carretera en el arreglo `carreterasClass`.
            carreteras.set(i, carretera);
        }

        // Imprime todas las carreteras generadas para depuración.
        for (Carretera carretera : carreteras) {
            System.out.println("Carretera " + carretera.getId() + ": " + Arrays.toString(carretera.getPosiciones()));
        }
    }

    /**
     * Obtiene las carreteras generadas.
     *
     * @return Un array con las carreteras.
     */
    public ArrayList<Carretera> getCarreteras() {
        return carreteras;
    }

    /**
     * Establece las carreteras generadas.
     * @param carreteras Un array con las carreteras.
     */
    public void setCarreteras(ArrayList<Carretera> carreteras) {
        this.carreteras = carreteras;
    }

    /**
     * Obtiene los cruces generados.
     * @return Una lista con los cruces.
     */
    public ArrayList<Cruce> getCruces() {
        return cruces;
    }

    /**
     * Establece los cruces generados.
     * @param cruces Una lista con los cruces.
     */
    public void setCruces(ArrayList<Cruce> cruces) {
        this.cruces = cruces;
    }

    /**
     * Obtiene el tamaño del mapa.
     * @return El tamaño del mapa.
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Establece el tamaño del mapa.
     * @param tamano El nuevo tamaño del mapa.
     */
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
}