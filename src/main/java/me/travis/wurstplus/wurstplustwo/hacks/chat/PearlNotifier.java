package me.travis.wurstplus.wurstplustwo.hacks.chat;

//Imports

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;

import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


//Module

public class PearlNotifier extends WurstplusHack {

    private Entity enderPearl;
    private boolean flag;
    private final HashMap list = new HashMap();

    //Module Info
    public PearlNotifier() {
        super(WurstplusCategory.WURSTPLUS_CHAT);

        this.name        = "Pearl Notifier"; //Commands and Clickgui
        this.tag         = "PearlNotifier"; //Config and Arraylist
        this.description = "Notify when someones throws a pearl"; //Useless but normally i add this
    }

    public void enable() {
        this.flag = true;
    }

    public void update() {
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
                }

            }
        }
    }
}
