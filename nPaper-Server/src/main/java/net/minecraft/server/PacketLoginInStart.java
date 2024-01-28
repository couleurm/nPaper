package net.minecraft.server;

import net.minecraft.util.com.mojang.authlib.GameProfile;

import java.io.IOException;
import java.util.UUID;

public class PacketLoginInStart extends Packet {
    private GameProfile a;

    public PacketLoginInStart() {
    }

    public PacketLoginInStart(GameProfile var1) {
        this.a = var1;
    }

    public void a(PacketDataSerializer var1) {
        try {
            this.a = new GameProfile((UUID)null, var1.c(16));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void b(PacketDataSerializer var1) {
        try {
            var1.a(this.a.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketLoginInListener) packetlistener);
    }

    public void a(PacketLoginInListener var1) {
        var1.a(this);
    }

    public GameProfile c() {
        return this.a;
    }
}
