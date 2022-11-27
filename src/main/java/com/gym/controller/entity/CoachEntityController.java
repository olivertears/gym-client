package com.gym.controller.entity;

import com.gym.State;
import com.gym.entity.User;
import com.gym.utils.CommonUtils;
import com.gym.utils.SubscriptionUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CoachEntityController implements Initializable {
    @FXML
    private Pane pane_order_workout;
    @FXML
    private Label lbl_coach;
    @FXML
    private Label lbl_email;
    @FXML
    private Label lbl_price;

    private User coach;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane_order_workout.setOnMouseClicked(event -> {
            if (State.subscription == null) {
                try {
                    CommonUtils.showAlert("Прежде чем записаться на тренировку нужно приобрести абонемент (:");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    State.coach = this.coach;
                    CommonUtils.showModal("components/modal/workout-modal.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setData(User user) {
        coach = user;
        lbl_coach.setText(user.getName() + " " + user.getSurname());
        lbl_email.setText(user.getEmail());
        lbl_price.setText("$" +  new DecimalFormat("#0.00").format(user.getPrice()).replace(',', '.'));
        pane_order_workout.setVisible(!State.user.getRole().equals("COACH"));
    }
}
