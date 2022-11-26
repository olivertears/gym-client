package com.gym.controller.modal;

import com.gym.State;
import com.gym.controller.IControllerWithProperty;
import com.gym.entity.Subscription;
import com.gym.utils.SubscriptionUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SubscriptionModalController implements Initializable, IControllerWithProperty {
    private static final ObjectProperty<Object> selectedProperty = new SimpleObjectProperty();
    @FXML
    private ImageView iv_exit;
    @FXML
    private ChoiceBox cb_type;
    @FXML
    private DatePicker dp_start;
    @FXML
    private ChoiceBox cb_duration;
    @FXML
    private Label lbl_price;
    @FXML
    private Button btn_confirm;

    private String typeValues[] = { "STANDARD", "PREMIUM" };
    private String durationValues[] = { "1 WEEK", "1 MONTH", "3 MONTHs", "6 MONTHS", "1 YEAR"};
    private double priceValues[] = {30.00, 85.00, 225.00, 390.00, 660.00};
    private LocalDate today = LocalDate.now();

    @Override
    public ObjectProperty<Object> selectedProperty() {
        return selectedProperty;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dp_start.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isBefore(today));
                    }}
        );

        setData();

        iv_exit.setOnMouseClicked(event -> ((Stage) iv_exit.getScene().getWindow()).close());

        cb_type.setOnAction(event -> lbl_price.setText("$" + getPrice() + "0"));

        cb_duration.setOnAction(event -> lbl_price.setText("$" + getPrice() + "0"));

        btn_confirm.setOnMouseClicked(event -> {
            SubscriptionUtils.createSubscription(getData());
            ((Stage) btn_confirm.getScene().getWindow()).close();
            selectedProperty.set(new Object());
        });
    }

    public void setData() {
        cb_type.setItems(FXCollections.observableArrayList(typeValues));
        cb_type.setValue(typeValues[0]);
        dp_start.setValue(today);
        cb_duration.setItems(FXCollections.observableArrayList(durationValues));
        cb_duration.setValue(durationValues[0]);
        lbl_price.setText("$" + priceValues[0] + "0");
    }

    public Subscription getData() {
        Subscription subscription = new Subscription();
        subscription.setUserId(State.user.getId());
        subscription.setType((String) cb_type.getValue());
        subscription.setPrice(getPrice());
        subscription.setStart(dp_start.getValue());
        subscription.setEnd(getEndDate());
        return subscription;
    }

    private double getPrice() {
        int index = Arrays.asList(durationValues).indexOf(cb_duration.getValue());
        double finalPrice = cb_type.getValue() == "STANDARD" ? priceValues[index] : priceValues[index] * 2;
        return finalPrice;
    }

    private LocalDate getEndDate() {
        if (durationValues[0].equals(cb_duration.getValue())) {
            return dp_start.getValue().plusWeeks(1);
        }
        if (durationValues[1].equals(cb_duration.getValue())) {
            return dp_start.getValue().plusMonths(1);
        }
        if (durationValues[2].equals(cb_duration.getValue())) {
            return dp_start.getValue().plusMonths(3);
        }
        if (durationValues[3].equals(cb_duration.getValue())) {
            return dp_start.getValue().plusMonths(6);
        }
        return dp_start.getValue().plusYears(1);
    }
}
