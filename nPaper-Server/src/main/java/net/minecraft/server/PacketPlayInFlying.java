package net.minecraft.server;

public class PacketPlayInFlying extends Packet {
    protected double x;
    protected double y;
    protected double z;
    protected double stance;
    protected float yaw;
    protected float pitch;
    protected boolean g;
    protected boolean hasPos;
    protected boolean hasLook;

    public PacketPlayInFlying() {
    }

    public void a(PacketPlayInListener var1) {
        var1.a(this);
    }

    public void a(PacketDataSerializer var1) {
        this.g = var1.readUnsignedByte() != 0;
    }

    public void b(PacketDataSerializer var1) {
        var1.writeByte(this.g ? 1 : 0);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }

    public double c() {
        return this.x;
    }

    public double d() {
        return this.y;
    }

    public double e() {
        return this.z;
    }

    public double f() {
        return this.stance;
    }

    public float g() {
        return this.yaw;
    }

    public float h() {
        return this.pitch;
    }

    public boolean i() {
        return this.g;
    }

    public boolean j() {
        return this.hasPos;
    }

    public boolean k() {
        return this.hasLook;
    }

    public void a(boolean var1) {
        this.hasPos = var1;
    }
}
