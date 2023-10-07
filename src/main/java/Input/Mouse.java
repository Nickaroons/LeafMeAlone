package Input;

import UI.StandardLibs.Button;
import UI.StandardLibs.UIX;
import com.MatrixEngine.UIRegistry;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    private static Point position = new Point();
    public static boolean isDown = false;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isDown = true;
        position = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        position = new Point(e.getX(), e.getY());
        for (UIX UIObj : UIRegistry.getObjects()) {
            if (UIObj instanceof Button) {
                Button button = (Button)UIObj;
                if (button.isClicked()) {
                    button.DEFAULTACTIONONCLICK();
                }
            }
        }
        isDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static Point getPosition() {
        return position;
    }
}
