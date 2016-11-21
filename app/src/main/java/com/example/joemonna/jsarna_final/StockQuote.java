package com.example.joemonna.jsarna_final;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joeon 11/21/2016.
 * This class takes a JSON stock object and returns useful data
 */

public class StockQuote {
    private JSONObject stock;
    private JSONObject quote;
    public StockQuote(JSONObject stock) {
        this.stock = stock;

        try {
            JSONObject quotes = this.stock.getJSONObject("quotes");
            this.quote = quotes.getJSONObject("quote");
        } catch (Exception e) {

        }
    }

    // must pass stock to constructor
    private StockQuote(){}

    public String GetSymbol() throws JSONException {
        return (String)this.quote.get("symbol");
    }

    public String GetDescription() throws JSONException {
        return (String)this.quote.get("description");
    }

    public String GetLastPrice() throws JSONException {
        String s = Double.toString((Double)this.quote.get("last"));
        return s;
    }

    public String GetPriceChange() throws JSONException {
        return Double.toString((Double)this.quote.get("change"));
    }

    public String GetChangePercent() throws JSONException {
        return (String)this.quote.get("change_percentage");
    }

    public String GetOpenPrice() throws JSONException {
        return (String)this.quote.get("open");
    }

    public String GetHighPrice() throws JSONException {
        return (String)this.quote.get("high");
    }

    public String GetLowPrice() throws JSONException {
        return (String)this.quote.get("low");
    }

    public String GetClosePrice() throws JSONException {
        return (String)this.quote.get("close");
    }

    public String GetBidPrice() throws JSONException {
        return (String)this.quote.get("bid");
    }

    public String GetAskPrice() throws JSONException {
        return (String)this.quote.get("ask");
    }
}
