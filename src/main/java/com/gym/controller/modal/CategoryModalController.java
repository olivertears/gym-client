package com.gym.controller.modal;

import com.gym.State;
import com.gym.entity.Category;
import com.gym.utils.CategoryUtils;
import com.gym.utils.CommonUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoryModalController implements Initializable {
    @FXML
    private ImageView iv_exit;
    @FXML
    private ChoiceBox<String> cb_type;
    @FXML
    private TextField tf_name;
    @FXML
    private Button btn_create;

    private String typeValues[] = { "INCOME", "EXPENSE" };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepare();

        tf_name.setOnKeyTyped(event -> {
            if (tf_name.getText().equals("")) {
                btn_create.setDisable(true);
            } else {
                btn_create.setDisable(false);
            }
        });

        btn_create.setOnMouseClicked(event -> {
            if (State.category != null) {
                if (!CategoryUtils.updateCategory(getData())) {
                    try {
                        CommonUtils.showAlert("Категория с таким названием уже существует :0");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    State.category = null;
                    State.refresh.firePropertyChange("category", 1, 2);
                    ((Stage) btn_create.getScene().getWindow()).close();
                }
            } else {
                if (!CategoryUtils.createCategory(getData())) {
                    try {
                        CommonUtils.showAlert("Категория с таким названием уже существует :0");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    State.refresh.firePropertyChange("category", 1, 2);
                    ((Stage) btn_create.getScene().getWindow()).close();
                }
            }
        });

        iv_exit.setOnMouseClicked(event -> {
            State.category = null;
            ((Stage) iv_exit.getScene().getWindow()).close();
        });

    }

    public void prepare() {
        if (State.category != null && (State.category.isDefaultWorkoutCategory() || State.category.isDefaultSubscriptionCategory())) {
            typeValues = new String[]{"INCOME"};
        }
        cb_type.setItems(FXCollections.observableArrayList(typeValues));

        if (State.category != null) {
            cb_type.setValue(State.category.getType());
            tf_name.setText(State.category.getName());
            btn_create.setText("СОХРАНИТЬ");
        } else {
            cb_type.setValue(typeValues[0]);
        }

        if (tf_name.getText().equals("")) {
            btn_create.setDisable(true);
        }
    }

    public Category getData() {
        Category category = new Category();
        if (State.category != null) {
            category.setId(State.category.getId());
        }
        category.setType(cb_type.getValue());
        category.setName(tf_name.getText());
        return category;
    }
}
