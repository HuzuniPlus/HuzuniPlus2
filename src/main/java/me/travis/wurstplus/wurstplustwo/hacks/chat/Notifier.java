package me.travis.wurstplus.wurstplustwo.hacks.chat;

//Imports

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;




//Module

public class Notifier extends WurstplusHack {
    
    //Strenght notifier
    private final Set<EntityPlayer> str;


    //Module Info
    public Notifier() {
        super(WurstplusCategory.WURSTPLUS_CHAT);

        this.name        = "PvP Info"; //Commands and Clickgui
        this.tag         = "PvPInfo"; //Config and Arraylist
        this.description = "alerts when someone burrows"; //Useless but normally i add this
        this.str = Collections.newSetFromMap(new WeakHashMap<EntityPlayer, Boolean>());
    }
    WurstplusSetting burrow = create("BurrowNotifier", "BurrowNotif", false);
    WurstplusSetting pearl = create("PearlNotifier", "PearlNotif", true);
    WurstplusSetting strenght = create("StrenghtNotifier", "StrenghtNotif", true);
    WurstplusSetting weakness = create("WeaknessNotifier", "WeakNotif", true);

    List<Entity> knownPlayers = new ArrayList<>();
    List<Entity> burrowedPlayers = new ArrayList<>();

    // PearlNotifier
    private Entity enderPearl;
    private boolean flag;
    private final HashMap list = new HashMap();
    //weakness alert
    private boolean hasAnnounced = false;
    
    


    @Override
    public void enable() {
        this.flag = true;
    }

    @Override
    public void update() {
        // burrow
        for (Entity entity : mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityPlayer).collect(Collectors.toList())) {
            if (!(entity instanceof EntityPlayer)){
                continue;
            }

            if (!burrowedPlayers.contains(entity) && isBurrowed(entity)) {
                burrowedPlayers.add(entity);
                WurstplusMessageUtil.send_client_message(ChatFormatting.DARK_PURPLE + "" + ChatFormatting.BOLD + "Burrow Announcer " + ChatFormatting.DARK_AQUA + " > " + ChatFormatting.RESET + entity.getName() + " burrowed!");
            }
            else if (burrowedPlayers.contains(entity) && !isBurrowed(entity)) {
                burrowedPlayers.remove(entity);
                WurstplusMessageUtil.send_client_message(ChatFormatting.DARK_PURPLE + "" + ChatFormatting.BOLD + "Burrow Announcer " + ChatFormatting.DARK_AQUA + " > " + ChatFormatting.RESET + entity.getName() + " unburrowed!");
            }}
        if(pearl.get_value(true)){
            if (mc.world != null && mc.player != null) {
            this.enderPearl = null;
            Iterator var1 = mc.world.loadedEntityList.iterator();

            while(var1.hasNext()) {
                Entity e = (Entity)var1.next();
                if (e instanceof EntityEnderPearl) {
                    this.enderPearl = e;
                    break;
                }
            }

            if (this.enderPearl == null) {
                this.flag = true;
            } else {
                EntityPlayer closestPlayer = null;
                Iterator var5 = mc.world.playerEntities.iterator();

                while(var5.hasNext()) {
                    EntityPlayer entity = (EntityPlayer)var5.next();
                    if (closestPlayer == null) {
                        closestPlayer = entity;
                    } else if (closestPlayer.getDistance(this.enderPearl) > entity.getDistance(this.enderPearl)) {
                        closestPlayer = entity;
                    }
                }

                if (closestPlayer == mc.player) {
                    this.flag = false;
                }

                if (closestPlayer != null && this.flag) {
                    String faceing = this.enderPearl.getHorizontalFacing().toString();
                    if (faceing.equals("west")) {
                        faceing = "east";
                    } else if (faceing.equals("east")) {
                        faceing = "west";
                    }

                    WurstplusMessageUtil.send_client_message(ChatFormatting.GREEN + "" + ChatFormatting.BOLD + "Pearl Notifier" + ChatFormatting.DARK_AQUA + "> " + ChatFormatting.RED + closestPlayer.getName() + ChatFormatting.WHITE + " thrown a pearl heading " + faceing);
                    this.flag = false;
                
        }}}
        // weakness
        if (weakness.get_value(true)){
            if (mc.player.isPotionActive(MobEffects.WEAKNESS) && !hasAnnounced) {
            hasAnnounced = true;
            WurstplusMessageUtil.send_client_message(ChatFormatting.GRAY + "" + ChatFormatting.BOLD + "You now have weakness");
        }
            if (!mc.player.isPotionActive(MobEffects.WEAKNESS) && hasAnnounced) {
                hasAnnounced = false;
                WurstplusMessageUtil.send_client_message(ChatFormatting.GRAY + "" + ChatFormatting.BOLD + "You no longer have weakness");
        }

    }
    // streng
    for (final EntityPlayer player : mc.world.playerEntities) {
        if (player.equals((Object)mc.player)) {
            continue;
        }
        if (player.isPotionActive(MobEffects.STRENGTH) && !this.str.contains(player)) {
            WurstplusMessageUtil.send_client_message(ChatFormatting.RED + "" + ChatFormatting.BOLD + "Strength Detect" + ChatFormatting.RESET + ChatFormatting.DARK_AQUA + " > " + ChatFormatting.RESET + player.getDisplayNameString() + " Has Strength");
            this.str.add(player);
        }
        if (!this.str.contains(player)) {
            continue;
        }
        if (player.isPotionActive(MobEffects.STRENGTH)) {
            continue;
        }
        WurstplusMessageUtil.send_client_message(ChatFormatting.RED + "" + ChatFormatting.BOLD + "Strength Detect" + ChatFormatting.RESET + ChatFormatting.DARK_AQUA + " > " + ChatFormatting.RESET + player.getDisplayNameString() + " Has Ran Out Of Strength");
        this.str.remove(player);
    }}}// end of public void update
    
    private boolean isBurrowed(Entity entity) {
        BlockPos entityPos = new BlockPos((entity.posX), entity.posY, (entity.posZ));

        if (mc.world.getBlockState(entityPos).getBlock() == Blocks.OBSIDIAN || mc.world.getBlockState(entityPos).getBlock() == Blocks.ENDER_CHEST || mc.world.getBlockState(entityPos).getBlock() == Blocks.SKULL) {
            return true;
        }

        return false;
    }



}