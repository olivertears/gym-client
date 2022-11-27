package com.gym.controller.pages;

import com.gym.Application;
import com.gym.State;
import com.gym.controller.entity.WorkoutEntityController;
import com.gym.entity.Workout;
import com.gym.utils.WorkoutUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MyWorkoutsController implements Initializable {
    @FXML
    private VBox wrap_my_workouts;
    @FXML
    private Label lbl_title;

    private List<Workout> workouts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initController();

        State.refresh.addPropertyChangeListener(event -> {
            if (event.getPropertyName().equals("workout"))   {
                initController();
            }
        });
    }

    private void setWorkouts(List<Workout> workouts) {
        wrap_my_workouts.getChildren().clear();
        wrap_my_workouts.getChildren().add(lbl_title);
        for (Workout workout : workouts) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Application.class.getResource("components/entity/workout-entity.fxml"));
            try {
                AnchorPane workoutEntity = fxmlLoader.load();
                WorkoutEntityController workoutEntityController = fxmlLoader.getController();
                workoutEntityController.setData(workout);
                wrap_my_workouts.getChildren().add(workoutEntity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initController() {
        if (State.user.getRole().equals("COACH")) {
            workouts = WorkoutUtils.getCoachWorkouts(State.user.getId());
        } else {
            workouts = WorkoutUtils.getClientWorkouts(State.user.getId());
        }

        setWorkouts(workouts);
    }
}
