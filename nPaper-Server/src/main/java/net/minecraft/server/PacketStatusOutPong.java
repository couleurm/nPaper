package net.minecraft.server;

public class PacketStatusOutPong extends Packet {
    private long a;

    public PacketStatusOutPong() {
    }

    public PacketStatusOutPong(long var1) {
        this.a = var1;
    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.readLong();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeLong(this.a);
    }

    @Override
    public void handle(PacketListener packetlistener) {

    }

    public void a(PacketStatusOutListener var1) {
        var1.a(this);
    }

    public boolean a() {
        return true;
    }
}
