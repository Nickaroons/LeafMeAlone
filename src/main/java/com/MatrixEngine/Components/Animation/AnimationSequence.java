package com.MatrixEngine.Components.Animation;

import com.MatrixEngine.DataTypes.Sprite;

public class AnimationSequence {
    private Sprite[] sequence;
    private String name;
    private int frameNumber = 0;
    private double sequenceSpeed = 1;
    private boolean shouldLoop = false;
    private boolean canBeInterrupted = false;
    private long lastFrameChange = System.currentTimeMillis();
    private boolean sequenceHasRun;
    public AnimationSequence(String name, Sprite[] sequence, double sequenceSpeed, boolean shouldLoop, boolean canBeInterrupted) {
        this.sequence = sequence;
        this.sequenceSpeed = sequenceSpeed;
        this.shouldLoop = shouldLoop;
        this.canBeInterrupted = canBeInterrupted;
        this.name = name;
    }

    public void animate() {
        if (System.currentTimeMillis() - lastFrameChange > sequenceSpeed * 1000) {
            if (frameNumber >= sequence.length-1) {
                if (shouldLoop){
                    frameNumber = 0;
                } else {
                    sequenceHasRun = true;
                }
                lastFrameChange = System.currentTimeMillis();
            } else {
                frameNumber++;
                lastFrameChange = System.currentTimeMillis();
            }
        }
    }

    public Sprite animationFrame() {
        if (!sequenceHasRun){
            return sequence[frameNumber];
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
