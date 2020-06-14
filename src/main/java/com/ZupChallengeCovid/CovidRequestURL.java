package com.ZupChallengeCovid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CovidRequestURL {

    public String URL_COVID19_API = "https://coronavirus-19-api.herokuapp.com/countries/";

    HttpURLConnection conexao = null;

    public String getResponseBodyFromRequestURL(String country) {

        try {
            URL url = new URL(URL_COVID19_API + URLEncoder.encode(country, "UTF-8").replace("+", "%20"));
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader inputData = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                StringBuffer responseMessage = new StringBuffer();
                String inputLine;

                while ((inputLine = inputData.readLine()) != null) {
                    responseMessage.append(inputLine);
                }

                inputData.close();

                return responseMessage.toString();
            }
        } catch (Exception e) {
             throw new RuntimeException("An error occurred executing the GET request.",e);
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
        }
        return "The GET request failed, response code received is different than 200.\n";
    }

    protected void setURL_COVID19_API(String URL_COVID19_API) {
        this.URL_COVID19_API = URL_COVID19_API;
    }
}
