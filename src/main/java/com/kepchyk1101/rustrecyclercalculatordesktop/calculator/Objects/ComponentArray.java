package com.kepchyk1101.rustrecyclercalculatordesktop.calculator.Objects;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.RustItems.Components;

public class ComponentArray {

    private Components component;
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
