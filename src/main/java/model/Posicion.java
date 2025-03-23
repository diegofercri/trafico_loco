package model;

import java.util.Objects;

/**
 * Clase que representa una posición en el mapa mediante coordenadas (x, y).
 */
public class Posicion {

    // Coordenada X de la posición
    private int x;

    // Coordenada Y de la posición
    private int y;

    /**
     * Constructor de la clase Posicion.
     * @param x La coordenada X de la posición.
     * @param y La coordenada Y de la posición.
     */
    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtiene la coordenada X de la posición.
     * @return La coordenada X.
     */
    public int getX() {
        return x;
    }

    /**
     * Establece la coordenada X de la posición.
     * @param x La nueva coordenada X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Obtiene la coordenada Y de la posición.
     * @return La coordenada Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Establece la coordenada Y de la posición.
     * @param y La nueva coordenada Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Representación en cadena de texto de la posición.
     * @return Una cadena con el formato "x: [valor] y: [valor]".
     */
    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }

    /**
     * Compara esta posición con otra para determinar si son iguales.
     * Dos posiciones son iguales si tienen las mismas coordenadas X e Y.
     * @param  o objeto a comparar.
     * @return true si las posiciones son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicion posicion = (Posicion) o;
        return x == posicion.x && y == posicion.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}