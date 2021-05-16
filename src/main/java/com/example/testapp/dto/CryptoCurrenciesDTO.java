package com.example.testapp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@ToString
public class CryptoCurrenciesDTO {

    private CurrenciesDataDto data;

    @Data
    @NoArgsConstructor
    @ToString
    @Setter
    public static class CurrenciesDataDto {

        @JsonProperty("market_cap_percentage")
        private Map<String, BigDecimal> marketCapPercentage;

    }
}
