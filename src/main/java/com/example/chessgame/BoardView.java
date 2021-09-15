package com.example.chessgame;

import javafx.application.Application;
import javafx.event.EventType;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BoardView extends Application {
    private Image imageback = new Image("file:src/main/resources/chess_board.png");

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = getGridPane();

        Scene scene = new Scene(grid, 500, 500, Color.BLACK);
        Game game = new Game(new Board(), grid);

        grid.setOnMouseClicked(e -> {
            int x = (int) (e.getX()/65);
            int y = (int) (e.getY()/65);
            System.out.println(x);
            System.out.println(y);
            game.doClick(x,y);
        });
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();


    }

    private GridPane getGridPane() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10,10,10));
        grid.setHgap(20);
        grid.setVgap(10);
        setColumnWidthAndRowHeight(grid, 41.5, 51.5);
//        grid.setGridLinesVisible(true);
        grid.setBackground(background);
        return grid;
    }

    private void setColumnWidthAndRowHeight(GridPane grid, double columnWidth, double rowHeight) {
        for (int i = 0; i < 8; i++) {
            ColumnConstraints column = new ColumnConstraints(columnWidth);
            grid.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 8; i++) {
            RowConstraints row = new RowConstraints(rowHeight);
            grid.getRowConstraints().add(row);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}