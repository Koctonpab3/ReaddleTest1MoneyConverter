package com.bondarenko.av;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;


public class MoneyConverter {

    public static void main(String[] args) throws Exception {
        System.out.println("Please enter the amount you want to convert and required currency [allowable: \"USD\",\"UAH\",\"EUR\",\"GBP\" ]");

        InputStream stream = System.in;
        Scanner scanner = new Scanner(stream);
        System.out.println("Amount:");
        String inputAmount = scanner.next();
        System.out.println("Currency to convert:");
        String inputCurrency = scanner.next();
        scanner.close();

        CurrencyCalculator currencyCalculator = new CurrencyCalculator();
      BigDecimal result = currencyCalculator.getResult(inputAmount,inputCurrency);
        System.out.println("Convertation result = " + result);
        System.out.println("Bye");
        System.exit(0);
    }

}
