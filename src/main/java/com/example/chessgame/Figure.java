package com.example.chessgame;

import java.util.LinkedList;
import java.util.List;

abstract class Figure {
    protected String symbol;
    private String color;

    public Figure(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }

    public List<Coordinates> getPossibleMoves(int row1, int col1) {
        return new LinkedList<Coordinates>();
    }
}
