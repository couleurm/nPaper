package net.minecraft.server;

public class PacketStatusInStart extends Packet {
    public PacketStatusInStart() {
    }

    public void a(PacketDataSerializer var1) {
    }

    public void b(PacketDataSerializer var1) {
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketStatusInListener) packetlistener);
    }

    public void a(PacketStatusInListener var1) {
        var1.a(this);
    }

    public boolean a() {
        return true;
    }
}
