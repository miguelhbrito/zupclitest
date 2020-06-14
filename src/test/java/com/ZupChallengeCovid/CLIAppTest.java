package com.ZupChallengeCovid;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CLIAppTest {

    public final String HELP_MESSAGE = "To use the CLI program type \"covid19 country {country name}\"";

    @Test
    public void runCLI_whenPassedHelpAsArgs_returnHelpMessage() {
        CLIApp cliApp = new CLIApp();
        String result = cliApp.runCLI(new String[]{"help"});

        assertEquals("Message returned is different than help message",HELP_MESSAGE, result);
    }

    @Test
    public void runCLI_whenPassedLessThanZeroArgs_returnHelpMessage() {
        CLIApp cliApp = new CLIApp();
        String result = cliApp.runCLI(new String[]{});

        assertEquals("Message returned is different than help message", HELP_MESSAGE, result);
    }

    @Test
    public void runCLI_whenPassedMoreThanTwoArgsWithoutCountryWordMethodReturnNull_returnHelpMessage() {
        CLIApp cliApp = setup_CLIAppWithArgsParametersMock(null,new String[]{"test", "test"});

        String result = cliApp.runCLI((new String[]{"test", "test"}));
        assertEquals("Message returned is different than help message", HELP_MESSAGE, result);
    }

    @Test
    public void runCLI_whenPassedMoreThanTwoArgsWithoutCountryWordReturnEmptyString_returnHelpMessage() {
        CLIApp cliApp = setup_CLIAppWithArgsParametersMock("", new String[]{"test", "test"});

        String result = cliApp.runCLI((new String[]{"test", "test"}));
        assertEquals("Message returned is different than help message", HELP_MESSAGE, result);
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
        assertEquals("Not a OK message","OK", result);
    }


    private CLIApp setup_CLIAppWithArgsParametersMock(String returnedString, String... args){
        CLIApp cliApp = new CLIApp();

        ArgsParameters argsParameters = mock(ArgsParameters.class);
        cliApp.setArgsParameters(argsParameters);

        when(argsParameters.processPassedArgs(args)).thenReturn(returnedString);

        return cliApp;
    }
}


