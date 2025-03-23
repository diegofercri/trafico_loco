import model.Modelo;

/**
 * Clase principal que actúa como punto de entrada para la simulación.
 */
public class Simulador {

    /**
     * Mét0d0 principal que inicia la simulación.
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        // Crea un modelo con un tamaño de mapa de 10x10
        Modelo modelo = new Modelo(10);

        // Genera las carreteras y los cruces en el mapa

        do {
            modelo = new Modelo(10);
            modelo.generarCarreteras();
            modelo.getCommonPoints();
        } while (modelo.invalidFormation);



        // Pinta las carreteras en la consola
        modelo.pintarCarreteras();

    }
}