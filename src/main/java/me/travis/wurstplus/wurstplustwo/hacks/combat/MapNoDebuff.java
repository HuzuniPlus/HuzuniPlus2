package me.travis.wurstplus.wurstplustwo.hacks.combat;

import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.init.MobEffects;

public class MapNoDebuff extends WurstplusHack {

    //Module Info
    public MapNoDebuff() {
        super(WurstplusCategory.WURSTPLUS_RENDER);

        this.name        = "No Debuff"; //Commands and Clickgui
        this.tag         = "AntiDebuff"; //Config and Arraylist
        this.description = "clean debuff efects"; //Useless but normally i add this
    }
    @Override
    public void update(){
        MapNoDebuff.mc.player.removePotionEffect(MobEffects.WITHER);
        MapNoDebuff.mc.player.removePotionEffect(MobEffects.SLOWNESS);
        MapNoDebuff.mc.player.removePotionEffect(MobEffects.NAUSEA);
        MapNoDebuff.mc.player.removePotionEffect(MobEffects.BLINDNESS);
        MapNoDebuff.mc.player.removePotionEffect(MobEffects.WEAKNESS);
        MapNoDebuff.mc.player.removePotionEffect(MobEffects.POISON);
    }
}