package com.MatrixEngine;

import UI.StandardLibs.DynamicUIX;
import UI.StandardLibs.UIX;

import java.awt.*;
import java.util.LinkedList;

public class UIRegistry {

    private static LinkedList<UIX> objects = new LinkedList<>();

    public static void add(UIX o) {
        objects.add(o);
    }

    public static void remove(UIX o) {
        objects.remove(o);
    }

    public static void updateThenRender(Graphics g) {
        try {
            for (UIX o : objects) {
                o.update();
                o.draw(g);
            }
        } catch (Exception e) {}
    }
    public static LinkedList<UIX> getObjects() {
        return objects;
    }

    public static DynamicUIX getDynamicUIXObject(String UIXID) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof DynamicUIX) {
                if (((DynamicUIX)objects.get(i)).getID().equals(UIXID)) {
                    return (DynamicUIX)objects.get(i);
                }
            }
        }
        return null;
    }

    public static void clear() {
        objects.clear();
    }
}
