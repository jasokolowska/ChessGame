package com.example.chessgame;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private List<Figure> capturedFigures;


    public Player() {
        this.capturedFigures = new LinkedList<>();
    }

    void addCapturedFigure(Figure figure) {
        capturedFigures.add(figure);
    }

    public List<Figure> getCapturedFigures() {
        return capturedFigures;
    }
}
