package Components.Particles;

import Components.Collider.BoxCollider;
import Components.SpriteRenderer;
import DataTypes.ID;
import DataTypes.ObjectShape;
import DataTypes.Vector2;
import Objects.GameObject;
import com.MatrixEngine.Engine;
import com.MatrixEngine.ObjectHandler;

import java.awt.*;

public class Particle extends GameObject {
    Emitter emitter;
    int direction;
    double lifetime;
    long createdTime;
    Color color;
    Color startColor;
    Color endColor;
    double startingVel;
    double ratio;

    public Particle(ID referenceID, Emitter emitter, double lifetime, Color colorStart, Color colorEnd, double startingVel) {
        super(referenceID);
        this.emitter = emitter;
        this.lifetime = lifetime;
        this.color = colorStart;
        this.startingVel = startingVel;
        this.startColor = colorStart;
        this.endColor = colorEnd;
        createdTime = System.currentTimeMillis();
        transform.position.set(emitter.attachedObj.transform.position);
    }

    @Override
    public void start() {
        direction = Engine.randInt(0, 360);
        transform.position = new Vector2(200, 200);
        height = 20;
        width = 20;
        transform.setDrag(0);
        componentManager.newComponent("Box Collider", new BoxCollider((GameObject) this, (int) height, (int) width));
    }

    @Override
    public void update() {
        int red = (int)Math.abs((ratio * endColor.getRed()) + ((1 - ratio) * startColor.getRed()));
        int green = (int)Math.abs((ratio * endColor.getGreen()) + ((1 - ratio) * startColor.getGreen()));
        int blue = (int)Math.abs((ratio * endColor.getBlue()) + ((1 - ratio) * startColor.getBlue()));
        if (ratio <= 1){
            color = new Color(red, green, blue);
            ratio = ((System.currentTimeMillis()-createdTime)/900.0)/lifetime;
        } else {
            ratio = 1;
        }
        transform.addForce(direction, startingVel);
        if (System.currentTimeMillis() - createdTime > lifetime * 1000) {
            emitter.remove(this);
        }
        if (((BoxCollider)componentManager.getComponent("Box Collider")).isColliding().hasCollided()) {
            if (transform.direction() != -1) {
                GameObject obj = ((BoxCollider)componentManager.getComponent("Box Collider")).isColliding().objectCollidedWith();;
                if (!obj.equals(emitter.attachedObj)){
                    direction += 180;
                    transform.addForce(direction, startingVel*1000);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval((int) Engine.relativePosition(transform.position).getX(), (int) Engine.relativePosition(transform.position).getY(), 10, 10);
    }
}
