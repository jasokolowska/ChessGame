package com.example.chessgame.figures;

import com.example.chessgame.Coordinates;

import java.util.LinkedList;
import java.util.List;

public class Queen extends Figure{

    public Queen(FigureColor color) {
        super(color);
        super.symbol = "Q";
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

        for (int i = 1; (i+row) < 8 && (i+col) < 8; i++) {
            possibleMoves.add(new Coordinates(i+row,i+col));
        }

        for (int i = 1; (row-i) >= 0 && (col-i) >= 0; i++) {
            possibleMoves.add(new Coordinates(row - i,col - i));
        }

        for (int i = 1; (row+i) < 8 && (col-i) >= 0; i++) {
            possibleMoves.add(new Coordinates(row + i,col - i));
        }

        for (int i = 1; (row-i) >= 0 && (col+i) < 8; i++) {
            possibleMoves.add(new Coordinates(row - i,col + i));
        }
        return possibleMoves;
    }

    @Override
    public int getValue() {
        return 90;
    }
}
