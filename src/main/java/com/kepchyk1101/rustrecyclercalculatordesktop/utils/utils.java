package com.kepchyk1101.rustrecyclercalculatordesktop.utils;

import com.kepchyk1101.rustrecyclercalculatordesktop.gui.GUI;
import javafx.scene.image.Image;

import java.util.Objects;

public class utils {

    public static Image getImageFromResources(String iconName) {
        return new Image(Objects.requireNonNull(GUI.class.getResourceAsStream("/icons/" + iconName)));
    }

    public static boolean isDigit(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
