package com.example.testapp.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {


    @SerializedName("active_cryptocurrencies")
    @Expose
    private Integer activeCryptocurrencies;
    @SerializedName("upcoming_icos")
    @Expose
    private Integer upcomingIcos;
    @SerializedName("ongoing_icos")
    @Expose
    private Integer ongoingIcos;
    @SerializedName("ended_icos")
    @Expose
    private Integer endedIcos;
    @SerializedName("markets")
    @Expose
    private Integer markets;
    @SerializedName("total_market_cap")
    @Expose
    private TotalMarketCap totalMarketCap;
    @SerializedName("total_volume")
    @Expose
    private TotalVolume totalVolume;
    @SerializedName("market_cap_percentage")
    @Expose
    private MarketCapPercentage marketCapPercentage;
    @SerializedName("market_cap_change_percentage_24h_usd")
    @Expose
    private Double marketCapChangePercentage24hUsd;
    @SerializedName("updated_at")
    @Expose
    private Integer updatedAt;


}


