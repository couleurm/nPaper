package net.minecraft.server;

public class PacketPlayInTransaction extends Packet {
    private int a;
    private short b;
    private boolean c;

    public PacketPlayInTransaction() {
    }

    public void a(PacketPlayInListener var1) {
        var1.a(this);
    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.readByte();
        this.b = var1.readShort();
        this.c = var1.readByte() != 0;
    }

    public void b(PacketDataSerializer var1) {
        var1.writeByte(this.a);
        var1.writeShort(this.b);
        var1.writeByte(this.c ? 1 : 0);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }

    public String b() {
        return String.format("id=%d, uid=%d, accepted=%b", this.a, this.b, this.c);
    }

    public int c() {
        return this.a;
    }

    public short d() {
        return this.b;
    }
}
