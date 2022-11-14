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

public class SignupController implements Initializable {
    @FXML
    private Button btn_signup;
    @FXML
    private Button btn_link_login;

    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_surname;
    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField pf_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_signup.setOnAction(event -> {
                String name = tf_name.getText().trim();
                String surname = tf_surname.getText().trim();
                String email = tf_email.getText().trim();
                String password = pf_password.getText();
                if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up (:");
                    alert.show();
                } else {
                    DBUtils.signupUser(event, name, surname, email, password);
                    if (DBUtils.loginUser(event, email, password)) {
                        DBUtils.changeScene(btn_signup,"home.fxml", "Home");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Something went wrong please try again later :0");
                        alert.show();
                    }
                }
        });

        btn_link_login.setOnAction(event ->  {
            DBUtils.changeScene(btn_link_login,"login.fxml","Login");
        });
    }
}
