package com.MatrixEngine.UI.StandardLibs;

import java.awt.*;

public interface UIX {
    public String UIXID = null;
    // called once per frame
    public void update();

    // called once per frame
    public void draw(Graphics g);
}
