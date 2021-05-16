package com.example.testapp.repository;

import com.example.testapp.entities.CryptoCurrency;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;


@DataJdbcTest
class CryptoCurrencyRepositoryTest {

    private final static Logger logger = LoggerFactory.getLogger(CryptoCurrencyRepositoryTest.class);

    @Autowired
    CryptoCurrencyRepository currencyRepository;

    @Test
    void shouldSaveCurrency() {
        logger.info("Repository save currency method testing");

        CryptoCurrency currency = new CryptoCurrency("abc", BigDecimal.valueOf(22.3));
        currencyRepository.save(currency);

        assertThat(currencyRepository.findAll(), hasItem(currency));
    }

    @Test
    @Sql("/sql/currencies/shouldGetAll.sql")
    void shouldGetAll() {
        logger.info("Repository get all currencies method testing");

        CryptoCurrency currency1 = new CryptoCurrency("currency1", BigDecimal.valueOf(1.1));
        CryptoCurrency currency2 = new CryptoCurrency("currency2", BigDecimal.valueOf(2.2));
        CryptoCurrency currency3 = new CryptoCurrency("currency3", BigDecimal.valueOf(3.3));
        CryptoCurrency currency4 = new CryptoCurrency("currency4", BigDecimal.valueOf(4.4));

        List<CryptoCurrency> actual = (List<CryptoCurrency>) currencyRepository.findAll();
        assertThat(actual, hasItems(currency1, currency2, currency3, currency4));

    }

}