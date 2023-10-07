package com.MatrixEngine.DataTypes;

import com.MatrixEngine.Objects.GameObject;
import com.MatrixEngine.Engine;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    protected BufferedImage currentSprite;
    protected ObjectShape shape;
    protected GameObject attachedObj;
    protected int x2, y2;
    private Color color;

    public Sprite(BufferedImage sprite, GameObject attachedObj) {
        currentSprite = sprite;
        this.attachedObj = attachedObj;
    }

    public Sprite(ObjectShape shape, GameObject attachedObj, Color color) {
        this.shape = shape;
        this.attachedObj = attachedObj;
        this.color = color;
    }

    public void setX2AndY2ForLine(int x2, int y2) {
        this.x2 = x2;
        this.y2 = y2;
    }


    public void draw(Graphics g) {
        if (currentSprite != null) {
            g.drawImage((Image) currentSprite, (int) Engine.relativePosition(attachedObj.transform.getPosition()).getX(), (int) Engine.relativePosition(attachedObj.transform.getPosition()).getY(), attachedObj.getWidth(), attachedObj.getHeight(), null);
        } else if (shape != null) {
            g.setColor(color);
            switch (shape) {
                case RECTANGLE:
                    g.fillRect((int) Engine.relativePosition(attachedObj.transform.getPosition()).getX(), (int) Engine.relativePosition(attachedObj.transform.getPosition()).getY(), attachedObj.getWidth(), attachedObj.getHeight());
                    break;
                case CIRCLE:
                    g.fillOval((int) Engine.relativePosition(attachedObj.transform.getPosition()).getX(), (int) Engine.relativePosition(attachedObj.transform.getPosition()).getY(), attachedObj.getWidth(), attachedObj.getHeight());
                    break;
                case LINE:
                    g.drawLine((int) Engine.relativePosition(attachedObj.transform.getPosition()).getX(), (int) Engine.relativePosition(attachedObj.transform.getPosition()).getY(), (int) Engine.relativePosition(x2, y2).getX(), (int) Engine.relativePosition(x2, y2).getY());
                    break;
            }
        }
    }

    public BufferedImage getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(BufferedImage currentSprite) {
        this.currentSprite = currentSprite;
    }

}
