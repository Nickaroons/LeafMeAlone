package com.MatrixEngine.Scenes;

import com.MatrixEngine.DataTypes.ID;
import com.MatrixEngine.Engine;
import com.MatrixEngine.ObjectHandler;
import com.MatrixEngine.Objects.PlayerCharacter;
import com.MatrixEngine.UI.StandardLibs.Text;
import com.MatrixEngine.UIRegistry;

import java.awt.*;

public class GameScene implements Scene {

    // Called Only Once
    @Override
    public void load() {
        UIRegistry.add(new Text("FPS_Counter", "FPS: " + Engine.currentFPS, 10, 20, Color.BLACK));
        ObjectHandler.add(new PlayerCharacter(new ID("Player Character")));
    }

    // Called Once Per Frame
    @Override
    public void update() {
        try {
            ((Text) UIRegistry.getDynamicUIXObject("FPS_Counter")).updateText("FPS: " + Engine.currentFPS);
        } catch (Exception e) {}
    }
}
