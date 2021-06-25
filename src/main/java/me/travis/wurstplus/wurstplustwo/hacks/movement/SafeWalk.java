package me.travis.wurstplus.wurstplustwo.hacks.movement;

//Imports

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.network.play.client.CPacketEntityAction;


//Module

public class SafeWalk extends WurstplusHack {

    //Module Info
    public SafeWalk() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);

        this.name        = "Safe Walk"; //Commands and Clickgui
        this.tag         = "SafeWalk"; //Config and Arraylist
        this.description = "prevents u from falling from block edges"; //Useless but normally i add this
    }

    //Module Settings
    @Override
    public void update() {
        mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SNEAKING));
    }
    @Override
    public void disable() {
        mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
    }


}
