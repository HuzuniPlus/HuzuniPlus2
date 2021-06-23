package me.travis.wurstplus.wurstplustwo.hacks.chat;

//Imports

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;


//Module

public class StrenghtDetect extends WurstplusHack {

    private final Set<EntityPlayer> str;
    public static final Minecraft mc;

    //Module Info
    public StrenghtDetect() {
        super(WurstplusCategory.WURSTPLUS_CHAT);

        this.name        = "Strenght Detect"; //Commands and Clickgui
        this.tag         = "StrenghtDetect"; //Config and Arraylist
        this.description = "detects if someone has strenght"; //Useless but normally i add this
        this.str = Collections.newSetFromMap(new WeakHashMap<EntityPlayer, Boolean>());
    }

    @Override
    public void update() {
        for (final EntityPlayer player : mc.world.playerEntities) {
            if (player.equals((Object)mc.player)) {
                continue;
            }
            if (player.isPotionActive(MobEffects.STRENGTH) && !this.str.contains(player)) {
                WurstplusMessageUtil.send_client_message(ChatFormatting.RED + "" + ChatFormatting.BOLD + "StrengthDetect" + ChatFormatting.RESET + ChatFormatting.DARK_AQUA + " > " + ChatFormatting.RESET + player.getDisplayNameString() + " Has Strength");
                this.str.add(player);
            }
            if (!this.str.contains(player)) {
                continue;
            }
            if (player.isPotionActive(MobEffects.STRENGTH)) {
                continue;
            }
            WurstplusMessageUtil.send_client_message(ChatFormatting.RED + "" + ChatFormatting.BOLD + "StrengthDetect" + ChatFormatting.RESET + ChatFormatting.DARK_AQUA + " > " + ChatFormatting.RESET + player.getDisplayNameString() + " Has Ran Out Of Strength");
            this.str.remove(player);
        }
    }

    static {
        mc = Minecraft.getMinecraft();
    }
}
