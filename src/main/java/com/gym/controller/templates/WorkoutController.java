package com.gym.controller.templates;

import com.gym.utils.CommonUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkoutController implements Initializable {
    @FXML
    private VBox wrap_workouts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            coaches();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void coaches () throws IOException {
        CommonUtils.changePage(wrap_workouts, "components/pages/coaches.fxml");
    }

    public void myWorkouts () throws IOException {
        CommonUtils.changePage(wrap_workouts, "components/pages/my-workouts.fxml");
    }
}
