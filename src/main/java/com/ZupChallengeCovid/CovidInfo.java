package com.ZupChallengeCovid;

import java.util.HashMap;
import java.util.Map;

public class CovidInfo {

    CovidRequestURL covidRequestURL = new CovidRequestURL();

    public String getInfoAboutCovidByCountry(String country) {

        String messageBody = covidRequestURL.getResponseBodyFromRequestURL(country);

        if (messageBody != null && !messageBody.equals("Country not found")) {

            try {
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

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("The body receveid is not a JSON type !");
            }
        } else if (messageBody != null && messageBody.equals("Country not found")) {
            return "The GET request didnt found any information with the country informed," +
                    " please check the country typed. \n" +
                    "The country typed was: " + country;
        }

        return "Message body is null or didnt contains the right informations that we need !";
    }

    public String constructReponseBody(Map<String, String> JSONValuesMap) {

        String responseInfo = null;

        try {
            if (JSONValuesMap.containsKey("\"deaths\"") && JSONValuesMap.containsKey("\"cases\"") && JSONValuesMap.containsKey("\"recovered\"")) {

                float deaths = Float.parseFloat(JSONValuesMap.get("\"deaths\""));
                float cases = Float.parseFloat(JSONValuesMap.get("\"cases\""));
                int recovered = Integer.parseInt(JSONValuesMap.get("\"recovered\""));

                responseInfo = "------------------COVID STATS------------------\n" +
                        "Total cases 	: " + (int) cases + "\n" +
                        "Total deaths 	: " + (int) deaths + "\n" +
                        "Total recovered : " + recovered + "\n" +
                        "Mortality       : " + String.format("%.2f", calculatePercentage(deaths, cases)) + "(%)";
            }

            return responseInfo;

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Info received from API is not integer numbers !");
        }
    }

    public double calculatePercentage(double obtido, double total) {
        return obtido * 100 / total;
    }

    public void setCovidRequestURL(CovidRequestURL covidRequestURL) {
        this.covidRequestURL = covidRequestURL;
    }

}
