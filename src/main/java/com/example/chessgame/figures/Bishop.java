package com.example.chessgame.figures;

import com.example.chessgame.Coordinates;

import java.util.LinkedList;
import java.util.List;

public class Bishop extends Figure{

    public Bishop(FigureColor color) {
        super(color);
        super.symbol = "B";
    }

    public List<Coordinates> checkPossibleMoves(int row, int col) {
        List<Coordinates> possibleMoves = new LinkedList<>();

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
}
