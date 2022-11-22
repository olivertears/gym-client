package com.gym.controller.entity;

import com.gym.State;
import com.gym.entity.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserEntityController implements Initializable {
    @FXML
    private Label lbl_name;
    @FXML
    private Label lbl_surname;
    @FXML
    private Label lbl_email;
    @FXML
    private ChoiceBox cb_role;

    private int id;

    private String roleValues[] = { "CLIENT", "COACH" };

    public void setData(User user) {
        this.id = user.getId();
        lbl_name.setText(user.getName());
        lbl_surname.setText(user.getSurname());
        lbl_email.setText(user.getEmail());
        cb_role.setItems(FXCollections.observableArrayList(roleValues));
        cb_role.setValue(user.getRole());
    }

    public User getData() {
        User user = new User();
        user.setId(this.id);
        user.setName(lbl_name.getText());
        user.setSurname(lbl_surname.getText());
        user.setEmail(lbl_email.getText());
        user.setRole((String) cb_role.getValue());
        return user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!State.user.getRole().equals("ADMIN")) {
            cb_role.setDisable(true);
        }
    }
}
