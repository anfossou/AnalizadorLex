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

    //Marca como final un estado
    public void marcarFinal(int estado) {
        this.finales[estado] = true;
    }

    //Establece los finales del autómata
    public void setFinales(boolean[] estadosFinales){
        for (int i=0;i<this.finales.length;i++){
            for (int j=0;j<estadosFinales.length;j++){
                this.finales[i] = estadosFinales[j];
            }
        }
    }

    //Obtiene el numero de estados
    public int getNumEstados(){
        return this.numEstados;
    }

    //Obtiene los finales
    public boolean[] getFinales() {
        return finales;
    }

    //Obtiene si un estado es final o no
    public boolean esEstadoFinal (int estado){
        if (this.finales[estado] == true) return true;
        else return false;
    }

    public abstract int transicion(int estado, int letra);


    public int cierreTransicion(int estado, int cadena[]){
        //Consumir la cadena mientras que no de -1 y sino hacer un break.
        int t=estado;
        for (int i=0; i<cadena.length;i++){
            t = transicion(t,cadena[i]);
            if(t == -1) {
                return -1;
            }
        }
        return t;
    }

    //perteneceLenguaje: Es el que recibe una cadena que se tiene que consumir entera y acabar en un estado Final

    public boolean perteneceLenguaje(int cadena[]){
        int e = cierreTransicion(0,cadena);
        if (e != 1 && esEstadoFinal(e)){
            return true;
        }
        return false;
    }
}
