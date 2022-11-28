package com.gym.utils;

import com.gym.Connection;
import com.gym.command.Action;
import com.gym.dto.LoginDto;
import com.gym.dto.SignupDto;
import com.gym.dto.UserDataDto;
import com.gym.dto.UserRoleDto;
import com.gym.entity.User;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class UserUtils {
    static Connection connection;

    static {
        try {
            connection = new Connection(new Socket("127.0.0.1", 8000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User signup(String name, String surname, String email, String password) {
        SignupDto signupDto = new SignupDto();
        signupDto.setName(name);
        signupDto.setSurname(surname);
        signupDto.setEmail(email);
        signupDto.setPassword(password);

        connection.writeObject(Action.SIGNUP);
        connection.writeObject(signupDto);
        return (User) connection.readObject();
    }

    public static User login(String email, String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword(password);

        connection.writeObject(Action.LOGIN);
        connection.writeObject(loginDto);
        return (User) connection.readObject();
    }

    public static User getUserById (int id) {
        connection.writeObject(Action.GET_USER_BY_ID);
        connection.writeObject(id);
        return (User) connection.readObject();
    }

    public static List<User> getUsers () {
        connection.writeObject(Action.GET_USERS);
        return (List<User>) connection.readObject();
    }

    public static List<User> getCoaches () {
        connection.writeObject(Action.GET_COACHES);
        return (List<User>) connection.readObject();
    }

    public static boolean updateUser (UserDataDto userDataDto) {
        connection.writeObject(Action.UPDATE_USER);
        connection.writeObject(userDataDto);
        return (boolean) connection.readObject();
    }

    public static boolean updateUserRole (UserRoleDto userRoleDto) {
        connection.writeObject(Action.UPDATE_USER_ROLE);
        connection.writeObject(userRoleDto);
        return (boolean) connection.readObject();
    }

    public static boolean deleteUser (int id) {
        connection.writeObject(Action.DELETE_USER);
        connection.writeObject(id);
        return (boolean) connection.readObject();
    }
}
