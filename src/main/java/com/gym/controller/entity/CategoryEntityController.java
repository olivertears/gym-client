package com.gym.controller.entity;

import com.gym.State;
import com.gym.entity.Category;
import com.gym.entity.Subscription;
import com.gym.utils.CategoryUtils;
import com.gym.utils.CommonUtils;
import com.gym.utils.SubscriptionUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CategoryEntityController implements Initializable {
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_update;
    @FXML
    private Label lbl_category;
    @FXML
    private Label lbl_default;
    @FXML
    private Label lbl_expense;
    @FXML
    private Label lbl_income;

    private Category category;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_update.setOnMouseClicked(event -> {
            try {
                State.category = category;
                CommonUtils.showModal("components/modal/category-modal.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btn_delete.setOnMouseClicked(event -> {
            CategoryUtils.deleteCategory(this.category.getId());
            State.refresh.firePropertyChange("category", 1, 2);
        });
    }

    public void setData(Category category) {
        this.category = category;
        lbl_category.setText(category.getName());
        lbl_default.setVisible(category.isDefaultSubscriptionCategory() || category.isDefaultWorkoutCategory());
        lbl_income.setVisible(category.getType().equals("INCOME"));
        lbl_expense.setVisible(category.getType().equals("EXPENSE"));
        btn_delete.setDisable(category.isDefaultSubscriptionCategory() || category.isDefaultWorkoutCategory());
    }
}
