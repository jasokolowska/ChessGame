package com.example.chessgame;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private List<BoardRow> board;
    private Player player;
    private Computer computer;

    public Board() {
        this.player = new Player();
        this.computer = new Computer();
        this.board = new LinkedList<>();
        for (int i = 0; i < 8; i++){
            this.board.add(new BoardRow());
        }
    }

    public Figure getFigure(int row, int col){
        BoardRow currentRow = board.get(row);
        return currentRow.getFigure(col);
    }
    public void setFigure(int row, int col, Figure figure){
        BoardRow currentRow = board.get(row);
        currentRow.setFigure(col, figure);
    }

    public boolean move(int row1, int col1, int row2, int col2) {

        Figure figure = getFigure(row1, col1);
        Figure targetFigure = getFigure(row2, col2);
        List<Coordinates> possibleMoves = figure.getPossibleMoves(row1, col1);
        List<Coordinates> availableMoves = checkAvailableMoves(possibleMoves, row2, col2, figure, targetFigure);

        if (availableMoves.size() < possibleMoves.size() && (targetFigure.getSymbol().equals(" "))) {
            System.out.println("Figure cannot pass another figures");
            return false;
        }

        if (availableMoves.contains(new Coordinates(row2, col2))) {
            moveFigure(row1, col1, row2, col2, figure);
            if (!targetFigure.getSymbol().equals(" ")) {
                player.addCapturedFigure(figure);
            }
            return true;
        }
        return false;
    }

    private void moveFigure(int row1, int col1, int row2, int col2, Figure figure) {
        setFigure(row1, col1, new None("none"));
        setFigure(row2, col2, figure);
    }

    private List<Coordinates> checkAvailableMoves(List<Coordinates> possibleMoves, int row, int col, Figure figure, Figure targetFigure) {
        List<Coordinates> availableMoves = new LinkedList<>();

        for (Coordinates pm : possibleMoves) {
            if (getFigure(pm.getRow(), pm.getColumn()) instanceof None) {
               availableMoves.add(pm);
            } else {
                if (pm.equals(new Coordinates(row, col)) && !figure.getColor().equals(targetFigure.getColor())) {
                    availableMoves.add(pm);
                }
            }
        }
        return availableMoves;
    }


    public String toString(){
        Iterator<BoardRow> iterator = board.listIterator();
        String board = "";
        String midLinePattern = "\n  |-----|-----|-----|-----|-----|-----|-----|-----|\n";
        while (iterator.hasNext()) {
            board += (iterator.next().toString() + midLinePattern);
        }
        return midLinePattern + board;
    }
}
