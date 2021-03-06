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

package uwu.smsgamer.lwjgltest.gui.click.parts.vals;

import uwu.smsgamer.lwjgltest.gui.click.ValPart;
import uwu.smsgamer.lwjgltest.gui.click.parts.*;
import uwu.smsgamer.lwjgltest.stuff.ValStuff;
import uwu.smsgamer.lwjgltest.utils.RenderUtils;

import java.awt.*;

public class ValuesPart extends ValPart {
    public ValPart[] parts;

    public ValuesPart(CategoryPart category, ValStuff valStuff, ModulePart module, int indent) {
        super(category, valStuff, module, indent);
        ValStuff[] valStuffs = valStuff.values;
        this.parts = new ValPart[valStuffs.length];
        for (int i = 0; i < valStuffs.length; i++) {
            ValStuff vs = valStuffs[i]; //not visual studio lol
            switch (vs.type) {
                case VALUES:
                    parts[i] = new ValuesPart(this.category, vs, this.module, indent + 2);
                    break;
                case BOOLEAN:
                    parts[i] = new TogglePart(this.category, vs, this.module, indent + 2);
                    break;
                case NUMBER:
                    parts[i] = new SliderPart(this.category, vs, this.module, indent + 2);
                    break;
                case STRING:
                    parts[i] = new StringPart(this.category, vs, this.module, indent + 2);
                    break;
                case CHOICE:
                    parts[i] = new ChoicePart(this.category, vs, this.module, indent + 2);
                    break;
                case COLOUR:
                    parts[i] = new ColourPart(this.category, vs, this.module, indent + 2);
                    break;
                default:
                    parts[i] = new PPart(this.category, vs, this.module, indent + 2);
            }
        }
    }

    @Override
    public void render(float x, float y, float maxY) {
        super.render(x, y, maxY);
        if (open) {
            for (ValPart part : parts) {
                part.render(x, y, maxY);
            }
        }

        if (getY() + getSize()[1] / 2F > maxY &&
          getY() - getSize()[1] / 2F < category.y + category.getSize()[1] / 2F) {
            RenderUtils.drawBorderedRect(getX() - getSize()[0] / 2F + indent * 2,
              Math.min(category.y, Math.max(maxY, getY() - getSize()[1] / 2F)),
              getX() + getSize()[0] / 2F, Math.min(category.y, getY() + getSize()[1] / 2F), edgeRadius,
              open ? MORE_OPEN_COLOR : notOverridden() && hovering() ? MORE_HOVER_COLOR : MORE_BASE_COLOR, BORDER_COLOR);
            RenderUtils.drawString(this.name, getX() - (mainSize[0]) / 2F + edgeRadius + 2 + indent * 2, getY(),
              new float[]{-5000, maxY + edgeRadius}, new float[]{5000, category.y}, 0.1F, -1, Color.WHITE);
        }
    }

    @Override
    public void scroll(double amount) {
        super.scroll(amount);
    }

    boolean justOpened;

    @Override
    public void click(int button) {
        super.click(button);
        if (this.justOpened) this.justOpened = false;
        if (notOverridden() && hovering()) {
            if (button == 0 || button == 1) {
                if (this.open) close();
                else open();
                return;
            }
        }
        if (open) {
            for (ValPart module : parts) {
                module.click(button);
            }
        }
    }

    @Override
    public void unclick(int button) {
        super.unclick(button);
        if (open && !justOpened) {
            for (ValPart part : parts) {
                part.unclick(button);
            }
        }
    }

    @Override
    public void charKey(char c) {
        super.charKey(c);
        if (open) {
            for (ValPart part : parts) {
                part.charKey(c);
            }
        }
    }

    @Override
    public void key(int key) {
        super.key(key);
        if (open) {
            for (ValPart part : parts) {
                part.key(key);
            }
        }
    }

    @Override
    public void open() {
        super.open();
    }

    @Override
    public void close() {
        super.close();
        for (ValPart part : parts) {
            part.close();
        }
    }
}
