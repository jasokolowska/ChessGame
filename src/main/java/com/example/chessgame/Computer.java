package com.example.chessgame;

import java.util.LinkedList;
import java.util.List;

public class Computer {
    private List<Figure> capturedFigures;
    private List<Figure> figures;

    public Computer() {
        this.capturedFigures = new LinkedList<>();
        this.figures = new LinkedList<>();
    }

    void addCapturedFigure(Figure figure) {
        capturedFigures.add(figure);
    }

    public List<Figure> getCapturedFigures() {
        return capturedFigures;
    }
}
