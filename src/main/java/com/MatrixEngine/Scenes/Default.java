package com.MatrixEngine.Scenes;

import Objects.Box;
import Objects.Enemy;
import Objects.Player;
import UI.StandardLibs.Button;
import UI.StandardLibs.Text;
import com.MatrixEngine.Engine;
import com.MatrixEngine.ObjectHandler;
import com.MatrixEngine.UIRegistry;

import java.awt.*;

public class Default implements Scene {

    // Called Only Once
    @Override
    public void load() {
        ObjectHandler.add(new Player());
        ObjectHandler.add(new Box(200, 200));
        UIRegistry.add(new Text("FPS_Counter", "FPS: " + Engine.currentFPS, 10, 20, Color.BLACK));
    }

    // Called Once Per Frame
    @Override
    public void update() {
        ((Text)UIRegistry.getDynamicUIXObject("FPS_Counter")).updateText("FPS: " + Engine.currentFPS);
    }
}
