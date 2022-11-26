package com.gym.controller.entity;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WorkoutEntityController implements Initializable {
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_done_false;
    @FXML
    private Button btn_done_true;
    @FXML
    private Button btn_update;
    @FXML
    private Label lbl_date;
    @FXML
    private Label lbl_price;
    @FXML
    private Label lbl_time;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
