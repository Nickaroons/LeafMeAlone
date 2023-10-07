package com.MatrixEngine.Objects;

import com.MatrixEngine.Components.Collider.BoxCollider;
import com.MatrixEngine.Components.SpriteRenderer;
import com.MatrixEngine.DataTypes.ID;
import com.MatrixEngine.DataTypes.ObjectShape;
import com.MatrixEngine.DataTypes.Vector2;

import java.awt.*;

public class Box extends GameObject{

    public Box(int x, int y) {
        super(new ID("BOX"));
        width = 80;
        height = 40;
        transform.position = new Vector2(x, y);
        componentManager.newComponent("Sprite Renderer", new SpriteRenderer(this));
        //componentManager.newComponent("Rigid", new Rigidbody(this, 50, 0));
        componentManager.newComponent("Box Collider", new BoxCollider((GameObject) this, (int) width, (int) height));
        ((SpriteRenderer) componentManager.getComponent("Sprite Renderer")).setDefaultShape(ObjectShape.RECTANGLE, Color.RED);
        //transform.addForce(270, 2, 0.15);
        transform.setDrag(300);
    }


    @Override
    public void render(Graphics g) {
    }
}
