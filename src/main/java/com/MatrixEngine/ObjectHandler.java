package com.MatrixEngine;

import Objects.GameObject;

import java.awt.*;
import java.util.LinkedList;

public class ObjectHandler {

    private static LinkedList<GameObject> objects = new LinkedList<>();

    public static void add(GameObject o) {
        objects.add(o);
    }

    public static void remove(GameObject o) {
        objects.remove(o);
    }

    public static void updateThenRender(Graphics g) {
        try {
            for (GameObject o : objects) {
                o.updateFinal();
                o.renderFinal(g);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static LinkedList<GameObject> getObjects() {
        return objects;
    }

    public static void clear() {
        objects.clear();
    }

    public static GameObject search(String ID) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getID().equals(ID)) {
                return objects.get(i);
            }
        }
        return null;
    }

}
