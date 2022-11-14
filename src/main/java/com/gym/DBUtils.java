package com.gym;

import com.gym.command.ClientAction;
import com.gym.dto.LoginDto;
import com.gym.dto.SignupDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class DBUtils {
    static Connection connection;

    static {
        try {
            connection = new Connection(new Socket("127.0.0.1", 8000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void changeScene(Button button, String fxmlFile, String title) {
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
        Scene scene = null;

        try {
            scene = new Scene(loader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static boolean signupUser(ActionEvent event, String name, String surname, String email, String password) {
        SignupDto signupDto = new SignupDto();
        signupDto.setName(name);
        signupDto.setSurname(surname);
        signupDto.setEmail(email);
        signupDto.setPassword(password);

        ClientAction clientAction = ClientAction.SIGNUP;
        connection.writeObject(clientAction);
        connection.writeObject(signupDto);
        return (boolean) connection.readObject();
    }

    public static boolean loginUser(ActionEvent event, String email, String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword(password);

        ClientAction clientAction = ClientAction.LOGIN;
        connection.writeObject(clientAction);
        connection.writeObject(loginDto);
        return (boolean) connection.readObject();
    }
}
