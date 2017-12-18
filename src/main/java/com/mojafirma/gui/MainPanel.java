package com.mojafirma.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame {

    private JButton button1;
    private JButton button2;
    private JPanel panel;

    public MainPanel(){ iniMainPanel(); }

    private void iniMainPanel() {

        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);
        setSize(300, 300);
        setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CurrencyRatesPanel currencyRatesPanel = new CurrencyRatesPanel();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CurrencyForecastPanel currencyForecastPanel = new CurrencyForecastPanel();
            }
        });
    }
}
