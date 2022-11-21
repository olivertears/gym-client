package com.gym.controller.modal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryModalController implements Initializable {
    @FXML
    private ImageView iv_exit;
    @FXML
    private ChoiceBox cb_type;
    @FXML
    private TextField tf_name;
    @FXML
    private Button btn_create;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iv_exit.setOnMouseClicked(event -> ((Stage) iv_exit.getScene().getWindow()).close());

    }
}
