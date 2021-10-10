package com.example.chessgame;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;

import java.io.IOException;
import java.util.EventListener;
import java.util.List;

public class BoardView extends Application {
    private Image imageback = new Image("file:src/main/resources/chess_board.png");

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = getGridPane();
        Scene scene = new Scene(grid, 480, 480, Color.WHITE);
        Game game = new Game(new Board(), grid);

        grid.setOnMouseClicked(e -> {
            System.out.println(e.getX());
            System.out.println(e.getY());

            int x = (int) (e.getX() / 60);
            int y = (int) (e.getY() / 60);
            System.out.println(x);
            System.out.println(y);
            game.doClick(x, y);
        });

        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();

//        Popup popup = new Popup();
//        popup.setAutoHide(true);
//        popup.setAutoFix(true);
//        Label popupLabel = new Label("Szach Mat\n");
//        popupLabel.setStyle("-fx-background-color:blue;"
//                + " -fx-text-fill: black;"
//                + " -fx-font-size: 12;"
//                + " -fx-padding: 10px;"
//                + " -fx-background-radius: 6;");
//
//        popup.getContent().add(popupLabel);
//
//        game.isEndOfGame().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                popup.show(stage);
//            }
//        });

    }

    private GridPane getGridPane() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(0, 0, 0, 0));
        grid.setHgap(0);
        grid.setVgap(0);
        setColumnWidthAndRowHeight(grid, 59.5, 59.5);
        grid.setBackground(background);

        return grid;
    }

    private void setColumnWidthAndRowHeight(GridPane grid, double columnWidth, double rowHeight) {
        for (int i = 0; i < 8; i++) {
            ColumnConstraints column = new ColumnConstraints(columnWidth);
            column.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 8; i++) {
            RowConstraints row = new RowConstraints(rowHeight);
            row.setValignment(VPos.CENTER);
            grid.getRowConstraints().add(row);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}