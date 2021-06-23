package me.travis.wurstplus.wurstplustwo.hacks.movement;

import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMathUtil;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;

public class ElytraFly extends WurstplusHack {

        WurstplusSetting speed = create("Speed", "ElytraFlySpeed", 1.5, 0.5, 10.0);
        WurstplusSetting upspeed = create("Up Speed", "ElytraFlyUpSpeed", 1, 0.5, 10.0);
        WurstplusSetting downspeed = create("Down Speed", "ElytraFlyDownSpeed", 1, 0.5, 10.0);
        WurstplusSetting timer = create("On Air Timer", "ElytraFlyOnAirTimer", 0.5, 0.5, 10.0);

    public ElytraFly() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);
        this.name = "Elytra Fly";
        this.tag = "ElytraFly";
        this.description = "fly with elytras";
    }

    @Override
    public void enable() {
        if (this.mc.player == null) {
            return;
        }

    }

    @Override
    public void update() {

        if(this.mc.player.onGround)
            mc.timer.tickLength = 50;
        else
        if(mc.player.isElytraFlying())
            mc.timer.tickLength = 50;
        else

                mc.timer.tickLength = 170.0f / timer.get_value(0);



        if (this.mc.player == null) {
            return;
        }
        if (this.mc.player.isElytraFlying()) {
            final double[] forwardDirectionSpeed = WurstplusMathUtil.directionSpeed(this.speed.get_value(0));
            this.mc.player.setVelocity(0.0, 0.0, 0.0);
            if (this.mc.gameSettings.keyBindJump.isKeyDown()) {
                final EntityPlayerSP player = this.mc.player;
                player.motionY += this.upspeed.get_value(0);
            }
            if (this.mc.gameSettings.keyBindSneak.isKeyDown()) {
                final EntityPlayerSP player2 = this.mc.player;
                player2.motionY -= this.downspeed.get_value(0);
            }
            if (this.mc.player.movementInput.moveStrafe != 0.0f || this.mc.player.movementInput.moveForward != 0.0f) {
                this.mc.player.motionX = forwardDirectionSpeed[0];
                this.mc.player.motionZ = forwardDirectionSpeed[1];
            }
            else {
                this.mc.player.motionX = 0.0;
                this.mc.player.motionZ = 0.0;
            }
        }
    }
}
