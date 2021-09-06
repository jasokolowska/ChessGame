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


//    public boolean move(int row1, int col1, int row2, int col2) {
//
//    }

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
