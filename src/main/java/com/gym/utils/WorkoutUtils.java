package com.gym.utils;

import com.gym.Connection;
import com.gym.command.Action;
import com.gym.dto.WorkoutTimeDto;
import com.gym.entity.Workout;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class WorkoutUtils {
    static Connection connection;

    static {
        try {
            connection = new Connection(new Socket("127.0.0.1", 8000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean createWorkout(Workout workout) {
        connection.writeObject(Action.CREATE_WORKOUT);
        connection.writeObject(workout);
        return (boolean) connection.readObject();
    }

    public static boolean updateWorkout(Workout workout) {
        connection.writeObject(Action.UPDATE_WORKOUT);
        connection.writeObject(workout);
        return (boolean) connection.readObject();
    }

    public static boolean setWorkoutDone(int id) {
        connection.writeObject(Action.SET_WORKOUT_DONE);
        connection.writeObject(id);
        return (boolean) connection.readObject();
    }

    public static boolean deleteWorkout(int id) {
        connection.writeObject(Action.DELETE_WORKOUT);
        connection.writeObject(id);
        return (boolean) connection.readObject();
    }

    public static List<Workout> getClientWorkouts (int clientId) {
        connection.writeObject(Action.GET_CLIENT_WORKOUTS);
        connection.writeObject(clientId);
        return (List<Workout>) connection.readObject();
    }

    public static List<Workout> getCoachWorkouts (int coachId) {
        connection.writeObject(Action.GET_COACH_WORKOUTS);
        connection.writeObject(coachId);
        return (List<Workout>) connection.readObject();
    }

    public static List<String> getCoachDateWorkoutTimes (WorkoutTimeDto workoutTimeDto) {
        connection.writeObject(Action.GET_COACH_DATE_WORKOUT_TIMES);
        connection.writeObject(workoutTimeDto);
        return (List<String>) connection.readObject();
    }
}
