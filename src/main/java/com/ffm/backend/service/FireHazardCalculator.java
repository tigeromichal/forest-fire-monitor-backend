package com.ffm.backend.service;

public class FireHazardCalculator {

    public Float calculate(double tempMax, int humidity) {
        return (float) ((Math.sqrt(tempMax) / 40.0) / (humidity * humidity / 10000.0));
    }
}
