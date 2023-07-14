package com.kepchyk1101.rustrecyclercalculatordesktop.calculator.Objects;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.RustItems.Resoruces;

public class ComponentPrice {

    private Resoruces resoruce;
    private int amount;

    public ComponentPrice(Resoruces resoruce, int amount) {
        this.resoruce = resoruce;
        this.amount = amount;
    }

    public Resoruces getResoruce() {
        return resoruce;
    }

    public int getAmount() {
        return amount;
    }

}