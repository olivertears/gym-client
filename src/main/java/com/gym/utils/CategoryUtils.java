package com.gym.utils;

import com.gym.Connection;
import com.gym.command.Action;
import com.gym.dto.DefaultCategoryDto;
import com.gym.entity.Category;
import com.gym.entity.Workout;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class CategoryUtils {
    static Connection connection;

    static {
        try {
            connection = new Connection(new Socket("127.0.0.1", 8000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean createCategory(Category category) {
        connection.writeObject(Action.CREATE_CATEGORY);
        connection.writeObject(category);
        return (boolean) connection.readObject();
    }

    public static boolean updateCategory(Category category) {
        connection.writeObject(Action.UPDATE_CATEGORY);
        connection.writeObject(category);
        return (boolean) connection.readObject();
    }

    public static boolean setDefaultCategory(DefaultCategoryDto defaultCategoryDto) {
        connection.writeObject(Action.SET_DEFAULT_CATEGORY);
        connection.writeObject(defaultCategoryDto);
        return (boolean) connection.readObject();
    }

    public static boolean deleteCategory(int id) {
        connection.writeObject(Action.DELETE_CATEGORY);
        connection.writeObject(id);
        return (boolean) connection.readObject();
    }

    public static List<Category> getCategoriesByType (String type) {
        connection.writeObject(Action.GET_CATEGORIES_BY_TYPE);
        connection.writeObject(type);
        return (List<Category>) connection.readObject();
    }
}
