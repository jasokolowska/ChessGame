package com.example.chessgame;

abstract class Figure {
    protected String symbol;
    private String color;

    public Figure(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
