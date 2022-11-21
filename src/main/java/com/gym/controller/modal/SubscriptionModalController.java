package com.gym.controller.modal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SubscriptionModalController implements Initializable {
    @FXML
    private ImageView iv_exit;
    @FXML
    private ChoiceBox cb_type;
    @FXML
    private DatePicker dp_start;
    @FXML
    private ChoiceBox cb_duration;
    @FXML
    private Label lbl_price;
    @FXML
    private Button btn_confirm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iv_exit.setOnMouseClicked(event -> ((Stage) iv_exit.getScene().getWindow()).close());

    }
}
