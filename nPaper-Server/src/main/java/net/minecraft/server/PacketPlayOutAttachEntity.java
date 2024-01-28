package net.minecraft.server;

public class PacketPlayOutAttachEntity extends Packet {
    private int a;
    private int b;
    private int c;

    public PacketPlayOutAttachEntity() {
    }

    public PacketPlayOutAttachEntity(int var1, Entity var2, Entity var3) {
        this.a = var1;
        this.b = var2.getId();
        this.c = var3 != null ? var3.getId() : -1;
    }

    public void a(PacketDataSerializer var1) {
        this.b = var1.readInt();
        this.c = var1.readInt();
        this.a = var1.readUnsignedByte();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeInt(this.b);
        var1.writeInt(this.c);
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
