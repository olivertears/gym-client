package com.gym.controller.pages;

import com.gym.Application;
import com.gym.State;
import com.gym.controller.entity.CategoryEntityController;
import com.gym.controller.entity.TransactionEntityController;
import com.gym.entity.Category;
import com.gym.entity.Transaction;
import com.gym.utils.CategoryUtils;
import com.gym.utils.TransactionUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    @FXML
    private Pane btn_add_category;
    @FXML
    private DatePicker dp_end;
    @FXML
    private DatePicker dp_start;
    @FXML
    private VBox wrap_transaction;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initController();

        State.refresh.addPropertyChangeListener(event -> {
            if (event.getPropertyName().equals("transaction"))   {
                initController();
            }
        });
    }

    private void setTransactions(List<Transaction> transactions) {
        wrap_transaction.getChildren().clear();
        for (Transaction transaction : transactions) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Application.class.getResource("components/entity/transaction-entity.fxml"));
            try {
                VBox transactionEntity = fxmlLoader.load();
                TransactionEntityController transactionEntityController = fxmlLoader.getController();
                transactionEntityController.setData(transaction);
                wrap_transaction.getChildren().add(transactionEntity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initController() {
        State.transactions = TransactionUtils.getTransactions();
        setTransactions(State.transactions);
    }
}
