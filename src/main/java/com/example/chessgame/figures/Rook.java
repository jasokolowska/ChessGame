package com.example.chessgame.figures;

import com.example.chessgame.Coordinates;

import java.util.LinkedList;
import java.util.List;

public class Rook extends Figure{

    public Rook(FigureColor color) {
        super(color);
        super.symbol = "R";
    }

    public List<Coordinates> checkPossibleMoves(int row, int col) {
        List<Coordinates> possibleMoves = new LinkedList<>();

        for (int i = 0; i < 8; i++) {
            if (row != i) {
                possibleMoves.add(new Coordinates(i, col));
            }
            if (col != i) {
                possibleMoves.add(new Coordinates(row, i));
            }
        }

        return possibleMoves;
    }
}
