package org.example;

import java.util.List;
import java.util.Map;

public class AnalizadorLexico {
    private int[] cadena;
    private AutomataFinito A;
    private int posActual;
    private Map<Integer, String> tokens;
    private List<Token> historico;

    public AnalizadorLexico() {
        //this.cadena= "iniciar";

        this.posActual=0;
    }
}
