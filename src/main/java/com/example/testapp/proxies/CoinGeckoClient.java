package com.example.testapp.proxies;


import com.example.testapp.entities.CryptoCurrenciesData;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.cloud.openfeign.FeignClient(name = "Client",
        url = "https://api.coingecko.com/api/v3/global")
public interface CoinGeckoClient {

    @GetMapping(produces = "application/json")
    CryptoCurrenciesData getAndParseJson();
}
