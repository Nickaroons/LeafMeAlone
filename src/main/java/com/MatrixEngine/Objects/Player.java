package com.MatrixEngine.Objects;

import com.MatrixEngine.Components.Animation.AnimationSequence;
import com.MatrixEngine.Components.Animation.Animator;
import com.MatrixEngine.Components.Collider.BoxCollider;
import com.MatrixEngine.Components.Controller.TopDownController;
import com.MatrixEngine.Components.Particles.ParticleSystem;
import com.MatrixEngine.Components.Physics.Rigidbody;
import com.MatrixEngine.Components.SpriteRenderer;
import com.MatrixEngine.DataTypes.ID;
import com.MatrixEngine.DataTypes.Vector2;
import com.MatrixEngine.Camera;
import com.MatrixEngine.Engine;
import com.MatrixEngine.Networking.PacketTypes.PositionalPacket;
import com.MatrixEngine.Networking.UserClient;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.MatrixEngine.SpriteLoader.potato;
import static com.MatrixEngine.SpriteLoader.rock;

public class Player extends GameObject {

    public Player() {
        super(new ID("player"));
        componentManager.newComponent("Sprite Renderer", new SpriteRenderer(this));
        componentManager.newComponent("RigidBody", new Rigidbody(this, 100, 0));
        componentManager.newComponent("Box Collider", new BoxCollider(this, (int) width, (int) height));
        componentManager.newComponent("Controller", new TopDownController(this, 200, 5));
        componentManager.newComponent("Particle System", new ParticleSystem(true, this));
        //((SpriteRenderer)componentManager.getComponent("Sprite Renderer")).setSprite(rock);
        ((SpriteRenderer)componentManager.getComponent("Sprite Renderer")).newAnimationSequence(new AnimationSequence("idle", Animator.compileFrames(new BufferedImage[]{rock, potato}, this), 1, true, true));
        //((ParticleSystem)componentManager.getComponent("Particle System")).createEmitter(new Emitter(true, this));
        ((BoxCollider)componentManager.getComponent("Box Collider")).drawBounds(false);
        ((SpriteRenderer)componentManager.getComponent("Sprite Renderer")).startAnimationSequence("idle");
    }

    @Override
    public void start() {
        transform.position = new Vector2(150, 150);
        transform.setDrag(200);
        width = 20;
        height = 20;
        Engine.camera = new Camera(this, 250, 250);
        transform.physicsTag = "dynamic";
    }

    @Override
    public void update() {
        if (((BoxCollider)componentManager.getComponent("Box Collider")).isColliding().hasCollided()) {
            if (transform.direction() != -1) {
                GameObject obj = ((BoxCollider)componentManager.getComponent("Box Collider")).isColliding().objectCollidedWith();
                transform.convertForce(obj);
                transform.stopForce();
            }
        }
        PositionalPacket packet = new PositionalPacket();
        packet.position = new Vector2(transform.position.getX(), transform.position.getY());
        UserClient.sendPacketUDP(packet);
    }

    @Override
    public void render(Graphics g) {
    }
}
