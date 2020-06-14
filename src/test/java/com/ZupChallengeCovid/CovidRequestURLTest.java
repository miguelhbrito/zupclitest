package com.ZupChallengeCovid;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CovidRequestURLTest {

    @Test
    public void getResponseBodyFromRequestURL_whenURLDoesntWork_returnThrowException() {
        CovidRequestURL covidRequestURL = new CovidRequestURL();
        covidRequestURL.setURL_COVID19_API("test url connection");

        try {
            String result = covidRequestURL.getResponseBodyFromRequestURL("");
            Assert.fail("Should throw an Exception");
        } catch (RuntimeException e) {
            assertEquals("An error occurred executing the GET request.", e.getMessage());
        }
    }

    @Test
    public void getResponseBodyFromRequestURL_whenPassedNotACountry_returnNotFoundMessage() {
        CovidRequestURL covidRequestURL = new CovidRequestURL();

        String result = covidRequestURL.getResponseBodyFromRequestURL("Not a country");
        assertEquals("Country not found", result);
    }

}
