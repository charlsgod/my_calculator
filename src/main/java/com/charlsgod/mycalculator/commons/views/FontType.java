package com.charlsgod.mycalculator.commons.views;

/**
 * Created by dell on 26/06/2017.
 */

public enum FontType {

    DIGITAL_7("fonts/digital-7.ttf"),
    DIGITAL_7_ITALIC("fonts/digital-7-italic.ttf"),
    AVENIR_LIGHT("fonts/Avenir-Light.ttf");

    private String path;

    FontType(String path) {
        this.path = path;
    }

    public String getAssetPath() {
        return path;
    }
}