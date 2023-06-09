package org.example;

public class Token {
    private String idToken;
    private Integer[] lexema;

    public Token(String id, Integer[] lx){
        this.idToken = id;
        this.lexema = lx;
    }

    //Devuelve idToken
    public String getIdToken() {
        return this.idToken;
    }

    //Devuelve el lexema del token
    public Integer[] getLexema() {
        return this.lexema;
    }
}
