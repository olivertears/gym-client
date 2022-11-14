package com.gym.controller;

import com.gym.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_link_signup;

    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField pf_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_login.setOnAction(event -> {
            String email = tf_email.getText().trim();
            String password = pf_password.getText();
            if (email.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in all information to login (:");
                alert.show();
            } else {
                if (DBUtils.loginUser(event, email, password)) {
                    DBUtils.changeScene(btn_login,"home.fxml", "Home");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Wrong email or password :0");
                    alert.show();
                }
            }
        });

        btn_link_signup.setOnAction(event -> {
            DBUtils.changeScene(btn_link_signup, "signup.fxml", "Signup");
        });
    }
}
