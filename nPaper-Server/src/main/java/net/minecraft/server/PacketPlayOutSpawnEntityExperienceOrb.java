package net.minecraft.server;

public class PacketPlayOutSpawnEntityExperienceOrb extends Packet {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;

    public PacketPlayOutSpawnEntityExperienceOrb() {
    }

    public PacketPlayOutSpawnEntityExperienceOrb(EntityExperienceOrb var1) {
        this.a = var1.getId();
        this.b = MathHelper.floor(var1.locX * 32.0D);
        this.c = MathHelper.floor(var1.locY * 32.0D);
        this.d = MathHelper.floor(var1.locZ * 32.0D);
        this.e = var1.e();
    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.a();
        this.b = var1.readInt();
        this.c = var1.readInt();
        this.d = var1.readInt();
        this.e = var1.readShort();
    }

    public void b(PacketDataSerializer var1) {
        var1.b(this.a);
        var1.writeInt(this.b);
        var1.writeInt(this.c);
        var1.writeInt(this.d);
        var1.writeShort(this.e);
    }

    @Override
    public void handle(PacketListener packetlistener) {

    }

    public void a(PacketPlayOutListener var1) {
        var1.a(this);
    }

    public String b() {
        return String.format("id=%d, value=%d, x=%.2f, y=%.2f, z=%.2f", this.a, this.e, (float)this.b / 32.0F, (float)this.c / 32.0F, (float)this.d / 32.0F);
    }
}

