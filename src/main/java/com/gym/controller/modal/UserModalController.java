package com.gym.controller.modal;

import com.gym.State;
import com.gym.dto.UserDataDto;
import com.gym.entity.User;
import com.gym.utils.UserUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserModalController implements Initializable {
    @FXML
    private ImageView iv_exit;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_surname;
    @FXML
    private Button btn_save;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();

        iv_exit.setOnMouseClicked(event -> ((Stage) iv_exit.getScene().getWindow()).close());

        btn_save.setOnMouseClicked(event -> {
            UserUtils.updateUser(getData());
            ((Stage) btn_save.getScene().getWindow()).close();
            State.refresh.firePropertyChange("user", 1, 2);
        });
    }

    public void setData() {
        tf_name.setText(State.user.getName());
        tf_surname.setText(State.user.getSurname());
    }

    public UserDataDto getData() {
        UserDataDto userDataDto = new UserDataDto();
        userDataDto.setId(State.user.getId());
        userDataDto.setName(tf_name.getText());
        userDataDto.setSurname(tf_surname.getText());
        return userDataDto;
    }
}
