package com.example.testapp.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.domain.Persistable;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
@Data
public class CryptoCurrency implements Persistable<String> {

    @Id
    private final String currencyName;
    private final BigDecimal currencyValue;

    @PersistenceConstructor
    public CryptoCurrency(String currencyName, BigDecimal currencyValue) {
        this.currencyName = currencyName;
        this.currencyValue = currencyValue;
    }

    @Override
    public String getId() {
        return currencyName;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
