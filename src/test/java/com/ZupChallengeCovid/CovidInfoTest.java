package com.ZupChallengeCovid;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
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
    public void getInfoAboutCovidByCountry_whenIsAnPassedCountry_returnConstructMessageBody() {
        CovidInfo covidInfo = setup_CovidInfoWithMockCovidRequestURL("brazil", JSON_RESPONSE);

        String result = covidInfo.getInfoAboutCovidByCountry("brazil");
        assertEquals("Should return constructed message body", BODY_RESPONSE, result);
    }

    @Test
    public void getInfoAboutCovidByCountry_whenMessageBodyIsNull_returnMessageBodyNull() {
        CovidInfo covidInfo = setup_CovidInfoWithMockCovidRequestURL("brazil", null);

        String result = covidInfo.getInfoAboutCovidByCountry("brazil");
        assertEquals("Should return message about null return", "Message body is null or didnt contains the right informations that we need !", result);
    }

    @Test
    public void getInfoAboutCovidByCountry_whenMessageBodyIsNotAJson_throwAnException() {
        CovidInfo covidInfo = setup_CovidInfoWithMockCovidRequestURL("brazil", "Not a json");

        try {
            String result = covidInfo.getInfoAboutCovidByCountry("brazil");
            Assert.fail("Should be thrown a RuntimeException");
        } catch (RuntimeException e) {
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
            Assert.fail("Should be thrown a RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("An error occurred trying to convert string to float !For input string: \"\"1234\"\"", e.getMessage());
        }
    }

    private CovidInfo setup_CovidInfoWithMockCovidRequestURL(String country, String bodyReturn) {

        CovidInfo covidInfo = new CovidInfo();
        CovidRequestURL covidRequestURL = mock(CovidRequestURL.class);

        covidInfo.setCovidRequestURL(covidRequestURL);

        when(covidRequestURL.getResponseBodyFromRequestURL(country)).thenReturn(bodyReturn);

        return covidInfo;
    }
}
