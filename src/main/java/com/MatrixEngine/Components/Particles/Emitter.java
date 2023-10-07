package com.MatrixEngine.Components.Particles;

import com.MatrixEngine.Components.StandardLibs.Component;
import com.MatrixEngine.DataTypes.ID;
import com.MatrixEngine.Objects.GameObject;

import java.awt.*;
import java.util.ArrayList;

public class Emitter extends Component {
    private ArrayList<Particle> particles = new ArrayList<>();
    double spawnRate = 0.01;
    long lastSpawnTime = 0;

    public Emitter(boolean renderBeforeObject, GameObject attachedObj) {
        super(renderBeforeObject, attachedObj);
    }


    @Override
    public void update() {
        if (System.currentTimeMillis() - lastSpawnTime > spawnRate * 1000) {
            particles.add(new Particle(new ID("part2"), this, 3, Color.CYAN, Color.YELLOW, 0.1));
            lastSpawnTime = System.currentTimeMillis();
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).updateFinal();
        }
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).renderFinal(g);
        }
    }

    public void remove(Particle particle) {
        particles.remove(particle);
    }
}
