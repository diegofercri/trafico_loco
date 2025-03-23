import java.util.Random;

import model.Modelo;

public class Simulador {

    public static void main(String[] args) {
        Modelo modelo = new Modelo(10);
        modelo.generarCarreteras();
    }
}
