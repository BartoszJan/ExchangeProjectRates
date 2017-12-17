package com.mojafirma.logic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class CurrencyForecastGetter {

    private Map<LocalDate, Double> currencyForecastMap;

    public Map<LocalDate, Double> getCurrencyForecast() throws IOException {

        Document document = Jsoup.connect("http://usdforecast.com/pl/kurs-dolara-prognozy.html").timeout(6000).get();

        Elements col = document.select("table td.xl66");
        for (int i = 5; i < col.size(); i++) {
            LocalDate key = LocalDate.parse(col.get(i).text());
            i = i+2;
            Double value = Double.valueOf(col.get(i).text());
            i = i+2;
            currencyForecastMap.put(key, value);
        }

        return currencyForecastMap;
    }

    public static void main(String[] args) throws IOException {
        CurrencyForecastGetter forecastGetter = new CurrencyForecastGetter();
        System.out.println(forecastGetter.getCurrencyForecast());
    }
}
