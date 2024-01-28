package net.minecraft.server;

public class PacketPlayOutUpdateTime extends Packet {
    private long a;
    private long b;

    public PacketPlayOutUpdateTime() {
    }

    public PacketPlayOutUpdateTime(long var1, long var3, boolean var5) {
        this.a = var1;
        this.b = var3;
        if (!var5) {
            this.b = -this.b;
            if (this.b == 0L) {
                this.b = -1L;
            }
        }

    }

    public void a(PacketDataSerializer var1) {
        this.a = var1.readLong();
        this.b = var1.readLong();
    }

    public void b(PacketDataSerializer var1) {
        var1.writeLong(this.a);
        var1.writeLong(this.b);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }

    public void a(PacketPlayOutListener var1) {
        var1.a(this);
    }

    public String b() {
        return String.format("time=%d,dtime=%d", this.a, this.b);
    }
}
