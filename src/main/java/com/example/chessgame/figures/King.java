package com.example.chessgame.figures;

import com.example.chessgame.Coordinates;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class King extends Figure{

    public King(FigureColor color) {
        super(color);
        super.symbol = "K";
    }

    public List<Coordinates> checkPossibleMoves(int row, int col) {
        List<Coordinates> possibleMoves = new LinkedList<>();

        possibleMoves.add(new Coordinates(row +1, col +1));
        possibleMoves.add(new Coordinates(row +1, col -1));
        possibleMoves.add(new Coordinates(row -1, col +1));
        possibleMoves.add(new Coordinates(row -1, col -1));
        possibleMoves.add(new Coordinates(row +1, col ));
        possibleMoves.add(new Coordinates(row -1, col ));
        possibleMoves.add(new Coordinates(row, col +1));
        possibleMoves.add(new Coordinates(row, col -1));

        return possibleMoves;
    }
}
