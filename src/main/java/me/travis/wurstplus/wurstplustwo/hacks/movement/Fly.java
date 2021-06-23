package me.travis.wurstplus.wurstplustwo.hacks.movement;

//Imports

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;


//Module

public class Fly extends WurstplusHack {

    //Module Info
    public Fly() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);

        this.name        = "Fly"; //Commands and Clickgui
        this.tag         = "Fly"; //Config and Arraylist
        this.description = "WTF who doesn't know what is this"; //Useless but normally i add this
    }

    @Override
    public void enable() {
        mc.player.capabilities.isFlying = true;
        if (mc.player.capabilities.isCreativeMode) return;
        mc.player.capabilities.allowFlying = true;

    }

    @Override
    public void disable() {
        mc.player.capabilities.isFlying = false;
        if (mc.player.capabilities.isCreativeMode) return;
        mc.player.capabilities.allowFlying = false;
    }

}


