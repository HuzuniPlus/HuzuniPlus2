package me.travis.wurstplus.wurstplustwo.hacks.misc;

import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.world.GameType;

public class ClientSideSurvival extends WurstplusHack {
    public ClientSideSurvival() {
        super(WurstplusCategory.WURSTPLUS_MISC);
        this.name = "Client Side Survival";
        this.tag = "ClientSideSurvival";
        this.description = "makes your gamemode survival in client side";
    }

    @Override
    public void update() {
        if (mc.player==null || mc.playerController==null) return;
        if(!mc.playerController.getCurrentGameType().equals(GameType.SURVIVAL)) {
            mc.playerController.setGameType(GameType.SURVIVAL);
            mc.player.setGameType(GameType.SURVIVAL);
        }
    }

    @Override
    protected void enable() {
        if (mc.player==null || mc.playerController==null) return;
        mc.playerController.setGameType(GameType.SURVIVAL);
        mc.player.setGameType(GameType.SURVIVAL);
    }
    public void disable() {
        if (mc.player==null || mc.playerController==null) return;
        if(!mc.playerController.getCurrentGameType().equals(GameType.SURVIVAL)) {
            mc.playerController.setGameType(GameType.SURVIVAL);
            mc.player.setGameType(GameType.SURVIVAL);
    }  
  }
}
