package com.gym.controller.pages;

import com.gym.Application;
import com.gym.State;
import com.gym.controller.entity.CategoryEntityController;
import com.gym.controller.entity.TransactionEntityController;
import com.gym.dto.TransactionFilterDto;
import com.gym.entity.Category;
import com.gym.entity.Transaction;
import com.gym.utils.CategoryUtils;
import com.gym.utils.CommonUtils;
import com.gym.utils.TransactionUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    @FXML
    private Pane btn_add_transaction;
    @FXML
    private DatePicker dp_end;
    @FXML
    private DatePicker dp_start;
    @FXML
    private VBox wrap_transaction;

    private LocalDate start =  LocalDate.now().withDayOfMonth(1);
    private LocalDate end = LocalDate.now();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepare();
        initController();

        dp_start.setOnAction(event -> {
            start = dp_start.getValue();
            initController();
        });

        dp_end.setOnAction(event -> {
            end = dp_end.getValue();
            initController();
        });

        btn_add_transaction.setOnMouseClicked(event -> {
            try {
                CommonUtils.showModal("components/modal/transaction-modal.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

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
        TransactionFilterDto transactionFilterDto = new TransactionFilterDto();
        transactionFilterDto.setStart(dp_start.getValue());
        transactionFilterDto.setEnd(dp_end.getValue());
        State.transactions = TransactionUtils.getTransactions(transactionFilterDto);
        setTransactions(State.transactions);
    }

    private void prepare() {
        dp_start.setValue(start);
        dp_end.setValue(end);

        dp_start.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(end));
                    }}
        );

        dp_end.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isBefore(start));
                    }}
        );
    }
}
