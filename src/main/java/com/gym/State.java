package com.gym;

import com.gym.entity.*;

import java.beans.PropertyChangeSupport;
import java.util.List;

public class State {
    public static PropertyChangeSupport refresh = new PropertyChangeSupport(1);

    public static User user;
    public static List<User> users;
    public static Subscription subscription;

    public static List<User> coaches;
    public static User coach;

    public static Workout workout;

    public static Category category;
    public static List<Category> categories;

    public static Transaction transaction;
    public static List<Transaction> transactions;
}
