package com.example.tictactoe_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
    @FXML
    private Label winLabel;
    private Button[][] buttonList;
    private String XO = "X";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttonList = new Button[3][3];
        buttonList[0][0] = b1;
        buttonList[0][1] = b2;
        buttonList[0][2] = b3;
        buttonList[1][0] = b4;
        buttonList[1][1] = b5;
        buttonList[1][2] = b6;
        buttonList[2][0] = b7;
        buttonList[2][1] = b8;
        buttonList[2][2] = b9;
    }

    @FXML
    private void setOnClick(ActionEvent e) {
        for (Button[] buttons : buttonList) {
            for (Button button : buttons) {
                if(e.getSource() == button && button.getText().equals("")) {
                    //checks that win label isnt visible if game is still playing
                    if (!winLabel.getText().equals("")) {
                        winLabel.setText("");
                    } else {
                        button.setText(XO);
                        //checks for a win
                        if (checkWin()) {
                            winLabel.setText(XO + ": WINS");
                            restart();
                            //checks for draw
                        } else if (checkDraw()) {
                            winLabel.setText("DRAW");
                            restart();
                        } else {
                            changeXO();
                        }
                    }
                    return;
                }
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            //check rows
            if (checkLineEqual(buttonList[i][0], buttonList[i][1], buttonList[i][2])
                    //check columns
                || checkLineEqual(buttonList[0][i], buttonList[1][i], buttonList[2][i])) {
                return true;
            }
        }

        //check Diagonals
        if(checkLineEqual(buttonList[0][0], buttonList[1][1], buttonList[2][2])
            || checkLineEqual(buttonList[0][2], buttonList[1][1], buttonList[2][0])) {
            return true;
        }
        return false;
    }

    private boolean checkLineEqual(Button b1, Button b2, Button b3) {
        return b1.getText().equals(XO) && b2.getText().equals(XO) && b3.getText().equals(XO);
    }

    private void changeXO() {
        XO = (Objects.equals(XO, "O")) ? "X" : "O";
    }

    private boolean checkDraw() {
        long count = Arrays.stream(buttonList)
                .flatMap(Arrays::stream)
                .filter(button -> button.getText().equals(""))
                .count();
        return count == 0;
    }

    private void restart() {
        Arrays.stream(buttonList)
                .flatMap(Arrays::stream)
                .forEach(button -> button.setText(""));
        changeXO();
    }
}