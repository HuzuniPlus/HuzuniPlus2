package me.travis.wurstplus.wurstplustwo.hacks.misc;

import me.zero.alpine.fork.listener.Listenable;
import me.travis.wurstplus.wurstplustwo.event.WurstplusEventBus;
import java.util.function.Predicate;
import net.minecraft.client.gui.GuiScreen;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;
import net.minecraft.client.gui.GuiGameOver;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.zero.alpine.fork.listener.EventHandler;
import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventGUIScreen;
import me.zero.alpine.fork.listener.Listener;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;

public class AutoRespawn extends WurstplusHack
{
    WurstplusSetting coords;
    @EventHandler
    private final Listener<WurstplusEventGUIScreen> listener;

    public AutoRespawn() {
        super(WurstplusCategory.WURSTPLUS_MISC);
        this.coords = this.create("DeathCoords", "AutoRespawnDeathCoords", true);
        this.listener = new Listener<WurstplusEventGUIScreen>(event -> {
            if (event.get_guiscreen() instanceof GuiGameOver) {
                if (this.coords.get_value(true)) {
                    WurstplusMessageUtil.send_client_message(String.format("You died at x%d y%d z%d", (int)AutoRespawn.mc.player.posX, (int)AutoRespawn.mc.player.posY, (int)AutoRespawn.mc.player.posZ));
                }
                if (AutoRespawn.mc.player != null) {
                    AutoRespawn.mc.player.respawnPlayer();
                }
                AutoRespawn.mc.displayGuiScreen(null);
            }
            return;
        }, new Predicate[0]);
        this.name = "Auto Respawn";
        this.tag = "AutoRespawn";
        this.description = "AutoRespawn";
    }

    public void enable() {
        WurstplusEventBus.EVENT_BUS.subscribe(this);
    }

    public void disable() {
        WurstplusEventBus.EVENT_BUS.unsubscribe(this);
    }
}