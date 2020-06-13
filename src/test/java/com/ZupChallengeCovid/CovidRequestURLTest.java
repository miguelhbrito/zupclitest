package com.ZupChallengeCovid;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CovidRequestURLTest {

    //@Test
    public void getResponseBodyFromRequestURL_whenIsPassedCountry_returnResponseBody() {
        CovidRequestURL covidRequestURL =  new CovidRequestURL();
        HttpURLConnection httpURLConnection = mock(HttpURLConnection.class);

        String result = covidRequestURL.getResponseBodyFromRequestURL("brazil");
        assertEquals("", result);
    }
}
