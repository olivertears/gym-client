package com.gym.controller.pages;

import com.gym.Application;
import com.gym.controller.entity.CategoryEntityController;
import com.gym.controller.entity.WorkoutEntityController;
import com.gym.entity.Category;
import com.gym.entity.Workout;
import com.gym.utils.CategoryUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    @FXML
    private Pane btn_add_category;
    @FXML
    private Pane btn_default_categories;
    @FXML
    private VBox wrap_category;
    @FXML
    private Pane wrap_header;
    @FXML
    private ChoiceBox<String> cb_filter;

    private String filterValues[] = { "ALL", "INCOME", "EXPENSE" };
    private List<Category> categories;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_filter.setItems(FXCollections.observableArrayList(filterValues));
        cb_filter.setValue(filterValues[0]);

        categories = CategoryUtils.getCategoriesByType(cb_filter.getValue());
        System.out.println(categories);

        setCategories(categories);
    }

    private void setCategories(List<Category> categories) {
        wrap_category.getChildren().clear();
        for (Category category : categories) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Application.class.getResource("components/entity/category-entity.fxml"));
            try {
                AnchorPane workoutEntity = fxmlLoader.load();
                CategoryEntityController categoryEntityController = fxmlLoader.getController();
                categoryEntityController.setData(category);
                wrap_category.getChildren().add(workoutEntity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
