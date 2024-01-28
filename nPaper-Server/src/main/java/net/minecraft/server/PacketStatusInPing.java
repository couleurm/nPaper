package net.minecraft.server;

public class PacketStatusInPing extends Packet {
    private long a;

    public PacketStatusInPing() {
    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.readLong();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeLong(this.a);
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

    public long c() {
        return this.a;
    }
}
