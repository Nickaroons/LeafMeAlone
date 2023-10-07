package com.MatrixEngine.Components.StandardLibs;

import com.MatrixEngine.Objects.GameObject;

import java.awt.*;

public abstract class Component {
    protected boolean renderBeforeObject;
    public GameObject attachedObj;

    public Component(boolean renderBeforeObject, GameObject attachedObj) {
        this.renderBeforeObject = renderBeforeObject;
        this.attachedObj = attachedObj;
    }

    public void update() {
    }

    public void render(Graphics g) {

    }

    public boolean renderBeforeObject() {
        return renderBeforeObject;
    }

}
