package com.MatrixEngine.Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Input extends KeyAdapter {
    private static ArrayList<Integer> activeKeys = new ArrayList<>();

    public static boolean getKeyDown(int keyCode) {
        return activeKeys.contains(keyCode);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (activeKeys.indexOf(e.getKeyCode()) == -1){
            activeKeys.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        activeKeys.remove(activeKeys.indexOf(e.getKeyCode()));
    }

}
