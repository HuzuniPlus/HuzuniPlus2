package me.travis.wurstplus.wurstplustwo.hacks.render;

//Imports

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;


//Module

public class SmallHand extends WurstplusHack {

    //Module Info
    public SmallHand() {
        super(WurstplusCategory.WURSTPLUS_RENDER);

        this.name        = "Small Hand"; //Commands and Clickgui
        this.tag         = "SmallHand"; //Config and Arraylist
        this.description = "decreases your hand Y position"; //Useless but normally i add this
    }

    //Module Settings
    WurstplusSetting multiplier = create("Multiplier", "SmallHandMultiplier", 90, 0, 360);

    public void update() {
        mc.player.renderArmPitch = multiplier.get_value(1);
    }


}
