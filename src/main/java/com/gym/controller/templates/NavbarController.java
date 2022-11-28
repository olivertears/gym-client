package com.gym.controller.templates;

import com.gym.Application;
import com.gym.State;
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

    @FXML
    private Button btn_budget;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iv_exit.setOnMouseClicked(event -> System.exit(0));

        btn_logout.setOnMouseClicked(event -> CommonUtils.changeScene(btn_logout, "components/pages/login.fxml"));

        if (!State.user.getRole().equals("ADMIN")) {
            btn_budget.setVisible(false);
        }

        try {
            setUser();
            profile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        State.refresh.addPropertyChangeListener(event -> {
            if (event.getPropertyName().equals("user"))   {
                setUser();
            }
        });
    }

    public void setUser() {
        lbl_user.setText(State.user.getName() + " " + State.user.getSurname());
    }

    public void profile () throws IOException {
        CommonUtils.changePage(wrap_navbar, "components/pages/profile.fxml");
    }

    public void subscription () throws IOException {
        CommonUtils.changePage(wrap_navbar, "components/pages/subscription.fxml");
    }

    public void workout () throws IOException {
        CommonUtils.changePage(wrap_navbar, "components/templates/workout.fxml");
    }

    public void budget () throws IOException {
        CommonUtils.changePage(wrap_navbar, "components/templates/budget.fxml");
    }

    public void user () throws IOException {
        CommonUtils.changePage(wrap_navbar, "components/pages/user.fxml");
    }
}
