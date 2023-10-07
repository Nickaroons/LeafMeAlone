package com.MatrixEngine.DataTypes;

import com.MatrixEngine.Objects.GameObject;

public class CollisionEvent {
    private final boolean hasCollided;
    private final GameObject objectCollidedWith;

    public CollisionEvent(boolean hasCollided, GameObject objectCollidedWith) {
        this.hasCollided = hasCollided;
        this.objectCollidedWith = objectCollidedWith;
    }

    public boolean hasCollided() {
        return hasCollided;
    }

    public GameObject objectCollidedWith() {
        return objectCollidedWith;
    }
}
