package Objects;

import Components.AI.Pathfinding.AStarPathfinder;
import Components.Animation.AnimationSequence;
import Components.StandardLibs.ComponentManager;
import Components.StandardLibs.Transform;
import DataTypes.ID;
import DataTypes.Vector2;
import com.MatrixEngine.Engine;

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
