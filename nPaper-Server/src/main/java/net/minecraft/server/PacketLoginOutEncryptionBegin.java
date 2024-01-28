package net.minecraft.server;


import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.security.PublicKey;

public class PacketLoginOutEncryptionBegin extends Packet {
    private String a;
    private PublicKey b;
    private byte[] c;

    public PacketLoginOutEncryptionBegin() {
    }

    public PacketLoginOutEncryptionBegin(String var1, PublicKey var2, byte[] var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public void a(PacketDataSerializer var1) {
        try {
            this.a = var1.c(20);
            this.b = MinecraftEncryption.a(a((ByteBuf)var1));
            this.c = a((ByteBuf) var1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void b(PacketDataSerializer var1) {
        try {
            var1.a(this.a);
        } catch (IOException e) {
            e.printStackTrace();
        }
        a(var1, this.b.getEncoded());
        a(var1, this.c);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketLoginOutListener) packetlistener);
    }

    public void a(PacketLoginOutListener var1) {
        var1.a(this);
    }
}
