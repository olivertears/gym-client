package com.gym.controller.pages;

import com.gym.utils.CommonUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button btn_test_modal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_test_modal.setOnMouseClicked(event -> {
            try {
                CommonUtils.showModal("components/modal/workout-modal.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
