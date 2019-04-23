package com.example.bilal.androidproject.model;

import java.util.List;

import androidx.annotation.NonNull;

public class Country {
    public String alpha3,currencyName;

    public Country(String alpha3, String currencyName){
        this.alpha3=alpha3;
        this.currencyName=currencyName;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @NonNull
    @Override
    public String toString() {
        return getAlpha3() + " - " + getCurrencyName();
    }
}
