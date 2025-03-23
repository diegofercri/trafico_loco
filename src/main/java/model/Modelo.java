package model;

import javax.swing.text.Caret;
import java.util.*;

public class Modelo {

    // Array que almacena las carreteras generadas
    private ArrayList<Carretera> carreterasClass;

    // Lista que almacena los cruces generados
    private ArrayList<Cruce> cruces;

    // Tamaño del mapa (20x20, 30x30, etc.)
    private int tamano;
    public boolean invalidFormation;

    // Matriz utilizada para representar visualmente el mapa
    String[][] pintar;

    /**
     * Método para "pintar" las carreteras en la matriz `pintar`.
     * Cada posición ocupada por una carretera se marca con el símbolo "#".
     */
    public void pintarCarreteras() {
        for (int i = 0; i <cruces.size() ; i++) {
            pintar[cruces.get(i).getX()][cruces.get(i).getY()] = "X";
        }

        for (int i = 0; i < carreterasClass.size(); i++) {
            Posicion[] currcarretera = carreterasClass.get(i).getPosiciones();

            // Marca las posiciones de la carretera actual con "#"
            for (int j = 0; j < currcarretera.length; j++) {
                if(pintar[currcarretera[j].getX()][currcarretera[j].getY()] == " "){
                    pintar[currcarretera[j].getX()][currcarretera[j].getY()] = "#";
                }


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
        carreterasClass = new ArrayList<>();
        cruces = new ArrayList<>();
        pintar = new String[tamano][tamano]; // NOTA: Aquí hay un problema potencial (ver observaciones).
        for (String[] row : pintar) {
            Arrays.fill(row, " "); // Inicializa todas las celdas con espacios en blanco.
        }
        invalidFormation = false;
    }

    /**
     * Genera las carreteras y los cruces en el mapa.
     */
    public void generarCarreteras() {
        LinkedList<Carretera> carreteras = new LinkedList<>();
        LinkedList<Integer> yOcupadas = new LinkedList<>(); // Almacena las filas ocupadas por carreteras verticales.
        LinkedList<Integer> xOcupadas = new LinkedList<>(); // Almacena las columnas ocupadas por carreteras horizontales.

        for (int i = 0; i < tamano-4 ; i++) {

            Carretera carretera = new Carretera();
            boolean esHorizontal = i % 2 == 0; // Alternar entre carreteras verticales y horizontales.

            carretera.setId(i);

            Random random = new Random(System.currentTimeMillis());
            int xInicial = random.nextInt(1, tamano / 2 - 1);
            int yInicial = random.nextInt(1, tamano / 2 - 1);
            int longitudCarretera;

            if (esHorizontal)
                longitudCarretera = random.nextInt(3, tamano - 1 - yInicial);
            else
                longitudCarretera = random.nextInt(3, tamano - 1 - xInicial);

            Posicion posicionInicial;

            if (i == 0) {
                posicionInicial = new Posicion(xInicial, yInicial);
            } else {
                boolean estaEnMismaFilaOColumna = true;

                do {

                    posicionInicial = new Posicion(random.nextInt(1, tamano-1), random.nextInt(1, tamano-1));
                    xInicial = posicionInicial.getX();
                    yInicial = posicionInicial.getY();
                    if(esHorizontal && !xOcupadas.contains(xInicial)){
                        System.out.println("x: " +  xInicial);
                        System.out.println("xOcupadas: " +  xOcupadas);
                        estaEnMismaFilaOColumna = false;
                    }
                    if(!esHorizontal && !yOcupadas.contains(yInicial)){
                        System.out.println("y: " + yInicial);
                        System.out.println("yOcupadas: " + yOcupadas);
                        estaEnMismaFilaOColumna = false;
                    }


                } while (estaEnMismaFilaOColumna);
            }

            int xFinal;
            int yFinal;



            if (esHorizontal) {
                carretera.setDireccion(Direccion.HORIZONTAL);
                xFinal = xInicial;

                if(yInicial>longitudCarretera){
                    yFinal = yInicial -longitudCarretera ;
                }
                else{
                    yFinal = yInicial + longitudCarretera - 1;
                }
                if (yFinal>= tamano){
                    yFinal = tamano-1;
                }


                xOcupadas.add(xInicial);
                xOcupadas.add(xInicial+1);
                xOcupadas.add(xInicial-1);


            } else {

                carretera.setDireccion(Direccion.VERTICAL);
                yFinal = yInicial;
                if(xInicial>longitudCarretera){
                    xFinal = xInicial - longitudCarretera ;

                }
                else{
                    xFinal = xInicial + longitudCarretera - 1;
                }
                if (xFinal>= tamano){
                    xFinal = tamano-1;
                }
                yOcupadas.add(yInicial);
                yOcupadas.add(yInicial+1);
                yOcupadas.add(yInicial-1);

            }

            carreteras.add(carretera);
            Posicion posicionFinal = new Posicion(xFinal, yFinal);
            carretera.setPosiciones(posicionInicial, posicionFinal);
            carreterasClass.add(carretera);
        }

        // Imprime las carreteras generadas para depuración
        for (Carretera carretera : carreteras) {

        }
    }
    /**
     * Obtiene las carreteras generadas.
     * @return Un array con las carreteras.
     */
    public ArrayList<Carretera> getCarreteras() {
        return carreterasClass;
    }

    public void getCommonPoints() {
        Map<Posicion, Integer> pointCount = new HashMap<>();

        // Iterate through each Carretera
        for (Carretera carretera : carreterasClass) {
            Posicion[] posiciones = carretera.getPosiciones();

            // Iterate through each point in the Carretera
            for (Posicion posicion : posiciones) {
                pointCount.put(posicion, pointCount.getOrDefault(posicion, 0) + 1);
            }
        }

        // Collect points that are common (count > 1)
        List<Posicion> commonPoints = new ArrayList<>();
        for (Map.Entry<Posicion, Integer> entry : pointCount.entrySet()) {
            if (entry.getValue() > 1) {

                cruces.add(new Cruce(entry.getKey()));

            }
        }
        if(cruces.size()<tamano/2){
            invalidFormation = true;
        }

    }

    /**
     * Establece las carreteras generadas.
     * @param carreteras Un array con las carreteras.
     */
    public void setCarreteras(ArrayList<Carretera> carreteras) {
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