package com.MatrixEngine.Networking.Server;

import com.MatrixEngine.Components.Controller.TopDownController;
import com.MatrixEngine.Components.SpriteRenderer;
import com.MatrixEngine.Components.StandardLibs.ComponentManager;
import com.MatrixEngine.Components.StandardLibs.Transform;
import com.MatrixEngine.DataTypes.ID;
import com.MatrixEngine.DataTypes.Sprite;
import com.MatrixEngine.DataTypes.Vector2;
import com.MatrixEngine.Networking.PacketTypes.Packet;
import com.MatrixEngine.Networking.PacketTypes.PlayerPacket;
import com.MatrixEngine.Objects.PlayerCharacter;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.IOException;
import java.util.HashMap;

public class GameServer {
    static Server server;
    public static void main(String[] args) throws IOException {
        server = new Server();
        server.start();
        server.bind(54555, 54777);

        server.getKryo().register(Packet.class);
        registerData(new Class[]{PlayerPacket.class, Vector2.class, Sprite.class, PlayerCharacter.class, ComponentManager.class, SpriteRenderer.class, Transform.class, HashMap.class, String.class, ID.class});
        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof Packet) {
                    server.sendToAllExceptTCP(connection.getID(), object);
                }
            }
        });
    }

    public static void registerData(Class c) {
        server.getKryo().register(c);
    }

    public static void registerData(Class[] c) {
        for (Class dataClass : c ) {
            server.getKryo().register(dataClass);
        }
    }

}
