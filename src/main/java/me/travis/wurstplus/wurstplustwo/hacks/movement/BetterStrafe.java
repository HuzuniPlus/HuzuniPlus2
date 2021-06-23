package me.travis.wurstplus.wurstplustwo.hacks.movement;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.MathHelper;

public class BetterStrafe
        extends WurstplusHack {
    int waitCounter;
    int forward;

    public BetterStrafe() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);
        this.name = "Better Strafe";
        this.tag = "BetterStrafe";
        this.description = "strafe but better";
        this.forward = 1;
    }

    WurstplusSetting autojump = this.create("AutoJump", "StrafeAutoJump", true);

    @Override
    public void update() {
        boolean boost;
        boolean bl = boost = Math.abs(BetterStrafe.mc.player.rotationYawHead - BetterStrafe.mc.player.rotationYaw) < 90.0f;
        if (BetterStrafe.mc.player.moveForward != 0.0f) {
            if (!BetterStrafe.mc.player.isSprinting()) {
                BetterStrafe.mc.player.setSprinting(true);
            }
            float yaw = BetterStrafe.mc.player.rotationYaw;
            if (BetterStrafe.mc.player.moveForward > 0.0f) {
                if (BetterStrafe.mc.player.movementInput.moveStrafe != 0.0f) {
                    yaw += BetterStrafe.mc.player.movementInput.moveStrafe > 0.0f ? -45.0f : 45.0f;
                }
                this.forward = 1;
                BetterStrafe.mc.player.moveForward = 1.0f;
                BetterStrafe.mc.player.moveStrafing = 0.0f;
            } else if (BetterStrafe.mc.player.moveForward < 0.0f) {
                if (BetterStrafe.mc.player.movementInput.moveStrafe != 0.0f) {
                    yaw += BetterStrafe.mc.player.movementInput.moveStrafe > 0.0f ? 45.0f : -45.0f;
                }
                this.forward = -1;
                BetterStrafe.mc.player.moveForward = -1.0f;
                BetterStrafe.mc.player.moveStrafing = 0.0f;
            }
            if (BetterStrafe.mc.player.onGround) {
                BetterStrafe.mc.player.setJumping(false);
                if (this.waitCounter < 1) {
                    ++this.waitCounter;
                    return;
                }
                this.waitCounter = 0;
                float f = (float)Math.toRadians(yaw);
                BetterStrafe.mc.player.motionY = 0.4;
                EntityPlayerSP player = BetterStrafe.mc.player;
                player.motionX -= (double)(MathHelper.sin((float)f) * 0.195f) * (double)this.forward;
                EntityPlayerSP player2 = BetterStrafe.mc.player;
                player2.motionZ += (double)(MathHelper.cos((float)f) * 0.195f) * (double)this.forward;
            }
        } else {
            double speed;
            if (this.waitCounter < 1) {
                ++this.waitCounter;
                return;
            }
            this.waitCounter = 0;
            double currentSpeed = Math.sqrt(BetterStrafe.mc.player.motionX * BetterStrafe.mc.player.motionX + BetterStrafe.mc.player.motionZ * BetterStrafe.mc.player.motionZ);
            double d = speed = boost ? 1.0034 : 1.001;
            if (BetterStrafe.mc.player.motionY < 0.0) {
                speed = 1.0;
            }
            double yaw = 0.0;
            double direction = Math.toRadians(yaw);
            BetterStrafe.mc.player.motionX = -Math.sin(direction) * speed * currentSpeed * (double)this.forward;
            BetterStrafe.mc.player.motionZ = Math.cos(direction) * speed * currentSpeed * (double)this.forward;
        }
    }
}
