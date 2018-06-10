package com.bondarenko.av;

import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParameterValidator {

    private static final List<String> allowedCurrencies = new ArrayList<String>(Arrays.asList("USD", "UAH", "EUR", "GBP"));

    public static void validateInput(String a, String b) {
        if (a==null || a.isEmpty() || !NumberUtils.isNumber(a)) {
            throw  new IllegalArgumentException(a+" is not a valid number. make sure that you set . instead of ,");
        }

        if (!allowedCurrencies.contains(b.toUpperCase())) {
            throw  new IllegalArgumentException(b + " is not a valid Currency");
        }
    }

    public static void validateResponce(JSONObject response, String convertQuery){
        try {
            Object value = response.get(convertQuery);
        } catch (Exception e) {
          throw new InvalidParameterException("Incorrect responce data");
        }
    }

}
