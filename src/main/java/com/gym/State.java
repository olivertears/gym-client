package com.gym;

import com.gym.entity.Subscription;
import com.gym.entity.User;
import com.gym.entity.Workout;

import java.beans.PropertyChangeSupport;
import java.util.List;

public class State {
    public static PropertyChangeSupport refresh = new PropertyChangeSupport(1);

    public static User user;
    public static Subscription subscription;

    public static List<User> coaches;
    public static User coach;

    public static Workout workout;
}
