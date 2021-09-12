package com.example.chessgame.figures;

import com.example.chessgame.Coordinates;

import java.util.LinkedList;
import java.util.List;

public class Pawn extends Figure {

    public Pawn(FigureColor color) {
        super(color);
        super.symbol = "P";
    }

    @Override
    public List<Coordinates> checkPossibleMoves(int row, int col) {
        List<Coordinates> possibleMoves = new LinkedList<>();

        if ((getColor().equals(FigureColor.WHITE)) && row > 0) {
            possibleMoves.add(new Coordinates(row-1, col));
            possibleMoves.add(new Coordinates(row-1, col-1));
            possibleMoves.add(new Coordinates(row-1, col+1));
        }
        if ((getColor().equals(FigureColor.WHITE)) && row == 6) {
            possibleMoves.add(new Coordinates((row - 2), col));
        }

        if (getColor().equals(FigureColor.BLACK) && row < 7) {
            possibleMoves.add(new Coordinates(row+1, col));
            possibleMoves.add(new Coordinates(row+1, col-1));
            possibleMoves.add(new Coordinates(row+1, col+1));
        }

        if ((getColor().equals(FigureColor.BLACK)) && row == 1) {
            possibleMoves.add(new Coordinates((row + 2), col));
        }

        return possibleMoves;
    }
}
