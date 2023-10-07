package com.MatrixEngine.UI;

import com.MatrixEngine.Networking.UserClient;
import com.MatrixEngine.SceneManager;
import com.MatrixEngine.Scenes.GameScene;
import com.MatrixEngine.UI.StandardLibs.Button;

import java.awt.*;
import java.io.IOException;

public class ConnectionButton extends Button {
    public ConnectionButton(int x, int y, int width, int height, Color buttonColor, Color textColor) {
        super(x, y, width, height, buttonColor, textColor);
    }

    public ConnectionButton(String text, int x, int y, int width, int height, Color buttonColor, Color textColor) {
        super(text, x, y, width, height, buttonColor, textColor);
    }

    public ConnectionButton(int x, int y) {
        super(x, y);
    }

    @Override
    public void DEFAULTACTIONONCLICK() {
        connect();
        SceneManager.loadScene(new GameScene());
    }

    private void connect() {
        try {
            UserClient.connectToServer("192.168.5.39", 54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
