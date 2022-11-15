package com.gym.controller.templates;

import com.gym.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BudgetController implements Initializable {
    @FXML
    private VBox wrap_budget;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            operation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void operation () throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("components/pages/operation.fxml")));
        wrap_budget.getChildren().removeAll();
        wrap_budget.getChildren().setAll(fxml);
    }

    public void category () throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("components/pages/category.fxml")));
        wrap_budget.getChildren().removeAll();
        wrap_budget.getChildren().setAll(fxml);
    }

    public void report () throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("components/pages/report.fxml")));
        wrap_budget.getChildren().removeAll();
        wrap_budget.getChildren().setAll(fxml);
    }
}
