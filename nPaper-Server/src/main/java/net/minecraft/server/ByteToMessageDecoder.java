package net.minecraft.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.util.internal.RecyclableArrayList;
import io.netty.util.internal.StringUtil;

import java.util.List;

public abstract class ByteToMessageDecoder extends ChannelHandlerAdapter {
    ByteBuf cumulation;
    private boolean singleDecode;
    private boolean decodeWasNull;

    protected ByteToMessageDecoder() {
        if (this.getClass().isAnnotationPresent(Sharable.class)) {
            throw new IllegalStateException("@Sharable annotation is not allowed");
        }
    }

    public void setSingleDecode(boolean singleDecode) {
        this.singleDecode = singleDecode;
    }

    public boolean isSingleDecode() {
        return this.singleDecode;
    }

    protected int actualReadableBytes() {
        return this.internalBuffer().readableBytes();
    }

    protected ByteBuf internalBuffer() {
        return this.cumulation != null ? this.cumulation : Unpooled.EMPTY_BUFFER;
    }

    public final void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = this.internalBuffer();
        int readable = buf.readableBytes();
        if (buf.isReadable()) {
            ByteBuf bytes = buf.readBytes(readable);
            buf.release();
            ctx.fireChannelRead(bytes);
        }

        this.cumulation = null;
        ctx.fireChannelReadComplete();
        this.handlerRemoved0(ctx);
    }

    protected void handlerRemoved0(ChannelHandlerContext ctx) throws Exception {
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RecyclableArrayList out = RecyclableArrayList.newInstance();
        boolean var15 = false;

        try {
            var15 = true;
            if (msg instanceof ByteBuf) {
                ByteBuf data = (ByteBuf)msg;
                if (this.cumulation == null) {
                    this.cumulation = data;

                    try {
                        this.callDecode(ctx, this.cumulation, out);
                    } finally {
                        if (this.cumulation != null && !this.cumulation.isReadable()) {
                            this.cumulation.release();
                            this.cumulation = null;
                        }

                    }

                    var15 = false;
                } else {
                    try {
                        if (this.cumulation.writerIndex() > this.cumulation.maxCapacity() - data.readableBytes()) {
                            ByteBuf oldCumulation = this.cumulation;
                            this.cumulation = ctx.alloc().buffer(oldCumulation.readableBytes() + data.readableBytes());
                            this.cumulation.writeBytes(oldCumulation);
                            oldCumulation.release();
                        }

                        this.cumulation.writeBytes(data);
                        this.callDecode(ctx, this.cumulation, out);
                    } finally {
                        if (this.cumulation != null) {
                            if (!this.cumulation.isReadable()) {
                                this.cumulation.release();
                                this.cumulation = null;
                            } else {
                                this.cumulation.discardSomeReadBytes();
                            }
                        }

                        data.release();
                    }

                    var15 = false;
                }
            } else {
                out.add(msg);
                var15 = false;
            }
        } catch (DecoderException var28) {
            throw var28;
        } catch (Throwable var29) {
            throw new DecoderException(var29);
        } finally {
            if (var15) {
                int size = out.size();
                if (size == 0) {
                    this.decodeWasNull = true;
                } else {
                    for(int i = 0; i < size; ++i) {
                        ctx.fireChannelRead(out.get(i));
                    }
                }

                out.recycle();
            }
        }

        int size = out.size();
        if (size == 0) {
            this.decodeWasNull = true;
        } else {
            for(int i = 0; i < size; ++i) {
                ctx.fireChannelRead(out.get(i));
            }
        }

        out.recycle();
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        if (this.decodeWasNull) {
            this.decodeWasNull = false;
            if (!ctx.channel().config().isAutoRead()) {
                ctx.read();
            }
        }

        ctx.fireChannelReadComplete();
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        RecyclableArrayList out = RecyclableArrayList.newInstance();
        boolean var11 = false;

        try {
            var11 = true;
            if (this.cumulation != null) {
                this.callDecode(ctx, this.cumulation, out);
                this.decodeLast(ctx, this.cumulation, out);
                var11 = false;
            } else {
                this.decodeLast(ctx, Unpooled.EMPTY_BUFFER, out);
                var11 = false;
            }
        } catch (DecoderException var12) {
            throw var12;
        } catch (Exception var13) {
            throw new DecoderException(var13);
        } finally {
            if (var11) {
                if (this.cumulation != null) {
                    this.cumulation.release();
                    this.cumulation = null;
                }

                int size = out.size();

                for(int i = 0; i < size; ++i) {
                    ctx.fireChannelRead(out.get(i));
                }

                ctx.fireChannelInactive();
                out.recycle();
            }
        }

        if (this.cumulation != null) {
            this.cumulation.release();
            this.cumulation = null;
        }

        int size = out.size();

        for(int i = 0; i < size; ++i) {
            ctx.fireChannelRead(out.get(i));
        }

        ctx.fireChannelInactive();
        out.recycle();
    }

    protected void callDecode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        try {
            while(true) {
                if (in.isReadable()) {
                    int outSize = out.size();
                    int oldInputLength = in.readableBytes();
                    this.decode(ctx, in, out);
                    if (!ctx.isRemoved()) {
                        if (outSize == out.size()) {
                            if (oldInputLength != in.readableBytes()) {
                                continue;
                            }
                        } else {
                            if (oldInputLength == in.readableBytes()) {
                                throw new DecoderException(StringUtil.simpleClassName(this.getClass()) + ".decode() did not read anything but decoded a message.");
                            }

                            if (!this.isSingleDecode()) {
                                continue;
                            }
                        }
                    }
                }

                return;
            }
        } catch (DecoderException var6) {
            throw var6;
        } catch (Throwable var7) {
            throw new DecoderException(var7);
        }
    }

    protected abstract void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws Exception;

    protected void decodeLast(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        this.decode(ctx, in, out);
    }
}
