package com.MatrixEngine;

import Objects.Box;
import Objects.Player;
import com.MatrixEngine.Scenes.Default;
import com.MatrixEngine.Scenes.Scene;

public class SceneManager {

    private static Scene currentScene = null;

    public static void loadScene(Scene scene) {
        ObjectHandler.clear();
        UIRegistry.clear();
        currentScene = scene;
        if (currentScene != null) {
            currentScene.load();
        }
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void update() {currentScene.update();}
}
