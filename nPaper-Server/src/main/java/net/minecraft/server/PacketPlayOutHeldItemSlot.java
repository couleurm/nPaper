package net.minecraft.server;

public class PacketPlayOutHeldItemSlot extends Packet {
    private int a;

    public PacketPlayOutHeldItemSlot() {
    }

    public PacketPlayOutHeldItemSlot(int var1) {
        this.a = var1;
    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.readByte();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeByte(this.a);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }

    public void a(PacketPlayOutListener var1) {
        var1.a(this);
    }
}
