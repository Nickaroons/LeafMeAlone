package Components.Physics;

import Components.StandardLibs.Component;
import DataTypes.Time;
import Objects.GameObject;

public class Rigidbody extends Component {
    private double gravitationalConstant;
    private double mass;

    public Rigidbody(GameObject attachedObj, double mass) {
        super(true, attachedObj);
        this.mass = mass;
        gravitationalConstant = 9.8;
    }

    public Rigidbody(GameObject attachedObj, double mass, double gravitationalConstant) {
        super(true, attachedObj);
        this.mass = mass;
        this.gravitationalConstant = gravitationalConstant;
    }

    @Override
    public void update() {
        attachedObj.transform.setAcceleration(0, (gravitationalConstant*mass)*Time.deltaTime);
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

}
