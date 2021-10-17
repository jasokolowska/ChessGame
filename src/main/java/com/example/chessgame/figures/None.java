package com.example.chessgame.figures;

public class None extends Figure {

    public None(FigureColor color) {
        super(color);
        super.symbol = " ";
    }

    @Override
    public int getValue() {
        return 0;
    }
}
