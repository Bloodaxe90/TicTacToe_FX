module com.example.tictactoe_fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoe_fx to javafx.fxml;
    exports com.example.tictactoe_fx;
}