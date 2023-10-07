package com.MatrixEngine.Networking;

import com.MatrixEngine.Components.Controller.TopDownController;
import com.MatrixEngine.Components.SpriteRenderer;
import com.MatrixEngine.Components.StandardLibs.ComponentManager;
import com.MatrixEngine.Components.StandardLibs.Transform;
import com.MatrixEngine.DataTypes.ID;
import com.MatrixEngine.DataTypes.Sprite;
import com.MatrixEngine.DataTypes.Vector2;
import com.MatrixEngine.Networking.PacketTypes.Packet;
import com.MatrixEngine.Networking.PacketTypes.PlayerPacket;
import com.MatrixEngine.Networking.PacketTypes.PositionalPacket;
import com.MatrixEngine.ObjectHandler;
import com.MatrixEngine.Objects.OtherPlayerCharacter;
import com.MatrixEngine.Objects.PlayerCharacter;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.IOException;
import java.util.HashMap;

public class UserClient {

    private static Client client;

    public static void connectToServer(String ipAddress, int tcpPort, int udpPort) throws IOException {
        client = new Client();
        client.start();
        client.connect(1000, ipAddress, tcpPort, udpPort);
        registerData(Packet.class);
        registerData(new Class[]{PlayerPacket.class, Vector2.class, Sprite.class, PlayerCharacter.class, ComponentManager.class, SpriteRenderer.class, Transform.class, HashMap.class, String.class, ID.class});
        Packet connPacket = new Packet();
        connPacket.message = "new_connection";
        client.sendTCP(connPacket);
        client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
               packetReceived(connection, object);
            }
        });
    }

    public static void registerData(Class c) {
        client.getKryo().register(c);
    }

    public static void registerData(Class[] c) {
        for (Class dataClass : c ) {
            client.getKryo().register(dataClass);
        }
    }

    private static void packetReceived(Connection connection, Object object) {
        // Called when a packet from the server is received
        if (object instanceof Packet) {
            if ((((Packet)object)).message.equals("new_connection")) {
                System.out.println("New Connection!");
            }
        }
    }

    public static void sendPacketTCP(Packet packet) {
        client.sendTCP(packet);
    }

    public static void sendPacketUDP(Packet packet) {
        client.sendUDP(packet);
    }

}
