package me.travis.wurstplus.wurstplustwo.hacks.dev;

import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventPacket.SendPacket;
import cf.warriorcrystal.other.phobos.UpdateWalkingPlayerEvent;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
// in collab with ! Beat#7777
public class GodMode extends WurstplusHack {
    public GodMode(){
        super(WurstplusCategory.WURSTPLUS_EXPLOIT);
        this.name = "GodMode";// from phobos LOL A PHOBOS MODULE
        this.tag = "GodMode";
        this.description = "modo dios activado Bv";
}
        public Minecraft mc = Minecraft.getMinecraft();
        Entity entity;
        WurstplusSetting remount = create("Remount", "Remount", false);

    @Override
    public void enable() {
        super.enable();
        if (mc.world != null && mc.player.getRidingEntity() != null) {
            entity = mc.player.getRidingEntity();
            mc.renderGlobal.loadRenderers();
            hideEntity();
            mc.player.setPosition((double)mc.player.getPosition().getX(), (double)(mc.player.getPosition().getY() - 1), (double)mc.player.getPosition().getZ());
        }
        if (mc.world != null && remount.get_value(true)) {
            remount.set_value(false);
        }
    }

    @Override
    public void disable() {
        super.disable();
        if (remount.get_value(true)) {
            remount.set_value(false);
        }
        mc.player.dismountRidingEntity();
        mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.START_SNEAKING));
        mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
    }

    @SubscribeEvent
    public void onPacketSend(SendPacket event) {
        if (event.get_packet() instanceof CPacketPlayer.Position || event.get_packet() instanceof CPacketPlayer.PositionRotation) {
            event.cancel();
        }
    }

    private void hideEntity() {
        if (mc.player.getRidingEntity() != null) {
            mc.player.dismountRidingEntity();
            mc.world.removeEntity(entity);
        }
    }

    private void showEntity(Entity entity2) {
        entity2.isDead = false;
        mc.world.loadedEntityList.add(entity2);
        mc.player.startRiding(entity2, true);
    }

    @SubscribeEvent
    public void onPlayerWalkingUpdate(UpdateWalkingPlayerEvent event) {
        if (entity == null) {
            return;
        }
        if (event.getStage() == 0) {//                           idk, the original include this
            if (remount.get_value(true) && Wurstplus.get_module_manager().get_module_with_tag("GodMode").is_active()) {
                showEntity(entity);
            }
            entity.setPositionAndRotation(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ, Minecraft.getMinecraft().player.rotationYaw, Minecraft.getMinecraft().player.rotationPitch);
            mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(mc.player.rotationYaw, mc.player.rotationPitch, true));
            mc.player.connection.sendPacket((Packet)new CPacketInput(mc.player.movementInput.moveForward, mc.player.movementInput.moveStrafe, false, false));
            mc.player.connection.sendPacket((Packet)new CPacketVehicleMove(entity));
        }
    }
}