package com.mojafirma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "rates_table")
public class RatesTableModelDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int table_id;
    @Column(name = "number")
    private String tableNumber;
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    @OneToMany(mappedBy = "ratesTable", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CurrencyModelDB> currencyList = new ArrayList<CurrencyModelDB>();
}
