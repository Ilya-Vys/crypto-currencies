package com.example.testapp.entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketCapPercentage {

    @SerializedName("btc")
    @Expose
    private Double btc;
    @SerializedName("eth")
    @Expose
    private Double eth;
    @SerializedName("bnb")
    @Expose
    private Double bnb;
    @SerializedName("xrp")
    @Expose
    private Double xrp;
    @SerializedName("usdt")
    @Expose
    private Double usdt;
    @SerializedName("doge")
    @Expose
    private Double doge;
    @SerializedName("ada")
    @Expose
    private Double ada;
    @SerializedName("dot")
    @Expose
    private Double dot;
    @SerializedName("bch")
    @Expose
    private Double bch;
    @SerializedName("ltc")
    @Expose
    private Double ltc;

}
