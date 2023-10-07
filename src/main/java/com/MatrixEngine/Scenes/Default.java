package com.MatrixEngine.Scenes;

import com.MatrixEngine.DataTypes.ID;
import com.MatrixEngine.DataTypes.Vector2;
import com.MatrixEngine.Networking.UserClient;
import com.MatrixEngine.Objects.Box;
import com.MatrixEngine.Objects.PlayerCharacter;
import com.MatrixEngine.Objects.TestPlayer;
import com.MatrixEngine.SceneManager;
import com.MatrixEngine.UI.StandardLibs.Text;
import com.MatrixEngine.Engine;
import com.MatrixEngine.ObjectHandler;
import com.MatrixEngine.UIRegistry;

import java.awt.*;
import java.io.IOException;

public class Default implements Scene {

    // Called Only Once
    @Override
    public void load() {
        SceneManager.loadScene(new ConnectionScene());
    }

    // Called Once Per Frame
    @Override
    public void update() {

    }
}
