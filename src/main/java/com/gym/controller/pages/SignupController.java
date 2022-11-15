package com.gym.controller.pages;

import com.gym.utils.CommonUtils;
import com.gym.utils.UserUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    @FXML
    private ImageView iv_exit;
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
        iv_exit.setOnMouseClicked(event -> System.exit(0));

        btn_signup.setOnAction(event -> {
                String name = tf_name.getText().trim();
                String surname = tf_surname.getText().trim();
                String email = tf_email.getText().trim();
                String password = pf_password.getText();
                if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Чтобы зарегистрироваться нужно заполнить все поля (:");
                    alert.show();
                } else {
                    if (UserUtils.signupUser(name, surname, email, password)) {
                        CommonUtils.changeScene(btn_signup,"components/templates/navbar.fxml");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Что-то пошло не так, пожалуйста попробуйте позже :0");
                        alert.show();
                    }
                }
        });

        btn_link_login.setOnAction(event ->  CommonUtils.changeScene(btn_link_login,"components/pages/login.fxml"));
    }
}
