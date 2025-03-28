package model;

public class Cruce {

    // Posición del cruce en el mapa
    private Posicion posicion;

    /**
     * Constructor de la clase Cruce.
     * @param posicion La posición del cruce en el mapa.
     */
    public Cruce(Posicion posicion) {
        this.posicion = posicion;
    }

    /**
     * Obtiene la coordenada X de la posición del cruce.
     * @return La coordenada X de la posición del cruce.
     */
    public int getX() {
        return posicion.getX();
    }

    /**
     * Obtiene la coordenada Y de la posición del cruce.
     * @return La coordenada Y de la posición del cruce.
     */
    public int getY() {
        return posicion.getY();
    }

    /**
     * Obtiene la posición completa del cruce.
     * @return La posición del cruce como un objeto Posicion.
     */
    public Posicion getPosicion() {
        return posicion;
    }
}