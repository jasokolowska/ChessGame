package com.example.chessgame;

import com.example.chessgame.figures.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private final List<BoardRow> board = new LinkedList<>();

    public Board() {
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

        List<Coordinates> possibleMoves = figure.checkPossibleMoves(row1, col1);
        System.out.println("possible moves: " + possibleMoves);
        List<Coordinates> currentMoves = checkCurrentMoves(row1, col1, row2, col2, figure);
        System.out.println("available moves: " + currentMoves);

        if (targetFigure instanceof None || (targetFigure instanceof Figure && !targetFigure.getColor().equals(figure.getColor()))) {
            if (currentMoves.contains(new Coordinates(row2, col2)) && possibleMoves.contains(new Coordinates(row2, col2))) {
                moveFigure(row1, col1, row2, col2, figure);
                return true;
            } else {
                System.out.println("This piece cannot be moved to this destination field - this move is not allowed");
                return false;
            }
        } else {
            System.out.println("There is a piece with same color on this destination field");
        }

        return false;
    }

    private List<Coordinates> checkCurrentMoves(int row1, int col1, int row2, int col2, Figure figure) {
        List<Coordinates> availableMoves = new LinkedList<>();
        Coordinates endPoint = new Coordinates(row2, col2);

        int rowFactor = getFactor(row1, row2);
        int colFactor = getFactor(col1, col2);

        if (figure instanceof Knight){
            return figure.checkPossibleMoves(row1, col1);
        } else if (figure instanceof Pawn && colFactor == 0 && !(getFigure(row2,col2) instanceof None)) {
            System.out.println("Pawn can only capture pieces diagonally");
        } else {
            while (!endPoint.equals(new Coordinates(row1, col1))) {
                row1 += rowFactor;
                col1 += colFactor;

                if ((!(getFigure(row1,col1) instanceof None)) && (!endPoint.equals(new Coordinates(row1, col1))) ) {
                    System.out.println("This piece cannot leap over other piece");
                    break;
                }
                availableMoves.add(new Coordinates(row1, col1));
            }
        }
    return availableMoves;
    }

    private int getFactor(int nbr1, int nbr2) {
        int rowFactor = 0;
        if (nbr2 - nbr1 > 0) {
            rowFactor = 1;
        } else if (nbr2 - nbr1 < 0) {
            rowFactor = -1;
        }
        return rowFactor;
    }

    private void moveFigure(int row1, int col1, int row2, int col2, Figure figure) {
        setFigure(row1, col1, new None(FigureColor.NONE));
        setFigure(row2, col2, figure);
    }

    public void init() {

        for (int i = 0; i < 8; i++) {
            setFigure(1, i, new Pawn(FigureColor.BLACK));
            setFigure(6, i, new Pawn(FigureColor.WHITE));
        }

        setFigure(0,0, new Rook(FigureColor.BLACK));
        setFigure(0,7, new Rook(FigureColor.BLACK));
        setFigure(0,1, new Knight(FigureColor.BLACK));
        setFigure(0,6, new Knight(FigureColor.BLACK));
        setFigure(0, 2, new Bishop(FigureColor.BLACK));
        setFigure(0, 5, new Bishop(FigureColor.BLACK));
        setFigure(0,3, new Queen(FigureColor.BLACK));
        setFigure(0,4, new King(FigureColor.BLACK));

        setFigure(7,0, new Rook(FigureColor.WHITE));
        setFigure(7,7, new Rook(FigureColor.WHITE));
        setFigure(7,1, new Knight(FigureColor.WHITE));
        setFigure(7,6, new Knight(FigureColor.WHITE));
        setFigure(7, 2, new Bishop(FigureColor.WHITE));
        setFigure(7, 5, new Bishop(FigureColor.WHITE));
        setFigure(7,3, new Queen(FigureColor.WHITE));
        setFigure(7,4, new King(FigureColor.WHITE));


    }


    public String toString(){
        Iterator<BoardRow> iterator = board.listIterator();
        StringBuilder board = new StringBuilder();
        String midLinePattern = "\n  |-----|-----|-----|-----|-----|-----|-----|-----|\n";
        while (iterator.hasNext()) {
            board.append(iterator.next().toString()).append(midLinePattern);
        }
        return midLinePattern + board;
    }
}
