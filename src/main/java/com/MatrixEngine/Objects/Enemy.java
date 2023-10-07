package com.MatrixEngine.Objects;

import com.MatrixEngine.Components.AI.Pathfinding.AStarPathfinder;
import com.MatrixEngine.Components.Collider.BoxCollider;
import com.MatrixEngine.Components.SpriteRenderer;
import com.MatrixEngine.DataTypes.ID;
import com.MatrixEngine.DataTypes.ObjectShape;
import com.MatrixEngine.DataTypes.Vector2;
import com.MatrixEngine.Engine;
import com.MatrixEngine.ObjectHandler;

import java.awt.*;

public class Enemy extends GameObject{

    public Enemy() {
        super(new ID("EN"));
        width = 40;
        height = 40;
        componentManager.newComponent("Sprite Renderer", new SpriteRenderer(this));
        //componentManager.newComponent("Rigid", new Rigidbody(this, 50, 0));
        componentManager.newComponent("Box Collider", new BoxCollider((GameObject) this, (int) width, (int) height));
        ((SpriteRenderer) componentManager.getComponent("Sprite Renderer")).setDefaultShape(ObjectShape.RECTANGLE, Color.RED);
        transform.position(new Vector2(300,300));
        componentManager.newComponent("Pathfinder", new AStarPathfinder(this, 40, 20, 20));
        //transform.addForce(270, 2, 0.15);
        transform.setDrag(300);
        ((AStarPathfinder)componentManager.getComponent("Pathfinder")).setPathMoveSpeed(30);
        ((AStarPathfinder)componentManager.getComponent("Pathfinder")).setUpdateInterval(0.05);
        transform.position = new Vector2(Engine.randInt(40, 400), 100);
    }

    @Override
    public void update() {
        GameObject player = ObjectHandler.search("player");
        ((AStarPathfinder)componentManager.getComponent("Pathfinder")).findPathToGameObject(player);
    }

    @Override
    public void render(Graphics g) {
    }
}
