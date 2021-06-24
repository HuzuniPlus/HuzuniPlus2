package me.travis.wurstplus.wurstplustwo.hacks.chat;

//Imports

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;
import net.minecraft.init.MobEffects;

//Module

public class a extends WurstplusHack {

    //Module Info
    public a() {
        super(WurstplusCategory.WURSTPLUS_CHAT);

        this.name        = "Weakness Detect"; //Commands and Clickgui
        this.tag         = "WeaknessDetect"; //Config and Arraylist
        this.description = "avisa si tenes weakness, no se como se dice en ingles"; //Useless but normally i add this
    }
    private boolean hasAnnounced = false;
    @Override
    public void update() {
            if (mc.player.isPotionActive(MobEffects.WEAKNESS) && !hasAnnounced) {
            hasAnnounced = true;
            WurstplusMessageUtil.send_client_message(ChatFormatting.GRAY + "" + ChatFormatting.BOLD + "You now have weakness");
        }
            if (!mc.player.isPotionActive(MobEffects.WEAKNESS) && hasAnnounced) {
                hasAnnounced = false;
                WurstplusMessageUtil.send_client_message(ChatFormatting.GRAY + "" + ChatFormatting.BOLD + "You no longer have weakness");
        }
    }
}