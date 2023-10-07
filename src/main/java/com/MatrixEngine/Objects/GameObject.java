package com.MatrixEngine.Objects;

import com.MatrixEngine.Components.AI.Pathfinding.AStarPathfinder;
import com.MatrixEngine.Components.StandardLibs.ComponentManager;
import com.MatrixEngine.Components.StandardLibs.Transform;
import com.MatrixEngine.DataTypes.ID;
import com.MatrixEngine.DataTypes.Vector2;

import java.awt.*;

public abstract class GameObject {
    public ComponentManager componentManager = new ComponentManager();
    public Transform transform;
    public boolean hasCollider;
    protected double width, height;
    public ID referenceID;
    public boolean onPath = false;

    public GameObject(ID referenceID) {
        transform = new Transform(this);
        this.referenceID = referenceID;
        start();
    }

    public void start() {

    }

    public void update() {

    }

    public final void updateFinal() {
        transform.update();
        update();
        componentManager.update();
    }

    public void render(Graphics g) {

    }

    public final void renderFinal(Graphics g) {
        componentManager.renderFirst(g);
        componentManager.renderSprite(g);
        render(g);
        componentManager.renderLast(g);
    }

    public int getWidth() {
        return (int)width;
    }
    public int getHeight() {
        return (int)height;
    }

    public String getID() {
        return referenceID.ID;
    }

    public void searchPath(Vector2 goal) {
        for (String key : componentManager.getComponents().keySet()) {
            if (componentManager.getComponents().get(key) instanceof AStarPathfinder) {
                ((AStarPathfinder)componentManager.getComponents().get(key)).setNode((int) transform.position.getX() / ((AStarPathfinder)componentManager.getComponents().get(key)).getTileSize(), (int) transform.position.getY() / ((AStarPathfinder)componentManager.getComponents().get(key)).getTileSize(), (int) goal.getX(), (int) goal.getY());

                if (((AStarPathfinder)componentManager.getComponents().get(key)).search() == true) {
                    int nextX = ((AStarPathfinder)componentManager.getComponents().get(key)).pathList.get(0).col;
                    int nextY = ((AStarPathfinder)componentManager.getComponents().get(key)).pathList.get(0).row;

                }
            }
        }
    }

}
