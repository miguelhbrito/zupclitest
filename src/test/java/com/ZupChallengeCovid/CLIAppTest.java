package com.ZupChallengeCovid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CLIAppTest {

    public final String HELP_MESSAGE = "To use the CLI program type \"covid19 country {country name}\"";

    @Test
    public void runCLI_whenPassedHelpAsArgs_returnHelpMessage() {
        CLIApp cliApp = new CLIApp();
        String result = cliApp.runCLI(new String[]{"help"});

        assertEquals(HELP_MESSAGE, result);
    }

    @Test
    public void runCLI_whenPassedLessThanZeroArgs_returnHelpMessage() {
        CLIApp cliApp = new CLIApp();
        String result = cliApp.runCLI(new String[]{});

        assertEquals(HELP_MESSAGE, result);
    }

    @Test
    public void runCLI_whenPassedMoreThanTwoArgsWithoutCountryWordMethodReturnNull_returnHelpMessage() {
        CLIApp cliApp = new CLIApp();

        ArgsParameters argsParameters = mock(ArgsParameters.class);
        cliApp.setArgsParameters(argsParameters);

        when(argsParameters.processPassedArgs((new String[]{"test", "test"}))).thenReturn(null);

        String result = cliApp.runCLI((new String[]{"test", "test"}));
        assertEquals(HELP_MESSAGE, result);
    }

    @Test
    public void runCLI_whenPassedMoreThanTwoArgsWithoutCountryWordReturnEmptyString_returnHelpMessage() {
        CLIApp cliApp = new CLIApp();

        ArgsParameters argsParameters = mock(ArgsParameters.class);
        cliApp.setArgsParameters(argsParameters);

        when(argsParameters.processPassedArgs((new String[]{"test", "test"}))).thenReturn("");

        String result = cliApp.runCLI((new String[]{"test", "test"}));
        assertEquals(HELP_MESSAGE, result);
    }

    @Test
    public void runCLI_whenPassedMoreThanTwoArgsWithCountryWord_returnInfoFromCountry() {
        CLIApp cliApp = new CLIApp();

        ArgsParameters argsParameters = mock(ArgsParameters.class);
        CovidInfo covidInfo = mock(CovidInfo.class);

        cliApp.setArgsParameters(argsParameters);
        cliApp.setCovidInfo(covidInfo);

        when(argsParameters.processPassedArgs((new String[]{"country", "brazil"}))).thenReturn("brazil");
        when(covidInfo.getInfoAboutCovidByCountry("brazil")).thenReturn("OK");

        String result = cliApp.runCLI(new String[]{"country", "brazil"});
        assertEquals("OK", result);
    }

}


