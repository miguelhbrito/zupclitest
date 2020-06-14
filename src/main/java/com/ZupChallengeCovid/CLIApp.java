package com.ZupChallengeCovid;

public class CLIApp {

    public final String HELP_MESSAGE = "To use the CLI program type \"covid19 country {country name}\"";

    ArgsParameters argsParameters = new ArgsParameters();

    CovidInfo covidInfo = new CovidInfo();

    public String runCLI(String[] args) {
        String country;

        if (args.length > 0) {

            if ("help".equals(args[0])) {
                return HELP_MESSAGE;
            }

            if ("country".equals(args[0])) {
                country = argsParameters.processPassedArgs(args);

                if (country != null && country.length() > 0) {
                    return covidInfo.getInfoAboutCovidByCountry(country);
                }
            }
            return HELP_MESSAGE;
        } else {
            return HELP_MESSAGE;
        }
    }

    protected void setArgsParameters(ArgsParameters argsParameters) {
        this.argsParameters = argsParameters;
    }

    protected void setCovidInfo(CovidInfo covidInfo) {
        this.covidInfo = covidInfo;
    }

}
