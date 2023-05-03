package org.example;

public abstract class AutomataFinito {
    private int numEstados;
    private boolean[] finales;
    private int tamAlfabeto;

    public AutomataFinito(int num, int alf) {
        this.numEstados = num;
        this.tamAlfabeto = alf;
    }
    public AutomataFinito(int numEstados, boolean[] finales, int tamAlfabeto) {
        super();
        this.numEstados = numEstados;
        this.finales = finales;
        this.tamAlfabeto = tamAlfabeto;
    }

    public void marcarFinal(int estado) {
        this.finales =
    }
}
