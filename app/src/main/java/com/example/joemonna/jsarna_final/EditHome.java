package com.example.joemonna.jsarna_final;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class EditHome extends AppCompatActivity {
    private String one;
    private String two;
    private String three;
    JSONObject symbolInfo;
    String error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_home);

        // save stocks in case cacnel is hit
        this.one = MainActivity.homeStocks[0];
        this.two = MainActivity.homeStocks[1];
        this.three = MainActivity.homeStocks[2];

        SetCurrentValues();
        SetOnClickListeners();
    }

    private void SetCurrentValues() {
        ((EditText)findViewById(R.id.edit_pick1)).setText(MainActivity.homeStocks[0]);
        ((EditText)findViewById(R.id.edit_pick2)).setText(MainActivity.homeStocks[1]);
        ((EditText)findViewById(R.id.edit_pick3)).setText(MainActivity.homeStocks[2]);
    }

    private void SetOnClickListeners() {
        ((Button)findViewById(R.id.cancel_edit_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        ((Button)findViewById(R.id.check_for_data_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckForData();
            }
        });

        ((Button)findViewById(R.id.submit_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save values to array, update in main
                MainActivity.homeStocks[0] = ((EditText)findViewById(R.id.edit_pick1)).getText().toString();
                MainActivity.homeStocks[1] = ((EditText)findViewById(R.id.edit_pick2)).getText().toString();
                MainActivity.homeStocks[2] = ((EditText)findViewById(R.id.edit_pick3)).getText().toString();

                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void CheckForData() {
        TradierService s = new TradierService();
        StockQuote quote;
        boolean error = false;

        try {
            symbolInfo = s.execute(((EditText)findViewById(R.id.edit_pick1)).getText().toString()).get();
            quote = new StockQuote(symbolInfo);
            if (quote.quote == null) {
                ((EditText)findViewById(R.id.edit_pick1)).setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                error = true;
            } else {
                ((EditText)findViewById(R.id.edit_pick1)).setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
            }

            s = new TradierService();
            symbolInfo = s.execute(((EditText)findViewById(R.id.edit_pick2)).getText().toString()).get();
            quote = new StockQuote(symbolInfo);
            if (quote.quote == null) {
                ((EditText)findViewById(R.id.edit_pick2)).setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                error = true;
            } else {
                ((EditText)findViewById(R.id.edit_pick2)).setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
            }

            s = new TradierService();
            symbolInfo = s.execute(((EditText)findViewById(R.id.edit_pick3)).getText().toString()).get();
            quote = new StockQuote(symbolInfo);
            if (quote.quote == null) {
                ((EditText)findViewById(R.id.edit_pick3)).setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                error = true;
            } else {
                ((EditText)findViewById(R.id.edit_pick3)).setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
            }

            if (error) {
                ((Button)findViewById(R.id.submit_button)).setEnabled(false);
            } else {
                ((Button)findViewById(R.id.submit_button)).setEnabled(true);
            }

        } catch (Exception e) {
            this.error = e.getMessage();
        }
    }
}
