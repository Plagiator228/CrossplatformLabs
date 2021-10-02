package com.company;

public class ChristmasTreeToy extends ChristmasDecoration {
    private String material;
    private double weight;

    public ChristmasTreeToy(String name, String decorationType, String color, double price,
                            String material, double weight)
    {
        super(name, decorationType, color, price);
        this.material = material;
        this.weight = weight;
    }
}
