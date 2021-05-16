package com.example.testapp.services;

import com.example.testapp.entities.CryptoCurrency;

import java.util.List;

public interface CryptoCurrenciesService {

    void saveData() throws IllegalAccessException;

    List<CryptoCurrency> getData();
}
