package com.gym.controller.pages;

import com.gym.Application;
import com.gym.State;
import com.gym.controller.entity.SubscriptionEntityController;
import com.gym.entity.Subscription;
import com.gym.utils.CommonUtils;
import com.gym.utils.SubscriptionUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubscriptionController implements Initializable {
    @FXML
    private Button btn_buy;
    @FXML
    private AnchorPane wrap_subscription;

    private Subscription subscription;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subscription = SubscriptionUtils.getUserSubscription(State.user.getId());
        if (subscription != null) {
            setSubscription();
        }

        btn_buy.setOnMouseClicked(event -> {
            try {
                CommonUtils.showModal("components/modal/subscription-modal.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setSubscription() {
        wrap_subscription.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("components/entity/subscription-entity.fxml"));
        try {
            AnchorPane subscriptionEntity = fxmlLoader.load();
            SubscriptionEntityController subscriptionEntityController = fxmlLoader.getController();
            subscriptionEntityController.setData(subscription);
            wrap_subscription.getChildren().add(subscriptionEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initController() {
        subscription = SubscriptionUtils.getUserSubscription(State.user.getId());
        if (subscription != null) {
            System.out.println("res: " + subscription);
            setSubscription();
        }
    }
}
