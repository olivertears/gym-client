package com.gym.controller.entity;

import com.gym.State;
import com.gym.entity.User;
import com.gym.utils.CommonUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CoachEntityController implements Initializable {
    @FXML
    private ImageView iv_order_workout;
    @FXML
    private Label lbl_coach;
    @FXML
    private Label lbl_email;
    @FXML
    private Label lbl_price;

    private int id;

    public void setData(User user) {
        this.id = user.getId();
        lbl_coach.setText(user.getName() + " " + user.getSurname());
        lbl_email.setText(user.getEmail());
        lbl_price.setText("$" + user.getPrice());
        iv_order_workout.setVisible(!State.user.getRole().equals("COACH"));
    }

    public int getCoachId() {
        return this.id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iv_order_workout.setOnMouseClicked(event -> {
            try {
                CommonUtils.showModal("components/modal/workout-modal.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
