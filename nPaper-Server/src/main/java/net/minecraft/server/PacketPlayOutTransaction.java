package net.minecraft.server;

public class PacketPlayOutTransaction extends Packet {
    private int a;
    private short b;
    private boolean c;

    public PacketPlayOutTransaction() {
    }

    public PacketPlayOutTransaction(int var1, short var2, boolean var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public void a(PacketPlayOutListener var1) {
        var1.a(this);
    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.readUnsignedByte();
        this.b = var1.readShort();
        this.c = var1.readBoolean();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeByte(this.a);
        var1.writeShort(this.b);
        var1.writeBoolean(this.c);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }

    public String b() {
        return String.format("id=%d, uid=%d, accepted=%b", this.a, this.b, this.c);
    }
}
