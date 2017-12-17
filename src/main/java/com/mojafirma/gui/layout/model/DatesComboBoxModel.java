package com.mojafirma.gui.layout.model;

import com.mojafirma.model.RatesTableModelDB;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DatesComboBoxModel extends DefaultComboBoxModel<LocalDate> {

    private List<LocalDate> datesList = new ArrayList<>();

    public DatesComboBoxModel(List<LocalDate> dates) {
        this.datesList = dates;
    }

    @Override
    public int getSize() {
        return datesList.size();
    }

    @Override
    public LocalDate getElementAt(int index) {
        Collections.sort(datesList);
        return datesList.get(index);
    }
}
