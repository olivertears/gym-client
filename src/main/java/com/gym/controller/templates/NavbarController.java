package com.gym.controller.templates;

import com.gym.Application;
import com.gym.utils.CommonUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NavbarController implements Initializable {
    @FXML
    private ImageView iv_exit;
    @FXML
    private Button btn_logout;
    @FXML
    private Label lbl_user;
    @FXML
    private VBox wrap_navbar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iv_exit.setOnMouseClicked(event -> System.exit(0));

        btn_logout.setOnMouseClicked(event -> CommonUtils.changeScene(btn_logout, "components/pages/login.fxml"));

        try {
            home();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUser(String name, String surname) {
        lbl_user.setText(name + " " + surname);
    }
    public void home () throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("components/pages/home.fxml")));
        wrap_navbar.getChildren().removeAll();
        wrap_navbar.getChildren().setAll(fxml);
    }

    public void subscription () throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("components/pages/subscription.fxml")));
        wrap_navbar.getChildren().removeAll();
        wrap_navbar.getChildren().setAll(fxml);
    }

    public void workout () throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("components/pages/workout.fxml")));
        wrap_navbar.getChildren().removeAll();
        wrap_navbar.getChildren().setAll(fxml);
    }

    public void budget () throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("components/templates/budget.fxml")));
        wrap_navbar.getChildren().removeAll();
        wrap_navbar.getChildren().setAll(fxml);
    }

    public void user () throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("components/pages/user.fxml")));
        wrap_navbar.getChildren().removeAll();
        wrap_navbar.getChildren().setAll(fxml);
    }
}
