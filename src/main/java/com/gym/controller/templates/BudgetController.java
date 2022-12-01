package com.gym.controller.templates;

import com.gym.utils.CommonUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BudgetController implements Initializable {
    @FXML
    private VBox wrap_budget;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            transaction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void transaction () throws IOException {
        CommonUtils.changePage(wrap_budget, "components/pages/transaction.fxml");
    }

    public void category () throws IOException {
        CommonUtils.changePage(wrap_budget, "components/pages/category.fxml");
    }

    public void report () throws IOException {
        CommonUtils.changePage(wrap_budget, "components/pages/report.fxml");
    }
}
