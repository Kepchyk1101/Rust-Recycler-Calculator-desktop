package com.kepchyk1101.rustrecyclercalculatordesktop.calculator.rustitem;

import com.kepchyk1101.rustrecyclercalculatordesktop.utils.utils;
import javafx.scene.image.Image;

public enum Resources {

    SCRAP("Металлолом", utils.getImageFromResources("scrap.png")),
    METAL_FRAGMENTS("Фрагменты металла", utils.getImageFromResources("metal.fragments.png")),
    HIGH_QUALITY_METAL("Металл высокого качества", utils.getImageFromResources("metal.refined.png")),
    CLOTH("Ткань", utils.getImageFromResources("cloth.png")),
    TECH_TRASH("Старые микросхемы", utils.getImageFromResources("techparts.png")),
    ROPE("Веревка", utils.getImageFromResources("rope.png"));

    private final String ru_name;
    private final Image image;

    Resources(String ru_name, Image image) {
        this.ru_name = ru_name;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

}