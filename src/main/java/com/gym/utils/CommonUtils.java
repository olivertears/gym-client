package com.gym.utils;

import com.gym.Application;
import com.gym.controller.modal.AlertModalController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.IOException;
import java.util.Objects;

public class CommonUtils {
    public static void changeScene(Button button, String fxmlFile) {
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxmlFile));
        Scene scene;

        try {
            scene = new Scene(loader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void changePage(VBox wrapper, String fxmlFile) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource(fxmlFile)));
        wrapper.getChildren().removeAll();
        wrapper.getChildren().setAll(fxml);
    }

    public static void showModal(String fxmlFile) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);
        stage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - scene.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - scene.getHeight()) / 2);
    }

    public static void showAlert(String text) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("components/modal/alert-modal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        AlertModalController alertModalController = fxmlLoader.getController();
        alertModalController.setData(text);

        stage.setScene(scene);
        stage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - scene.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - scene.getHeight()) / 2);
    }

    public static Object getController(Node node, String key) {
        Object controller = null;
        do {
            controller = node.getProperties().get(key);
            node = node.getParent();
        } while (controller == null && node != null);
        return controller;
    }
}
