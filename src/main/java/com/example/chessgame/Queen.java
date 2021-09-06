package com.example.chessgame;

public class Queen implements Figure{
    private String color;
    private String symbol;

    public Queen(String color) {
        this.color = color;
        this.symbol = "Q";
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
