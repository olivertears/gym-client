package com.gym.controller.entity;

import com.gym.State;
import com.gym.entity.Category;
import com.gym.entity.Subscription;
import com.gym.utils.SubscriptionUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    private int id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_update.setOnMouseClicked(event -> {

        });


        btn_delete.setOnMouseClicked(event -> {
        });
    }

    public void setData(Category category) {
        this.id = category.getId();
        lbl_category.setText(category.getName());
        lbl_default.setVisible(category.isDefaultSubscriptionCategory() || category.isDefaultWorkoutCategory());
        lbl_income.setVisible(category.getType().equals("INCOME"));
        lbl_expense.setVisible(category.getType().equals("EXPENSE"));
        btn_delete.setDisable(category.isDefaultSubscriptionCategory() || category.isDefaultWorkoutCategory());
    }
}
