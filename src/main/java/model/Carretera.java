package model;

public class Carretera {

    // Identificador único de la carretera
    private int id;

    // Array que almacena las posiciones (coordenadas) que conforman la carretera
    private Position[] posiciones;

    // Array que almacena los cruces asociados a esta carretera
    private Cruce[] cruces;

    // Dirección de la carretera: vertical u horizontal
    private Direccion direccion;

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
    public Position[] getPosiciones() {
        return posiciones;
    }

    /**
     * Genera las posiciones intermedias de la carretera entre dos puntos dados.
     * @param posicionInicial La posición inicial de la carretera.
     * @param posicionFinal La posición final de la carretera.
     */
    public void setPosiciones(Position posicionInicial, Position posicionFinal) {

        // Si la carretera es vertical, calcula las posiciones basándose en la diferencia de las coordenadas Y
        if (this.direccion == Direccion.VERTICAL) {
            int diferencia = Math.abs(posicionFinal.getY() - posicionInicial.getY());
            posiciones = new Position[diferencia + 1]; // Crea un array para almacenar las posiciones
            int x = posicionInicial.getX(); // La coordenada X es constante en una carretera vertical

            // Genera las posiciones intermedias
            for (int i = 1; i < diferencia; i++) {
                int y = posicionInicial.getY() + i;
                Position posicion = new Position(x, y);
                posiciones[i] = posicion;
            }

            // Asigna las posiciones inicial y final
            posiciones[0] = posicionInicial;
            posiciones[posiciones.length - 1] = posicionFinal;
        }
        // Si la carretera es horizontal, calcula las posiciones basándose en la diferencia de las coordenadas X
        else {
            int diferencia = posicionFinal.getX() - posicionInicial.getX();
            posiciones = new Position[diferencia + 1]; // Crea un array para almacenar las posiciones
            int y = posicionInicial.getY(); // La coordenada Y es constante en una carretera horizontal

            // Genera las posiciones intermedias
            for (int i = 1; i < diferencia; i++) {
                int x = posicionInicial.getX() + i;
                Position posicion = new Position(x, y);
                posiciones[i] = posicion;
            }

            // Asigna las posiciones inicial y final
            posiciones[0] = posicionInicial;
            posiciones[posiciones.length - 1] = posicionFinal;
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

    /**
     * Establece la dirección de la carretera.
     * @param direccion La nueva dirección de la carretera.
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la dirección de la carretera.
     * @return La dirección de la carretera (vertical u horizontal).
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Mét0d0 destinado a generar las posiciones intermedias de la carretera.
     * Actualmente no implementado.
     */
    private void generarPosicionesIntermedias() {
        // TODO: Implementar lógica para generar posiciones intermedias
    }
}