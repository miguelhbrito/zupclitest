package com.ZupChallengeCovid;

public class ZupChallengeApplication {
    public static void main(String[] args) {

        String country = null;
        ArgsParameters argsParameters = new ArgsParameters();

        if (args[0].equals("help")) {
            System.out.println("To use the cli program type \"covid19 -country {country name}\"");
            return;
        }

        if (args.length > 0) {
            country = argsParameters.processPassedArgs(args);
        }

        if (country != null) {
            CovidRequest covidRequest = new CovidRequest();
            System.out.println(covidRequest.getStatisticsFromCovid(country));
        }
    }
}
