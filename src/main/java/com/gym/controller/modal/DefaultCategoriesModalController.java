package com.gym.controller.modal;

import com.gym.State;
import com.gym.controller.KeyValuePair;
import com.gym.dto.DefaultCategoryDto;
import com.gym.entity.Category;
import com.gym.entity.Workout;
import com.gym.utils.CategoryUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DefaultCategoriesModalController implements Initializable {
    @FXML
    private Button btn_save;
    @FXML
    private ChoiceBox<KeyValuePair> cb_subscription_category;
    @FXML
    private ChoiceBox<KeyValuePair> cb_workout_category;
    @FXML
    private ImageView iv_exit;

    private Category workoutDefaultCategory;
    private Category subscriptionDefaultCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepare();

        iv_exit.setOnMouseClicked(event -> ((Stage) iv_exit.getScene().getWindow()).close());

        btn_save.setOnMouseClicked(event -> {
            DefaultCategoryDto defaultCategoryDto = new DefaultCategoryDto();
            defaultCategoryDto.setDefaultSubscriptionCategoryId(cb_subscription_category.getValue().getKey());
            defaultCategoryDto.setDefaultWorkoutCategoryId(cb_workout_category.getValue().getKey());
            CategoryUtils.setDefaultCategory(defaultCategoryDto);
            ((Stage) btn_save.getScene().getWindow()).close();
            State.refresh.firePropertyChange("category", 1, 2);
        });
    }

    public void prepare() {
        for (Category category: State.categories) {
            if (category.getType().equals("INCOME")) {
                cb_subscription_category.getItems().add(new KeyValuePair(category.getId(), category.getName()));
                cb_workout_category.getItems().add(new KeyValuePair(category.getId(), category.getName()));
            }
        }
        subscriptionDefaultCategory = State.categories.stream()
                .filter(category -> category.isDefaultSubscriptionCategory())
                .findFirst()
                .orElse(null);
        workoutDefaultCategory = State.categories.stream()
                .filter(category -> category.isDefaultWorkoutCategory())
                .findFirst()
                .orElse(null);

        cb_subscription_category.setValue(new KeyValuePair(subscriptionDefaultCategory.getId(), subscriptionDefaultCategory.getName()));
        cb_workout_category.setValue(new KeyValuePair(workoutDefaultCategory.getId(), workoutDefaultCategory.getName()));
    }
}
