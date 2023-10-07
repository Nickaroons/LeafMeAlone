package com.MatrixEngine;

import DataTypes.Vector2;
import Objects.GameObject;

import java.awt.*;

public class Camera {
    private Vector2 position;
    private GameObject followObj;
    private int xOffset, yOffset;

    public Camera (GameObject followObj) {
        this.followObj = followObj;
        position = new Vector2((int) followObj.transform.position.getX(), (int) followObj.transform.position.getY());
    }

    public Camera (GameObject followObj, int xOffset, int yOffset) {
        this.followObj = followObj;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        position = new Vector2((int) followObj.transform.position.getX()-xOffset, (int) followObj.transform.position.getY()-yOffset);
    }

    public Camera (int x, int y) {
        position = new Vector2(x, y);
    }

    public void update() {
        if (followObj != null) {
            position = new Vector2((int) followObj.transform.position.getX()-xOffset, (int) followObj.transform.position.getY()-yOffset);
        }
    }

    public Point getPosition() {
        return new Point((int) position.getX(), (int) position.getY());
    }

}
