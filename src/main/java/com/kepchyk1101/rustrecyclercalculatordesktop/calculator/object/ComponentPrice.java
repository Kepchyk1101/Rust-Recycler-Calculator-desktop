package com.kepchyk1101.rustrecyclercalculatordesktop.calculator.object;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.rustitem.Resources;

public class ComponentPrice {

    private final Resources resource;
    private final int amount;

    public ComponentPrice(Resources resource, int amount) {
        this.resource = resource;
        this.amount = amount;
    }

    public Resources getResource() {
        return resource;
    }

    public int getAmount() {
        return amount;
    }

}