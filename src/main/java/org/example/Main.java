package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int matriz[][] = {{1,2,3}, {4,5,-1},{-1,5,-1},{-1,-1,3},{6,7,-1},{-1,5-1},{6,5,-1},{-1,8,-1},{-1,5,-1}};
        //a,b,c
        int alfabeto = 3;
        //Estados de 0 a 8
        int nEstados = 9;

        //Estados finales
        boolean[] estFin = {false, true, true, true, false, true, false, true, true};

        //Cadena para analizar
        String palabra="aab";
        int[] cad = convPal(palabra);

        //Tabla para relacionar estados finales con los ID de tokens
        Map<Integer, String> equivTokens = new HashMap<>();
        equivTokens.put(1, "cero");
        equivTokens.put(2, "uno");
        equivTokens.put(3, "cuatro");
        equivTokens.put(5, "tres");
        equivTokens.put(7, "tres");
        equivTokens.put(8, "dos");

        //Contruimos el autómana
        AutomataFinito automataFinito = new AutomataFinitoMatriz(nEstados,estFin, alfabeto, matriz);

        //Construimos el analizador
        AnalizadorLexico analizadorLexico = new AnalizadorLexico(cad, automataFinito, equivTokens);

        //Prueba de funcionamiento
        int[] prueba = convPal("aabb");
        System.out.print("Prueba de pertenece al lenguaje:\n");
        System.out.print(automataFinito.perteneceLenguaje(prueba));
        System.out.print("\n");

        int[] prueba2 = convPal("aabbcc");
        System.out.print("Prueba 2 de pertenece al lenguaje:\n");
        System.out.print(automataFinito.perteneceLenguaje(prueba2));
        System.out.print("\n");

        System.out.print("Prueba nextToken\n");
        System.out.print(analizadorLexico.nextToken().getIdToken());
        System.out.print("\n");

        System.out.print("Prueba hasMoreToken\n");
        System.out.print(analizadorLexico.hasMoreTokens());
        System.out.print("\n");

        System.out.print("Prueba finalizarAnalisis\n");
        List<Token> finAnalizador = analizadorLexico.finalizarAnalisis();
        System.out.print(finAnalizador.toString());
        System.out.print("\n");

        System.out.print("---------------Cadena de prueba: aaabbbccaaa-----------------\n");
        for(Token t: analizadorLexico.getHistorico()){
            System.out.print("LEXEMA: " + t.getLexema() + "\n");
        }

        analizadorLexico.reset();


    }
    //Funcion de conversión de letras a numeros
    static int conversorLetra(char l){
        if(l == 'a'){
            return 0;
        }
        if(l == 'b'){
            return 1;
        }
        if(l == 'c'){
            return 2;
        }
        return -1;
    }

    //Conversor de números a letras
    static char conversorNum(int n){
        if(n == 0){
            return 'a';
        }
        if(n == 1){
            return 'b';
        }
        if(n == 2){
            return 'c';
        }
        return '-';
    }

    //Conversor de cadena a números
    static int[] convPal(String palabra){
        int[] cad = new int[palabra.length()];
        for(int i=0; i<palabra.length();i++){
            cad[i] = conversorLetra(palabra.charAt(i));
        }
        return cad;
    }

    static String convNum(int[] cad){
        String cadena = "";
        for(int i = 0; i < cad.length; i++){
            cadena = cadena + conversorNum(cad[i]);
        }
        return cadena;
    }
}