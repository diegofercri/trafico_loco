package model;

public class Carretera {

    // Identificador único de la carretera
    private int id;

    // Array que almacena las posiciones (coordenadas) que conforman la carretera
    private Posicion[] posiciones;

    // Array que almacena los cruces asociados a esta carretera
    private Cruce[] cruces;

    /**
     * Obtiene el identificador de la carretera.
     * @return El ID de la carretera.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la carretera.
     * @param id El nuevo ID de la carretera.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene las posiciones que conforman la carretera.
     * @return Un array con las posiciones de la carretera.
     */
    public Posicion[] getPosiciones() {
        return posiciones;
    }

    /**
     * Genera las posiciones intermedias de la carretera entre dos puntos dados.
     * @param posicionInicial La posición inicial de la carretera.
     * @param posicionFinal La posición final de la carretera.
     */
    public void setPosiciones(Posicion posicionInicial, Posicion posicionFinal) {

        // En el if podeis dejar la que ya hay o usar esta otra condición:
        // this.direccion == Direccion.VERTICAL

        // Diferenciamos entre orientacion vertical y horizontal
        // Carretera Vertical
        if (posicionInicial.getX() == posicionFinal.getX()) {

            // Calcula la diferencia absoluta entre las coordenadas Y
            int diferencia = Math.abs(posicionFinal.getY() - posicionInicial.getY());

            // Crea un array para almacenar las posiciones
            posiciones = new Posicion[diferencia + 1];

            // La coordenada X es constante en una carretera vertical
            int x = posicionInicial.getX();

            // Determina la dirección: incrementar (+1) o decrementar (-1)
            int direccion = (posicionFinal.getY() > posicionInicial.getY()) ? 1 : -1;

            // Genera las posiciones intermedias
            for (int i = 0; i <= diferencia; i++) {
                int y = posicionInicial.getY() + i * direccion;
                Posicion posicion = new Posicion(x, y);
                posiciones[i] = posicion;
            }

        // Carretera Horizontal
        } else {

            // Calcula la diferencia absoluta entre las coordenadas X
            int diferencia = Math.abs(posicionFinal.getX() - posicionInicial.getX());

            // Crea un array para almacenar las posiciones
            posiciones = new Posicion[diferencia + 1];

            // La coordenada Y es constante en una carretera vertical
            int y = posicionInicial.getY();

            // Determina la dirección: incrementar (+1) o decrementar (-1)
            int direccion = (posicionFinal.getX() > posicionInicial.getX()) ? 1 : -1;

            // Genera las posiciones intermedias
            for (int i = 0; i <= diferencia; i++) {
                int x = posicionInicial.getX() + i * direccion;
                Posicion posicion = new Posicion(x, y);
                posiciones[i] = posicion;
            }
        }
    }

    /**
     * Obtiene los cruces asociados a la carretera.
     * @return Un array con los cruces de la carretera.
     */
    public Cruce[] getCruces() {
        return cruces;
    }

    /**
     * Establece los cruces asociados a la carretera.
     * @param cruces Un array con los cruces de la carretera.
     */
    public void setCruces(Cruce[] cruces) {
        this.cruces = cruces;
    }

}