package com.gym.controller.modal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DefaultCategoriesModalController implements Initializable {
    @FXML
    private Button btn_save;
    @FXML
    private ChoiceBox<?> cb_subscription_category;
    @FXML
    private ChoiceBox<?> cb_workout_category;
    @FXML
    private ImageView iv_exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iv_exit.setOnMouseClicked(event -> ((Stage) iv_exit.getScene().getWindow()).close());

    }
}
