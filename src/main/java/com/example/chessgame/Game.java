package com.example.chessgame;

import com.example.chessgame.figures.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Game {
    private final Image whitePawn = new Image("file:src/main/resources/white_pawn.png");
    private final Image blackPawn = new Image("file:src/main/resources/black_pawn.png");
    private final Image whiteRook = new Image("file:src/main/resources/white_rook.png");
    private final Image blackRook = new Image("file:src/main/resources/black_rook.png");
    private final Image whiteKnight = new Image("file:src/main/resources/white_knight.png");
    private final Image blackKnight = new Image("file:src/main/resources/black_knight.png");
    private final Image whiteBishop = new Image("file:src/main/resources/white_bishop.png");
    private final Image blackBishop = new Image("file:src/main/resources/black_bishop.png");
    private final Image whiteQueen = new Image("file:src/main/resources/white_queen.png");
    private final Image blackQueen = new Image("file:src/main/resources/black_queen.png");
    private final Image whiteKing = new Image("file:src/main/resources/white_king.png");
    private final Image blackKing = new Image("file:src/main/resources/black_king.png");

    private final Board board;
    private final GridPane gridPane;
    private int oldX = -1;
    private int oldY = -1;
    private BooleanProperty endOfGame;

    public Game(Board board, GridPane gridPane) {
        this.board = board;
        this.gridPane = gridPane;
        this.endOfGame = new SimpleBooleanProperty(false);
        board.init();
        displayOnGrid();
    }

    public void doClick(int x, int y) {
        if (oldX == -1){
            oldX = x;
            oldY = y;
            markClickedFigure(y, x);
            markAvailableMoves(y, x);
        }else {
            if (board.move(oldY, oldX, y, x)){
                oldX = -1;
                oldY = -1;
                if (board.isBlackMate() || board.isWhiteMate()) {
                    endOfGame.setValue(true);
                } else {
                    board.switchPlayer();
                }
            } else {
                oldX = -1;
                oldY = -1;
            }
            displayOnGrid();
        }
    }

    private void displayOnGrid() {
        gridPane.getChildren().clear();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++){
                Image image = null;
                Figure figure = board.getFigure(row, col);
                if (figure.getColor() == FigureColor.BLACK){
                    if (figure instanceof Pawn)
                        image = blackPawn;
                    else if (figure instanceof Bishop)
                        image = blackBishop;
                    else if (figure instanceof Rook)
                        image = blackRook;
                    else if (figure instanceof Knight)
                        image = blackKnight;
                    else if (figure instanceof King)
                        image = blackKing;
                    else if (figure instanceof Queen)
                        image = blackQueen;
                } else {
                    if (figure instanceof Pawn)
                        image = whitePawn;
                    else if (figure instanceof Bishop)
                        image = whiteBishop;
                    else if (figure instanceof Rook)
                        image = whiteRook;
                    else if (figure instanceof Knight)
                        image = whiteKnight;
                    else if (figure instanceof King)
                        image = whiteKing;
                    else if (figure instanceof Queen)
                        image = whiteQueen;
                }

                gridPane.add(new ImageView(image), col,row);
            }
        }

    }

    private void markClickedFigure(int x, int y) {
        Rectangle rectangle = new Rectangle(60.5,60.5);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.AQUAMARINE);
        rectangle.setStrokeWidth(2);

        gridPane.add(rectangle, oldX, oldY);
    }

    private void markSpot(int x, int y) {
        Circle circle = new Circle(25,Color.AQUAMARINE);
        circle.setOpacity(0.5);
        gridPane.add(circle, x, y);
    }

    private void markAvailableMoves(int row, int col) {
        List<Coordinates> movesCoordinates = board.availableMoves(row, col);
        for (Coordinates moveCoordinate : movesCoordinates) {
            markSpot(moveCoordinate.getColumn(), moveCoordinate.getRow());
        }
    }

    public BooleanProperty isEndOfGame() {
        return endOfGame;
    }
}
