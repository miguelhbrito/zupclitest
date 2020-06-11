package com.ZupChallengeCovid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CovidRequest {

    public String URL_COVID19_API = "https://coronavirus-19-api.herokuapp.com/countries/";

    public double calculatePercentage(double obtido, double total) {
        return obtido * 100 / total;
    }

    public String getResponseFromRequestURL(String country) {

        HttpURLConnection conexao = null;

        try {
            URL url = new URL(URL_COVID19_API + URLEncoder.encode(country, "UTF-8"));
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
        }
        return "Fazer essa parte! ";
    }

    public String getStatisticsFromCovid(String country) {

        float deaths;
        float cases;
        float recovered;

        String messageBody = getResponseFromRequestURL(country);

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
                    "Total recovered : " + (int) recovered + "\n" +
                    "Mortality       : " + String.format("%.2f", calculatePercentage(deaths, cases)) + "(%)";

            return totalResponse;
        } else if (messageBody.equals("Country not found")) {
            return "GET request didnt found any information, please check the country typed. \n" +
                    "The country typed was: " + country;
        }

        return "Fazer essa parte";
    }

}
