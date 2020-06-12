package com.ZupChallengeCovid;

import java.util.HashMap;
import java.util.Map;

public class CovidRequest {

    CovidRequestURL covidRequestURL = new CovidRequestURL();

    public double calculatePercentage(double obtido, double total) {
        return obtido * 100 / total;
    }

    public String getStatisticsFromCovid(String country) {

        float deaths;
        float cases;
        int recovered;

        String messageBody = covidRequestURL.getResponseBodyFromRequestURL(country);

        if (messageBody != null && !messageBody.equals("Country not found")) {
            String[] JSONValues = messageBody.replace("{", "").replace("}", "").split(",");
            Map<String, String> JSONValuesMap = new HashMap<String, String>();
            for (String value : JSONValues) {
                String[] temp = value.split(":");
                JSONValuesMap.put(temp[0], temp[1]);
            }

            deaths = Float.parseFloat(JSONValuesMap.get("\"deaths\""));
            cases = Float.parseFloat(JSONValuesMap.get("\"cases\""));
            recovered = Integer.parseInt(JSONValuesMap.get("\"recovered\""));

            String totalResponse = "------------------COVID STATS------------------\n" +
                    "Total cases 	: " + (int) cases + "\n" +
                    "Total deaths 	: " + (int) deaths + "\n" +
                    "Total recovered : " + recovered + "\n" +
                    "Mortality       : " + String.format("%.2f", calculatePercentage(deaths, cases)) + "(%)";

            return totalResponse;

        } else if (messageBody.equals("Country not found")) {
            return "GET request didnt found any information, please check the country typed. \n" +
                    "The country typed was: " + country;
        }

        return "Message body is null";
    }

}
