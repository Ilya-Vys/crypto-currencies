package com.example.testapp.services;

import com.example.testapp.dto.CryptoCurrenciesDTO;
import com.example.testapp.entities.CryptoCurrency;
import com.example.testapp.proxies.CoinGeckoClient;
import com.example.testapp.repository.CryptoCurrencyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@DataJdbcTest
class CryptoCurrenciesServiceImplTest {

    private final static Logger logger = LoggerFactory.getLogger(CryptoCurrenciesServiceImplTest.class);

    @Mock
    CryptoCurrencyRepository currencyRepository;

    @Mock
    CoinGeckoClient coinGeckoClient;

    @Mock
    CryptoCurrenciesDTO currenciesDTO;

    @InjectMocks
    CryptoCurrenciesServiceImpl service;

    @Test
    void shouldSaveData() {
        logger.info("Service save data method testing");

        Map<String, BigDecimal> marketCapPercentage = new HashMap<>();
        marketCapPercentage.put("abc", BigDecimal.valueOf(22.3));
        marketCapPercentage.put("def", BigDecimal.valueOf(33.2));
        CryptoCurrenciesDTO.CurrenciesDataDto currenciesDataDto = new CryptoCurrenciesDTO.CurrenciesDataDto();
        currenciesDataDto.setMarketCapPercentage(marketCapPercentage);

        when(coinGeckoClient.getAndParseJson()).thenReturn(currenciesDTO);
        when(currenciesDTO.getData()).thenReturn(currenciesDataDto);

        service.saveData();

        verify(coinGeckoClient, times(1)).getAndParseJson();
        verify(currenciesDTO, times(1)).getData();
        verify(currencyRepository, times(2)).save(any());
    }

    @Test
    void shouldGetData() {
        logger.info("Service get data method testing");

        List<CryptoCurrency> cryptoCurrencyList = List.of(
                new CryptoCurrency("currency10", BigDecimal.valueOf(10.1)),
                new CryptoCurrency("currency20", BigDecimal.valueOf(20.2)),
                new CryptoCurrency("currency30", BigDecimal.valueOf(30.3)));

        when(currencyRepository.findAll()).thenReturn(cryptoCurrencyList);

        List<CryptoCurrency> actual = service.getData();

        assertEquals(actual, cryptoCurrencyList);
    }
}