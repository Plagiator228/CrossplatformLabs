package com.company;

public class Tinsel extends LongDecoration {
    private String form;

    public Tinsel(String name, String decorationType, String color, double price, double length, String form)
    {
        super(name, decorationType, color, price, length);
        this.form = form;
    }
}
