package com.MatrixEngine.Components;

import com.MatrixEngine.Components.Animation.AnimationSequence;
import com.MatrixEngine.Components.Animation.Animator;
import com.MatrixEngine.Components.StandardLibs.Component;
import com.MatrixEngine.DataTypes.ObjectShape;
import com.MatrixEngine.DataTypes.Sprite;
import com.MatrixEngine.Objects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRenderer extends Component {
    private Sprite sprite = new Sprite(ObjectShape.RECTANGLE, attachedObj, Color.black);
    private Animator animator = null;

    public SpriteRenderer (GameObject attachedObject) {
        super(false, attachedObject);
    }

    public void setDefaultShape(ObjectShape shape, Color color) {
        sprite = new Sprite(shape, attachedObj, color);
    }
    public void setSprite(BufferedImage sprite) {
        this.sprite = new Sprite(sprite, attachedObj);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void newAnimationSequence(AnimationSequence sequence) {
        if (animator == null) {animator = new Animator();}
        animator.newSequence(sequence);
    }

    public void startAnimationSequence(String name) {
        if (animator == null) {animator = new Animator();}
        animator.queueSequence(name);
    }


    public void activateAnimator() {
            if (animator == null) {animator = new Animator();}
    }

    @Override
    public void update() {
        super.update();
        if (animator != null) {
            animator.update();
        }
    }

    @Override
    public void render(Graphics g) {
        if (animator == null || animator.getSequenceFrame() == null) {
            sprite.draw(g);
        } else {
            animator.getSequenceFrame().draw(g);
        }
    }

    public Sprite getSpriteFrame() {
        if (animator == null || animator.getSequenceFrame() == null) {
            return sprite;
        } else {
            return animator.getSequenceFrame();
        }
    }
}
