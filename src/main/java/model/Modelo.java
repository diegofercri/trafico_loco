package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Modelo {

    private Carretera[] carreteras;

    private ArrayList<Cruce> cruces;

    private int tamano;

    public Modelo(int tamano) {
        this.tamano = tamano;
        carreteras = new Carretera[tamano / 2];
        cruces = new ArrayList<>(tamano / 2);
        // generarCarreteras();
    }

    public void generarCarreteras() {
        LinkedList<Carretera> carreterasVerticales = new LinkedList<>();
        LinkedList<Carretera> carreterasHorizontales = new LinkedList<>();
        LinkedList<Integer> yOcupadas = new LinkedList<>();
        LinkedList<Integer> xOcupadas = new LinkedList<>();
        for (int i = 0; i < tamano / 2; i++) {
            Carretera carretera = new Carretera();
            boolean esVertical = i % 2 == 0;
            carretera.setId(i);
            Random random = new Random(System.currentTimeMillis());
            int xInicial = random.nextInt(1, tamano/2 - 1);
            int yInicial = random.nextInt(1, tamano/2 - 1);
            int longitudCarretera;
            if (esVertical) 
                longitudCarretera = random.nextInt(3, tamano - 1 - yInicial);
            else
                longitudCarretera = random.nextInt(3, tamano - 1 - xInicial);
            Position posicionInicial;
            if (i == 0) {
                posicionInicial = new Position(xInicial, yInicial);
            } else {
                boolean estaEnMismaFilaOColumna = true;
                do {
                    Carretera carreteraRandom; 
                    int indexCarreteraRandom;
                    if (esVertical) {
                        indexCarreteraRandom = random.nextInt(carreterasHorizontales.size());
                        carreteraRandom = carreterasHorizontales.get(indexCarreteraRandom);
                    }
                    else {
                        indexCarreteraRandom = random.nextInt(carreterasVerticales.size());
                        carreteraRandom = carreterasVerticales.get(indexCarreteraRandom);
                    }

                    Position[] posiciones = carreteraRandom.getPosiciones();
                    int indexPosicionRandom = random.nextInt(posiciones.length);
                    posicionInicial = posiciones[indexPosicionRandom];
                    if (esVertical) {
                        boolean estaEnVerticalOcupada = false;
                        for (int y: yOcupadas) {
                            if (posicionInicial.getY() == y)
                                estaEnVerticalOcupada = true;
                        }
                        if (!estaEnVerticalOcupada)
                            estaEnMismaFilaOColumna = false;
                    } else {
                        boolean estaEnHorizontalOcupada = false;
                        for (int x: xOcupadas) {
                            if (posicionInicial.getX() == x)
                                estaEnHorizontalOcupada = true;
                        }
                        if (!estaEnHorizontalOcupada)
                            estaEnMismaFilaOColumna = false;
                    }
                } while (estaEnMismaFilaOColumna);
            }
            int xFinal;
            int yFinal;
            if (esVertical) {
                carretera.setDireccion(Direccion.VERTICAL);
                xFinal = xInicial;
                yFinal = yInicial + longitudCarretera - 1;
                carreterasVerticales.add(carretera);
                yOcupadas.add(posicionInicial.getY());
            } else {
                carretera.setDireccion(Direccion.HORIZONTAL);
                yFinal = yInicial;
                xFinal = xInicial + longitudCarretera - 1;
                carreterasHorizontales.add(carretera);
                xOcupadas.add(posicionInicial.getX());
            }

            Position posicionFinal = new Position(xFinal, yFinal);
            carretera.setPosiciones(posicionInicial, posicionFinal);
            carreteras[i] = carretera;
        }

        for (Carretera carretera : carreteras) {
            System.out.println("Carretera " + carretera.getId() + ": " + Arrays.toString(carretera.getPosiciones()));
        }
    }

    public Carretera[] getCarreteras() {
        return carreteras;
    }

    public void setCarreteras(Carretera[] carreteras) {
        this.carreteras = carreteras;
    }

    public ArrayList<Cruce> getCruces() {
        return cruces;
    }

    public void setCruces(ArrayList<Cruce> cruces) {
        this.cruces = cruces;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
}
