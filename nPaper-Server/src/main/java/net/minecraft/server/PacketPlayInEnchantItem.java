package net.minecraft.server;

public class PacketPlayInEnchantItem extends Packet {
    private int a;
    private int b;

    public PacketPlayInEnchantItem() {
    }

    public void a(PacketPlayInListener var1) {
        var1.a(this);
    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.readByte();
        this.b = var1.readByte();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeByte(this.a);
        var1.writeByte(this.b);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }

    public String b() {
        return String.format("id=%d, button=%d", this.a, this.b);
    }

    public int c() {
        return this.a;
    }

    public int d() {
        return this.b;
    }
}
