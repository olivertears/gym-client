package com.gym.controller.modal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertModalController implements Initializable {
    @FXML
    private ImageView iv_exit;
    @FXML
    private Label lbl_text;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iv_exit.setOnMouseClicked(event -> ((Stage) iv_exit.getScene().getWindow()).close());
    }

    public void setData(String text) {
        lbl_text.setText(text);
    }
}
