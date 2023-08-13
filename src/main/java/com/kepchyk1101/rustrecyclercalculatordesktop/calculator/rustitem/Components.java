package com.kepchyk1101.rustrecyclercalculatordesktop.calculator.rustitem;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.object.ComponentPrice;
import com.kepchyk1101.rustrecyclercalculatordesktop.utils.utils;
import javafx.scene.image.Image;

public enum Components {

    ELECTRIC_FUSE(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 20)
    }, utils.getImageFromResources("fuse.png")),
    METAL_BLADE(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 2),
            new ComponentPrice(Resources.METAL_FRAGMENTS, 15)
    }, utils.getImageFromResources("metalblade.png")),
    SMG_BODY(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 15),
            new ComponentPrice(Resources.HIGH_QUALITY_METAL, 2)
    }, utils.getImageFromResources("smgbody.png")),
    TECH_TRASH(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 20),
            new ComponentPrice(Resources.HIGH_QUALITY_METAL, 1)
    }, utils.getImageFromResources("techparts.png")),
    EMPTY_PROPANE_TANK(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 1),
            new ComponentPrice(Resources.METAL_FRAGMENTS, 50)
    }, utils.getImageFromResources("propanetank.png")),
    GEARS(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 10),
            new ComponentPrice(Resources.METAL_FRAGMENTS, 13)
    }, utils.getImageFromResources("gears.png")),
    METAL_PIPE(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 5),
            new ComponentPrice(Resources.HIGH_QUALITY_METAL, 1)
    }, utils.getImageFromResources("metalpipe.png")),
    RIFLE_BODY(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 25),
            new ComponentPrice(Resources.HIGH_QUALITY_METAL, 2)
    }, utils.getImageFromResources("riflebody.png")),
    SEMI_AUTOMATIC_BODY(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 15),
            new ComponentPrice(Resources.HIGH_QUALITY_METAL, 2),
            new ComponentPrice(Resources.METAL_FRAGMENTS, 75)
    }, utils.getImageFromResources("semibody.png")),
    METAL_SPRING(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 10),
            new ComponentPrice(Resources.HIGH_QUALITY_METAL, 1)
    }, utils.getImageFromResources("metalspring.png")),
    ROAD_SIGNS(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 5),
            new ComponentPrice(Resources.HIGH_QUALITY_METAL, 1)
    }, utils.getImageFromResources("roadsigns.png")),
    SEWING_KIT(new ComponentPrice[] {
            new ComponentPrice(Resources.CLOTH, 10),
            new ComponentPrice(Resources.ROPE, 2)
    }, utils.getImageFromResources("sewingkit.png")),
    TARP(new ComponentPrice[] {
            new ComponentPrice(Resources.CLOTH, 50)
    }, utils.getImageFromResources("tarp.png")),
    ROPE(new ComponentPrice[] {
            new ComponentPrice(Resources.CLOTH, 15)
    }, utils.getImageFromResources("rope.png")),
    SHEET_METAL(new ComponentPrice[] {
            new ComponentPrice(Resources.SCRAP, 8),
            new ComponentPrice(Resources.METAL_FRAGMENTS, 100),
            new ComponentPrice(Resources.HIGH_QUALITY_METAL, 1)
    }, utils.getImageFromResources("sheetmetal.png")),
    TARGETING_COMPUTER(new ComponentPrice[] {
            new ComponentPrice(Resources.TECH_TRASH, 3),
            new ComponentPrice(Resources.HIGH_QUALITY_METAL, 1),
            new ComponentPrice(Resources.METAL_FRAGMENTS, 50)
    }, utils.getImageFromResources("targeting.computer.png")),
    CCTV_CAMERA(new ComponentPrice[] {
            new ComponentPrice(Resources.TECH_TRASH, 2),
            new ComponentPrice(Resources.HIGH_QUALITY_METAL, 2)
    }, utils.getImageFromResources("cctv.camera.png"));

    private final ComponentPrice[] componentPrice;
    private final Image image;

    Components(ComponentPrice[] componentPrice, Image image) {
        this.componentPrice = componentPrice;
        this.image = image;
    }

    public ComponentPrice[] getComponentPrice() {
        return componentPrice;
    }

    public Image getImage() {
        return image;
    }

}