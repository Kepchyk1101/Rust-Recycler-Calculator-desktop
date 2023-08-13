package com.kepchyk1101.rustrecyclercalculatordesktop.calculator.object;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.rustitem.Components;

public class ComponentArray {

    private final Components component;
    private int amount;

    public ComponentArray(Components component, int amount) {
        this.component = component;
        this.amount = amount;
    }

    public Components getComponent() {
        return component;
    }

    public int getAmount() {
        return amount;
    }

}
