package com.mojafirma.gui;

import com.mojafirma.gui.layout.model.CurrencyListModel;
import com.mojafirma.gui.layout.model.DatesComboBoxModel;
import com.mojafirma.model.dao.CurrencyDao;
import com.mojafirma.model.dao.RatesTableDao;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JFrame{
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JTextField textField2;
    private JComboBox firstDateComboBox;
    private JTextField textField3;
    private JComboBox lastDateComboBox;
    private JTextField textField1;
    private JList currencyJList;
    private JButton button1;

    public MainPanel() { iniMainPanel(); }

    CurrencyDao currencyDao = new CurrencyDao();
    RatesTableDao ratesTableDao = new RatesTableDao();

    private void iniMainPanel() {

        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);
        setSize(500, 500);
        setVisible(true);

        currencyJList.setModel(new CurrencyListModel(currencyDao.getCurrencyList()));
        firstDateComboBox.setModel(new DatesComboBoxModel(ratesTableDao.getPublicationDateList()));

        firstDateComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<LocalDate> publicationDates = ratesTableDao.getPublicationDateList();
                LocalDate selectedDate = (LocalDate) firstDateComboBox.getSelectedItem();

                List<LocalDate> nextDates = new ArrayList<>();
                for (int i = 0; i < publicationDates.size(); i++) {
                    if (selectedDate.isBefore(publicationDates.get(i))) {
                        nextDates.add(publicationDates.get(i));
                    }
                }

                lastDateComboBox.setModel(new DatesComboBoxModel(nextDates));
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String currencyName =(String) currencyJList.getSelectedValue();
                LocalDate startDate = (LocalDate) firstDateComboBox.getSelectedItem();
                LocalDate endDate = (LocalDate) lastDateComboBox.getSelectedItem();

                RatesChartPanel chart = new RatesChartPanel(currencyName, startDate, endDate);
                RefineryUtilities.centerFrameOnScreen( chart );
            }
        });
    }
}


