package me.travis.wurstplus.wurstplustwo.hacks.chat;

//Imports

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;
import net.minecraft.init.MobEffects;
import me.travis.wurstplus.Wurstplus;


//Module

public class WeaknessAlert extends WurstplusHack {
    private boolean hasAnnounced = false;

    //Module Info
    public WeaknessAlert() {
        super(WurstplusCategory.WURSTPLUS_CHAT);

        this.name        = "Weakness Alert"; //Commands and Clickgui
        this.tag         = "WeaknessAlert"; //Config and Arraylist
        this.description = "te dice cuando tenes weakness"; //Useless but normally i add this
    }

    //Module Settings


        {
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




