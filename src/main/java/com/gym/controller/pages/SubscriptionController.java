package com.gym.controller.pages;

import com.gym.Application;
import com.gym.State;
import com.gym.controller.IControllerWithProperty;
import com.gym.controller.entity.SubscriptionEntityController;
import com.gym.entity.Subscription;
import com.gym.utils.CommonUtils;
import com.gym.utils.SubscriptionUtils;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubscriptionController implements Initializable {
    private ObjectProperty<Object> modalProperty;
    private ObjectProperty<Object> entityProperty;

    @FXML
    private Button btn_buy;
    @FXML
    private Label lbl_text;
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
                IControllerWithProperty controller = CommonUtils.showModal("components/modal/subscription-modal.fxml");
                modalProperty = controller.selectedProperty();
                btn_buy.cacheProperty().bind(modalProperty.isNotNull());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        
        btn_buy.cacheProperty().addListener(event -> {
            initController();
        });

        wrap_subscription.cacheProperty().addListener(event -> {
            initController();
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

            entityProperty = subscriptionEntityController.selectedProperty();
            wrap_subscription.cacheProperty().bind(entityProperty.isNotNull());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initController() {
        subscription = SubscriptionUtils.getUserSubscription(State.user.getId());
        if (subscription != null) {
            setSubscription();
        } else {
            wrap_subscription.getChildren().clear();
            wrap_subscription.getChildren().add(lbl_text);
            wrap_subscription.getChildren().add(btn_buy);
        }
    }
}
