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
        System.out.println("Possible moves: " + possibleMoves);

//        possibleMoves.add(new Coordinates(row + 1, col + 2));
//        possibleMoves.add(new Coordinates(row + 1, col - 2));
//        possibleMoves.add(new Coordinates(row - 1, col + 2));
//        possibleMoves.add(new Coordinates(row - 1, col - 2));
//        possibleMoves.add(new Coordinates(row + 2, col + 1));
//        possibleMoves.add(new Coordinates(row + 2, col - 1));
//        possibleMoves.add(new Coordinates(row - 2, col + 1));
//        possibleMoves.add(new Coordinates(row - 2, col - 1));

        return possibleMoves;
    }

    private List<Coordinates> generateCoordinates(int[] factor1, int[] factor2, int row, int col) {
        List<Coordinates> coordinates = new LinkedList<>();

        for (int i : factor1) {
            for (int j : factor2) {
                if (row + i < 8 && row + i > -1 && col + j < 8 && col + j > -1){
                    coordinates.add(new Coordinates(row + i, col + j));
                }
            }
        }
        return coordinates;
    }
}
