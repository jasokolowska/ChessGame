package com.example.chessgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Board extends Application {

    private Image imageback = new Image("file:src/main/resources/chess_board.png");
    private Image whitePawn = new Image("file:src/main/resources/white_pawn.png");

    @Override
    public void start(Stage stage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10,10,10));
        grid.setHgap(20);
        grid.setVgap(10);
        for (int i = 0; i < 8; i++) {
            ColumnConstraints column = new ColumnConstraints(41.5);
            grid.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 8; i++) {
            RowConstraints row = new RowConstraints(51.5);
            grid.getRowConstraints().add(row);
        }
//        grid.setGridLinesVisible(true);
        grid.setBackground(background);

        ImageView whitePawnFigure = new ImageView(whitePawn);
        ImageView whitePawnFigure2 = new ImageView(whitePawn);

        grid.add(whitePawnFigure, 0,0);
        GridPane.setHalignment(whitePawnFigure, HPos.CENTER);
//        grid.add(whitePawnFigure2, 7,7);
        Scene scene = new Scene(grid, 500, 500, Color.BLACK);

        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}