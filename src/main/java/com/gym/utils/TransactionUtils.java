package com.gym.utils;

import com.gym.Connection;
import com.gym.command.Action;
import com.gym.dto.DefaultCategoryDto;
import com.gym.entity.Category;
import com.gym.entity.Transaction;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class TransactionUtils {
    static Connection connection;

    static {
        try {
            connection = new Connection(new Socket("127.0.0.1", 8000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean createTransaction(Transaction transaction) {
        connection.writeObject(Action.CREATE_TRANSACTION);
        connection.writeObject(transaction);
        return (boolean) connection.readObject();
    }

    public static boolean updateTransaction(Transaction transaction) {
        connection.writeObject(Action.UPDATE_TRANSACTION);
        connection.writeObject(transaction);
        return (boolean) connection.readObject();
    }

    public static boolean deleteTransaction(int id) {
        connection.writeObject(Action.DELETE_TRANSACTION);
        connection.writeObject(id);
        return (boolean) connection.readObject();
    }

    public static List<Transaction> getTransactions () {
        connection.writeObject(Action.GET_TRANSACTION);
        return (List<Transaction>) connection.readObject();
    }
}
