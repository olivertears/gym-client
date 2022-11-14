package com.gym.controller;

import com.gym.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button btn_logout;

    @FXML
    private Label lbl_user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_logout.setOnAction(event -> {
                DBUtils.changeScene(btn_logout, "login.fxml", "Login");
        });
    }

    public void setUser(String name, String surname) {
        lbl_user.setText(name + " " + surname);
    }
}
