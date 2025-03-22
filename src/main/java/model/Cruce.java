package model;

public class Cruce {

    private Carretera carreteraVertical;
    private Carretera carreteraHorizontal;
    private Position posicion;

    public Cruce(Carretera carreteraVertical, Carretera carreteraHorizontal, Position posicion) {
        this.carreteraVertical = carreteraVertical;
        this.carreteraHorizontal = carreteraHorizontal;
        this.posicion = posicion;
    }
}
