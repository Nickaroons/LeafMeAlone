package com.MatrixEngine.Components.Particles;

import com.MatrixEngine.Components.StandardLibs.Component;
import com.MatrixEngine.Objects.GameObject;

import java.awt.*;
import java.util.ArrayList;

public class ParticleSystem extends Component {

    private ArrayList<Emitter> emitters = new ArrayList<>();

    public ParticleSystem(boolean renderBeforeObject, GameObject attachedObj) {
        super(renderBeforeObject, attachedObj);
    }

    public void createEmitter(Emitter emitter) {
        emitters.add(emitter);
    }

    @Override
    public void update() {
        for (Emitter emitter : emitters) {
            emitter.update();
        }
    }

    @Override
    public void render(Graphics g) {
        for (Emitter emitter : emitters) {
            emitter.render(g);
        }
    }
}
