package net.minecraft.server;

public class PacketPlayOutCloseWindow extends Packet {
    private int a;

    public PacketPlayOutCloseWindow() {
    }

    public PacketPlayOutCloseWindow(int var1) {
        this.a = var1;
    }

    public void a(PacketPlayOutListener var1) {
        var1.a(this);
    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.readUnsignedByte();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeByte(this.a);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
