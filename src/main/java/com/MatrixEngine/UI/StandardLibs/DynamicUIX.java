package com.MatrixEngine.UI.StandardLibs;

import java.awt.*;

public class DynamicUIX implements UIX{
    private String UIXID;

    public DynamicUIX (String UIXID) {
        this.UIXID = UIXID;

    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }

    public String getID() {
        return UIXID;
    }
}
