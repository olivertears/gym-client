package com.gym.controller.pages;

import com.gym.Application;
import com.gym.State;
import com.gym.controller.entity.UserEntityController;
import com.gym.entity.User;
import com.gym.utils.CommonUtils;
import com.gym.utils.UserUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    private TextField tf_search;
    @FXML
    private Button btn_save;
    @FXML
    private VBox wrapper_users;

    private List<User> users;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        users = UserUtils.getUsers();
        setUsers(users);

        if (!State.user.getRole().equals("ADMIN")) {
            btn_save.setVisible(false);
        }

        btn_save.setOnMouseClicked(event -> {
            ObservableList<Node> usersEntities = wrapper_users.getChildren();
            for (Node usersEntity : usersEntities) {
                UserEntityController userEntityController = (UserEntityController) CommonUtils.getController(usersEntity);
                User user = userEntityController.getData();
                UserUtils.updateUser(user);
            }
        });

        tf_search.setOnKeyTyped(event -> {
            List<User> filteredUsers = new ArrayList<>();
            for (User user: users) {
                if ((user.getName() + " " + user.getSurname()).toLowerCase().startsWith(tf_search.getText().toLowerCase()) ||
                        (user.getSurname() + " " + user.getName()).toLowerCase().startsWith(tf_search.getText().toLowerCase())) {
                    filteredUsers.add(user);
                }
            }
            setUsers(filteredUsers);
        });
    }

    private void setUsers(List<User> users) {
        wrapper_users.getChildren().clear();
        for (User user : users) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Application.class.getResource("components/entity/user-entity.fxml"));
            try {
                HBox userEntity = fxmlLoader.load();
                UserEntityController userEntityController = fxmlLoader.getController();
                userEntityController.setData(user);
                wrapper_users.getChildren().add(userEntity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
