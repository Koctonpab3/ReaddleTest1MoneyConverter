package com.bondarenko.av;

import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;

public class CurrencyCalculator {

    private static final String source_currency="USD";
    private static final String url="https://free.currencyconverterapi.com/api/v5";

    public BigDecimal getResult(String inputAmount, String inputCurrency) {

        ParameterValidator.validateInput(inputAmount, inputCurrency);

        BigDecimal amount = new BigDecimal(inputAmount);
        String convertQuery = source_currency+"_"+inputCurrency.toUpperCase();
        JSONObject response = null;
        try {
            response = JSONReader.readJsonFromUrl(url+"/convert?q=" + convertQuery + "&compact=ultra");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ParameterValidator.validateResponce(response, convertQuery);

        BigDecimal currencyValue = getCurrencyValue(response.get(convertQuery));

        System.out.println("Today's Rate for " + convertQuery + " is " + currencyValue);
        //TODO check for null
        return amount.multiply(currencyValue);
    }


    private BigDecimal getCurrencyValue(Object value) {

        if (value instanceof Integer) {
            return BigDecimal.valueOf(((Integer) value).doubleValue());
        }

        if (value instanceof Double) {
            return BigDecimal.valueOf((Double) value);
        }
        return null;
    }
}
