package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutRespawn extends Packet {
    private int a;
    private EnumDifficulty b;
    private EnumGamemode c;
    private WorldType d;

    public PacketPlayOutRespawn() {
    }

    public PacketPlayOutRespawn(int var1, EnumDifficulty var2, WorldType var3, EnumGamemode var4) {
        this.a = var1;
        this.b = var2;
        this.c = var4;
        this.d = var3;
    }

    public void a(PacketPlayOutListener var1) {
        var1.a(this);
    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.readInt();
        this.b = EnumDifficulty.getById(var1.readUnsignedByte());
        this.c = EnumGamemode.getById(var1.readUnsignedByte());
        try {
            this.d = WorldType.getType(var1.c(16));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.d == null) {
            this.d = WorldType.NORMAL;
        }

    }

    public void b(PacketDataSerializer var1) {
        var1.writeInt(this.a);
        var1.writeByte(this.b.a());
        var1.writeByte(this.c.getId());
        try {
            var1.a(this.d.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
