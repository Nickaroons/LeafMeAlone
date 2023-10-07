package com.MatrixEngine.Components.Physics;

import com.MatrixEngine.Components.StandardLibs.Component;
import com.MatrixEngine.DataTypes.Time;
import com.MatrixEngine.Objects.GameObject;

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
