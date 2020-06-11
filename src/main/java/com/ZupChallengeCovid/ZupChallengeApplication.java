package com.ZupChallengeCovid;

public class ZupChallengeApplication {
    public static void main(String[] args) {
        String[] country = new String[0];
        if (args.length > 0) {
            country = args;
        }
        CovidRequest covidRequest = new CovidRequest();
        System.out.println(covidRequest.getStatisticsFromCovid(country[0]));
    }
}
