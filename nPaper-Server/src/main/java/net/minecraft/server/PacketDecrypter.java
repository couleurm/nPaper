package net.minecraft.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import javax.crypto.Cipher;
import java.util.List;

public class PacketDecrypter extends MessageToMessageDecoder<ByteBuf> {
    private final PacketEncryptionHandler a;

    public PacketDecrypter(Cipher var1) {
        this.a = new PacketEncryptionHandler(var1);
    }
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf obj, List out) {
        out.add(this.a.a(ctx, obj));
    }
}
