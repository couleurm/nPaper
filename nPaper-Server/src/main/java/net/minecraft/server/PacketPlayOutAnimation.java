package net.minecraft.server;

public class PacketPlayOutAnimation extends Packet {
    private int a;
    private int b;

    public PacketPlayOutAnimation() {
    }

    public PacketPlayOutAnimation(Entity var1, int var2) {
        this.a = var1.getId();
        this.b = var2;
    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.a();
        this.b = var1.readUnsignedByte();
    }

    public void b(PacketDataSerializer var1) {
        var1.b(this.a);
        var1.writeByte(this.b);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }

    public void a(PacketPlayOutListener var1) {
        var1.a(this);
    }

    public String b() {
        return String.format("id=%d, type=%d", this.a, this.b);
    }
}
