package net.minecraft.server;

public class PacketPlayOutGameStateChange extends Packet {
    public static final String[] a = new String[]{"tile.bed.notValid", null, null, "gameMode.changed"};
    private int b;
    private float c;

    public PacketPlayOutGameStateChange() {
    }

    public PacketPlayOutGameStateChange(int var1, float var2) {
        this.b = var1;
        this.c = var2;
    }

    public void a(PacketDataSerializer var1) {
        this.b = var1.readUnsignedByte();
        this.c = var1.readFloat();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeByte(this.b);
        var1.writeFloat(this.c);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }

    public void a(PacketPlayOutListener var1) {
        var1.a(this);
    }
}
