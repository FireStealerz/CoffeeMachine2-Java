package com.univa.cafetera;

public class Recetas {

    private int cafe;

    Recetas(int cafe) {  //medida cafe c.c.
        setCafe(cafe);
    }

    public int getCafe() {
        return cafe;
    }

    private void setCafe(int cafe) { //this. es para elegir el de la clase , el otro es el del metodo
        this.cafe = cafe;
    }
}
