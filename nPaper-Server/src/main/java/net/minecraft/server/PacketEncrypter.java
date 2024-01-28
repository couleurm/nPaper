package net.minecraft.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import javax.crypto.Cipher;

public class PacketEncrypter extends MessageToByteEncoder<ByteBuf> {
    private final PacketEncryptionHandler a;

    public PacketEncrypter(Cipher var1) {
        this.a = new PacketEncryptionHandler(var1);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        this.a.a(ctx, msg);
    }
}
