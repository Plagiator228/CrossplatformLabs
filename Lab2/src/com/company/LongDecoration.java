package com.company;

public class LongDecoration extends ChristmasDecoration {
    private double length;

    public LongDecoration(String name, String decorationType, String color, double price, double length)
    {
        super(name, decorationType, color, price);
        this.length = length;
    }

    public double getLength() {
        return length;
    }
}
