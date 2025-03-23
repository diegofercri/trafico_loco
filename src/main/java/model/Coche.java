package model;

public class Coche {

    // Identificador único del coche
    private int id;

    // Posición actual del coche en el mapa
    private Position position;

    // Velocidad del coche (número de casillas que se mueve por ciclo de simulación)
    private int velocidad;

    /**
     * Obtiene el identificador del coche.
     * @return El ID del coche.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del coche.
     * @param id El nuevo ID del coche.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la posición actual del coche.
     * @return La posición actual del coche.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Establece la posición actual del coche.
     * @param position La nueva posición del coche.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Obtiene la velocidad del coche.
     * @return La velocidad del coche (número de casillas por ciclo).
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Establece la velocidad del coche.
     * @param velocidad La nueva velocidad del coche.
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}