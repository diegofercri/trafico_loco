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

        // Inicializar la matriz `pintar` con el tamaño del mapa
        pintar = new String[tamano][tamano];
        for (String[] row : pintar) {
            Arrays.fill(row, " "); // Inicializa todas las celdas con espacios en blanco
        }
    }

    /**
     * Genera las carreteras y los cruces en el mapa.
     */
    public void generarCarreteras() {
        Random random = new Random();
        int numCarreteras = tamano / 2; // Número total de carreteras (N/2 verticales y N/2 horizontales)
        int carreterasVerticalesGeneradas = 0;
        int carreterasHorizontalesGeneradas = 0;

        while (carreterasVerticalesGeneradas + carreterasHorizontalesGeneradas < numCarreteras) {
            Carretera nuevaCarretera = new Carretera();

            if (carreteras.size() == 0) {
                // Primera carretera: generada en una posición aleatoria
                Posicion posicionInicial, posicionFinal;
                boolean esVertical = random.nextBoolean(); // Decide si la carretera es vertical u horizontal

                if (esVertical) {
                    // Carretera vertical
                    int x = random.nextInt(tamano);
                    int y1 = random.nextInt(tamano - 1); // Asegura espacio para la longitud mínima
                    int y2 = random.nextInt(tamano - y1 - 1) + y1 + 1;
                    posicionInicial = new Posicion(x, y1);
                    posicionFinal = new Posicion(x, y2);
                    carreterasVerticalesGeneradas++;
                } else {
                    // Carretera horizontal
                    int y = random.nextInt(tamano);
                    int x1 = random.nextInt(tamano - 1); // Asegura espacio para la longitud mínima
                    int x2 = random.nextInt(tamano - x1 - 1) + x1 + 1;
                    posicionInicial = new Posicion(x1, y);
                    posicionFinal = new Posicion(x2, y);
                    carreterasHorizontalesGeneradas++;
                }

                nuevaCarretera.setPosiciones(posicionInicial, posicionFinal);
                carreteras.add(nuevaCarretera);
            } else {
                // Carreteras adicionales: basadas en una carretera existente
                Carretera carreteraBase = carreteras.get(random.nextInt(carreteras.size()));
                Posicion[] posicionesBase = carreteraBase.getPosiciones();
                Posicion puntoCruce = posicionesBase[random.nextInt(posicionesBase.length)];

                // Verificar si el punto de cruce ya existe
                boolean cruceExistente = false;
                for (Cruce cruce : cruces) {
                    if (cruce.getPosicion().equals(puntoCruce)) {
                        cruceExistente = true;
                        break;
                    }
                }

                if (!cruceExistente) {
                    // Crear un nuevo cruce y añadirlo al ArrayList de cruces
                    Cruce nuevoCruce = new Cruce(puntoCruce);
                    cruces.add(nuevoCruce);

                    // Generar una nueva carretera que cruce en este punto
                    Posicion posicionInicial, posicionFinal;
                    boolean esVertical = posicionesBase[0].getX() != posicionesBase[1].getX(); // Cambiar orientación

                    if (esVertical) {
                        // Carretera vertical
                        int x = puntoCruce.getX();
                        int y1 = random.nextInt(puntoCruce.getY());
                        int y2 = random.nextInt(tamano - puntoCruce.getY() - 1) + puntoCruce.getY() + 1;
                        posicionInicial = new Posicion(x, y1);
                        posicionFinal = new Posicion(x, y2);
                        carreterasVerticalesGeneradas++;
                    } else {
                        // Carretera horizontal
                        int y = puntoCruce.getY();
                        int x1 = random.nextInt(puntoCruce.getX());
                        int x2 = random.nextInt(tamano - puntoCruce.getX() - 1) + puntoCruce.getX() + 1;
                        posicionInicial = new Posicion(x1, y);
                        posicionFinal = new Posicion(x2, y);
                        carreterasHorizontalesGeneradas++;
                    }

                    nuevaCarretera.setPosiciones(posicionInicial, posicionFinal);
                    carreteras.add(nuevaCarretera);
                }
            }
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
