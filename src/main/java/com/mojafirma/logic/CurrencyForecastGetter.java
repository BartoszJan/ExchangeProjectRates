package com.mojafirma.logic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyForecastGetter {

    private List<String> currenciesForecast = new ArrayList();

    public List getCurrencyForecast(String currency) throws IOException {

        Document document = null;
        if (currency.equals("euro")) {
            document = Jsoup.connect("http://usdforecast.com/pl/kurs-" + currency + "-prognozy.html").timeout(6000).get();
        }
        else {
            document = Jsoup.connect("http://usdforecast.com/pl/kurs-" + currency + "a-prognozy.html").timeout(6000).get();
        }
        Elements col = document.select("table td.xl66");
        for (int i = 5; i < col.size(); i++) {

            String date = col.get(i).text();
            i = i + 2;
            currenciesForecast.add(date);
            String rate = col.get(i).text();
            i = i + 2;
            currenciesForecast.add(rate);
        }
        return currenciesForecast;
    }
}
