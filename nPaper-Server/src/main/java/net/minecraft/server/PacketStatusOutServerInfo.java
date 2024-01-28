package net.minecraft.server;

import net.minecraft.util.com.google.gson.Gson;
import net.minecraft.util.com.google.gson.GsonBuilder;

import java.io.IOException;

public class PacketStatusOutServerInfo extends Packet {
    private static final Gson a = (new GsonBuilder()).registerTypeAdapter(ServerPingServerData.class, new ServerPingServerDataSerializer()).registerTypeAdapter(ServerPingPlayerSample.class, new ServerPingPlayerSampleSerializer()).registerTypeAdapter(ServerPing.class, new ServerPingSerializer()).registerTypeHierarchyAdapter(IChatBaseComponent.class, new ChatSerializer()).registerTypeHierarchyAdapter(ChatModifier.class, new ChatModifierSerializer()).registerTypeAdapterFactory(new ChatTypeAdapterFactory()).create();
    private ServerPing b;

    public PacketStatusOutServerInfo() {
    }

    public PacketStatusOutServerInfo(ServerPing var1) {
        this.b = var1;
    }

    public void a(PacketDataSerializer var1) {
        try {
            this.b = (ServerPing)a.fromJson(var1.c(32767), ServerPing.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void b(PacketDataSerializer var1) {
        try {
            var1.a(a.toJson(this.b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketStatusOutListener) packetlistener);
    }

    public void a(PacketStatusOutListener var1) {
        var1.a(this);
    }

    public boolean a() {
        return true;
    }
}
