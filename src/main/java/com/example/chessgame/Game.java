package com.example.chessgame;

import com.example.chessgame.figures.*;
import javafx.geometry.Pos;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Game {
    private Image whitePawn = new Image("file:src/main/resources/white_pawn.png");
    private Image blackPawn = new Image("file:src/main/resources/black_pawn.png");
    private Image whiteRook = new Image("file:src/main/resources/white_rook.png");
    private Image blackRook = new Image("file:src/main/resources/black_rook.png");
    private Image whiteKnight = new Image("file:src/main/resources/white_knight.png");
    private Image blackKnight = new Image("file:src/main/resources/black_knight.png");
    private Image whiteBishop = new Image("file:src/main/resources/white_bishop.png");
    private Image blackBishop = new Image("file:src/main/resources/black_bishop.png");
    private Image whiteQueen = new Image("file:src/main/resources/white_queen.png");
    private Image blackQueen = new Image("file:src/main/resources/black_queen.png");
    private Image whiteKing = new Image("file:src/main/resources/white_king.png");
    private Image blackKing = new Image("file:src/main/resources/black_king.png");

    private Board board;
    private GridPane gridPane;
    private int oldX = -1;
    private int oldY = -1;
    private boolean endOfGame;

    public Game(Board board, GridPane gridPane) {
        this.board = board;
        this.gridPane = gridPane;
        this.endOfGame = false;
        board.init();
        displayOnGrid();
    }

    public void doClick(int x, int y) {
        if (oldX == -1){
            oldX = x;
            oldY = y;
            markClicked(y, x);
        }else {
            if (board.move(oldY, oldX, y, x)){
                oldX = -1;
                oldY = -1;
                displayOnGrid();
                board.switchPlayer();
            } else {
                oldX = -1;
                oldY = -1;
            }
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

    private void markClicked(int x, int y) {
        Rectangle rectangle = new Rectangle(62.5,62);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.AQUAMARINE);
//        rectangle.setBlendMode(BlendMode.SOFT_LIGHT);
        rectangle.setStrokeWidth(2.5);

        gridPane.add(rectangle, oldX, oldY);
    }
}
