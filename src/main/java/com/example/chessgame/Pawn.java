package com.example.chessgame;

import java.util.LinkedList;
import java.util.List;

public class Pawn extends Figure {

    public Pawn(String color) {
        super(color);
        super.symbol = "P";
    }

    @Override
    public List<Coordinates> getPossibleMoves(int row, int col) {
        List<Coordinates> possibleMoves = new LinkedList<>();

        if ((getColor().equals("white")) && row == 6) {
            possibleMoves.add(new Coordinates((row - 2), col));
        }
        if (getColor().equals("white") && row > 0) {
            possibleMoves.add(new Coordinates((row - 1), col));
        }

        if ((getColor().equals("black")) && row == 1) {
            possibleMoves.add(new Coordinates((row + 2), col));
        }
        if (getColor().equals("black") && row < 7) {
            possibleMoves.add(new Coordinates((row + 1), col));
        }
        return possibleMoves;
    }
}
