package com.example.testapp.services;

import com.example.testapp.dto.CryptoCurrenciesDTO;
import com.example.testapp.entities.CryptoCurrency;
import com.example.testapp.proxies.CoinGeckoClient;

import com.example.testapp.repository.CryptoCurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoCurrenciesServiceImpl implements CryptoCurrenciesService {

    private final CoinGeckoClient coinGeckoClient;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private boolean firstTimeSaving = true;


    public void saveData() {
        if (firstTimeSaving) {
            CryptoCurrenciesDTO data = coinGeckoClient.getAndParseJson();
            data.getData().getMarketCapPercentage()
                    .forEach((string, bigDecimal) -> cryptoCurrencyRepository.save(new CryptoCurrency(string, bigDecimal)));
            firstTimeSaving = false;
        }
    }

    public List<CryptoCurrency> getData() {
        return (List<CryptoCurrency>) cryptoCurrencyRepository.findAll();

    }
}
