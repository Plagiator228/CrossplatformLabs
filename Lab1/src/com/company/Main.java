package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть число ітерацій:\t");
        int n = scanner.nextInt();
        Calculator calc;
        if (n >= 15)
        {
            calc = new SeriesCalculatorUltra();
        } else {
            calc = new SeriesCalculator();
        }
        calc.calculateSum(n);
        calc.output();
        scanner.close();
    }
}