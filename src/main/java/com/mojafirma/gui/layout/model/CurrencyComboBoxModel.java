package com.mojafirma.gui.layout.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyComboBoxModel extends DefaultComboBoxModel<String> {

    private List<String> currencies = new ArrayList<>();

    public CurrencyComboBoxModel(List<String> currencies) {
        this.currencies = currencies;
    }

    @Override
    public int getSize() {
        return currencies.size();
    }

    @Override
    public String getElementAt(int index) {
        return currencies.get(index);
    }
}
