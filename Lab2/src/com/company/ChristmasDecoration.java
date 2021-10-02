package com.company;

import java.lang.String;

public class ChristmasDecoration {
    private String name;
    private String decorationType;
    private String color;
    private double price;

    static public void printLine() {
        for(int i = 0; i < 99; i++)
            System.out.printf("-");
        System.out.printf("\n");
    }

    public ChristmasDecoration(String name, String decorationType, String color, double price)
    {
        this.name = name;
        this.decorationType = decorationType;
        this.color = color;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getDecorationType() {
        return decorationType;
    }

    public String getName() {
        return name;
    }

    public void outputInfo() {
        System.out.format("| %20s | %17s | %20s | %15s | %11.2f |\n", this.getClass().getSimpleName(), this.getName(),
                this.getDecorationType(), this.getColor(), this.getPrice());
        ChristmasDecoration.printLine();
    }
}

