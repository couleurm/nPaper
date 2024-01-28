package net.minecraft.server;

public class PacketPlayOutEntityStatus extends Packet {
    private int a;
    private byte b;

    public PacketPlayOutEntityStatus() {
    }

    public PacketPlayOutEntityStatus(Entity var1, byte var2) {
        this.a = var1.getId();
        this.b = var2;
    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.readInt();
        this.b = var1.readByte();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeInt(this.a);
        var1.writeByte(this.b);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }

    public void a(PacketPlayOutListener var1) {
        var1.a(this);
    }
}
