package com.ZupChallengeCovid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CovidRequestURLTest {

    @Test
    public void getResponseBodyFromRequestURL_whenURLDoesntWork_returnThrowException() {
        CovidRequestURL covidRequestURL = new CovidRequestURL();
        covidRequestURL.setURL_COVID19_API("test url connection");

        try {
            String result = covidRequestURL.getResponseBodyFromRequestURL("");
        } catch (Exception e) {
            assertEquals("An error occurred executing the GET request, message error: no protocol: test url connection", e.getMessage());
        }
    }

    @Test
    public void getResponseBodyFromRequestURL_whenPassedNotACountry_returnNotFoundMessage() {
        CovidRequestURL covidRequestURL = new CovidRequestURL();

        try {
            String result = covidRequestURL.getResponseBodyFromRequestURL("Not a country");
        } catch (Exception e) {
            assertEquals("Country not found", e.getMessage());
        }
    }

}
