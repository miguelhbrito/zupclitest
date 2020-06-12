package com.ZupChallengeCovid;

public class ZupChallengeApplication {
    public static void main(String[] args) {

        String country = null;
        ArgsParameters argsParameters = new ArgsParameters();

        if (args.length > 0) {

            if ("help".equals(args[0])) {
                System.out.println("To use the cli program type \"covid19 country {country name}\"");
                return;
            }

            country = argsParameters.processPassedArgs(args);

            if (country != null && country.length() > 0) {
                CovidRequest covidRequest = new CovidRequest();
                System.out.println(covidRequest.getStatisticsFromCovid(country));
            } else {
                System.out.println("To use the cli program type \"covid19 country {country name}\"");
            }
        } else {
            System.out.println("To use the cli program type \"covid19 country {country name}\"");
        }
    }
}
