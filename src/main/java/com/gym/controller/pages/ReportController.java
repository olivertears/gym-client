package com.gym.controller.pages;

import com.gym.dto.ChartDto;
import com.gym.entity.ChartValue;
import com.gym.utils.ReportUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URL;
import java.util.*;

public class ReportController extends Component implements Initializable {
    @FXML
    private CheckBox cb_expense;
    @FXML
    private CheckBox cb_income;
    @FXML
    private ChoiceBox<String> cb_period;
    @FXML
    private AnchorPane wrap_chart;
    @FXML
    private Button btn_save;
    @FXML
    private VBox wrap_stage;

    private ChartDto chartDto;
    private String periodValues[] = { "LAST WEEK", "LAST MONTH", "LAST YEAR" };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepare();
        chartDto = ReportUtils.getWeekChartReport();
        initChart();

        cb_expense.setOnAction(event -> initChart());

        cb_income.setOnAction(event -> initChart());

        cb_period.setOnAction(event -> {
            if (cb_period.getValue().equals(periodValues[0])) {
                chartDto = ReportUtils.getWeekChartReport();
            } else if (cb_period.getValue().equals(periodValues[1])) {
                chartDto = ReportUtils.getMonthChartReport();
            } else {
                chartDto = ReportUtils.getYearChartReport();
            }
            initChart();
        });

        btn_save.setOnMouseClicked(event -> {
            PrinterJob job = PrinterJob.createPrinterJob();
            if(job != null) {
                job.showPrintDialog(btn_save.getScene().getWindow());
                job.printPage(wrap_stage);
                job.endJob();
            }
        });
    }

    private void prepare() {
        cb_expense.setSelected(true);
        cb_income.setSelected(true);
        cb_period.setItems(FXCollections.observableArrayList(periodValues));
        cb_period.setValue(cb_period.getItems().get(0));
    }


    private void initChart() {
        LineChart lc_chart = new LineChart(new CategoryAxis(), new NumberAxis());
        lc_chart.setMaxHeight(236);
        lc_chart.setMaxWidth(350);
        wrap_chart.getChildren().clear();
        wrap_chart.getChildren().add(lc_chart);

        XYChart.Series expenseSeries = new XYChart.Series();
        if (cb_expense.isSelected()) {
            for (ChartValue chartValue : chartDto.getExpenseChartValueList()) {
                expenseSeries.getData().add(new XYChart.Data(chartValue.getKey(), chartValue.getValue()));
            }
        }
        XYChart.Series incomeSeries = new XYChart.Series();
        if (cb_income.isSelected()) {
            for (ChartValue chartValue : chartDto.getIncomeChartValueList()) {
                incomeSeries.getData().add(new XYChart.Data(chartValue.getKey(), chartValue.getValue()));
            }
        }

        lc_chart.getData().addAll(expenseSeries, incomeSeries);
        lc_chart.setLegendVisible(false);
        lc_chart.setCreateSymbols(false);
        lc_chart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        lc_chart.getXAxis().setStyle("-fx-border-color: black transparent transparent");
        lc_chart.getYAxis().setStyle("-fx-border-color: transparent black transparent transparent;");
        expenseSeries.getNode().setStyle("-fx-stroke: #910000");
        incomeSeries.getNode().setStyle("-fx-stroke: #00731e");
    }
}
