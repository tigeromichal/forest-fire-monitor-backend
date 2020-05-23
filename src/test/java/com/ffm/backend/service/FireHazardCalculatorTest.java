package com.ffm.backend.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FireHazardCalculatorTest {

    private final FireHazardCalculator calculator = new FireHazardCalculator();

    @Test
    void lowTempAndHighHumidityShouldReturnLowFireHazard() {
        // given
        double tempMax = 10;
        int humidity = 80;

        // when
        float hazard = calculator.calculate(tempMax, humidity);

        // then
        assertTrue(hazard < 0.2);
    }

    @Test
    void highTempAndHighHumidityShouldReturnLowFireHazard() {
        // given
        double tempMax = 30;
        int humidity = 80;

        // when
        float hazard = calculator.calculate(tempMax, humidity);

        // then
        assertTrue(hazard > 0.2 && hazard < 0.4);
    }

    @Test
    void lowTempAndLowHumidityShouldReturnMediumFireHazard() {
        // given
        double tempMax = 10;
        int humidity = 40;

        // when
        float hazard = calculator.calculate(tempMax, humidity);

        // then
        assertTrue(hazard > 0.4 && hazard < 0.7);
    }

    @Test
    void highTempAndLowHumidityShouldReturnHighFireHazard() {
        // given
        double tempMax = 30;
        int humidity = 40;

        // when
        float hazard = calculator.calculate(tempMax, humidity);

        // then
        assertTrue(hazard > 0.7);
    }
}