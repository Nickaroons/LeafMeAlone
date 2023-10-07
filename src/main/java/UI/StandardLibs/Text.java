package UI.StandardLibs;

import java.awt.*;

public class Text extends DynamicUIX {
    protected int x, y;
    protected String text;
    protected Color color;

    public Text(String UIXID, String text, int x, int y, Color color) {
        super(UIXID);
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
    }

    @Override
    public void update() {

    }

    public void updateText(String text) {
        this.text = text;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawString(text, x, y);
    }

}
