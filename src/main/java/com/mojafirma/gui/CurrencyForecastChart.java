package com.mojafirma.gui;

import com.mojafirma.logic.CurrencyForecastGetter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class CurrencyForecastChart extends JFrame {

    public CurrencyForecastChart(String currency) throws IOException {
        super("Prognoza");

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Wykres Prognozy Kursu Waluty " + currency.toUpperCase() + " (źródło 'www.usdforecast.com')",
                "Data", "Wartość Kursu",
                createDataset(currency),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        setContentPane(chartPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        NumberAxis yAxis = (NumberAxis) lineChart.getCategoryPlot().getRangeAxis();
        yAxis.setAutoTickUnitSelection(true);
        yAxis.setAutoRangeIncludesZero(false);

        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        DecimalFormat decimalformat = new DecimalFormat("##.#####");
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat));
        renderer.setItemLabelsVisible(true);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2.0));
    }

    private DefaultCategoryDataset createDataset(String currency) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        CurrencyForecastGetter forecastGetter = new CurrencyForecastGetter();

        List<String> currenciesForecast = forecastGetter.getCurrencyForecast(currency);

        for (int i = 0; i < currenciesForecast.size(); i++) {

            String date = currenciesForecast.get(i);
            i++;
            Double rate = Double.parseDouble(currenciesForecast.get(i));
            dataset.addValue(rate, "kurs", date);
        }
        return dataset;
    }
}
