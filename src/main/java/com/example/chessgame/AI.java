package com.example.chessgame;

//add random available move from computer side
//instead of random move check points - which move will get more points - in case if the result is the same then random move
//implement minimax algorithm

import com.example.chessgame.figures.Figure;
import com.example.chessgame.figures.Pawn;

import java.util.*;
import java.util.stream.Collectors;

public class AI {
    private Board clonedBoard;

    public AI(Board clonedBoard) {
        this.clonedBoard = clonedBoard;
        System.out.println(clonedBoard);
    }

    public List<Coordinates> generateMoveCoordinates() {
        Map<Coordinates, List<Coordinates>> moveCoordinates = new HashMap<>();

        List<Coordinates> currentFigures = clonedBoard.getCurrentFigures(clonedBoard.getFigureColor());
        for (Coordinates currentFigure : currentFigures) {
            List<Coordinates> moves = clonedBoard.availableMoves(currentFigure.getRow(), currentFigure.getColumn());
            moveCoordinates.put(currentFigure, moves);

        }
        System.out.println("moveCoordinates: " + moveCoordinates);

        Map<Coordinates, Map<Coordinates, Integer>> moveScores = new HashMap<>();

        for (Map.Entry<Coordinates, List<Coordinates>> entry : moveCoordinates.entrySet()) {
            Map<Coordinates, Integer> moveScore = new HashMap<>();
            for (int i = 0; i < entry.getValue().size() ; i++) {
                moveScore.put(entry.getKey(), getMoveScore(entry.getValue().get(i)));
            }
            moveScores.put(entry.getKey(), moveScore);
        }

        //stream filter
        System.out.println(moveScores);
        List<List<Coordinates>> maxMove = moveScores.entrySet().stream()

//                .max(Map.Entry.comparingByValue())
//                .stream().map(Map.Entry::getKey)
//                .map(entry -> List.of(entry.getKey(), entry.getValue()))
//                .toList();

        System.out.println("Computer move: " + maxMove.get(0));
        return maxMove.get(0);
    }

    public int getMoveScore(Coordinates moveCoordinates) {
        Figure figure = clonedBoard.getFigure(moveCoordinates.getRow(), moveCoordinates.getColumn());

        return figure.getValue();
    }
}
