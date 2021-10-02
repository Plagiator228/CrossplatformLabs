package com.company;

public class Garland extends LongDecoration {
    private int numberOfModes;

    public Garland(String name, String decorationType, String color, double price, double length, int numberOfModes)
    {
        super(name, decorationType, color, price, length);
        this.numberOfModes = numberOfModes;
    }
}
