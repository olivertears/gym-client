package com.gym.controller.modal;

import com.gym.State;
import com.gym.controller.KeyValuePair;
import com.gym.dto.WorkoutTimeDto;
import com.gym.entity.User;
import com.gym.entity.Workout;
import com.gym.utils.WorkoutUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WorkoutModalController implements Initializable {
    @FXML
    private ImageView iv_exit;
    @FXML
    private ChoiceBox<KeyValuePair> cb_coach;
    @FXML
    private DatePicker dp_date;
    @FXML
    private ChoiceBox<String> cb_time;
    @FXML
    private Label lbl_price;
    @FXML
    private Button btn_confirm;

    private List<String> busyTimes = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepare();
        setData();

        iv_exit.setOnMouseClicked(event -> {
            State.workout = null;
            ((Stage) iv_exit.getScene().getWindow()).close();
        });

        cb_coach.setOnAction(event -> {
            setData();
        });

        dp_date.setOnAction(event -> {
            setData();
        });

        btn_confirm.setOnMouseClicked(event -> {
            if (State.workout != null) {
                WorkoutUtils.updateWorkout(getData());
                State.workout = null;
                State.refresh.firePropertyChange("workout", 1, 2);
            } else {
                WorkoutUtils.createWorkout(getData());
            }
            ((Stage) btn_confirm.getScene().getWindow()).close();
        });
    }

    public void setData() {
        if (cb_coach.getValue() == null) {
            if (State.workout != null) {
                State.coach = State.coaches.stream()
                    .filter(coach -> State.workout.getCoachId() == coach.getId())
                    .findAny()
                    .orElse(null);
            }
            cb_coach.setValue(new KeyValuePair(State.coach.getId(), State.coach.getName() + " " + State.coach.getSurname()));
        }
        if (dp_date.getValue() == null) {
            if (State.workout != null) {
                dp_date.setValue(State.workout.getDate());
            } else {
                dp_date.setValue(State.subscription.getStart().isBefore(LocalDate.now()) ? LocalDate.now() : State.subscription.getStart());
            }
        }

        WorkoutTimeDto workoutTimeDto = new WorkoutTimeDto();
        workoutTimeDto.setCoachId(cb_coach.getValue().getKey());
        workoutTimeDto.setDate(dp_date.getValue());
        busyTimes = WorkoutUtils.getCoachDateWorkoutTimes(workoutTimeDto);

        cb_time.getItems().clear();
        int currentHours = LocalTime.now().getHour();

        boolean selectedWorkout = State.workout != null && dp_date.getValue().equals(State.workout.getDate()) && State.workout.getCoachId() == cb_coach.getValue().getKey();

        for (int i = 9; i <= 21; i++) {
            if (dp_date.getValue().equals(LocalDate.now()) && currentHours > i) {
                i = currentHours;
                continue;
            }
            String timeValue = i < 10 ? "0"+ i + ":00" : i + ":00";

            if (!busyTimes.contains(timeValue) || (selectedWorkout && State.workout.getTime().equals(timeValue))) {
                cb_time.getItems().add(timeValue);
            }
        }

        btn_confirm.setDisable(false);

        if (cb_time.getItems().size() > 0) {
            cb_time.setValue(selectedWorkout ? State.workout.getTime() : cb_time.getItems().get(0));
        } else {
            btn_confirm.setDisable(true);
        }

        lbl_price.setText("$" + new DecimalFormat("#0.00").format(State.coach.getPrice()).replace(',', '.'));
    }

    public Workout getData() {
        Workout workout = new Workout();
        if (State.workout != null) {
            workout.setId(State.workout.getId());
        }
        workout.setClientId(State.user.getId());
        workout.setCoachId(cb_coach.getValue().getKey());
        workout.setPrice(State.coach.getPrice());
        workout.setDate(dp_date.getValue());
        workout.setTime(cb_time.getValue());
        return workout;
    }

    public void prepare() {
        dp_date.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isBefore(State.subscription.getStart()) || item.isBefore(LocalDate.now()) || item.isAfter(State.subscription.getEnd()));
                    }}
        );

        for (User coach: State.coaches) {
            cb_coach.getItems().add(new KeyValuePair(coach.getId(), coach.getName() + " " + coach.getSurname()));
        }
    }
}
