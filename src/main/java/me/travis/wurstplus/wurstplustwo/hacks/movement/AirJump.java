package me.travis.wurstplus.wurstplustwo.hacks.movement;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.network.play.client.CPacketPlayer;

public class AirJump
        extends WurstplusHack {
    public AirJump() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);
        this.name = "Air Jump";
        this.tag = "AirJump";
        this.description = "Maybe usefull";
    }

    WurstplusSetting packet = create("Packet", "AirJumpPacket", true);


    @Override
    public void update() {
        if( packet.get_value(false)){
            mc.player.onGround = true;
    }
            else if(packet.get_value(true)) {
            mc.player.onGround = true;
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.41999998688698D, mc.player.posZ, true));
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.7531999805211997D, mc.player.posZ, true));
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.00133597911214D, mc.player.posZ, true));
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.16610926093821D, mc.player.posZ, true));
            }
        }
}



