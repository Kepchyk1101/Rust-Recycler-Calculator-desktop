package com.kepchyk1101.rustrecyclercalculatordesktop.calculator;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.object.ComponentArray;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.object.ComponentPrice;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.object.Result;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.rustitem.Components;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.rustitem.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RustRecyclerCalculator {

    private final ArrayList<Result> result = new ArrayList<>();
    private final Map<Resources, Result> resultMap = new HashMap<>(); // todo

    public ArrayList<Result> calculate(ComponentArray[] componentsArray, boolean isNeedFullRecycle) {

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
                        case TECH_TRASH -> addResultOfLeftoversToArray(Components.TECH_TRASH, resultResourceAmount);
                        case ROPE -> addResultOfLeftoversToArray(Components.ROPE, resultResourceAmount);
                        default -> addResultToArray(resultResource, resultResourceAmount, componentPrice);
                    }

                    // Если нет - просто добавляем обьект в рез. массив
                } else
                    addResultToArray(resultResource, resultResourceAmount, componentPrice);

            }

        }

        return result;

    }

    // Проверка: есть ли в рез. массиве конкретный элемент resources?
    private boolean isResultHaveResource(Resources resource) {
        for (Result result_ : result) {
            if (result_.getResource().equals(resource)) {
                return true;
            }
        }
        return false;
    }

    /*
        Возвращает индекс конкретного элемента resource из рез. массива..
        (запускается только после проверки isResultHaveResource)
     */
    private int getResourceIndex(Resources resource) {
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getResource().equals(resource)) {
                return i;
            }
        }
        return Integer.parseInt(null);
    }

    private void addResultToArray(Resources resource, int amount, ComponentPrice[] componentPrice) {

        // Если в рез. массиве уже есть хотя-бы 1 элемент - переходим дальше
        if (componentPrice.length > 0) {

            /*
                Если в рез. массиве уже есть ресурс который мы хотим добавить
                достаём его и меняем значение (amount/количество)
             */
            if (isResultHaveResource(resource)) {
                int index = getResourceIndex(resource);
                result.get(index).setAmount(result.get(index).getAmount() + amount);

                // Если же нет - добавляем новый элемент в рез. массив
            } else {
                result.add(new Result(resource, amount));
            }

            // Если же нет - добавляем первый элемент в рез. массив
        } else {
            result.add(new Result(resource, amount));
        }

    }

    private void addResultOfLeftoversToArray(Components component, int resultResourceAmount) {

        ComponentPrice[] componentPrices = component.getComponentPrice();
        for (ComponentPrice componentPrice : componentPrices) {
            Resources resource = componentPrice.getResource();
            int amountOfResource = componentPrice.getAmount() * resultResourceAmount;
            addResultToArray(resource, amountOfResource, componentPrices);
        }

    }

}