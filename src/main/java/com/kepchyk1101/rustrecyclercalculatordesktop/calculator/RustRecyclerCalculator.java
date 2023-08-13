package com.kepchyk1101.rustrecyclercalculatordesktop.calculator;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.object.ComponentArray;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.object.ComponentPrice;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.object.Result;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.rustitem.Components;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.rustitem.Resources;

import java.util.HashMap;
import java.util.Map;

public class RustRecyclerCalculator {

    private final Map<Resources, Result> output = new HashMap<>();

    public Map<Resources, Result> calculate(ComponentArray[] componentsArray, boolean isNeedFullRecycle) {

        // Проходимся по всему, что нужно "переработать"
        for (ComponentArray componentArray : componentsArray) {

            ComponentPrice[] componentPrice = componentArray.getComponent().getComponentPrice();

            // Проходимся по "цене" предмета, который "перерабатываем"
            for (ComponentPrice componentPr1ce : componentPrice) {
                Resources resultResource = componentPr1ce.getResource();
                int resultResourceAmount = componentPr1ce.getAmount() * componentArray.getAmount();

                // Если включена функция переработки побочных компонентов по типу: веревки, микросхемы.. - следующие проверки
                if (isNeedFullRecycle) {

                    switch (resultResource) {
                        case TECH_TRASH -> addFullRecycleResultToOutput(Components.TECH_TRASH, resultResourceAmount);
                        case ROPE -> addFullRecycleResultToOutput(Components.ROPE, resultResourceAmount);
                        default -> addResultToOutput(resultResource, resultResourceAmount);
                    }

                    // Если нет - просто добавляем обьект в рез. массив
                } else
                    addResultToOutput(resultResource, resultResourceAmount);

            }

        }

        return output;

    }

    private void addResultToOutput(Resources resource, int amount) {

        if (output.containsKey(resource)) {
            Result result = output.get(resource);
            result.setAmount(result.getAmount() + amount);
            output.replace(resource, result);
            return;
        }
        output.put(resource, new Result(resource, amount));
    }

    private void addFullRecycleResultToOutput(Components component, int resultResourceAmount) {

        ComponentPrice[] componentPrices = component.getComponentPrice();
        for (ComponentPrice componentPrice : componentPrices) {
            Resources resource = componentPrice.getResource();
            int amountOfResource = componentPrice.getAmount() * resultResourceAmount;
            addResultToOutput(resource, amountOfResource);
        }

    }

}