package Components.StandardLibs;

import Components.Physics.Rigidbody;
import DataTypes.Time;
import DataTypes.Vector2;
import Objects.GameObject;
import com.MatrixEngine.Scenes.Scene;

import static java.lang.Math.round;

public class Transform {
    public Vector2 position;
    private Vector2 velocity = new Vector2();
    private Vector2 acceleration = new Vector2();
    private GameObject attachedObj;
    private double drag;
    private double maxAcc;
    public String physicsTag = "static";

    public Transform(GameObject attachedObj) {
        position = new Vector2();
        this.attachedObj = attachedObj;
        drag = 100;
        maxAcc = 200;
    }

    public void position(Vector2 vector2) {
        this.position = vector2;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setDrag(double drag) {
        this.drag = drag;
    }
    public void setMaxVelocity(double maxVelocity) {
        this.maxAcc = maxVelocity;
    }

    public void addForce(double velX, double velY) {
        velocity.move(velX, velY);
    }

    public void addForce(int degrees, double vel) {
        double rad = Math.toRadians(degrees);
        velocity.move(vel*Math.cos(rad), vel*Math.sin(rad));
    }


    public void update() {
        if ((int)velocity.getX() != 0 || (int)velocity.getY() != 0){
            velocity.move(-drag * (velocity.getX() / Math.sqrt(Math.pow(velocity.getX(), 2) + Math.pow(velocity.getY(), 2))) * Time.deltaTime, -drag * (velocity.getY() / Math.sqrt(Math.pow(velocity.getX(), 2) + Math.pow(velocity.getY(), 2))) * Time.deltaTime);
        } else if ((int)velocity.getX() == 0){
            velocity.set((int)velocity.getX(), velocity.getY());
        } else if ((int)velocity.getY() == 0) {
            velocity.set(velocity.getX(), (int)velocity.getY());
        }
        if (velocity.getX() > maxAcc) {
            velocity.set(maxAcc, velocity.getY());
        }
        if (velocity.getX() < -maxAcc) {
            velocity.set(-maxAcc, velocity.getY());

        }
        if (velocity.getY() > maxAcc) {
            velocity.set(velocity.getX(), maxAcc);

        }
        if (velocity.getY() < -maxAcc) {
            velocity.set(velocity.getX(), -maxAcc);

        }
        velocity.move(acceleration.getX() * Time.deltaTime, acceleration.getY() * Time.deltaTime);
        position.move(velocity.getX() * Time.deltaTime, velocity.getY() * Time.deltaTime);
    }


    public void stopForce() {
        velocity.set(0, 0);
    }

    public int direction() {
        if ((int)velocity.getX() != 0 || (int)velocity.getY() != 0) {
            int val = (int) (Math.atan2(velocity.getY(), velocity.getX()) * (180 / Math.PI));
            if (val < 0) {
                return val + 360;
            }
            return val;
        } else {
            return -1;
        }
    }

    public Vector2 currentVelocity() {
        return new Vector2(velocity.getX(), velocity.getY());
    }

    public void convertForce(GameObject obj) {
        for (String key : obj.componentManager.getComponents().keySet()) {
            if (obj.componentManager.getComponents().get(key) instanceof Rigidbody) {
                Rigidbody r = (Rigidbody) obj.componentManager.getComponents().get(key);
                double mass = r.getMass();
                double attachedObjMass = 0;
                for (String key2 : attachedObj.componentManager.getComponents().keySet()) {
                    if (attachedObj.componentManager.getComponents().get(key2) instanceof Rigidbody) {
                        Rigidbody attachedR = (Rigidbody) attachedObj.componentManager.getComponents().get(key2);
                        attachedObjMass = attachedR.getMass();
                        obj.transform.addForce(direction(), Math.abs(((velocity.getX() + velocity.getY()) / 2) * (attachedObjMass / mass)));
                        return;
                    }
                }
                throw new RuntimeException("Method convertForce() cannot be called by an object without a Rigidbody component. Note: The parameter does not need a Rigidbody only the object of which the transform is being called from.");
            }
        }
    }

    public void setAcceleration(double x, double y) {
        acceleration.set(x, y);
    }
}
