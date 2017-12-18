package com.mojafirma.gui;

import com.mojafirma.gui.layout.model.CurrencyComboBoxModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CurrencyForecastPanel extends JFrame {
    private JComboBox currenciesComboBox;
    private JPanel panel1;
    private JButton button1;
    private JTextField textField2;
    private JTextField textField1;
    private JButton backButton;

    public CurrencyForecastPanel() {
        iniCurrencyForecastPanel();
    }

    private void iniCurrencyForecastPanel() {

        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);
        setSize(420, 350);
        setVisible(true);

        List<String> currencies = Arrays.asList("euro", "dolar", "funt", "frank");
        currenciesComboBox.setModel(new CurrencyComboBoxModel(currencies));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String currency = (String) currenciesComboBox.getSelectedItem();

                    CurrencyForecastChart currencyForecastChart = new CurrencyForecastChart(currency);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainPanel mainPanel = new MainPanel();
            }
        });
    }
}
