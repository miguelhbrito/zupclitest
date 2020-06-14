package com.ZupChallengeCovid;

import java.util.HashMap;
import java.util.Map;

public class CovidInfo {

    CovidRequestURL covidRequestURL = new CovidRequestURL();

    public String getInfoAboutCovidByCountry(String country) {

        String messageBody = covidRequestURL.getResponseBodyFromRequestURL(country);

        if (messageBody != null && !messageBody.equals("Country not found")) {

            try {
                String[] jsonValues = messageBody.replace("{", "").replace("}", "").split(",");
                Map<String, String> jsonValuesMap = new HashMap<String, String>();
                for (String value : jsonValues) {
                    String[] temp = value.split(":");
                    jsonValuesMap.put(temp[0], temp[1]);
                }

                String responseBody = constructReponseBody(jsonValuesMap);

                if (responseBody != null) {
                    return responseBody;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RuntimeException("The body receveid is not a JSON type !", e);
            }
        } else if (messageBody != null && messageBody.equals("Country not found")) {
            return "The GET request didnt found any information with the country informed," +
                    " please check the country typed. \n" +
                    "The country typed was: " + country;
        }

        return "Message body is null or didnt contains the right informations that we need !";
    }

    public String constructReponseBody(Map<String, String> jsonValuesMap) {

        String responseInfo = null;

        try {
            if (jsonValuesMap.containsKey("\"deaths\"") && jsonValuesMap.containsKey("\"cases\"") && jsonValuesMap.containsKey("\"recovered\"")) {

                float deaths = Float.parseFloat(jsonValuesMap.get("\"deaths\""));
                float cases = Float.parseFloat(jsonValuesMap.get("\"cases\""));
                int recovered = Integer.parseInt(jsonValuesMap.get("\"recovered\""));

                responseInfo = "------------------COVID STATS------------------\n" +
                        "Total cases 	: " + (int) cases + "\n" +
                        "Total deaths 	: " + (int) deaths + "\n" +
                        "Total recovered : " + recovered + "\n" +
                        "Mortality       : " + String.format("%.2f", calculatePercentage(deaths, cases)) + "(%)";
            }

            return responseInfo;

        } catch (NumberFormatException e) {
            throw new RuntimeException("An error occurred trying to convert string to float !" + e.getMessage(), e);
        }
    }

    public double calculatePercentage(double obtido, double total) {
        return obtido * 100 / total;
    }

    protected void setCovidRequestURL(CovidRequestURL covidRequestURL) {
        this.covidRequestURL = covidRequestURL;
    }

}
