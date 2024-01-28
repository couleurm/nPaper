package net.minecraft.server;

import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.GenericFutureListener;

class QueuedProtocolSwitch implements Runnable {
    private final NetworkManager e;
    private final EnumProtocol a;
    private final EnumProtocol b;
    private final Packet c;
    private final GenericFutureListener[] d;
    QueuedProtocolSwitch(NetworkManager var1, EnumProtocol var2, EnumProtocol var3, Packet var4, GenericFutureListener[] var5) {
        this.e = var1;
        this.a = var2;
        this.b = var3;
        this.c = var4;
        this.d = var5;
    }

    public void run() {
        if (this.a != this.b) {
            this.e.a(this.a);
        }

        NetworkManager.a(this.e).writeAndFlush(this.c).addListeners(this.d).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }
}
