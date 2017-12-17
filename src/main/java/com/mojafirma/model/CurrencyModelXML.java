package com.mojafirma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyModelXML {

    @XmlElement(name = "nazwa_waluty")
    private  String name;
    @XmlElement(name = "przelicznik")
    private int converter;
    @XmlElement(name = "kod_waluty")
    private String code;
    @XmlElement(name = "kurs_sredni")
    private String rate;
}
