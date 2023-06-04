package org.example;

public class Token {
    private String idToken;
    private Integer[] lexema;

    public Token(String id, Integer[] lx){
        this.idToken = id;
        this.lexema = lx;
    }

    public String getIdToken() {
        return this.idToken;
    }

    public Integer[] getLexema() {
        return this.lexema;
    }
}
