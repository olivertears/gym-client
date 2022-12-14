package com.gym.controller.pages;

import com.gym.State;
import com.gym.entity.User;
import com.gym.utils.CommonUtils;
import com.gym.utils.UserUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
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

        tf_email.setText("admin@gmail.com");
        pf_password.setText("admin");

        btn_login.setOnAction(event -> {
            String email = tf_email.getText().trim();
            String password = pf_password.getText();
            if (email.isEmpty() || password.isEmpty()) {
                try {
                    CommonUtils.showAlert("Чтобы войти нужно заполнить все поля (:");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                User user = UserUtils.login(email, password);
                if (user == null) {
                    try {
                        CommonUtils.showAlert("Неверная почта или пароль :0");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    State.user = user;
                    CommonUtils.changeScene(btn_login,"components/templates/navbar.fxml");
                }
            }
        });

        btn_link_signup.setOnAction(event -> CommonUtils.changeScene(btn_link_signup, "components/pages/signup.fxml"));
    }
}
