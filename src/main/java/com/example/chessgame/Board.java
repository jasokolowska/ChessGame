package com.example.chessgame;

import com.example.chessgame.figures.*;
import javafx.scene.image.Image;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private final List<BoardRow> board = new LinkedList<>();
    private FigureColor whoseMove = FigureColor.WHITE;
    private boolean whiteCheck = false;
    private boolean blackCheck = false;

    public Board() {
        for (int i = 0; i < 8; i++) {
            this.board.add(new BoardRow());
        }
    }

    public void switchPlayer() {
        whoseMove = (whoseMove == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
    }

    public Figure getFigure(int row, int col) {
        BoardRow currentRow = board.get(row);
        return currentRow.getFigure(col);
    }

    public void setFigure(int row, int col, Figure figure) {
        BoardRow currentRow = board.get(row);
        currentRow.setFigure(col, figure);
    }

    public boolean move(int row1, int col1, int row2, int col2) {

        Figure figure = getFigure(row1, col1);
        Figure targetFigure = getFigure(row2, col2);

        List<Coordinates> currentMoves = checkCurrentMoves(row1, col1, row2, col2, figure);
        System.out.println(currentMoves);

        if (figure.getColor().equals(whoseMove)) {
            if (targetFigure instanceof None ||
                    (targetFigure instanceof Figure && !targetFigure.getColor().equals(figure.getColor()))) {
                if (currentMoves.size() > 0) {
                    moveFigure(row1, col1, row2, col2, figure);
                    checkIfCheck();
                    return true;
                } else {
                    System.out.println("This piece cannot be moved to this destination field - this move is not allowed");
                    return false;
                }
            } else {
                System.out.println("There is a piece with same color on this destination field");
                return false;
            }
        } else if (figure.getColor().equals(FigureColor.NONE)) {
            System.out.println("There is no piece on this field, try again");
            return false;
        } else {
            System.out.println("Now is the move of Player " + whoseMove);
            return false;
        }
    }

    private List<Coordinates> checkCurrentMoves(int row1, int col1, int row2, int col2, Figure figure) {

        List<Coordinates> availableMoves = new LinkedList<>();
        List<Coordinates> possibleMoves = figure.checkPossibleMoves(row1, col1);
        Coordinates endPoint = new Coordinates(row2, col2);

        int rowFactor = getFactor(row1, row2);
        int colFactor = getFactor(col1, col2);

        if (figure instanceof Knight) {
            return figure.checkPossibleMoves(row1, col1);
        } else if (figure instanceof Pawn && colFactor != 0 && getFigure(row2, col2) instanceof None) {
            System.out.println("Pawn can only move diagonally when capturing the piece");
        } else {
            while (!endPoint.equals(new Coordinates(row1, col1))) {

                row1 += rowFactor;
                col1 += colFactor;

                if (row1 < 8 && row1 > -1 && col1 < 8 && col1 > -1) {
                    if ((!(getFigure(row1, col1) instanceof None)) && (!endPoint.equals(new Coordinates(row1, col1)))) {
//                    System.out.println("This piece cannot leap over other piece");
                        break;
                    } else if (possibleMoves.contains(new Coordinates(row1, col1))) {
                        availableMoves.add(new Coordinates(row1, col1));
                    }
                }
            }
        }
        return availableMoves;
    }

    public boolean checkIfCheck() {

        //powinna sprawdzac jednoczesnie obydwu kroli

        FigureColor opponentsColor = (whoseMove == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
        Coordinates figureCoordinates = getKingCoordinates(opponentsColor);
        int kingCol = figureCoordinates.getColumn();
        int kingRow = figureCoordinates.getRow();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = getFigure(row, col);

                if (!(figure instanceof None) && figure.getColor() == whoseMove) {
//                    System.out.println("Figura: " + figure);
//                    System.out.println("row: " + row + " col: " + col + "kingRow: " + kingRow + "kingCol: " + kingCol);
//                    System.out.println("Current moves: " + checkCurrentMoves(row, col, kingRow, kingCol, figure));
                    if (checkCurrentMoves(row, col, kingRow, kingCol, figure).contains(figureCoordinates)) {
                        System.out.println("Figura: " + figure);
                        System.out.println("Koordynaty: " + row + " ," + col);
                        System.out.println("Current moves: " + checkCurrentMoves(row, col, kingRow, kingCol, figure));
                        System.out.println(opponentsColor + " King is not protected - CHECK");
                        return true;
                    }
                }
            }
        }
        System.out.println(opponentsColor + " King is protected");
        return false;
    }

    private Coordinates getKingCoordinates(FigureColor figureColor) {
        int row = 0;
        for (BoardRow boardRow : board) {
            if (boardRow.getFigureColumn(figureColor) > -1) {
                return new Coordinates(row, boardRow.getFigureColumn(figureColor));
            }
            row++;
        }
        System.out.println("There is no King on the board");
        return new Coordinates(-1, -1);
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

        setFigure(0, 0, new Rook(FigureColor.BLACK));
        setFigure(0, 7, new Rook(FigureColor.BLACK));
        setFigure(0, 1, new Knight(FigureColor.BLACK));
        setFigure(0, 6, new Knight(FigureColor.BLACK));
        setFigure(0, 2, new Bishop(FigureColor.BLACK));
        setFigure(0, 5, new Bishop(FigureColor.BLACK));
        setFigure(0, 3, new Queen(FigureColor.BLACK));
        setFigure(0, 4, new King(FigureColor.BLACK));

        setFigure(7, 0, new Rook(FigureColor.WHITE));
        setFigure(7, 7, new Rook(FigureColor.WHITE));
        setFigure(7, 1, new Knight(FigureColor.WHITE));
        setFigure(7, 6, new Knight(FigureColor.WHITE));
        setFigure(7, 2, new Bishop(FigureColor.WHITE));
        setFigure(7, 5, new Bishop(FigureColor.WHITE));
        setFigure(7, 3, new Queen(FigureColor.WHITE));
        setFigure(7, 4, new King(FigureColor.WHITE));


    }


    public String toString() {
        Iterator<BoardRow> iterator = board.listIterator();
        StringBuilder board = new StringBuilder();
        String midLinePattern = "\n  |-----|-----|-----|-----|-----|-----|-----|-----|\n";
        while (iterator.hasNext()) {
            board.append(iterator.next().toString()).append(midLinePattern);
        }
        return midLinePattern + board;
    }
}
