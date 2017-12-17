package com.mojafirma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "tabela_kursow")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatesTableModelXML {

    @XmlElement(name = "numer_tabeli")
    private String tableNumber;
    @XmlElement(name = "data_publikacji")
    private String publicationDate;
    @XmlElement(name = "pozycja")
    private List<CurrencyModelXML> currencyList = new ArrayList<CurrencyModelXML>();
}
