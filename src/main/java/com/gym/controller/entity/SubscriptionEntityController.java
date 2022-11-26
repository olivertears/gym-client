package com.gym.controller.entity;

import com.gym.State;
import com.gym.controller.IControllerWithProperty;
import com.gym.entity.Subscription;
import com.gym.utils.SubscriptionUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SubscriptionEntityController implements Initializable, IControllerWithProperty {
    private static final ObjectProperty<Object> selectedProperty = new SimpleObjectProperty();
    @FXML
    private Label lbl_client;
    @FXML
    private Label lbl_type;
    @FXML
    private Label lbl_status;
    @FXML
    private Label lbl_date;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;

    private LocalDate today = LocalDate.now();
    private int id;

    @Override
    public ObjectProperty<Object> selectedProperty() {
        return selectedProperty;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_update.setOnMouseClicked(event -> {
            SubscriptionUtils.updateSubscriptionToPremium(id);
            lbl_type.setText("PREMIUM");
            btn_update.setDisable(true);
        });


        btn_delete.setOnMouseClicked(event -> {
            SubscriptionUtils.deleteSubscription(id);
            selectedProperty.set(new Object());
        });
    }

    public void setData(Subscription subscription) {
        boolean isActive = (today.isEqual(subscription.getStart()) || today.isAfter(subscription.getStart())) &&
                (today.isEqual(subscription.getEnd()) || today.isBefore(subscription.getEnd()));

        this.id = subscription.getId();
        lbl_client.setText(State.user.getName().toUpperCase() + " " + State.user.getSurname().toUpperCase());
        lbl_type.setText(subscription.getType());
        lbl_status.setVisible(isActive);
        lbl_date.setText(subscription.getStart() + "-" + subscription.getEnd());
        btn_delete.setDisable(isActive);
        btn_update.setDisable(subscription.getType().equals("PREMIUM"));
    }
}
