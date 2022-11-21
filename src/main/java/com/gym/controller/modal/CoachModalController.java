package com.gym.controller.modal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CoachModalController implements Initializable {
    @FXML
    private ImageView iv_exit;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_surname;
    @FXML
    private TextField tf_price;
    @FXML
    private Button btn_save;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iv_exit.setOnMouseClicked(event -> ((Stage) iv_exit.getScene().getWindow()).close());

        tf_price.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*\\.?\\d{0,2}"))
                return null;
            else
                return c;
        }));
    }
}
