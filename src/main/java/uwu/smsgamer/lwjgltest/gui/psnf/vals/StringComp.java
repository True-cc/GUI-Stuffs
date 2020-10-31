/*
 ----------------------------------------------------*\
 |                                                      |
 |    ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\    |
 |   //      Copyright (c) 2020 Shoghi Simon       \\   |
 |   \\   License: GNU GENERAL PUBLIC LICENSE V3   //   |
 |    \\\\\\\\\\\\\\\\\\\\\\\///////////////////////    |
 |                                                      |
 \*----------------------------------------------------
 */

package uwu.smsgamer.lwjgltest.gui.psnf.vals;

import uwu.smsgamer.lwjgltest.gui.psnf.Component;
import uwu.smsgamer.lwjgltest.gui.psnf.*;
import uwu.smsgamer.lwjgltest.gui.psnf.components.*;
import uwu.smsgamer.lwjgltest.stuff.ValStuff;
import uwu.smsgamer.lwjgltest.utils.RenderUtils;

import java.awt.*;

public class StringComp extends ValComp {

    public StringComp(ValStuff valStuff, Module module, Category category, Component prevComponent) {
        super(valStuff, module, category, prevComponent);
    }

    @Override
    public void render() {
        if (isActive()) unclick();
        RenderUtils.drawRoundBorderedRect(x, y, x + WIDTH, y + HEIGHT, ROUND, EDGE_RAD, Color.LIGHT_GRAY, Color.RED);
        RenderUtils.drawString(String.valueOf(valStuff.name), x + WIDTH / 2, y + HEIGHT / 16 * 11, 14f, 1, Color.WHITE);
        RenderUtils.drawString(String.valueOf(valStuff.value), x + WIDTH / 2, y + HEIGHT / 16 * 5, 8f, 1, Color.WHITE);
    }

    @Override
    public void click() {
        unclick();
    }
}
