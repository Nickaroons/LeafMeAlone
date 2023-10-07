package com.MatrixEngine.Scenes;

import com.MatrixEngine.Networking.UserClient;
import com.MatrixEngine.UI.ConnectionButton;
import com.MatrixEngine.UI.StandardLibs.Button;
import com.MatrixEngine.UIRegistry;

import java.awt.*;
import java.io.IOException;

public class ConnectionScene implements Scene{
    @Override
    public void load() {
        UIRegistry.add(new ConnectionButton("Connect.", 200, 200, 50, 30, Color.GREEN, Color.BLACK));
    }

    @Override
    public void update() {

    }
}
