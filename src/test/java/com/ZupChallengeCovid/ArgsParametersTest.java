package com.ZupChallengeCovid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArgsParametersTest {

    @Test
    public void processPassedArgs_whenIsNotPassedCountryAsArgs_returnEmptyString() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"test"});
        assertEquals("", result);
    }

    @Test
    public void processPassedArgs_whenIsPassedOnlyCountryAsArgs_returnEmptyString() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"country"});
        assertEquals("", result);
    }

    @Test
    public void processPassedArgs_whenIsPassedTwoCountryAsArgs_returnEmptyString() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"country","country"});
        assertEquals("", result);
    }

    @Test
    public void processPassedArgs_whenIsPassedThreeCountryAsArgs_returnEmptyString() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"country","country","country"});
        assertEquals("", result);
    }

    @Test
    public void processPassedArgs_whenIsPassedCountryAndACountryNameAsArgs_returnCountry() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"country","brazil"});
        assertEquals("brazil", result);
    }

    @Test
    public void processPassedArgs_whenIsPassedCountryAndACountryNameWithTwoNamesAsArgs_returnConcatenatedCountryName() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"country","Saudi","Arabia"});
        assertEquals("Saudi Arabia", result);
    }
}
