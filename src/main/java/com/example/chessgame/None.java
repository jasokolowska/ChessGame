package com.example.chessgame;

public class None implements Figure {
    private String color;
    private String symbol;

    public None(String color) {
        this.color = color;
        this.symbol = " ";
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
