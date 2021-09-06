package com.example.chessgame;

public class Pawn implements Figure {
    private String color;
    private String symbol;

    public Pawn(String color) {
        this.color = color;
        this.symbol = "P";
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
