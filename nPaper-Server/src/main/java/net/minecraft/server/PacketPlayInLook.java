package net.minecraft.server;

public class PacketPlayInLook extends PacketPlayInFlying {
    public PacketPlayInLook() {
        this.hasLook = true;
    }

    public void a(PacketDataSerializer var1) {
        this.yaw = var1.readFloat();
        this.pitch = var1.readFloat();
        super.a(var1);
    }

    public void b(PacketDataSerializer var1) {
        var1.writeFloat(this.yaw);
        var1.writeFloat(this.pitch);
        super.b(var1);
    }
}
