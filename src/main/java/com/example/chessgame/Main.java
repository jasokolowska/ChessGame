package com.example.chessgame;

public class Main {
    public static void main(String[] args) {
        Board chess = new Board();

        chess.setFigure(2,1, new Pawn("black"));
        chess.setFigure(0,0, new Pawn("black"));
        chess.setFigure(1,1, new Pawn("black"));
        chess.setFigure(1,5, new Queen("white"));
        chess.setFigure(0,2, new Queen("white"));
        System.out.println("Current figure is: " + chess.getFigure(1,5));
        System.out.println(chess);

        chess.move(1,1, 3,1);
        chess.move(1,5, 4,7);
        System.out.println(chess);
    }
}
