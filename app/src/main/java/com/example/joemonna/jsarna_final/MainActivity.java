package com.example.joemonna.jsarna_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    JSONObject symbolInfo;
    JSONObject quotes;
    JSONObject quote;
    String symbol;
    String error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetHomeStocks();



    }

    private void SetHomeStocks() {
        // proof of concept
        TextView first = (TextView) findViewById(R.id.pick1);
        TextView second = (TextView) findViewById(R.id.pick2);


        TradierService s = new TradierService();
        StockQuote quote;

        try {
            symbolInfo = s.execute(first.getText().toString()).get();
            quote = new StockQuote(symbolInfo);
            ((TextView)findViewById(R.id.pick1Price)).setText(quote.GetLastPrice());

            s = new TradierService();
            symbolInfo = s.execute(second.getText().toString()).get();
            quote = new StockQuote(symbolInfo);
            ((TextView)findViewById(R.id.pick2Price)).setText(quote.GetLastPrice());
        } catch (Exception e) {
            this.error = e.getMessage();
        }
    }

}



