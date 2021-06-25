package me.travis.wurstplus.wurstplustwo.hacks.render;

//Imports

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;


//Module

public class FullBright extends WurstplusHack {

    float old_gamme_value;

    //Module Info
    public FullBright() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);

        this.name        = "Full Bright"; //Commands and Clickgui
        this.tag         = "FullBright"; //Config and Arraylist
        this.description = "lleno brillo"; //Useless but normally i add this
    }

    //Module Settings
    WurstplusSetting mode = create("Mode", "FBMode", "Gamma", combobox("Gamma","Potion"));

    public void update() {
        old_gamme_value = mc.gameSettings.gammaSetting;

        if(mode.in("Potion")); {
            mc.player.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 80950, 1, false, false)));
        }

    }
    public void enable() {
        if (mode.in("Gamma")) ;
        {
            mc.gameSettings.gammaSetting += 100;
        }
    }
    public void disable()
    {
        if (mode.in("Gamma")) ;
        {
            mc.gameSettings.gammaSetting = old_gamme_value;
        }

        if (mode.in("Potion")); {
        mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
    }

    }

}
