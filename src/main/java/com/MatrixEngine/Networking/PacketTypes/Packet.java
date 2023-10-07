package com.MatrixEngine.Networking.PacketTypes;

public class Packet {
    public String message;
    public long sentTime;

    public Packet() {
        sentTime = System.currentTimeMillis();
    }
}
