package com.kepchyk1101.rustrecyclercalculatordesktop.calculator.Objects;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.RustItems.Resources;

public class Result {

    private Resources resource;
    private int amount;

    public Result(Resources resource, int amount) {
        this.resource = resource;
        this.amount = amount;
    }

    public Resources getResource() {
        return resource;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
