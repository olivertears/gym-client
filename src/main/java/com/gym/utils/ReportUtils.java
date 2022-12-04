package com.gym.utils;

import com.gym.Connection;
import com.gym.command.Action;
import com.gym.dto.ChartDto;
import com.gym.entity.Subscription;

import java.io.IOException;
import java.net.Socket;

public class ReportUtils {
    static Connection connection;

    static {
        try {
            connection = new Connection(new Socket("127.0.0.1", 8000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ChartDto getWeekChartReport() {
        connection.writeObject(Action.GET_WEEK_CHART_REPORT);
        return (ChartDto) connection.readObject();
    }

    public static ChartDto getMonthChartReport() {
        connection.writeObject(Action.GET_MONTH_CHART_REPORT);
        return (ChartDto) connection.readObject();
    }

    public static ChartDto getYearChartReport() {
        connection.writeObject(Action.GET_YEAR_CHART_REPORT);
        return (ChartDto) connection.readObject();
    }
}
