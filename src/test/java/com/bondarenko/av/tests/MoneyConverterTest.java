package com.bondarenko.av.tests;

import com.bondarenko.av.CurrencyCalculator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class MoneyConverterTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    CurrencyCalculator currencyCalculatorMock = mock(CurrencyCalculator.class);
    CurrencyCalculator  currencyCalculator = new CurrencyCalculator();

    @Before
    public  void prepareTestData(){
        doReturn(BigDecimal.valueOf(2600)).when(currencyCalculatorMock).getResult("100","Uah");
    }

    @Test
    public void testExecuteGetResult() {
        BigDecimal expectedResult = BigDecimal.valueOf(2600);

        BigDecimal result = currencyCalculatorMock.getResult("100","Uah");

        assertEquals(expectedResult, result);
    }


    @Test
    public void testZeroInput() {
        BigDecimal expectedResut = BigDecimal.valueOf(0.000000);
        BigDecimal result = currencyCalculator.getResult("0","Uah").setScale(1,1);

        assertEquals(expectedResut, result);

    }

    @Test
    public void testWrongAmountWithException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("ddd is not a valid number. make sure that you set . instead of ,");
        BigDecimal result = currencyCalculator.getResult("ddd","Uah");
    }

    @Test
    public void testWrongCurrencyWithException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("122 is not a valid Currency");
        BigDecimal result = currencyCalculator.getResult("100","122");

    }


}
