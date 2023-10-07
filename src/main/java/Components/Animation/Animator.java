package Components.Animation;

import Components.StandardLibs.Component;
import DataTypes.ObjectShape;
import DataTypes.Sprite;
import Objects.GameObject;
import Objects.Player;

import javax.sound.midi.Sequence;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static com.MatrixEngine.SpriteLoader.potato;
import static com.MatrixEngine.SpriteLoader.rock;

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
