package com.gym.utils;

import com.gym.Connection;
import com.gym.command.ClientAction;
import com.gym.dto.LoginDto;
import com.gym.dto.SignupDto;
import com.gym.entity.User;

import java.io.IOException;
import java.net.Socket;

public class UserUtils {
    static Connection connection;

    static {
        try {
            connection = new Connection(new Socket("127.0.0.1", 8000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User signupUser(String name, String surname, String email, String password) {
        SignupDto signupDto = new SignupDto();
        signupDto.setName(name);
        signupDto.setSurname(surname);
        signupDto.setEmail(email);
        signupDto.setPassword(password);

        ClientAction clientAction = ClientAction.SIGNUP;
        connection.writeObject(clientAction);
        connection.writeObject(signupDto);
        return (User) connection.readObject();
    }

    public static User loginUser(String email, String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword(password);

        ClientAction clientAction = ClientAction.LOGIN;
        connection.writeObject(clientAction);
        connection.writeObject(loginDto);
        return (User) connection.readObject();
    }
}
