package com.kepchyk1101.rustrecyclercalculatordesktop.calculator.RustItems;

import com.kepchyk1101.rustrecyclercalculatordesktop.calculator.Objects.ComponentPrice;
import com.kepchyk1101.rustrecyclercalculatordesktop.utils.utils;
import javafx.scene.image.Image;

public enum Components {

    Electric_Fuse(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 20)
    }, utils.getImageFromResources("fuse.png")),
    Metal_Blade(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 2),
            new ComponentPrice(Resources.Metal_Fragments, 15)
    }, utils.getImageFromResources("metalblade.png")),
    SMG_Body(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 15),
            new ComponentPrice(Resources.High_Quality_Metal, 2)
    }, utils.getImageFromResources("smgbody.png")),
    Tech_Trash(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 20),
            new ComponentPrice(Resources.High_Quality_Metal, 1)
    }, utils.getImageFromResources("techparts.png")),
    Empty_Propane_Tank(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 1),
            new ComponentPrice(Resources.Metal_Fragments, 50)
    }, utils.getImageFromResources("propanetank.png")),
    Gears(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 10),
            new ComponentPrice(Resources.Metal_Fragments, 13)
    }, utils.getImageFromResources("gears.png")),
    Metal_Pipe(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 5),
            new ComponentPrice(Resources.High_Quality_Metal, 1)
    }, utils.getImageFromResources("metalpipe.png")),
    Rifle_Body(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 25),
            new ComponentPrice(Resources.High_Quality_Metal, 2)
    }, utils.getImageFromResources("riflebody.png")),
    Semi_Automatic_Body(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 15),
            new ComponentPrice(Resources.High_Quality_Metal, 2),
            new ComponentPrice(Resources.Metal_Fragments, 75)
    }, utils.getImageFromResources("semibody.png")),
    Metal_Spring(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 10),
            new ComponentPrice(Resources.High_Quality_Metal, 1)
    }, utils.getImageFromResources("metalspring.png")),
    Road_Signs(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 5),
            new ComponentPrice(Resources.High_Quality_Metal, 1)
    }, utils.getImageFromResources("roadsigns.png")),
    Sewing_Kit(new ComponentPrice[] {
            new ComponentPrice(Resources.Cloth, 10),
            new ComponentPrice(Resources.Rope, 2)
    }, utils.getImageFromResources("sewingkit.png")),
    Tarp(new ComponentPrice[] {
            new ComponentPrice(Resources.Cloth, 50)
    }, utils.getImageFromResources("tarp.png")),
    Rope(new ComponentPrice[] {
            new ComponentPrice(Resources.Cloth, 15)
    }, utils.getImageFromResources("rope.png")),
    SheetMetal(new ComponentPrice[] {
            new ComponentPrice(Resources.Scrap, 8),
            new ComponentPrice(Resources.Metal_Fragments, 100),
            new ComponentPrice(Resources.High_Quality_Metal, 1)
    }, utils.getImageFromResources("sheetmetal.png")),
    Targeting_Computer(new ComponentPrice[] {
            new ComponentPrice(Resources.Tech_Trash, 3),
            new ComponentPrice(Resources.High_Quality_Metal, 1),
            new ComponentPrice(Resources.Metal_Fragments, 50)
    }, utils.getImageFromResources("targeting.computer.png")),
    CCTV_Camera(new ComponentPrice[] {
            new ComponentPrice(Resources.Tech_Trash, 2),
            new ComponentPrice(Resources.High_Quality_Metal, 2)
    }, utils.getImageFromResources("cctv.camera.png"));

    private ComponentPrice[] componentPrice;
    private Image image;

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