package com.mojafirma.gui.layout.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyListModel extends AbstractListModel<String>{

    private List<String> currencyList = new ArrayList<>();

    public CurrencyListModel(List<String> currencyList) {
        this.currencyList = currencyList;
    }

    @Override
    public int getSize() {
        return currencyList.size();
    }

    @Override
    public String getElementAt(int index) {
        return currencyList.get(index);
    }
}
