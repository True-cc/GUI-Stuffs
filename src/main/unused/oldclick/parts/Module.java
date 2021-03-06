/*----------------------------------------------------*\
|                                                      |
|    ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\    |
|   //      Copyright (c) 2020 Shoghi Simon       \\   |
|   \\   License: GNU GENERAL PUBLIC LICENSE V3   //   |
|    \\\\\\\\\\\\\\\\\\\\\\\///////////////////////    |
|                                                      |
\*----------------------------------------------------*/
package uwu.smsgamer.lwjgltest.gui.oldclick.parts;

import uwu.smsgamer.lwjgltest.gui.oldclick.*;
import uwu.smsgamer.lwjgltest.gui.oldclick.parts.buttons.*;
import uwu.smsgamer.lwjgltest.stuff.*;

@Deprecated
public class Module extends Part {
    public static final float spacing = 30; //haha like this got implemented
    public String name;
    public ValStuff[] valStuff;
    public EditPart[] editParts;
    public boolean open;
    public Category category;

    public Module(String category, String name, Category cat) {
        this.name = name;
        this.category = cat;
        this.valStuff = Stuff.values.get(category).get(name);
        this.editParts = new EditPart[this.valStuff.length];
        ValStuff[] stuff = this.valStuff;
        for (int i = 0; i < stuff.length; i++) {
            ValStuff valStuff = stuff[i];
            switch (valStuff.type) {
                case VALUES:
                    editParts[i] = new ValuesPart(valStuff, this, this.category, 0);
                    break;
                case NUMBER:
                    editParts[i] = new SliderPart(valStuff, this.category, this);
                    break;
                case BOOLEAN:
                    editParts[i] = new TogglePart(valStuff, this.category, this);
                    break;
                case STRING:
                    editParts[i] = new StringPart(valStuff, this.category, this);
                    break;
                case CHOICE:
                    editParts[i] = new ChoicePart(valStuff, this, this.category, 0);
                    break;
                default:
                    editParts[i] = new ExPart(valStuff, this.category, this);
            }
        }
    }

    public void render(float y) {
        if (open) {
            float maxY = category.y - Category.mainSize[1] * (category.getMaxItems());
            y += category.yAdd;
            for (int i = 0; i < editParts.length; i++) {
                EditPart part = editParts[i];
                float owoY = y - Category.mainSize[1] * (i + 1) - category.yAdd;
                category.yAdd += part.getSize()[1];
                if (owoY <= maxY - Category.mainSize[1]) break;
                part.render(category.x, owoY, maxY);
            }
        }
    }

    public void toggle() {
        if (open = !open) open();
        else close();
    }

    public void close() {
        open = false;
        for (EditPart part : editParts) {
            part.close();
            this.category.moreTabs--;
        }
    }

    public void open() {
        open = true;
        this.category.moreTabs += editParts.length;
        this.category.justOpened = true;
    }

    @Override
    public void scroll(double amount) {
        super.scroll(amount);
        if (open) {
            if (OldClickGUIManager.getInstance().inputOverride == null)
                for (EditPart editPart : editParts) {
                    if (OldClickGUIManager.getInstance().inputOverride == null)
                        editPart.scroll(amount);
                } else OldClickGUIManager.getInstance().inputOverride.scroll(amount);
        }
    }

    @Override
    public void click(int button) {
        super.click(button);
        if (open) {
            if (OldClickGUIManager.getInstance().inputOverride == null)
            for (EditPart editPart : editParts) {
                if (OldClickGUIManager.getInstance().inputOverride == null)
                    editPart.click(button);
            } else OldClickGUIManager.getInstance().inputOverride.click(button);
        }
    }

    @Override
    public void unclick(int button) {
        super.unclick(button);
        if (open) {
            if (OldClickGUIManager.getInstance().inputOverride == null)
                for (EditPart editPart : editParts) {
                    if (OldClickGUIManager.getInstance().inputOverride == null)
                        editPart.unclick(button);
                } else OldClickGUIManager.getInstance().inputOverride.unclick(button);
        }
    }

    @Override
    public void charKey(char c) {
        super.charKey(c);
        if (open) {
            if (OldClickGUIManager.getInstance().inputOverride == null)
                for (EditPart editPart : editParts) {
                    if (OldClickGUIManager.getInstance().inputOverride == null)
                        editPart.charKey(c);
                } else OldClickGUIManager.getInstance().inputOverride.charKey(c);
        }
    }

    @Override
    public void key(int key) {
        super.key(key);
        if (open) {
            if (OldClickGUIManager.getInstance().inputOverride == null)
                for (EditPart editPart : editParts) {
                    if (OldClickGUIManager.getInstance().inputOverride == null)
                        editPart.key(key);
                } else OldClickGUIManager.getInstance().inputOverride.key(key);
        }
    }
}
