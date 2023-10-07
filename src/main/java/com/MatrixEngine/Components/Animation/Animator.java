package com.MatrixEngine.Components.Animation;

import com.MatrixEngine.DataTypes.Sprite;
import com.MatrixEngine.Objects.GameObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator {
    ArrayList<AnimationSequence> sequences = new ArrayList<>();
    AnimationSequence currentSequence;

    public Sprite getSequenceFrame() {
        if (currentSequence != null) {
            return currentSequence.animationFrame();
        } else {
            return null;
        }
    }

    public void newSequence(AnimationSequence sequence) {
        sequences.add(sequence);
    }

    public void queueSequence(String name) {
        for (AnimationSequence s : sequences) {
            if (s.getName().equals(name)) {
                currentSequence = s;
                return;
            }
        }
    }

    public void update() {
        currentSequence.animate();
    }

    public static Sprite[] compileFrames(BufferedImage[] images, GameObject attachedObj) {
        ArrayList<Sprite> sprites = new ArrayList<>();
        for (BufferedImage i : images) {
            sprites.add(new Sprite(i, attachedObj));
        }
        return sprites.toArray(new Sprite[0]);
    }
}
