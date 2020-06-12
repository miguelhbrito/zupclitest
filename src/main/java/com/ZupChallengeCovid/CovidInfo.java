package com.ZupChallengeCovid;

import java.util.HashMap;
import java.util.Map;

public class CovidInfo {

    CovidRequestURL covidRequestURL = new CovidRequestURL();

    public String getInfoAboutCovidByCountry(String country) {

        String messageBody = covidRequestURL.getResponseBodyFromRequestURL(country);

        if (messageBody != null && !messageBody.equals("Country not found")) {

            String[] JSONValues = messageBody.replace("{", "").replace("}", "").split(",");
            Map<String, String> JSONValuesMap = new HashMap<String, String>();
            for (String value : JSONValues) {
                String[] temp = value.split(":");
                JSONValuesMap.put(temp[0], temp[1]);
            }

            String responseBody = constructReponseBody(JSONValuesMap);
            if (responseBody != null) {
                return responseBody;
            }

        } else if (messageBody.equals("Country not found")) {
            return "The GET request didnt found any information with the country informed," +
                    " please check the country typed. \n" +
                    "The country typed was: " + country;
        }

        return "Message body is null";
    }

    public String constructReponseBody(Map<String, String> JSONValuesMap) {

        float deaths = Float.parseFloat(JSONValuesMap.get("\"deaths\""));
        float cases = Float.parseFloat(JSONValuesMap.get("\"cases\""));
        int recovered = Integer.parseInt(JSONValuesMap.get("\"recovered\""));

        String responseInfo = "------------------COVID STATS------------------\n" +
                "Total cases 	: " + (int) cases + "\n" +
                "Total deaths 	: " + (int) deaths + "\n" +
                "Total recovered : " + recovered + "\n" +
                "Mortality       : " + String.format("%.2f", calculatePercentage(deaths, cases)) + "(%)";

        return responseInfo;
    }

    public double calculatePercentage(double obtido, double total) {
        return obtido * 100 / total;
    }

}
