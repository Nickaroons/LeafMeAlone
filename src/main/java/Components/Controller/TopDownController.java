package Components.Controller;

import DataTypes.Vector2;
import Input.Input;
import Components.StandardLibs.Component;
import Objects.GameObject;

import java.awt.event.KeyEvent;

public class TopDownController extends Component {
    private double acc, moveSpeed;

    public TopDownController(GameObject attachedObj, double moveSpeed, double acceleration) {
        super(true, attachedObj);
        this.acc = acceleration;
        this.moveSpeed = moveSpeed;
        attachedObj.transform.setMaxVelocity(moveSpeed);
    }

    @Override
    public void update() {
        if (attachedObj.transform.position.getX() < 1) {
            attachedObj.transform.position.set(new Vector2(1, attachedObj.transform.position.getY()));
        }
        if (attachedObj.transform.position.getY() < 1) {
            attachedObj.transform.position.set(new Vector2(attachedObj.transform.position.getX(), 1));
        }
        if (Input.getKeyDown(KeyEvent.VK_D)) {
            attachedObj.transform.addForce(acc, 0.0);
        }
        if (Input.getKeyDown(KeyEvent.VK_A)) {
            attachedObj.transform.addForce(-acc, 0.0);
        }
        if (Input.getKeyDown(KeyEvent.VK_W)) {
            attachedObj.transform.addForce(0.0, -acc);
        }
        if (Input.getKeyDown(KeyEvent.VK_S)) {
            attachedObj.transform.addForce(0.0, acc);
        }
    }
}
