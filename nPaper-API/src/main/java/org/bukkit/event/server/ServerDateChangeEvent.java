package org.bukkit.event.server;

import org.bukkit.event.HandlerList;

// Rinny 
public class ServerDateChangeEvent extends ServerEvent {
	private static final HandlerList handlers = new HandlerList();
	
	@Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
