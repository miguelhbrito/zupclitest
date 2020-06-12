package com.ZupChallengeCovid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class CovidRequestURL {

    public String URL_COVID19_API = "https://coronavirus-19-api.herokuapp.com/countries/";

    public String getResponseBodyFromRequestURL(String country) {

        HttpURLConnection conexao = null;

        try {
            URL url = new URL(URL_COVID19_API + URLEncoder.encode(country, "UTF-8").replace("+","%20"));
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
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
        }
        return "GET request failed, response code received is different than 200.\n";
    }
}
