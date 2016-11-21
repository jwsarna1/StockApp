package com.example.joemonna.jsarna_final;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.*;
import android.os.AsyncTask;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

/**
 * Created by JoeMonna on 11/20/2016.
 */

public class TradierService extends AsyncTask<String, Void, JSONObject> {
    private static String api = "https://sandbox.tradier.com/v1/";
    private static String token = "Bearer 1Ts1XRe5A77XqkJfEPG4fLRHbChd";
    private static String quotes = "markets/quotes";

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... params)
    {
        JSONObject object = null;
        URL url;
        try {
            url = new URL("https://sandbox.tradier.com/v1/markets/quotes?symbols=" + params[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", token);
            connection.setRequestProperty("Accept", "application/json");


            InputStream s = connection.getInputStream();
            InputStreamReader r = new InputStreamReader(s);
            BufferedReader in = new BufferedReader(r);

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            object  = (JSONObject) new JSONTokener(response.toString()).nextValue();
        } catch (Exception ex) {
            String s = ex.getMessage();
            System.out.print(s);
        }

        return (object);
    }

}
