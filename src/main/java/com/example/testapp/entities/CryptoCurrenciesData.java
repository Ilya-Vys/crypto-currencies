package com.example.testapp.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptoCurrenciesData {

    @SerializedName("data")
    @Expose
    private Data data;
}
