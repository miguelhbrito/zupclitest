package com.ZupChallengeCovid;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class CovidInfoTest {

    public final String JSON_RESPONSE = "{\"cases\":1234,\"deaths\":1234,\"recovered\":1234}";

    public final String BODY_RESPONSE = "------------------COVID STATS------------------\n" +
            "Total cases 	: 1234\n" +
            "Total deaths 	: 1234\n" +
            "Total recovered : 1234\n" +
            "Mortality       : 100,00(%)";

    @Test
    public void getInfoAboutCovidByCountry_whenIsPassedCountry_returnConstructMessageBody() {
        CovidInfo covidInfo = new CovidInfo();
        CovidRequestURL covidRequestURL = mock(CovidRequestURL.class);

        covidInfo.setCovidRequestURL(covidRequestURL);

        when(covidRequestURL.getResponseBodyFromRequestURL(anyString())).thenReturn(JSON_RESPONSE);

        String result = covidInfo.getInfoAboutCovidByCountry("brazil");
        assertEquals(BODY_RESPONSE, result);
    }

    @Test
    public void getInfoAboutCovidByCountry_whenMessageBodyIsNull_returnMessageBodyNull() {
        CovidInfo covidInfo = new CovidInfo();
        CovidRequestURL covidRequestURL = mock(CovidRequestURL.class);

        covidInfo.setCovidRequestURL(covidRequestURL);

        when(covidRequestURL.getResponseBodyFromRequestURL(anyString())).thenReturn(null);

        String result = covidInfo.getInfoAboutCovidByCountry("brazil");
        assertEquals("Message body is null or didnt contains the right informations that we need !", result);
    }

    @Test
    public void getInfoAboutCovidByCountry_whenMessageBodyIsNotAJson_throwAnException() {
        CovidInfo covidInfo = new CovidInfo();
        CovidRequestURL covidRequestURL = mock(CovidRequestURL.class);
        covidInfo.setCovidRequestURL(covidRequestURL);

        when(covidRequestURL.getResponseBodyFromRequestURL(anyString())).thenReturn("Not a json");

        try {
            String result = covidInfo.getInfoAboutCovidByCountry("brazil");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("The body receveid is not a JSON type !", e.getMessage());
        }
    }

    @Test
    public void constructReponseBody_whenIsNotPassedAFilledMap_returnReponseBodyNull() {
        CovidInfo covidInfo = spy(CovidInfo.class);

        Map<String, String> createdMap = new HashMap<>();
        String result = null;

        result = covidInfo.constructReponseBody(createdMap);
        assertEquals(null, result);
    }

    @Test
    public void constructReponseBody_whenIsPassedAMapWithoutStringFormat_throwAnException() {
        CovidInfo covidInfo = spy(CovidInfo.class);

        Map<String, String> createdMap = new HashMap<>();
        createdMap.put("\"cases\"", "\"1234\"");
        createdMap.put("\"deaths\"", "\"1234\"");
        createdMap.put("\"recovered\"", "\"1234\"");

        try {
            String result = covidInfo.constructReponseBody(createdMap);
        } catch (NumberFormatException e) {
            assertEquals("An error occurred trying to convert string to float !For input string: \"\"1234\"\"", e.getMessage());
        }
    }
}
