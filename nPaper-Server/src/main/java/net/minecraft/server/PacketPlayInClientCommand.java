package net.minecraft.server;

public class PacketPlayInClientCommand extends Packet {
    private EnumClientCommand a;

    public PacketPlayInClientCommand() {
    }

    public PacketPlayInClientCommand(EnumClientCommand var1) {
        this.a = var1;
    }

    public void a(PacketDataSerializer var1) {
        this.a = EnumClientCommand.values()[var1.readByte() % EnumClientCommand.values().length];
    }

    public void b(PacketDataSerializer var1) {
        var1.writeByte(this.a.ordinal());
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }

    public void a(PacketPlayInListener var1) {
        var1.a(this);
    }

    public EnumClientCommand c() {
        return this.a;
    }
}