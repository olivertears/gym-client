package com.gym.controller.modal;

import com.gym.State;
import com.gym.entity.Category;
import com.gym.entity.Transaction;
import com.gym.utils.CategoryUtils;
import com.gym.utils.TransactionUtils;
import com.gym.utils.WorkoutUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionModalController implements Initializable {
    @FXML
    private ImageView iv_exit;
    @FXML
    private ChoiceBox<String> cb_type;
    @FXML
    private ChoiceBox<String> cb_category;
    @FXML
    private TextField tf_price;
    @FXML
    private TextArea tf_description;
    @FXML
    private Button btn_create;
    @FXML
    private DatePicker dp_date;

    private String typeValues[] = { "INCOME", "EXPENSE" };
    private String categoryType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        State.categories = CategoryUtils.getCategoriesByType("ALL");

        prepare();
        setCategoryNames(true);

        cb_type.setOnAction(event -> setCategoryNames(false));

        iv_exit.setOnMouseClicked(event -> {
            State.transaction = null;
            ((Stage) iv_exit.getScene().getWindow()).close();
        });

        tf_price.setOnKeyTyped(event -> handleButtonDisabled());

        btn_create.setOnMouseClicked(event -> {
            if (State.transaction != null) {
                TransactionUtils.updateTransaction(getData());
                State.transaction = null;
            } else {
                TransactionUtils.createTransaction(getData());
            }
            State.refresh.firePropertyChange("transaction", 1, 2);
            ((Stage) btn_create.getScene().getWindow()).close();
        });
    }

    private void prepare() {
        tf_price.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*\\.?\\d{0,2}"))
                return null;
            else
                return c;
        }));

        cb_type.setItems(FXCollections.observableArrayList(typeValues));
        if (State.transaction != null) {
            categoryType = State.categories.stream()
                    .filter(category -> category.getName().equals(State.transaction.getCategoryName()))
                    .findFirst()
                    .orElse(null)
                    .getType();
            cb_type.setValue(categoryType);
            dp_date.setValue(State.transaction.getDate());
            tf_price.setText(new DecimalFormat("#0.00")
                    .format(categoryType.equals("EXPENSE") ? -State.transaction.getPrice() : State.transaction.getPrice())
                    .replace(',', '.'));
            tf_description.setText(State.transaction.getDescription());
        } else {
            cb_type.setValue(cb_type.getItems().get(0));
            dp_date.setValue(LocalDate.now());
        }
    }

    private void setCategoryNames(boolean isFirst) {
        cb_category.getItems().clear();
        for (Category category: State.categories) {
            if (category.getType().equals(cb_type.getValue())) {
                cb_category.getItems().add(category.getName());
            }
        }

        if (State.transaction != null && isFirst) {
            cb_category.setValue(State.transaction.getCategoryName());
        } else if (cb_category.getItems().size() != 0) {
            System.out.println();
            cb_category.setValue(cb_category.getItems().get(0));
        } else {
            btn_create.setDisable(true);
        }

        handleButtonDisabled();
    }

    private Transaction getData() {
        Transaction transaction = new Transaction();
        if (State.transaction != null) {
            transaction.setId(State.transaction.getId());
        }
        transaction.setCategoryName(cb_category.getValue());
        transaction.setDate(dp_date.getValue());
        transaction.setPrice(cb_type.getValue().equals("EXPENSE") ? -1 * Double.parseDouble(tf_price.getText()) : Double.parseDouble(tf_price.getText()));
        transaction.setDescription(tf_description.getText());
        return transaction;
    }

    private void handleButtonDisabled() {
        btn_create.setDisable(tf_price.getText().equals(".") || tf_price.getText().equals("") || cb_category.getValue() == null);
    }
}
