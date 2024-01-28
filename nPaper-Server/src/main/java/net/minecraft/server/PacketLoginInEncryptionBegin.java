package net.minecraft.server;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.security.PrivateKey;
import javax.crypto.SecretKey;

public class PacketLoginInEncryptionBegin extends Packet {
    private byte[] a = new byte[0];
    private byte[] b = new byte[0];

    public PacketLoginInEncryptionBegin() {
    }

    public void a(PacketDataSerializer var1) {
        try {
            this.a = a((ByteBuf) var1);
            this.b = a((ByteBuf)var1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void b(PacketDataSerializer var1) {
        a(var1, this.a);
        a(var1, this.b);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketLoginInListener) packetlistener);
    }


    public void a(PacketLoginInListener var1) {
        var1.a(this);
    }

    public SecretKey a(PrivateKey var1) {
        return MinecraftEncryption.a(var1, this.a);
    }

    public byte[] b(PrivateKey var1) {
        return var1 == null ? this.b : MinecraftEncryption.b(var1, this.b);
    }
}
