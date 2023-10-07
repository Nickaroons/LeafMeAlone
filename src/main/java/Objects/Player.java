package Objects;

import Components.AI.Pathfinding.AStarPathfinder;
import Components.Animation.AnimationSequence;
import Components.Animation.Animator;
import Components.Collider.BoxCollider;
import Components.Controller.TopDownController;
import Components.Particles.Emitter;
import Components.Particles.ParticleSystem;
import Components.Physics.Rigidbody;
import Components.SpriteRenderer;
import Components.StandardLibs.Component;
import DataTypes.ID;
import DataTypes.ObjectShape;
import DataTypes.Sprite;
import DataTypes.Vector2;
import com.MatrixEngine.Camera;
import com.MatrixEngine.Engine;
import com.MatrixEngine.ObjectHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;

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
    }

    @Override
    public void render(Graphics g) {
    }
}
