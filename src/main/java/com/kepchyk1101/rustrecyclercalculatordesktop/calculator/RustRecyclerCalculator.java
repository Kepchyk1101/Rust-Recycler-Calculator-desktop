package com.kepchyk1101.rustrecyclercalculatordesktop.calculator;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.Objects.ComponentArray;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.Objects.ComponentPrice;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.Objects.Result;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.RustItems.Components;
import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.RustItems.Resoruces;

import java.util.ArrayList;

public class RustRecyclerCalculator {

    public static ArrayList<Result> Calculate(ComponentArray[] componentsArray, boolean isFullrecycle) {

        ArrayList<Result> result = new ArrayList<>();
        // "рез. масив" - Результативный массив (финальная версия того, что должно получится из "переработки")

        for (ComponentArray componentArray : componentsArray) {    // Проходимся по всему, что нужно "переработать"

            ComponentPrice[] componentPrice = componentArray.getComponent().getComponentPrice();
            for (ComponentPrice componentPr1ce : componentPrice) {    // Проходимся по "цене" предмета, который "перерабатываем"
                Resoruces resultResource = componentPr1ce.getResoruce();
                int resultResourceAmount = componentPr1ce.getAmount() * componentArray.getAmount();

                if (isFullrecycle) {    // Если включена функция переработки побочных компонентов по типу: веревки, микросхемы.. - следующие проверки

                    switch (resultResource) {
                        case Tech_Trash -> addResultOfLeftoversToArray(Components.Tech_Trash, resultResourceAmount, result);
                        case Rope -> addResultOfLeftoversToArray(Components.Rope, resultResourceAmount, result);
                        default -> addResultToArray(resultResource, resultResourceAmount, componentPrice, result);
                    }

                } else    // Если нет - просто добавляем обьект в рез. массив
                    addResultToArray(resultResource, resultResourceAmount, componentPrice, result);

            }

        }

        return result;    // Выводим результат

    }

    private static boolean isResultHaveResource(Resoruces resoruce, ArrayList<Result> result) {    // Проверка: есть ли в рез. массиве конкретный элемент resoruce?
        for (Result result_ : result) {
            if (result_.getResoruce().equals(resoruce)) {
                return true;
            }
        }
        return false;
    }

    private static int getResourceIndex(Resoruces resoruce, ArrayList<Result> result) {    // Возвращает индекс конкретного элемента resource из рез. массива..
        // ..(запускается только после проверки isResultHaveResource)
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getResoruce().equals(resoruce)) {
                return i;
            }
        }
        return Integer.parseInt(null);
    }

    private static void addResultToArray(Resoruces resoruce, int amount, ComponentPrice[] componentPrice, ArrayList<Result> result) {

        if (componentPrice.length > 0) {    // Если в рез. массиве уже есть хотя-бы 1 элемент - переходим дальше
            if (isResultHaveResource(resoruce, result)) {    // Если в рез. массиве уже есть ресурс который мы хотим добавить ..
                // ..достаём его и меняем значение (amount/количество)
                int index = getResourceIndex(resoruce, result);
                result.get(index).setAmount(result.get(index).getAmount() + amount);
            } else {    // Если же нет - добавляем новый элемент в рез. массив
                result.add(new Result(resoruce, amount));
            }
        } else {    // Если же нет - добавляем первый элемент в рез. массив
            result.add(new Result(resoruce, amount));
        }

    }

    private static void addResultOfLeftoversToArray(Components component, int resultResourceAmount, ArrayList<Result> result) {

        ComponentPrice[] componentPrices = component.getComponentPrice();
        for (ComponentPrice componentPrice : componentPrices) {
            Resoruces resource = componentPrice.getResoruce();
            int amountOfResource = componentPrice.getAmount() * resultResourceAmount;
            addResultToArray(resource, amountOfResource, componentPrices, result);
        }

    }

}