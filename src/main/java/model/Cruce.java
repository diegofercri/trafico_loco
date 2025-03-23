package model;

public class Cruce {


    private Position posicion;

    public Cruce( Position posicion) {

        this.posicion = posicion;
    }
    public int getX(){
        return posicion.getX();
    }
    public int getY(){
        return posicion.getY();
    }
    public Position getPosicion(){
        return posicion;
    }
}
