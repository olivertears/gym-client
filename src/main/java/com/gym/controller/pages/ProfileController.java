package com.gym.controller.pages;

import com.gym.State;
import com.gym.utils.CommonUtils;
import com.gym.utils.UserUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Button btn_change;

    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_price;

    @FXML
    private Label lbl_surname;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();

        btn_change.setOnMouseClicked(event -> {
            try {
                CommonUtils.showModal(State.user.getRole().equals("COACH") ? "components/modal/coach-modal.fxml" : "components/modal/user-modal.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        State.refresh.addPropertyChangeListener(event -> {
            if (event.getPropertyName().equals("user"))   {
                setData();
            }
        });
    }

    public void setData() {
        State.user = UserUtils.getUserById(State.user.getId());
        lbl_name.setText("Имя: " + State.user.getName());
        lbl_surname.setText("Фамилия: " + State.user.getSurname());
        if (State.user.getRole().equals("COACH")) {
            lbl_price.setText("Стоимость тренировки: $" + new DecimalFormat("#0.00").format(State.user.getPrice()).replace(',', '.'));
        } else {
            lbl_price.setVisible(false);
        }
    }
}
