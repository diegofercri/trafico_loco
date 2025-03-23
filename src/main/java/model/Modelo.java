package model;

import java.util.*;

public class Modelo {

    // Array que almacena las carreteras generadas
    private Carretera[] carreterasClass;

    // Lista que almacena los cruces generados
    private ArrayList<Cruce> cruces;

    // Tamaño del mapa (20x20, 30x30, etc.)
    private int tamano;

    // Matriz utilizada para representar visualmente el mapa
    String[][] pintar;

    /**
     * Mét0d0 para "pintar" las carreteras en la matriz `pintar`.
     * Cada posición ocupada por una carretera se marca con el símbolo "#".
     */
    public void pintarCarreteras() {
        for (int i = 0; i < carreterasClass.length; i++) {
            Posicion[] currcarretera = carreterasClass[i].getPosiciones();

            // Marca las posiciones de la carretera actual con "#"
            for (int j = 0; j < currcarretera.length; j++) {
                pintar[currcarretera[j].getX()][currcarretera[j].getY()] = "#";
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
        carreterasClass = new Carretera[tamano / 2];
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
        LinkedList<Carretera> carreteras = new LinkedList<>();
        LinkedList<Integer> yOcupadas = new LinkedList<>(); // Almacena las filas ocupadas por carreteras verticales.
        LinkedList<Integer> xOcupadas = new LinkedList<>(); // Almacena las columnas ocupadas por carreteras horizontales.

        for (int i = 0; i < tamano / 2; i++) {
            Carretera carretera = new Carretera();
            boolean esVertical = i % 2 == 0; // Alternar entre carreteras verticales y horizontales.
            System.out.println(esVertical);
            carretera.setId(i);

            Random random = new Random(System.currentTimeMillis());
            int xInicial = random.nextInt(1, tamano / 2 - 1);
            int yInicial = random.nextInt(1, tamano / 2 - 1);
            int longitudCarretera;

            if (esVertical)
                longitudCarretera = random.nextInt(3, tamano - 1 - yInicial);
            else
                longitudCarretera = random.nextInt(3, tamano - 1 - xInicial);

            Posicion posicionInicial;

            if (i == 0) {
                posicionInicial = new Posicion(xInicial, yInicial);
            } else {
                boolean estaEnMismaFilaOColumna = true;
                do {
                    Carretera carreteraRandom;
                    int indexCarreteraRandom;

                    indexCarreteraRandom = random.nextInt(0, carreteras.size());
                    carreteraRandom = carreteras.get(indexCarreteraRandom);
                    Posicion[] posiciones = carreteraRandom.getPosiciones();
                    int indexPosicionRandom = random.nextInt(posiciones.length);
                    posicionInicial = posiciones[indexPosicionRandom];
                    if(esVertical){
                    for (int j = 0; j < cruces.size(); j++) {
                        if (!posicionInicial.equals(cruces.get(j).getPosicion()) && !xOcupadas.contains(posicionInicial.getX())) {
                            estaEnMismaFilaOColumna = false;
                            break;
                        }
                    }
                    }
                    else{
                        for (int j = 0; j < cruces.size(); j++) {
                            if (!posicionInicial.equals(cruces.get(j).getPosicion()) && !yOcupadas.contains(posicionInicial.getY())) {
                                estaEnMismaFilaOColumna = false;
                                break;
                            }
                        }

                    }


                    xInicial = posicionInicial.getX();
                    yInicial = posicionInicial.getY();

                } while (estaEnMismaFilaOColumna);
            }

            int xFinal;
            int yFinal;

            if (esVertical) {
                carretera.setDireccion(Direccion.VERTICAL);
                xFinal = xInicial;
                yFinal = yInicial + longitudCarretera - 1;
                yOcupadas.add(yInicial);
            } else {
                carretera.setDireccion(Direccion.HORIZONTAL);
                yFinal = yInicial;
                xFinal = xInicial + longitudCarretera - 1;
                xOcupadas.add(xInicial);
            }

            carreteras.add(carretera);
            Posicion posicionFinal = new Posicion(xFinal, yFinal);
            System.out.println("Las posiciones iniciales son x: " + posicionInicial.getX() + " y: " + posicionInicial.getY());
            System.out.println("Las posiciones finales son x: " + posicionFinal.getX() + " y: " + posicionFinal.getY());
            carretera.setPosiciones(posicionInicial, posicionFinal);
            cruces.add(new Cruce(new Posicion(xInicial, yInicial)));
            carreterasClass[i] = carretera;
        }

        // Imprime las carreteras generadas para depuración
        for (Carretera carretera : carreteras) {
            System.out.println("Carretera " + carretera.getId() + ": " + Arrays.toString(carretera.getPosiciones()));
        }
    }

    /**
     * Obtiene las carreteras generadas.
     * @return Un array con las carreteras.
     */
    public Carretera[] getCarreteras() {
        return carreterasClass;
    }

    /**
     * Establece las carreteras generadas.
     * @param carreteras Un array con las carreteras.
     */
    public void setCarreteras(Carretera[] carreteras) {
        this.carreterasClass = carreteras;
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