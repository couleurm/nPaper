package net.minecraft.server;

public class PacketPlayInSetCreativeSlot extends Packet {
    private int slot;
    private ItemStack b;

    public PacketPlayInSetCreativeSlot() {
    }

    public void a(PacketPlayInListener var1) {
        var1.a(this);
    }

    public void a(PacketDataSerializer var1) {
        this.slot = var1.readShort();
        this.b = var1.c();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeShort(this.slot);
        var1.a(this.b);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }

    public int c() {
        return this.slot;
    }

    public ItemStack getItemStack() {
        return this.b;
    }
}
