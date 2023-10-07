package com.MatrixEngine.Scenes;

import com.MatrixEngine.Networking.PacketTypes.PositionalPacket;
import com.MatrixEngine.Networking.UserClient;
import com.MatrixEngine.Objects.Box;
import com.MatrixEngine.Objects.Player;
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
        ObjectHandler.add(new Player());
        ObjectHandler.add(new Box(200, 200));
        UIRegistry.add(new Text("FPS_Counter", "FPS: " + Engine.currentFPS, 10, 20, Color.BLACK));
        try {
            UserClient.connectToServer("192.168.5.44", 54555, 54777);
            UserClient.registerData(PositionalPacket.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Called Once Per Frame
    @Override
    public void update() {
        ((Text)UIRegistry.getDynamicUIXObject("FPS_Counter")).updateText("FPS: " + Engine.currentFPS);
    }
}
