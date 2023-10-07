package Components.Collider;

import Components.StandardLibs.Component;
import DataTypes.CollisionEvent;
import DataTypes.Vector2;
import Objects.GameObject;
import com.MatrixEngine.ObjectHandler;

public abstract class Collider extends Component {
    Vector2 vector2 = new Vector2();
    private boolean drawBounds;
    public Collider(GameObject attachedObj) {
        super(false, attachedObj);
        attachedObj.hasCollider = true;
    }

    @Override
    public void update() {
        vector2 = attachedObj.transform.getPosition();
    }

    public CollisionEvent isColliding() {
        for (GameObject o : ObjectHandler.getObjects()) {
            if (!o.equals(attachedObj)){
                for (String key : o.componentManager.getComponents().keySet()) {
                    if (o.componentManager.getComponents().get(key) instanceof Collider) {
                        if (((Collider) o.componentManager.getComponents().get(key)).checkBounds(attachedObj, this) && ((Collider) attachedObj.componentManager.getComponents().get(key)).checkBounds(o, ((Collider) o.componentManager.getComponents().get(key)))) {
                            return new CollisionEvent(true, o);
                        }
                    }
                }
            }
        }
        return new CollisionEvent(false, null);
    }

    protected boolean checkBounds(GameObject obj, Collider c) {
        return false;
    }

    public boolean isDrawingBounds() {
        return drawBounds;
    }

    public void drawBounds(boolean drawBounds) {
        this.drawBounds = drawBounds;
    }
}
