package com.example.joemonna.jsarna_final;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends FragmentActivity {
    JSONObject symbolInfo;
    JSONObject quotes;
    JSONObject quote;
    String symbol;
    String error;
    private LocationListener LocListener;
    private LocationManager LocManager;
    public static String[] homeStocks = {"GOOG", "AAPL", "TSLA"};
    public int EditHomeCode = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetHomeStocks();
        SetLocationListener();
        SetListeners();
    }

    private void SetHomeStocks() {
        TradierService s = new TradierService();
        StockQuote quote;

        ((TextView)findViewById(R.id.pick1)).setText(homeStocks[0]);
        ((TextView)findViewById(R.id.pick2)).setText(homeStocks[1]);
        ((TextView)findViewById(R.id.pick3)).setText(homeStocks[2]);

        try {
            symbolInfo = s.execute(homeStocks[0]).get();
            quote = new StockQuote(symbolInfo);
            ((TextView)findViewById(R.id.pick1Price)).setText(quote.GetLastPrice());

            s = new TradierService();
            symbolInfo = s.execute(homeStocks[1]).get();
            quote = new StockQuote(symbolInfo);
            ((TextView)findViewById(R.id.pick2Price)).setText(quote.GetLastPrice());

            s = new TradierService();
            symbolInfo = s.execute(homeStocks[2]).get();
            quote = new StockQuote(symbolInfo);
            ((TextView)findViewById(R.id.pick3Price)).setText(quote.GetLastPrice());

        } catch (Exception e) {
            this.error = e.getMessage();
        }
    }

    private void SetLocationListener() {
        LocListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                ((TextView)findViewById(R.id.location)).setText(location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }

    private void SetListeners() {
        ((Button)findViewById(R.id.configure_home_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intext for result to open editor
                Intent intent = new Intent(MainActivity.this, EditHome.class);
                startActivityForResult(intent, EditHomeCode);
            }
        });

        ((LinearLayout)findViewById(R.id.first)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewDetails.class);
                intent.putExtra("stock", homeStocks[0]);
                startActivity(intent);
            }
        });

        ((LinearLayout)findViewById(R.id.second)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewDetails.class);
                intent.putExtra("stock", homeStocks[1]);
                startActivity(intent);
            }
        });

        ((LinearLayout)findViewById(R.id.third)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewDetails.class);
                intent.putExtra("stock", homeStocks[2]);
                startActivity(intent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EditHomeCode)
        {
            if (resultCode == RESULT_OK) {
                SetHomeStocks();
                Toast.makeText(MainActivity.this, "Favorites Successfully Updated!", Toast.LENGTH_LONG).show();
            }
        }
    }
}




