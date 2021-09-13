package com.company;

import java.math.*;

public class SeriesCalculatorUltra implements Calculator {
    private BigInteger numerator;
    private BigInteger denominator;

    public void calculateSum(int n) {
        BigInteger next_numerator, next_denominator;
        for (int i = 1; i <= n; i++)
        {
            if (i == 1)
            {
                numerator = BigInteger.valueOf(i);
                denominator = BigInteger.valueOf(i+2);
            } else {
                next_numerator = BigInteger.valueOf(i);
                next_denominator = BigInteger.valueOf(i+2);
                numerator = numerator.multiply(next_denominator);
                next_numerator = next_numerator.multiply(denominator);
                denominator = denominator.multiply(next_denominator);
                numerator = numerator.add(next_numerator);
                algorithmEuclidean();
            }
        }
    }

    public void algorithmEuclidean() {
        BigInteger temp, temp1, temp2;
        if (numerator.compareTo(denominator) == 1)
        {
            temp1 = numerator;
            temp2 = denominator;
        } else {
            temp1 = denominator;
            temp2 = numerator;
        }
        while (temp2.compareTo(BigInteger.valueOf(1)) == 1)
        {
            temp = temp1.mod(temp2);
            if(temp.compareTo(BigInteger.valueOf(0)) == 0)
            {
                numerator = numerator.divide(temp2);
                denominator = denominator.divide(temp2);
                return;
            }
            temp1 = temp2;
            temp2 = temp;
        }
    }

    public void output() {
        System.out.print("Отримано наступну суму:\t" + numerator + " / "+ denominator);
    }
}
