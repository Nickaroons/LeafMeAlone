package com.MatrixEngine.Components.Collider;

import com.MatrixEngine.Objects.GameObject;
import com.MatrixEngine.Engine;

import java.awt.*;

public class BoxCollider extends Collider{
    public int height, width;

    public BoxCollider(GameObject attachedObj, int width, int height) {
        super(attachedObj);
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(Graphics g) {
        if (isDrawingBounds()){
            g.setColor(Color.RED);
            g.drawRect((int) Engine.relativePosition(vector2).getX(), (int) Engine.relativePosition(vector2).getY(), width, height);
        }
    }

    @Override
    protected boolean checkBounds(GameObject obj, Collider c) {
        int x = (int) vector2.getX();
        int y = (int) vector2.getY();
        int ox = (int) c.vector2.getX();
        int oy = (int) c.vector2.getY();
        int oHeight = 0;
        int oWidth = 0;
        if (c instanceof BoxCollider){
            oHeight = ((BoxCollider)c).height;
            oWidth = ((BoxCollider)c).width;
        }
        if (x < ox + oWidth && x + width > ox && y < oy + oHeight && height + y > oy) {
            return true;
        }
        return false;
    }
}
