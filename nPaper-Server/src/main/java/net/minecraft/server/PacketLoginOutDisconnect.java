package net.minecraft.server;

import java.io.IOException;

public class PacketLoginOutDisconnect extends Packet {
    private IChatBaseComponent a;

    public PacketLoginOutDisconnect() {
    }

    public PacketLoginOutDisconnect(IChatBaseComponent var1) {
        this.a = var1;
    }

    public void a(PacketDataSerializer var1) {
        try {
            this.a = ChatSerializer.a(var1.c(32767));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void b(PacketDataSerializer var1) {
        try {
            var1.a(ChatSerializer.a(this.a));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketLoginOutListener) packetlistener);
    }

    public void a(PacketLoginOutListener var1) {
        var1.a(this);
    }

    public boolean a() {
        return true;
    }
}
