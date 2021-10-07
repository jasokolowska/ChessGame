package com.example.chessgame.figures;

import com.example.chessgame.Coordinates;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Figure {
    protected String symbol;
    private FigureColor color;

    public Figure(FigureColor color) {
        this.color = color;
    }

    public FigureColor getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }

    public List<Coordinates> checkPossibleMoves(int row1, int col1) {
        return new LinkedList<Coordinates>();
    }

    public List<Coordinates> validateCoordinates (List<Coordinates> coordinates) {
        return coordinates.stream()
                .filter(c -> c.getColumn() < 8 &&
                        c. getRow() < 8 &&
                        c.getColumn() > -1 &&
                        c.getRow() > -1)
                .collect(Collectors.toList());
    }

}
