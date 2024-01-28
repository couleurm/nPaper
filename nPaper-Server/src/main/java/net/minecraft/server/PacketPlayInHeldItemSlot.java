package net.minecraft.server;

public class PacketPlayInHeldItemSlot extends Packet {
    private int itemInHandIndex;

    public PacketPlayInHeldItemSlot() {
    }

    public void a(PacketDataSerializer var1) {
        this.itemInHandIndex = var1.readShort();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeShort(this.itemInHandIndex);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }

    public void a(PacketPlayInListener var1) {
        var1.a(this);
    }

    public int c() {
        return this.itemInHandIndex;
    }
}
