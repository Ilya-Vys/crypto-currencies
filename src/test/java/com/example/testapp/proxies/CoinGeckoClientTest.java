package com.example.testapp.proxies;

import com.example.testapp.dto.CryptoCurrenciesDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoinGeckoClientTest {

    private final static Logger logger = LoggerFactory.getLogger(CoinGeckoClientTest.class);

    @Autowired
    CoinGeckoClient coinGeckoClient;

    @Test
    void shouldGet(){
        logger.info("Testing coinGecko client");
        CryptoCurrenciesDTO data = coinGeckoClient.getAndParseJson();
        Map<String, BigDecimal> map = data.getData().getMarketCapPercentage();
        assertEquals(10, map.size());
        assertTrue(map.containsKey("btc"));
        assertTrue(map.containsKey("bnb"));
        assertTrue(map.containsKey("ltc"));


    }

}