package com.mojafirma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "currency")
public class CurrencyModelDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int currency_id;
    @Column(name = "name")
    private  String name;
    @Column(name = "converter")
    private int converter;
    @Column(name = "code")
    private String code;
    @Column(name = "rate")
    private Double rate;
    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private RatesTableModelDB ratesTable;
}
