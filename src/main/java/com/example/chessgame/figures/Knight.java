package com.example.chessgame.figures;

import com.example.chessgame.Coordinates;

import java.util.LinkedList;
import java.util.List;

public class Knight extends Figure{

    public Knight(FigureColor color) {
        super(color);
        super.symbol = "k";
    }

    public List<Coordinates> checkPossibleMoves(int row, int col) {
        List<Coordinates> possibleMoves = new LinkedList<>();
        int[] factor1 = {-1, 1};
        int[] factor2 = {-2, 2};

        possibleMoves.addAll(generateCoordinates(factor1, factor2, row, col));
        possibleMoves.addAll(generateCoordinates(factor2, factor1, row, col));

        return validateCoordinates(possibleMoves);
    }

    private List<Coordinates> generateCoordinates(int[] factor1, int[] factor2, int row, int col) {
        List<Coordinates> coordinates = new LinkedList<>();

        for (int i : factor1) {
            for (int j : factor2) {
                coordinates.add(new Coordinates(row + i, col + j));
            }
        }
        return coordinates;
    }

    @Override
    public int getValue() {
        return 30;
    }
}
