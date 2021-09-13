package com.company;

public class SeriesCalculator implements Calculator {
    private long numerator;
    private long denominator;

    public void calculateSum(int n) {
        long next_numerator, next_denominator;
        for (int i = 1; i <= n; i++)
        {
            if (i == 1)
            {
                numerator = i;
                denominator = i + 2;
            } else {
                next_numerator = i;
                next_denominator = i + 2;
                numerator *= next_denominator;
                next_numerator *= denominator;
                denominator *= next_denominator;
                numerator += next_numerator;
                algorithmEuclidean();
            }
        }
    }

    public void algorithmEuclidean() {
        long temp, temp1, temp2;
        if (numerator > denominator)
        {
            temp1 = numerator;
            temp2 = denominator;
        } else {
            temp1 = denominator;
            temp2 = numerator;
        }
        while (temp2 > 1)
        {
            temp = temp1 % temp2;
            if(temp == 0)
            {
                numerator /= temp2;
                denominator /= temp2;
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