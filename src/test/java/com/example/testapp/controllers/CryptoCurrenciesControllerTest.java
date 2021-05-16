package com.example.testapp.controllers;

import com.example.testapp.entities.CryptoCurrency;
import com.example.testapp.services.CryptoCurrenciesService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(CryptoCurrenciesController.class)
class CryptoCurrenciesControllerTest {

    private final static Logger logger = LoggerFactory.getLogger(CryptoCurrenciesControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CryptoCurrenciesService currenciesService;

    @Test
    void shouldGetIndex() throws Exception {
        logger.info("Controller get index method testing");
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    void shouldSaveDataAndGetIndex() throws Exception {
        logger.info("Controller parse method testing");
        mockMvc.perform(MockMvcRequestBuilders.get("/parse"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
        verify(currenciesService, times(1)).saveData();
    }

    @Test
    void shouldTakeDataAndGetIndex() throws Exception {
        logger.info("Controller show method testing");
        List<CryptoCurrency> currencies = List.of(
                new CryptoCurrency("currency100", BigDecimal.valueOf(100.1)),
                new CryptoCurrency("currency200", BigDecimal.valueOf(200.2)),
                new CryptoCurrency("currency300", BigDecimal.valueOf(300.3)));

        when(currenciesService.getData()).thenReturn(currencies);

        mockMvc.perform(MockMvcRequestBuilders.get("/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attribute("currencies", currencies));

        verify(currenciesService, times(1)).getData();
    }



}