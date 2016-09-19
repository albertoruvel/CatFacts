package com.catfacts.app.service;

import com.catfacts.app.data.CatFactResponse;
import com.catfacts.app.exception.CatFactException;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jose.rubalcaba on 19/09/2016.
 */
public class CatFactsService {

    private static final String API_URL = "http://catfacts-api.appspot.com/api/facts";

    public static CatFactResponse getCatFact() throws CatFactException, Exception{
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setDoOutput(false);
        conn.setDoInput(true);

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        conn.connect();

        //get input stream
        InputStream stream = conn.getInputStream();

        if(stream == null)
            throw new CatFactException("Could not get input stream from connection");

        //get all string response
        StringBuilder response = new StringBuilder();
        String line = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        while((line = reader.readLine()) != null)
            response.append(line);

        //parse to a json response
        if(response.toString().isEmpty())
            throw new CatFactException("No response from server");

        return new Gson().fromJson(response.toString(), CatFactResponse.class);
    }
}
