package com.example.chessgame;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BoardRow {
    private List<Figure> row;

    public BoardRow() {
        this.row = new LinkedList<>();
        for (int i = 0; i < 8; i++){
            this.row.add(new None("white"));
        }
    }

    public Figure getFigure(int col) {
        return row.get(col-1);
    }

    public void setFigure(int col, Figure figure){
        row.remove(col-1);
        row.add(col-1, figure);
    }

    @Override
    public String toString() {
        Iterator<Figure> iterator = row.listIterator();
        String row = "";
        while (iterator.hasNext()) {
            row += ("  |  " + iterator.next().toString());
        }
        return row + "  |";
    }
}

