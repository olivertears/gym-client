package com.gym.utils;

import com.gym.Connection;
import com.gym.command.ClientAction;
import com.gym.dto.LoginDto;
import com.gym.dto.SignupDto;

import java.io.IOException;
import java.net.Socket;

public class UserUtils {
    static Connection connection;

    static {
        try {
            connection = new Connection(new Socket("127.0.0.1", 8000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean signupUser(String name, String surname, String email, String password) {
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

    public static boolean loginUser(String email, String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword(password);

        ClientAction clientAction = ClientAction.LOGIN;
        connection.writeObject(clientAction);
        connection.writeObject(loginDto);
        return (boolean) connection.readObject();
    }
}
