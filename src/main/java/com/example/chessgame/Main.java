package com.example.chessgame;

import com.example.chessgame.figures.*;

public class Main {
    public static void main(String[] args) {
        Board chess = new Board();

        chess.init();
//        chess.setFigure(2,1, new Pawn(FigureColor.BLACK));
//        chess.setFigure(0,0, new Pawn(FigureColor.BLACK));
//        chess.setFigure(1,1, new Pawn(FigureColor.BLACK));
//        chess.setFigure(2,2, new Pawn(FigureColor.WHITE));
//        chess.setFigure(0,2, new Rook(FigureColor.WHITE));
//        chess.setFigure(2,5, new Bishop(FigureColor.WHITE));
//        chess.setFigure(4,3, new Knight(FigureColor.WHITE));
//        chess.setFigure(5,6, new King(FigureColor.WHITE));
//        chess.setFigure(6,1, new Queen(FigureColor.WHITE));

        chess.setFigure(2,1, new Queen(FigureColor.WHITE));

        System.out.println(chess);

        System.out.println("Pawn moves:");
        chess.move(1,1, 2,1);
        System.out.println("Queen moves:");
        chess.move(0,3, 6,3);
        System.out.println("King moves:");
        chess.move(0,4, 5,5);
        System.out.println("Knight moves:");
        chess.move(0,1, 2,0);
        System.out.println("Bishop moves:");
        chess.move(0,5, 4,7);
        System.out.println("Rook moves:");
        chess.move(0,0, 1,3);
        System.out.println(chess);
    }
}
