package com.gym.utils;

import com.gym.Application;
import com.gym.Connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class CommonUtils {
    static Connection connection;

    static {
        try {
            connection = new Connection(new Socket("127.0.0.1", 8000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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
}
