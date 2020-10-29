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

package uwu.smsgamer.lwjgltest.gui.psnf;

import uwu.smsgamer.lwjgltest.gui.psnf.components.Category;
import uwu.smsgamer.lwjgltest.input.InputManager;
import uwu.smsgamer.lwjgltest.stuff.Stuff;
import uwu.smsgamer.lwjgltest.utils.RenderUtils;

import java.awt.*;
import java.util.*;

// PlayStation Netflix
// lol
public class PSNFManager {
    public static final long CHANGE_TIME = 350L;
    public static final float MULT_Y = 50;
    public static final float MULT_X = 75;
    public static final float OFFSET_X = -100;
    public static final float OFFSET_Y = 100;

    private static PSNFManager instance;

    public static PSNFManager getInstance() {
        if (instance == null) instance = new PSNFManager();
        return instance;
    }

    public PSNFManager() {
        ArrayList<String> strings = new ArrayList<>(Stuff.values.keySet());
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            categories.add(new Category(s, i));
        }
    }
    public int select;
    public int lastSelect;
    public long timeAdd;
    public long changeTime;

    public LinkedList<Category> categories = new LinkedList<>();

    public void render() {
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            category.selected = (select == i);
            category.x = OFFSET_X;
            category.y = OFFSET_Y - (i - lastSelect - ((select - lastSelect) * getChange()
            )) * MULT_Y;
            category.render();
        }
        RenderUtils.drawRectBorder(OFFSET_X - Component.EDGE_RAD, OFFSET_Y - Component.EDGE_RAD,
          OFFSET_X + Component.WIDTH + Component.EDGE_RAD, OFFSET_Y + Component.HEIGHT + Component.EDGE_RAD,
          Component.EDGE_RAD, Color.WHITE);
        if (InputManager.UP.justPressed()) {
            changeCategory(-1);
        } else if (InputManager.UP.pressTimeMS() - CHANGE_TIME * 2 > 0 && getChangeTime() <= 0) {
            changeCategory(-1, -250);
        }
        if (InputManager.DOWN.justPressed()) {
            changeCategory(1);
        } else if (InputManager.DOWN.pressTimeMS() - CHANGE_TIME * 2 > 0 && getChangeTime() <= 0) {
            changeCategory(1, -250);
        }
    }

    public void changeCategory(int diff) {
        changeCategory(diff, 0);
    }

    public void changeCategory(int diff, long timeAdd) {
        lastSelect = select;
        select += diff;
        if (select >= 0 && select < categories.size()) {
            this.timeAdd = timeAdd;
            changeTime = System.currentTimeMillis() + Math.min(CHANGE_TIME - getChangeTime(), CHANGE_TIME) + timeAdd;
        } else if (select >= categories.size()) select = categories.size() - 1;
        else select = 0;
    }

    public float getChange() {
        return (1 - (getChangeTime() / (float) (CHANGE_TIME + timeAdd)));
    }

    public long getChangeTime() {
        return Math.max(0, changeTime - System.currentTimeMillis());
    }
}
