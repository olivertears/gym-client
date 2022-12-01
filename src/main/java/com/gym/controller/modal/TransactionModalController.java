package com.gym.controller.modal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionModalController implements Initializable {
    @FXML
    private ImageView iv_exit;
    @FXML
    private ChoiceBox cb_type;
    @FXML
    private TextField tf_category;
    @FXML
    private TextField tf_price;
    @FXML
    private TextArea tf_description;
    @FXML
    private Button btn_create;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iv_exit.setOnMouseClicked(event -> ((Stage) iv_exit.getScene().getWindow()).close());

        tf_price.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*\\.?\\d{0,2}"))
                return null;
            else
                return c;
        }));

        tf_price.setOnKeyTyped(event -> {
            btn_create.setDisable(tf_price.getText().equals(".") || tf_price.getText().equals(""));
        });
    }
}
