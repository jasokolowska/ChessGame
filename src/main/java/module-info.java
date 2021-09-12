module com.example.chessgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chessgame to javafx.fxml;
    exports com.example.chessgame;
    exports com.example.chessgame.figures;
    opens com.example.chessgame.figures to javafx.fxml;
}