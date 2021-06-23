package me.travis.wurstplus.wurstplustwo.hacks.misc;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class MapEffectSpoofer extends WurstplusHack {
    public MapEffectSpoofer() {
        super(WurstplusCategory.WURSTPLUS_MISC);
        this.name = "Effect Spoofer";
        this.tag = "EffectSpoofer";
        this.description = "";
    }
    // 100% no paste de AnasheClient+
    WurstplusSetting speed = create("Speed", "CSPSpeed", 1, 0, 5);
    WurstplusSetting strong = create("Strenght", "Multiplier", 1, 0, 3);
    //     nullname y usuni be like:^
    WurstplusSetting haste = create("Haste", "HSpeed", 1, 0, 3);


    @Override
    public void enable() {

        // espid (speed)
        if(speed.get_value(1) > 0){
            final PotionEffect espid = new PotionEffect(Potion.getPotionById(1), 123456789, (speed.get_value(1)-1));
            espid.setPotionDurationMax(true);
            MapEffectSpoofer.mc.player.addPotionEffect(espid);
        }
        // 'tren' (Strenght)
        if(strong.get_value(1) > 0){
            final PotionEffect tren = new PotionEffect(Potion.getPotionById(5), 123456789, (strong.get_value(1)-1));
            tren.setPotionDurationMax(true);
            MapEffectSpoofer.mc.player.addPotionEffect(tren);
        }
        // jeist (haste)
        if(haste.get_value(1) > 0){
            // (haste.get_value(1)-1) if for
            final PotionEffect jeist = new PotionEffect(Potion.getPotionById(3), 123456789, (haste.get_value(1)-1));
            jeist.setPotionDurationMax(true);
            MapEffectSpoofer.mc.player.addPotionEffect(jeist);
        }
    }
    @Override
    public void disable() {
        MapEffectSpoofer.mc.player.removePotionEffect(Potion.getPotionById(1));
        MapEffectSpoofer.mc.player.removePotionEffect(Potion.getPotionById(5));
        MapEffectSpoofer.mc.player.removePotionEffect(Potion.getPotionById(3));
    }
}
