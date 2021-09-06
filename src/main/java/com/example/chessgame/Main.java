package com.example.chessgame;

public class Main {
    public static void main(String[] args) {
        Board chess = new Board();

        chess.setFigure(3,3, new Pawn("white"));
        chess.setFigure(1,1, new Pawn("white"));
        chess.setFigure(2,2, new Pawn("white"));
        chess.setFigure(2,6, new Queen("white"));
        chess.setFigure(1,3, new Queen("white"));
        System.out.println("Current figure is: " + chess.getFigure(2,6));
        System.out.println(chess);


        System.out.println(chess);
    }
}
