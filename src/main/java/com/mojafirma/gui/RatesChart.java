package com.mojafirma.gui;

import com.mojafirma.model.CurrencyModelDB;
import com.mojafirma.model.RatesTableModelDB;
import com.mojafirma.model.dao.RatesTableDao;
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
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class RatesChart extends JFrame{

    private RatesTableDao ratesTableDao = new RatesTableDao();

    public RatesChart(String currencyName, LocalDate startDate, LocalDate endDate) {
        super("Wykres");

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Wykres Kursu Waluty " + currencyName.toUpperCase() + " (źródło 'www.nbp.pl')",
                "Data","Wartość Kursu",
                createDataset(currencyName, startDate, endDate),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 800 , 500 ) );
        setContentPane( chartPanel );
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

    private DefaultCategoryDataset createDataset(String currencyName, LocalDate startDate, LocalDate endDate) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        List<RatesTableModelDB> ratesTableList = ratesTableDao.getRatesTableList().stream()
                .sorted((o1, o2) -> o1.getPublicationDate().compareTo(o2.getPublicationDate())).collect(Collectors.toList());

        for (int i = 0; i < ratesTableList.size(); i++) {

            if (ratesTableList.get(i).getPublicationDate().isEqual(startDate) ||
                    ratesTableList.get(i).getPublicationDate().isEqual(endDate)) {

                List<CurrencyModelDB> currencyList = ratesTableList.get(i).getCurrencyList();
                Double rate = currencyList.stream().filter(o -> o.getName().equals(currencyName)).findFirst().get().getRate();
                dataset.addValue(rate, "kurs", ratesTableList.get(i).getPublicationDate());
            }
            if (ratesTableList.get(i).getPublicationDate().isAfter(startDate) &&
                    ratesTableList.get(i).getPublicationDate().isBefore(endDate)) {

                List<CurrencyModelDB> currencyList = ratesTableList.get(i).getCurrencyList();
                Double rate = currencyList.stream().filter(o -> o.getName().equals(currencyName)).findFirst().get().getRate();
                dataset.addValue(rate, "kurs", ratesTableList.get(i).getPublicationDate());
            }
        }
        return dataset;
    }
}
