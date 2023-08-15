package com.kepchyk1101.rustrecyclercalculatordesktop.calculator.rustitem;

import com.kepchyk1101.rustrecyclercalculatordesktop.utils.utils;
import javafx.scene.image.Image;

public enum Resources {

    SCRAP(utils.getImageFromResources("scrap.png")),
    METAL_FRAGMENTS(utils.getImageFromResources("metal.fragments.png")),
    HIGH_QUALITY_METAL(utils.getImageFromResources("metal.refined.png")),
    CLOTH(utils.getImageFromResources("cloth.png")),
    TECH_TRASH(utils.getImageFromResources("techparts.png")),
    ROPE(utils.getImageFromResources("rope.png"));

    private final Image image;

    Resources(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

}