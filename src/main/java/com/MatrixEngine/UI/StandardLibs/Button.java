package com.MatrixEngine.UI.StandardLibs;

import com.MatrixEngine.Input.Mouse;
import com.MatrixEngine.Engine;
import com.MatrixEngine.SceneManager;
import com.MatrixEngine.Scenes.Default;

import java.awt.*;

public class Button implements UIX {
    protected int x, y, width, height;
    protected Color buttonColor, textColor;
    protected String text;

    public Button(int x, int y, int width, int height, Color buttonColor, Color textColor) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.buttonColor = buttonColor;
        this.textColor = textColor;
    }

    public Button(String text, int x, int y, int width, int height, Color buttonColor, Color textColor) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.buttonColor = buttonColor;
        this.textColor = textColor;
    }

    public Button(int x , int y) {
        this.x = x;
        this.y = y;
    }



    @Override
    public void update() {
        if (isClicked()) {
            buttonColor = Color.darkGray;
        } else {
            buttonColor = Color.RED;
        }
    }

    public boolean isClicked() {
        if (Mouse.isDown){
            if (Mouse.getPosition().x > x && Mouse.getPosition().x < x + width &&
                    Mouse.getPosition().y > y && Mouse.getPosition().y < y + height)
                return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(buttonColor);
        g.fillRect(x, y, width, height);
        g.setColor(textColor);
        Engine.drawCenteredString(g, text, new Rectangle(x, y, width, height), new Font("Arial", 1, 12));
    }

    public void DEFAULTACTIONONCLICK() {
        text = "Clicked!";
        SceneManager.loadScene(new Default());
    }
}
