package com.MatrixEngine.Objects;

import com.MatrixEngine.Components.Controller.TopDownController;
import com.MatrixEngine.Components.Physics.Rigidbody;
import com.MatrixEngine.Components.SpriteRenderer;
import com.MatrixEngine.DataTypes.ID;
import com.MatrixEngine.DataTypes.Vector2;
import com.MatrixEngine.Networking.PacketTypes.PlayerPacket;
import com.MatrixEngine.Networking.UserClient;
import com.MatrixEngine.SpriteLoader;

import static com.MatrixEngine.SpriteLoader.player;

public class OtherPlayerCharacter extends GameObject{

    public OtherPlayerCharacter(ID referenceID) {
        super(referenceID);
        componentManager.newComponent("Sprite Renderer", new SpriteRenderer(this));
        componentManager.newComponent("RigidBody", new Rigidbody(this, 100, 0));
        ((SpriteRenderer) componentManager.getComponent("Sprite Renderer")).setSprite(player);
    }

    @Override
    public void start() {
        transform.position = new Vector2(150, 150);
        transform.setDrag(2000);
        width = 20;
        height = 20;
        //Engine.camera = new Camera(this, 250, 250);
        transform.physicsTag = "dynamic";
    }

    @Override
    public void update() {
    }
}
