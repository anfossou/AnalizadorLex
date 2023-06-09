package org.example;

public class AutomataFinitoMatriz extends AutomataFinito {

    private int[][] matriz;


    public AutomataFinitoMatriz(int num, int alf) {
        super(num, alf);
    }

    public AutomataFinitoMatriz(int numEstados, boolean[] finales, int tamAlfabeto, int[][] matriz) {
        super(numEstados, tamAlfabeto,  finales);
        this.matriz = matriz;
    }

    //Devuelve la correspondiente entrada de la matriz
    @Override
    public int transicion(int estado, int letra) {
        return this.matriz[estado][letra];
    }
}
