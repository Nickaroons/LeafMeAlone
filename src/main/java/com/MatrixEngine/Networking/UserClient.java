package com.MatrixEngine.Networking;

import com.MatrixEngine.Networking.PacketTypes.Packet;
import com.MatrixEngine.Networking.PacketTypes.PositionalPacket;
import com.MatrixEngine.ObjectHandler;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class UserClient {

    private static Client client;

    public static void connectToServer(String ipAddress, int tcpPort, int udpPort) throws IOException {
        client = new Client();
        client.start();
        client.connect(1000, ipAddress, tcpPort, udpPort);
        registerData(Packet.class);
        registerData(PositionalPacket.class);
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
        if (object instanceof PositionalPacket) {
            PositionalPacket packet = (PositionalPacket) object;
            ObjectHandler.search("BOX").transform.position = packet.position;
        }
    }

    public static void sendPacketTCP(Packet packet) {
        client.sendTCP(packet);
    }

    public static void sendPacketUDP(Packet packet) {
        client.sendUDP(packet);
    }

}
