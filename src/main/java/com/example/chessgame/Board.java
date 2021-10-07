package com.example.chessgame;

import com.example.chessgame.figures.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private final List<BoardRow> board = new LinkedList<>();
    private FigureColor figureColor = FigureColor.WHITE;
    List<Coordinates> coordinatesCapturingKing;
    List<Coordinates> figuresList;
    private boolean whiteCheck = false;
    private boolean blackCheck = false;
    private boolean whiteMate = false;
    private boolean blackMate = false;

    public Board() {
        for (int i = 0; i < 8; i++) {
            this.board.add(new BoardRow());
        }
    }

    public void switchPlayer() {
        figureColor = (figureColor == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
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

        if (figure.getColor().equals(figureColor)) {
            if (targetFigure instanceof None ||
                    (targetFigure != null && !targetFigure.getColor().equals(figure.getColor()))) {

                List<Coordinates> currentMoves = availableMoves(row1, col1);
                if (currentMoves.contains(new Coordinates(row2, col2))) {
                    moveFigure(row1, col1, row2, col2, figure);
                    this.whiteCheck = checkIfKingCanBeCaptured(FigureColor.WHITE);
                    this.blackCheck = checkIfKingCanBeCaptured(FigureColor.BLACK);
                    if (whiteCheck) {
                        checkIfCheckMate(FigureColor.WHITE);
                    } else if (blackCheck) {
                        checkIfCheckMate(FigureColor.BLACK);
                    }
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
            //dodac jakies resetowanie, np. jak nacisnie sie 2 razy na tym samym polu to mozna wykonac ruch inna bierka
            return false;
        } else {
            System.out.println("Now is the move of Player " + figureColor);
            return false;
        }

    }

    private void checkIfCheckMate(FigureColor figureColor) {
        System.out.println("Checking if check mate...");
            int kingRow = getKingCoordinates(figureColor).getRow();
            int kingCol = getKingCoordinates(figureColor).getColumn();

            List<Coordinates> availableMoves = availableMoves(kingRow, kingCol);
            int i = 0;
            int matCount = 0;

            for (Coordinates availableMove : availableMoves) {
                if (checkIfKingCanBeCaptured(figureColor)) {
                    matCount++;
                }
                i++;
            }

            if (matCount == i && checkIfKingCanBeProtected()) {
                if (figureColor == FigureColor.WHITE) {
                    this.whiteMate = true;
                } else {
                    this.blackMate = true;
                }
                System.out.println("Szach Mat");
            }
    }

    private boolean checkIfKingCanBeProtected() {
        System.out.println("Check if king can be protected");
        Coordinates kingCoordinates = getKingCoordinates(figureColor);

        for (Coordinates coordinates : figuresList) {
            for (Coordinates opponentCoor : coordinatesCapturingKing) {

                if (figureCanBeBeaten(coordinates, opponentCoor)) {
                    System.out.println("Figure can be beaten - true");
                    return false;
                } else if (kingCanBeHidden(kingCoordinates, opponentCoor)) {
                    System.out.println(availableMoves(opponentCoor.getRow(), opponentCoor.getColumn()));
                    System.out.println("coordinates: " + coordinates);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean kingCanBeHidden(Coordinates kingCoordinates, Coordinates opponentCoor) {
        return availableMoves(opponentCoor.getRow(), opponentCoor.getColumn()).contains(kingCoordinates);
    }

    private boolean figureCanBeBeaten(Coordinates coordinates, Coordinates opponentCoor) {
        return availableMoves(coordinates.getRow(), coordinates.getColumn()).contains(opponentCoor);
    }

    public List<Coordinates> availableMoves(int row1, int col1) {

        List<Coordinates> availableMoves = new LinkedList<>();
        List<Coordinates> possibleMoves = getFigure(row1, col1).checkPossibleMoves(row1, col1);
        Figure figure = getFigure(row1, col1);


        if (figure instanceof Knight) {
            for (Coordinates possibleMove : possibleMoves) {
                if (getFigure(possibleMove.getRow(), possibleMove.getColumn()) instanceof None) {
                    availableMoves.add(possibleMove);
                } else if (!getFigure(possibleMove.getRow(), possibleMove.getColumn()).getColor().equals(figure.getColor())) {
                    availableMoves.add(possibleMove);
                }
            }
            return availableMoves;
        } else if (figure instanceof Pawn) {
            for (Coordinates possibleMove : possibleMoves) {
                if (getFigure(possibleMove.getRow(), possibleMove.getColumn()) instanceof None) {
                    if (possibleMove.getColumn() == col1) {
                        availableMoves.add(possibleMove);
                    }
                } else if (!getFigure(possibleMove.getRow(), possibleMove.getColumn()).getColor().equals(figure.getColor())
                        && possibleMove.getColumn() != col1) {
                        availableMoves.add(possibleMove);
                }
            }
        } else {
            for (Coordinates possibleMove : possibleMoves) {
                int rowFactor = getFactor(row1, possibleMove.getRow());
                int colFactor = getFactor(col1, possibleMove.getColumn());
                int row = row1;
                int col = col1;

                do {
                    row += rowFactor;
                    col += colFactor;

                    if (row > 7 || col > 7 || row < 0 || col < 0) {
                        break;
                    } else if (!isFigureNone(row, col) && (!possibleMove.equals(new Coordinates(row, col)))) {
                        break;
                    } else if (getFigure(row, col).getColor().equals(figure.getColor())) {
                        break;
                    } else {
                        availableMoves.add(new Coordinates(row, col));
                    }
                } while (!(new Coordinates(row, col)).equals(possibleMove));
            }
        }

        return availableMoves;
    }

    private boolean isFigureNone(int row, int col) {
        return getFigure(row, col) instanceof None;
    }

    public boolean checkIfKingCanBeCaptured(FigureColor figureColor) {

        Coordinates kingCoordinates = getKingCoordinates(figureColor);
        FigureColor opponentsColor = (figureColor == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
        coordinatesCapturingKing = new LinkedList<>(); //czy potrzebne jest tworzenie listy na nowo?
        figuresList = new LinkedList<>();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = getFigure(row, col);

                if (!(figure instanceof None) && figure.getColor() == opponentsColor) {

                    if (availableMoves(row, col).contains(kingCoordinates)) {
                        System.out.println(figureColor + " King is not protected - CHECK");
                        coordinatesCapturingKing.add(new Coordinates(row, col));
                        if (figureColor == FigureColor.WHITE) {
                            this.whiteCheck = true;
                        } else {
                            this.blackCheck = true;
                        }

                        return true;
                    }
                } else if (!(figure instanceof None) && figure.getColor() == figureColor) {
                    figuresList.add(new Coordinates(row, col));
                }
            }
        }

        System.out.println(figureColor + " King is protected");
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

    public boolean isWhiteMate() {
        return whiteMate;
    }

    public boolean isBlackMate() {
        return blackMate;
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
