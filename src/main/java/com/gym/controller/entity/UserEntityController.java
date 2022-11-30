package com.gym.controller.entity;

import com.gym.State;
import com.gym.dto.UserRoleDto;
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
    private Double price;

    private String roleValues[] = { "CLIENT", "COACH" };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!State.user.getRole().equals("ADMIN")) {
            cb_role.setDisable(true);
        }

        cb_role.setOnAction(event -> {
            User userToChange = State.users.stream()
                                    .filter(user -> user.getId() == this.id)
                                    .findFirst()
                                    .orElse(null);
            int index = State.users.indexOf(userToChange);
            userToChange.setRole((String) cb_role.getValue());

            State.users.set(index, userToChange);
        });
    }

    public void setData(User user) {
        this.id = user.getId();
        this.price = user.getPrice();
        lbl_name.setText(user.getName());
        lbl_surname.setText(user.getSurname());
        lbl_email.setText(user.getEmail());
        cb_role.setItems(FXCollections.observableArrayList(roleValues));
        cb_role.setValue(user.getRole());
    }

    public UserRoleDto getData() {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setId(this.id);
        userRoleDto.setPrice(this.price);
        userRoleDto.setRole((String) cb_role.getValue());
        return userRoleDto;
    }
}
