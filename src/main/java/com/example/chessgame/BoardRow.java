package com.example.chessgame;

import com.example.chessgame.figures.Figure;
import com.example.chessgame.figures.FigureColor;
import com.example.chessgame.figures.King;
import com.example.chessgame.figures.None;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BoardRow {
    private List<Figure> row;

    public BoardRow() {
        this.row = new LinkedList<>();
        for (int i = 0; i < 8; i++){
            this.row.add(new None(FigureColor.NONE));
        }
    }

    public Figure getFigure(int col) {
        return row.get(col);
    }

    public int getFigureColumn(FigureColor figureColor) {
        int index = 0;
        for (Figure figure : row) {
            if (figure instanceof King && (figure.getColor() == figureColor)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void setFigure(int col, Figure figure){
        row.remove(col);
        row.add(col, figure);
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

