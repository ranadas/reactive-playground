package com.rdas.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import yahoofinance.YahooFinance;

import java.io.IOException;

/**
 * Created by rdas on 14/02/2018.
 */
@RequiredArgsConstructor
@ToString
@Getter
public class StockInfo {
    private final String ticker;
    private final double value;

    public static StockInfo fetch(String ticker) {
        try {
            return new StockInfo(ticker, YahooFinance.get(ticker).getQuote().getPrice().doubleValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
