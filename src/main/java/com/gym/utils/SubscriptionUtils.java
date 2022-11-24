package com.gym.utils;

import com.gym.Connection;
import com.gym.command.Action;
import com.gym.entity.Subscription;

import java.io.IOException;
import java.net.Socket;

public class SubscriptionUtils {
    static Connection connection;

    static {
        try {
            connection = new Connection(new Socket("127.0.0.1", 8000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean createSubscription(Subscription subscription) {
        connection.writeObject(Action.CREATE_SUBSCRIPTION);
        connection.writeObject(subscription);
        return (boolean) connection.readObject();
    }

    public static boolean updateSubscriptionToPremium(int id) {
        connection.writeObject(Action.UPDATE_SUBSCRIPTION_TO_PREMIUM);
        connection.writeObject(id);
        return (boolean) connection.readObject();
    }

    public static boolean deleteSubscription(int id) {
        connection.writeObject(Action.DELETE_SUBSCRIPTION);
        connection.writeObject(id);
        return (boolean) connection.readObject();
    }

    public static Subscription getUserSubscription(int userId) {
        connection.writeObject(Action.GET_USER_SUBSCRIPTION);
        connection.writeObject(userId);
        return (Subscription) connection.readObject();
    }
}
