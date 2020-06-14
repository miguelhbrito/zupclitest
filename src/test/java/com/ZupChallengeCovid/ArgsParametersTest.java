package com.ZupChallengeCovid;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ArgsParametersTest {

    @Test
    public void processPassedArgs_whenIsNotPassedCountryAsArgs_returnEmptyString() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"test"});
        assertEquals("Should return a empty string","", result);
    }

    @Test
    public void processPassedArgs_whenIsPassedOnlyCountryAsArgs_returnEmptyString() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"country"});
        assertEquals("Should return a empty string","", result);
    }

    @Test
    public void processPassedArgs_whenIsPassedTwoCountryAsArgs_returnEmptyString() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"country","country"});
        assertEquals("Should return a empty string","", result);
    }

    @Test
    public void processPassedArgs_whenIsPassedTwoCountryInDifferentPositionsAsArgs_returnEmptyString() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"country","brazil","country"});
        assertEquals("Should return null",null, result);
    }

    @Test
    public void processPassedArgs_whenIsPassedCountryAndACountryNameAsArgs_returnCountry() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"country","brazil"});
        assertEquals("Country is different than brazil","brazil", result);
    }

    @Test
    public void processPassedArgs_whenIsPassedCountryAndACountryNameWithTwoNamesAsArgs_returnConcatenatedCountryName() {
        ArgsParameters argsParameters = new ArgsParameters();

        String result = argsParameters.processPassedArgs(new String[]{"country","Saudi","Arabia"});
        assertEquals("Country is different than Saudi Arabia","Saudi Arabia", result);
    }
}
