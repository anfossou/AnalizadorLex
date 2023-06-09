package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalizadorLexico {
    private int[] cadena;
    private AutomataFinito A;
    private int posActual;
    private Map<Integer, String> tokens;
    private List<Token> historico;

    public AnalizadorLexico() {
        this.cadena = new int[this.posActual];
        this.posActual = 0;
        this.historico = new ArrayList<>();
        this.A = null;
        this.tokens = new HashMap<>();
    }

    public AnalizadorLexico(int[] cadena, AutomataFinito A, Map<Integer, String> tokens) {
        this.cadena = cadena;
        this.A = A;
        this.posActual = 0;
        this.tokens = tokens;
        this.historico = new ArrayList<>();
    }

    //Devuelve el siguiente Token o nulo si no se pueden extraer más tokens de la cadena.
    public Token nextToken() {

        if (hasMoreTokens()) { // Verifica si hay más tokens por extraer en la cadena
            int estadoActual = 0; // Estado inicial del autómata
            int posInicioToken = posActual; // Posición de inicio del token (inicialmente igual a la posición actual)
            int posFinalToken = posActual; // Posición final del token (inicialmente igual a la posición actual)

            while (posFinalToken < this.cadena.length) { // Recorre la cadena hasta llegar al final
                // Obtiene el siguiente estado a partir del estado actual y el símbolo en la posición actual
                int siguienteEstado = this.A.transicion(estadoActual, this.cadena[posFinalToken]);
                if (siguienteEstado == -1) {
                    break; // No hay transición posible, el token ha acabado
                }
                estadoActual = siguienteEstado; // Actualiza el estado actual con el siguiente estado
                posFinalToken++;
            }

            if (posFinalToken == posInicioToken) {
                return null; //No se pudo formar un token válido, retorna null
            }

            Integer[] lx = new Integer[posFinalToken - posInicioToken]; // Crea un arreglo para almacenar el lexema del token
            for (int i = posInicioToken; i < posFinalToken; i++) {
                lx[i - posInicioToken] = this.cadena[i]; // Copia los símbolos desde la posición de inicio hasta la posición final al lexema
            }

            // Construimos un Token con el identificador correspondiente al estado final y el lexema obtenido
            Token t = new Token(this.tokens.get(estadoActual), lx);
            this.historico.add(t); // Agrega el token al historial
            this.posActual = posFinalToken; // Actualiza la posición actual en la cadena

            return t; // Retorna el token obtenido
        }
        return null; // No quedan tokens por extraer, retorna null

    }


    //Si hay más tokens por analizar devuelve true y falso en caso contrario.
    public boolean hasMoreTokens(){
        /*int e=0;
        for(int i=0; i<this.cadena.length;i++){
            e = A.transicion(i,this.cadena[i]);
            if(e != -1 && A.esEstadoFinal(i)){ //Si no redirige error y es final, devolverá true, sino false
                return true;
            }
        }
        return false;*/
        return this.posActual < this.cadena.length;
    }

    //Devuelve el historial de tokens
    public List<Token> getHistorico(){
        return this.historico;
    }


    //Resetea el analizador lexico
    public void reset(){
        posActual = 0;
        historico.clear();
    }

    //Introduce una nueva cadena a analizar
    public void nuevaCadena(int[] cad){
        this.cadena = cad;
        this.posActual = 0;
        this.historico.clear();
    }

    //Finaliza el analisis devolviendo una listacon los tokens que quedan por reconocer
    public List<Token> finalizarAnalisis(){
        List<Token> lt = new ArrayList<>();
        while(this.hasMoreTokens()){
            Token t = nextToken();
            if(t == null){
                break;
            }
            lt.add(t);
        }
        return lt;
    }





}
