package me.travis.wurstplus.wurstplustwo.hacks.movement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import me.travis.wurstplus.wurstplustwo.util.TurokBlockInteractionHelper;
import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventPacket;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PacketFly extends WurstplusHack {
    WurstplusSetting fly_speed = this.create("Speed", "PhaseSpeed", 0.1f, 0.0, 5.0);
    WurstplusSetting fly_no_kick = this.create("Anti Kick", "PacketFlyNoKick", true);
    WurstplusSetting phase = this.create("Phase", " PacketFlyPhase", true);
    List<CPacketPlayer> fly_packets = new ArrayList<CPacketPlayer>();
    private int fly_teleport_id;
    @EventHandler
    public Listener<InputUpdateEvent> listener;
    @EventHandler
    public Listener<WurstplusEventPacket.SendPacket> sendListener;
    @EventHandler
    public Listener<WurstplusEventPacket.ReceivePacket> receiveListener;
    @EventHandler
    public Listener<TickEvent.ClientTickEvent> on_tick_event;

    public PacketFly() {
        super(WurstplusCategory.WURSTPLUS_EXPLOIT);
        this.name = "Packet Fly";
        this.tag = "PacketFly";
        this.description = "PacketKick";
        
        CPacketPlayer[] fly_bounds = new CPacketPlayer[1];
        double[] fly_speed_y = new double[1];
        double[] fly_speed_y_2 = new double[1];
        double[] fly_n = new double[1];
        double[][] fly_direcional_speed = new double[1][1];
        int[] fly_i = new int[1];
        int[] fly_j = new int[1];
        int[] fly_k = new int[1];
        this.listener = new Listener<InputUpdateEvent>(event -> {
            if (this.fly_teleport_id <= 0) {
                fly_bounds[0] = new CPacketPlayer.Position(Minecraft.getMinecraft().player.posX, 0.0, Minecraft.getMinecraft().player.posZ, Minecraft.getMinecraft().player.onGround);
                this.fly_packets.add(fly_bounds[0]);
                Minecraft.getMinecraft().player.connection.sendPacket((Packet)fly_bounds[0]);
            } else {
                PacketFly.mc.player.setVelocity(0.0, 0.0, 0.0);
                if (PacketFly.mc.world.getCollisionBoxes((Entity)PacketFly.mc.player, PacketFly.mc.player.getEntityBoundingBox().expand(-0.0625, 0.0, -0.0625)).isEmpty()) {
                    fly_speed_y[0] = 0.0;
                    if (PacketFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                        fly_speed_y_2[0] = this.fly_no_kick.get_value(true) ? (PacketFly.mc.player.ticksExisted % 20 == 0 ? (double)-0.04f : (double)0.062f) : (double)0.062f;
                    } else if (PacketFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        fly_speed_y_2[0] = -0.062;
                    } else {
                        fly_n[0] = PacketFly.mc.world.getCollisionBoxes((Entity)PacketFly.mc.player, PacketFly.mc.player.getEntityBoundingBox().expand(-0.0625, -0.0625, -0.0625)).isEmpty() ? (PacketFly.mc.player.ticksExisted % 4 == 0 ? (double)(this.fly_no_kick.get_value(true) ? -0.04f : 0.0f) : 0.0) : 0.0;
                        fly_speed_y_2[0] = fly_n[0];
                    }
                    fly_direcional_speed[0] = TurokBlockInteractionHelper.directionSpeed(this.fly_speed.get_value(1.0));
                    if (PacketFly.mc.gameSettings.keyBindJump.isKeyDown() || PacketFly.mc.gameSettings.keyBindSneak.isKeyDown() || PacketFly.mc.gameSettings.keyBindForward.isKeyDown() || PacketFly.mc.gameSettings.keyBindBack.isKeyDown() || PacketFly.mc.gameSettings.keyBindRight.isKeyDown() || PacketFly.mc.gameSettings.keyBindLeft.isKeyDown()) {
                        if (fly_direcional_speed[0][0] != 0.0 || fly_speed_y_2[0] != 0.0 || fly_direcional_speed[0][1] != 0.0) {
                            if (PacketFly.mc.player.movementInput.jump && (PacketFly.mc.player.moveStrafing != 0.0f || PacketFly.mc.player.moveForward != 0.0f)) {
                                PacketFly.mc.player.setVelocity(0.0, 0.0, 0.0);
                                this.move(0.0, 0.0, 0.0);
                                fly_i[0] = 0;
                                while (fly_i[0] <= 3) {
                                    PacketFly.mc.player.setVelocity(0.0, fly_speed_y_2[0] * (double)fly_i[0], 0.0);
                                    this.move(0.0, fly_speed_y_2[0] * (double)fly_i[0], 0.0);
                                    fly_i[0] = fly_i[0] + 1;
                                }
                            } else if (PacketFly.mc.player.movementInput.jump) {
                                PacketFly.mc.player.setVelocity(0.0, 0.0, 0.0);
                                this.move(0.0, 0.0, 0.0);
                                fly_j[0] = 0;
                                while (fly_j[0] <= 3) {
                                    PacketFly.mc.player.setVelocity(0.0, fly_speed_y_2[0] * (double)fly_j[0], 0.0);
                                    this.move(0.0, fly_speed_y_2[0] * (double)fly_j[0], 0.0);
                                    fly_j[0] = fly_j[0] + 1;
                                }
                            } else {
                                fly_k[0] = 0;
                                while (fly_k[0] <= 2) {
                                    PacketFly.mc.player.setVelocity(fly_direcional_speed[0][0] * (double)fly_k[0], fly_speed_y_2[0] * (double)fly_k[0], fly_direcional_speed[0][1] * (double)fly_k[0]);
                                    this.move(fly_direcional_speed[0][0] * (double)fly_k[0], fly_speed_y_2[0] * (double)fly_k[0], fly_direcional_speed[0][1] * (double)fly_k[0]);
                                    fly_k[0] = fly_k[0] + 1;
                                }
                            }
                        }
                    } else if (this.fly_no_kick.get_value(true) && PacketFly.mc.world.getCollisionBoxes((Entity)PacketFly.mc.player, PacketFly.mc.player.getEntityBoundingBox().expand(-0.0625, -0.0625, -0.0625)).isEmpty()) {
                        PacketFly.mc.player.setVelocity(0.0, PacketFly.mc.player.ticksExisted % 2 == 0 ? (double)0.04f : (double)-0.04f, 0.0);
                        this.move(0.0, PacketFly.mc.player.ticksExisted % 2 == 0 ? (double)0.04f : (double)-0.04f, 0.0);
                    }
                }
            }
        }, new Predicate[0]);
        CPacketPlayer[] packet = new CPacketPlayer[1];
        this.sendListener = new Listener<WurstplusEventPacket.SendPacket>(event -> {
            if (event.get_packet() instanceof CPacketPlayer && !(event.get_packet() instanceof CPacketPlayer.Position)) {
                event.cancel();
            }
            if (event.get_packet() instanceof CPacketPlayer) {
                packet[0] = (CPacketPlayer)event.get_packet();
                if (this.fly_packets.contains((Object)packet[0])) {
                    this.fly_packets.remove((Object)packet[0]);
                } else {
                    event.cancel();
                }
            }
        }, new Predicate[0]);
        SPacketPlayerPosLook[] packet2 = new SPacketPlayerPosLook[1];
        this.receiveListener = new Listener<WurstplusEventPacket.ReceivePacket>(event -> {
            if (event.get_packet() instanceof SPacketPlayerPosLook) {
                packet2[0] = (SPacketPlayerPosLook)event.get_packet();
                if (Minecraft.getMinecraft().player.isEntityAlive() && Minecraft.getMinecraft().world.isBlockLoaded(new BlockPos(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ)) && !(Minecraft.getMinecraft().currentScreen instanceof GuiDownloadTerrain)) {
                    if (this.fly_teleport_id <= 0) {
                        this.fly_teleport_id = packet2[0].getTeleportId();
                    } else {
                        event.cancel();
                    }
                }
            }
        }, new Predicate[0]);
    }

    @Override
    public void enable() {
        if (PacketFly.mc.world != null) {
            if (this.phase.get_value(true)) {
                PacketFly.mc.player.noClip = true;
            }
            this.fly_teleport_id = 0;
            this.fly_packets.clear();
            CPacketPlayer.Position fly_bounds = new CPacketPlayer.Position(PacketFly.mc.player.posX, 0.0, PacketFly.mc.player.posZ, PacketFly.mc.player.onGround);
            this.fly_packets.add((CPacketPlayer)fly_bounds);
            PacketFly.mc.player.connection.sendPacket((Packet)fly_bounds);
        }
    }

    private void move(double x, double y, double z) {
        Minecraft mc = Minecraft.getMinecraft();
        CPacketPlayer.Position pos = new CPacketPlayer.Position(mc.player.posX + x, mc.player.posY + y, mc.player.posZ + z, mc.player.onGround);
        this.fly_packets.add((CPacketPlayer)pos);
        mc.player.connection.sendPacket((Packet)pos);
        CPacketPlayer.Position fly_bounds = new CPacketPlayer.Position(mc.player.posX + x, 0.0, mc.player.posZ + z, mc.player.onGround);
        this.fly_packets.add((CPacketPlayer)fly_bounds);
        mc.player.connection.sendPacket((Packet)fly_bounds);
        ++this.fly_teleport_id;
        mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.fly_teleport_id - 1));
        mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.fly_teleport_id));
        mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.fly_teleport_id + 1));
    }
}