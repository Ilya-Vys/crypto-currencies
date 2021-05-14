package com.example.testapp.services;

import com.example.testapp.entities.CryptoCurrency;
import com.example.testapp.entities.CryptoCurrenciesData;
import com.example.testapp.entities.MarketCapPercentage;
import com.example.testapp.proxies.CoinGeckoClient;

import com.example.testapp.repository.CryptoCurrencyRepository;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoCurrenciesService {

    private final CryptoCurrencyRepository repository;
    private boolean firstTimeSaving = true;

    public CryptoCurrenciesService(CryptoCurrencyRepository repository) {
        this.repository = repository;
    }


    public void saveData() throws IllegalAccessException {
        if (firstTimeSaving) {
            CoinGeckoClient coinGeckoClient = getClient();
            CryptoCurrenciesData data = coinGeckoClient.getAndParseJson();
            MarketCapPercentage percentage = data.getData().getMarketCapPercentage();
            List<CryptoCurrency> currencies = getCurrencies(percentage);
            repository.saveAll(currencies);

            firstTimeSaving = false;
        }
    }

    public List<CryptoCurrency> getData(){
        List<CryptoCurrency> currencies = (List<CryptoCurrency>) repository.findAll();
        return currencies;
    }

    private CoinGeckoClient getClient(){
        return Feign.builder()
                .contract (new SpringMvcContract())
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(CoinGeckoClient.class))
                .logLevel(Logger.Level.FULL)
                .target(CoinGeckoClient.class, "https://api.coingecko.com/api/v3/global");
    }

    private List<CryptoCurrency> getCurrencies(MarketCapPercentage cap) throws IllegalAccessException {
        List<CryptoCurrency> list = new ArrayList<>();

        for (Field field1 : cap.getClass().getDeclaredFields()) {
            field1.setAccessible(true);
            list.add(new CryptoCurrency(field1.getName(),(Double)field1.get(cap)));
            field1.setAccessible(false);
        }
        return list;
    }
}
