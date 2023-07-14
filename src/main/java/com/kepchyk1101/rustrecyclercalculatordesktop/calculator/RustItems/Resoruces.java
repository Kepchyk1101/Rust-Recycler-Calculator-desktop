package com.kepchyk1101.rustrecyclercalculatordesktop.calculator.RustItems;

import com.kepchyk1101.rustrecyclercalculatordesktop.utils.utils;
import javafx.scene.image.Image;

public enum Resoruces {

    Scrap("Металлолом", utils.getImageFromResources("scrap.png")),
    Metal_Fragments("Фрагменты металла", utils.getImageFromResources("metal.fragments.png")),
    High_Quality_Metal("Металл высокого качества", utils.getImageFromResources("metal.refined.png")),
    Cloth("Ткань", utils.getImageFromResources("cloth.png")),
    Tech_Trash("Старые микросхемы", utils.getImageFromResources("techparts.png")),
    Rope("Веревка", utils.getImageFromResources("rope.png"));

    private String ru_name;
    private Image image;

    Resoruces(String ru_name, Image image) {
        this.ru_name = ru_name;
        this.image = image;
    }

    public String getRu_name() {
        return ru_name;
    }

    public Image getImage() {
        return image;
    }

}