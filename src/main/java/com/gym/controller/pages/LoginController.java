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

public class LoginController implements Initializable {
    @FXML
    private ImageView iv_exit;
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
        iv_exit.setOnMouseClicked(event -> System.exit(0));

        btn_login.setOnAction(event -> {
            String email = tf_email.getText().trim();
            String password = pf_password.getText();
            if (email.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Чтобы войти нужно заполнить все поля (:");
                alert.show();
            } else {
                if (UserUtils.loginUser(email, password)) {
                    CommonUtils.changeScene(btn_login,"components/templates/navbar.fxml");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Неверная почта или пароль :0");
                    alert.show();
                }
            }
        });

        btn_link_signup.setOnAction(event -> CommonUtils.changeScene(btn_link_signup, "components/pages/signup.fxml"));
    }
}
