package com.gym.controller.entity;

import com.gym.State;
import com.gym.entity.Workout;
import com.gym.utils.CommonUtils;
import com.gym.utils.WorkoutUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class WorkoutEntityController implements Initializable {
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_done_false;
    @FXML
    private Button btn_done_true;
    @FXML
    private Button btn_update;
    @FXML
    private Label lbl_date;
    @FXML
    private Label lbl_price;
    @FXML
    private Label lbl_time;
    @FXML
    private Label lbl_user;
    @FXML
    private Label lbl_user_value;

    private int id;
    private int coachId;
    private int clientId;
    private double price;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_update.setOnMouseClicked(event -> {
            try {
                State.workout = getData();
                CommonUtils.showModal("components/modal/workout-modal.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btn_delete.setOnMouseClicked(event -> {
            WorkoutUtils.deleteWorkout(this.id);
            State.refresh.firePropertyChange("workout", 1, 2);
        });

        btn_done_true.setOnMouseClicked(event -> {
            WorkoutUtils.setWorkoutDone(getData());
            State.refresh.firePropertyChange("workout", 1, 2);
        });

        btn_done_false.setOnMouseClicked(event -> {
            WorkoutUtils.deleteWorkout(this.id);
            State.refresh.firePropertyChange("workout", 1, 2);
        });
    }

    public void setData(Workout workout) {
        this.id = workout.getId();
        this.coachId = workout.getCoachId();
        this.clientId = workout.getClientId();
        this.price = workout.getPrice();
        lbl_date.setText(String.valueOf(workout.getDate()));
        lbl_time.setText(workout.getTime());
        lbl_price.setText("$" + new DecimalFormat("#0.00").format(workout.getPrice()).replace(',', '.'));
        if (State.user.getRole().equals("COACH")) {
            lbl_user.setText("Клиент:");
            lbl_user_value.setText(workout.getClient());
            btn_update.setVisible(false);
        } else {
            lbl_user.setText("Тренер:");
            lbl_user_value.setText(workout.getCoach());
        }
        boolean isFinishedWorkout = workout.getDate().isBefore(LocalDate.now()) || (workout.getDate().equals(LocalDate.now()) &&  LocalTime.now().getHour() >= Integer.parseInt(workout.getTime().substring(0,2)));
        btn_done_true.setVisible(isFinishedWorkout);
        btn_done_false.setVisible(isFinishedWorkout);
        btn_delete.setVisible(!isFinishedWorkout);
    }

    public Workout getData() {
        Workout workout = new Workout();
        workout.setId(this.id);
        workout.setClientId(this.clientId);
        workout.setCoachId(this.coachId);
        workout.setDate(LocalDate.parse(lbl_date.getText()));
        workout.setTime(lbl_time.getText());
        workout.setPrice(this.price);
        return workout;
    }
}
