package me.travis.wurstplus.wurstplustwo.hacks.movement;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value={Side.CLIENT})
public class LongJump
        extends WurstplusHack {
    WurstplusSetting speed = this.create("Speed", "LGSpeed", 30.0, 1.0, 100.0);
    WurstplusSetting packet = this.create("Packet", "LGPacket", false);
    WurstplusSetting toggle = this.create("Toggle", "LGToggle", false);
    private static boolean jumped = false;
    private static boolean boostable = false;

    public LongJump() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);
        this.name = "Long Jump";
        this.tag = "LongJump";
        this.description = "jumps longer";
    }

    @Override
    public void update() {
        if (LongJump.mc.player == null || LongJump.mc.world == null) {
            return;
        }
        if (jumped) {
            if (LongJump.mc.player.onGround || LongJump.mc.player.capabilities.isFlying) {
                jumped = false;
                LongJump.mc.player.motionX = 0.0;
                LongJump.mc.player.motionZ = 0.0;
                if (this.packet.get_value(true)) {
                    LongJump.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(LongJump.mc.player.posX, LongJump.mc.player.posY, LongJump.mc.player.posZ, LongJump.mc.player.onGround));
                    LongJump.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(LongJump.mc.player.posX + LongJump.mc.player.motionX, 0.0, LongJump.mc.player.posZ + LongJump.mc.player.motionZ, LongJump.mc.player.onGround));
                }
                if (this.toggle.get_value(true)) {
                    this.toggle();
                }
                return;
            }
            if (LongJump.mc.player.movementInput.moveForward == 0.0f && LongJump.mc.player.movementInput.moveStrafe == 0.0f) {
                return;
            }
            double yaw = this.getDirection();
            LongJump.mc.player.motionX = -Math.sin(yaw) * (double)((float)Math.sqrt(LongJump.mc.player.motionX * LongJump.mc.player.motionX + LongJump.mc.player.motionZ * LongJump.mc.player.motionZ) * (boostable ? (float)this.speed.get_value(0) / 10.0f : 1.0f));
            LongJump.mc.player.motionZ = Math.cos(yaw) * (double)((float)Math.sqrt(LongJump.mc.player.motionX * LongJump.mc.player.motionX + LongJump.mc.player.motionZ * LongJump.mc.player.motionZ) * (boostable ? (float)this.speed.get_value(0) / 10.0f : 1.0f));
            boostable = false;
            if (this.toggle.get_value(true)) {
                this.toggle();
            }
        }
        if (LongJump.mc.player.movementInput.moveForward == 0.0f && LongJump.mc.player.movementInput.moveStrafe == 0.0f && jumped) {
            LongJump.mc.player.motionX = 0.0;
            LongJump.mc.player.motionZ = 0.0;
        }
    }

    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent event) {
        if (LongJump.mc.player != null && LongJump.mc.world != null && event.getEntity() == LongJump.mc.player && (LongJump.mc.player.movementInput.moveForward != 0.0f || LongJump.mc.player.movementInput.moveStrafe != 0.0f)) {
            jumped = true;
            boostable = true;
        }
    }

    private double getDirection() {
        float rotationYaw = LongJump.mc.player.rotationYaw;
        if (LongJump.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (LongJump.mc.player.moveForward < 0.0f) {
            forward = -0.5f;
        } else if (LongJump.mc.player.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (LongJump.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * forward;
        }
        if (LongJump.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * forward;
        }
        return Math.toRadians(rotationYaw);
    }
}