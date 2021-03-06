//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.travis.wurstplus.wurstplustwo.hacks.movement;

import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventPacket;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusEntityUtil;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.entity.Entity;
import java.util.function.Predicate;
import net.minecraft.network.play.client.CPacketPlayer;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;


public class NoFall extends WurstplusHack
{
    WurstplusSetting packet;
    WurstplusSetting bucket;
    WurstplusSetting distance;
    private long last;
    @EventHandler
    public Listener<WurstplusEventPacket.SendPacket> sendListener;
    
    public NoFall() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);
        this.packet = this.create("Packet", "NoFallPacket", true);
        this.bucket = this.create("Bucket", "NoFallBucket", false);
        this.distance = this.create("BucketDistance", "NoFallDistance", 15, 0, 100);
        this.last = 0L;
        this.sendListener = new Listener<WurstplusEventPacket.SendPacket>(event -> {
            if (Wurstplus.get_module_manager().get_module_with_tag("ElytraFly").is_active()) {
                return;
            }
            else {
                if (event.get_packet() instanceof CPacketPlayer && this.packet.get_value(true)) {
                    ((CPacketPlayer)event.get_packet()).onGround = true;
                }
                return;
            }
        }, (Predicate<WurstplusEventPacket.SendPacket>[])new Predicate[0]);
        this.name = "No Fall";
        this.tag = "NoFall";
        this.description = "MrCoffee404";
    }
    
    @Override
    public void update() {
        if (Wurstplus.get_module_manager().get_module_with_tag("ElytraFly").is_active()) {
            return;
        }
        if (this.bucket.get_value(true) && mc.player.fallDistance >= this.distance.get_value(15) && !WurstplusEntityUtil.isLiving((Entity)mc.player) && System.currentTimeMillis() - this.last > 100L) {
            final Vec3d posVec = mc.player.getPositionVector();
            final RayTraceResult result = mc.world.rayTraceBlocks(posVec, posVec.add(0.0, -5.329999923706055, 0.0), true, true, false);
            if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
                EnumHand hand = EnumHand.MAIN_HAND;
                if (NoFall.mc.player.getHeldItemOffhand().getItem() == Items.WATER_BUCKET) {
                    hand = EnumHand.OFF_HAND;
                }
                else if (NoFall.mc.player.getHeldItemMainhand().getItem() != Items.WATER_BUCKET) {
                    for (int i = 0; i < 9; ++i) {
                        if (NoFall.mc.player.inventory.getStackInSlot(i).getItem() == Items.WATER_BUCKET) {
                            NoFall.mc.player.inventory.currentItem = i;
                            NoFall.mc.player.rotationPitch = 90.0f;
                            this.last = System.currentTimeMillis();
                            return;
                        }
                    }
                    return;
                }
                NoFall.mc.player.rotationPitch = 90.0f;
                NoFall.mc.playerController.processRightClick((EntityPlayer)NoFall.mc.player, (World)NoFall.mc.world, hand);
                this.last = System.currentTimeMillis();
            }
        }
    }
}
