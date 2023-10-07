package com.MatrixEngine.Scenes;

import Objects.GameObject;

public interface Scene {

    // called when scene if first loaded
    public void load();

    // called once per frame if scene is loaded
    public void update();


}
