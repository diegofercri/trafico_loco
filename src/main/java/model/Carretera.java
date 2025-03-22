package model;

public class Carretera {

    private int id;
    private Position[] posiciones;
    private Cruce[] cruces;
    private Direccion direccion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position[] getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(Position posicionInicial, Position posicionFinal) {
        if (this.direccion == Direccion.VERTICAL) {
            int diferencia = posicionFinal.getY() - posicionInicial.getY();
            posiciones = new Position[diferencia + 1];
            int x = posicionInicial.getX();
            for (int i = 1; i < Math.abs(diferencia); i++) {
                System.out.println("EN EL IF " + i);
                int y = posicionInicial.getY() + i;
                Position posicion = new Position(x, y);
                posiciones[i] = posicion;
            }
            posiciones[0] = posicionInicial;
            posiciones[posiciones.length - 1] = posicionFinal;
        }
        else {
            int diferencia = posicionFinal.getX() - posicionInicial.getX();
            posiciones = new Position[diferencia + 1];
            int y = posicionInicial.getY();
            for (int i = 1; i < Math.abs(diferencia); i++) {
                System.out.println("EN EL ELSE " + i);
                int x = posicionInicial.getX() + i;
                Position posicion = new Position(x, y);
                posiciones[i] = posicion;
            }
            posiciones[0] = posicionInicial;
            posiciones[posiciones.length - 1] = posicionFinal;
        }
    }

    public Cruce[] getCruces() {
        return cruces;
    }

    public void setCruces(Cruce[] cruces) {
        this.cruces = cruces;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    private void generarPosicionesIntermedias() {

    }
}
