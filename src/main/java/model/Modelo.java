package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Modelo {

    private Carretera[] carreterasClass;

    private ArrayList<Cruce> cruces;

    private int tamano;

    public Modelo(int tamano) {
        this.tamano = tamano;
        carreterasClass = new Carretera[tamano / 2];
        cruces = new ArrayList<>(tamano / 2);
        // generarCarreteras();
    }

    public void generarCarreteras() {
        LinkedList<Carretera> carreteras = new LinkedList<>();

        LinkedList<Integer> yOcupadas = new LinkedList<>();
        LinkedList<Integer> xOcupadas = new LinkedList<>();
        for (int i = 0; i < tamano / 2; i++) {
            Carretera carretera = new Carretera();
            boolean esVertical = i % 2 == 0;
            carretera.setId(i);
            Random random = new Random(System.currentTimeMillis());
            int xInicial = random.nextInt(1, tamano/2 - 1);
            int yInicial = random.nextInt(1, tamano/2 - 1);
            System.out.println("La x inicial " + xInicial);
            System.out.println("La y inicial " + yInicial);
            int longitudCarretera;
            if (esVertical)
                longitudCarretera = random.nextInt(3, tamano - 1 - yInicial);
            else
                longitudCarretera = random.nextInt(3, tamano - 1 - xInicial);
            Position posicionInicial;

            if (i == 0) {
                posicionInicial = new Position(xInicial, yInicial);


            }
            else {
                boolean estaEnMismaFilaOColumna = true;
                do {
                    Carretera carreteraRandom;
                    int indexCarreteraRandom;

                    indexCarreteraRandom = random.nextInt(carreteras.size());
                    if(carretera.getDireccion() == Direccion.VERTICAL){
                      if(!yOcupadas.contains(indexCarreteraRandom)){
                          estaEnMismaFilaOColumna = false;
                      }
                    }
                    else{
                        if(!xOcupadas.contains(indexCarreteraRandom)){
                            estaEnMismaFilaOColumna = false;
                        }
                    }

                        carreteraRandom = carreteras.get(indexCarreteraRandom);
                        Position[] posiciones = carreteraRandom.getPosiciones();
                        int indexPosicionRandom = random.nextInt(posiciones.length);
                        posicionInicial = posiciones[indexPosicionRandom];
                        xInicial = posicionInicial.getX();
                        yInicial = posicionInicial.getY();


                } while (estaEnMismaFilaOColumna);
            }

            int xFinal;
            int yFinal;
            if (esVertical) {
                carretera.setDireccion(Direccion.VERTICAL);
                xFinal = xInicial;
                System.out.println("La x inicial en el if" + xInicial);
                yFinal = yInicial + longitudCarretera - 1;
                yOcupadas.add(posicionInicial.getY());
            } else {
                carretera.setDireccion(Direccion.HORIZONTAL);
                yFinal = yInicial;
                System.out.println("La y inicial en el else" + yInicial);
                xFinal = xInicial + longitudCarretera - 1;
                xOcupadas.add(posicionInicial.getX());
            }
            carreteras.add(carretera);

            Position posicionFinal = new Position(xFinal, yFinal);
            System.out.println("Las posiciones iniciales son x: " + posicionInicial.getX() + " y: " + posicionInicial.getY());
            System.out.println("Las posiciones finales son x: " + posicionFinal.getX() + " y: " + posicionFinal.getY());
            carretera.setPosiciones(posicionInicial, posicionFinal);
            carreterasClass[i] = carretera;
        }

        for (Carretera carretera : carreteras) {
            System.out.println("Carretera " + carretera.getId() + ": " + Arrays.toString(carretera.getPosiciones()));
        }
    }

    public Carretera[] getCarreteras() {
        return carreterasClass;
    }

    public void setCarreteras(Carretera[] carreteras) {
        this.carreterasClass = carreteras;
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
