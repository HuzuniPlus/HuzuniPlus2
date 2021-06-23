package me.travis.wurstplus;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordRPC;
import net.minecraft.client.Minecraft;

public class WarriorRPC
{
    private static final String ClientId = "728052357228658742";
    private static final Minecraft mc;
    private static final DiscordRPC rpc;
    public static DiscordRichPresence presence;
    private static String details;
    private static String state;
    
    public static void init() {
        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + String.valueOf(var1) + ", var2: " + var2));
        WarriorRPC.rpc.Discord_Initialize("728052357228658742", handlers, true, "");
        WarriorRPC.presence.startTimestamp = System.currentTimeMillis() / 1000L;
        WarriorRPC.presence.details = "Pou owns u discord.gg/pou";
        WarriorRPC.presence.state = "AFK on Main Menu";
        WarriorRPC.presence.largeImageKey = "logo";
        WarriorRPC.presence.largeImageText = Wurstplus.HUZUNI;
        WarriorRPC.rpc.Discord_UpdatePresence(WarriorRPC.presence);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                	WarriorRPC.rpc.Discord_RunCallbacks();
                	WarriorRPC.details = "Pou owns u discord.gg/pou";
                	WarriorRPC.state = "";
                    if (WarriorRPC.mc.isIntegratedServerRunning()) {
                    	WarriorRPC.state = "Playing on Singleplayer";
                    }
                    else if (WarriorRPC.mc.getCurrentServerData() != null) {
                        if (!WarriorRPC.mc.getCurrentServerData().serverIP.equals("")) {
                        	WarriorRPC.state = "Playing on " + WarriorRPC.mc.getCurrentServerData().serverIP;
                        }
                    }
                    else {
                    	WarriorRPC.state = "AFK on Main Menu";
                    }
                    if (!WarriorRPC.details.equals(WarriorRPC.presence.details) || !WarriorRPC.state.equals(WarriorRPC.presence.state)) {
                    	WarriorRPC.presence.startTimestamp = System.currentTimeMillis() / 1000L;
                    }
                    WarriorRPC.presence.details = WarriorRPC.details;
                    WarriorRPC.presence.state = WarriorRPC.state;
                    WarriorRPC.rpc.Discord_UpdatePresence(WarriorRPC.presence);
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
            }
        }, "Discord-RPC-Callback-Handler").start();
    }
    
    static {
        mc = Minecraft.getMinecraft();
        rpc = DiscordRPC.INSTANCE;
        WarriorRPC.presence = new DiscordRichPresence();
    }
    public static void shutdown() {
        rpc.Discord_Shutdown();
    }
}

