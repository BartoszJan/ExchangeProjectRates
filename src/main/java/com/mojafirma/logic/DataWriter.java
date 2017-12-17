package com.mojafirma.logic;

import com.mojafirma.model.CurrencyModelDB;
import com.mojafirma.model.RatesTableModelDB;
import com.mojafirma.model.RatesTableModelXML;
import com.mojafirma.model.dao.RatesTableDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataWriter {

    private RatesTableDao ratesTableDao = new RatesTableDao();

    public void writeData (RatesTableModelXML ratesTableXML) {

        RatesTableModelDB ratesTableDB = new RatesTableModelDB();
        ratesTableDB.setPublicationDate(LocalDate.parse(ratesTableXML.getPublicationDate()));
        ratesTableDB.setTableNumber(ratesTableXML.getTableNumber());

        List<CurrencyModelDB> currencyModelDBList = new ArrayList<>();
        for (int i = 0; i < ratesTableXML.getCurrencyList().size(); i++) {
            CurrencyModelDB currencyModelDB = new CurrencyModelDB();

            currencyModelDB.setName(ratesTableXML.getCurrencyList().get(i).getName());
            currencyModelDB.setCode(ratesTableXML.getCurrencyList().get(i).getCode());
            currencyModelDB.setConverter(ratesTableXML.getCurrencyList().get(i).getConverter());
            currencyModelDB.setRate(Double.valueOf(ratesTableXML.getCurrencyList().get(i).getRate().replaceAll(",",".")));
            currencyModelDB.setRatesTable(ratesTableDB);

            currencyModelDBList.add(currencyModelDB);
        }
        ratesTableDB.setCurrencyList(currencyModelDBList);
        ratesTableDao.addRatesTable(ratesTableDB);
    }
}
