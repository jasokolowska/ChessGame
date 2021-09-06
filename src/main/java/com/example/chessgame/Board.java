package com.example.chessgame;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private List<BoardRow> board;

    public Board() {
        this.board = new LinkedList<>();
        for (int i = 0; i < 8; i++){
            this.board.add(new BoardRow());
        }
    }

    public Figure getFigure(int row, int col){
        BoardRow currentRow = board.get(row-1);
        return currentRow.getFigure(col);
    }
    public void setFigure(int row, int col, Figure figure){
        BoardRow currentRow = board.get(row-1);
        currentRow.setFigure(col, figure);
    }


    public boolean move(int row1, int col1, int row2, int col2) {

        if (getFigure(row1, col1).toString() != " " || getFigure(row2, col2).toString() == " " &&
                (row2 < 8 && row2 >= 0 && col2 < 8 )) {

            if (row1 - row2 == col1 - col2 || row1 + col1 == row2 + col2) {

                if (Math.abs(row1 - row2) == 1) {
                    Figure figure = getFigure(row1, col1);
                    setFigure(row1, col1, new None("black"));
                    setFigure(row2, col2, figure);
                    System.out.println("Figure: " + figure.toString() + "(" + row1 + "," + col1 + ")" +" has been moved to field " + row2 + "," + col2);
                    return true;
//                } else if (row1 - row2 == 2) {
                    //sprawdz czy na kolejnym polu znajduje sie sie pionek z przeciwnym znakiem
                    // zbij pionek i wykonaj ruch
                } else {
                    if (getFigure(row1, col1).toString() != "Q") {
                        System.out.println("Only Queen can move more than one field at a time");
                        return false;
                    } else {
                        Figure figure = getFigure(row1, col1);
                        setFigure(row1, col1, new None("black"));
                        setFigure(row2, col2, figure);
                        System.out.println("Figure: " + figure.toString() + "(" + row1 + "," + col1 + ")" +" has been moved to field " + row2 + "," + col2);
                        return true;
                    }
                }
            } else {
                System.out.println("This move is not allowed, you can move diagonally");
                return false;
            }
        } else {
            System.out.println("Move not possible");
            return false;
        }
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
