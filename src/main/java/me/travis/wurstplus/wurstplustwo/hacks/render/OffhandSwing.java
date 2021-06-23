package me.travis.wurstplus.wurstplustwo.hacks.render;

import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.util.EnumHand;

public class OffhandSwing extends WurstplusHack {

    public OffhandSwing() {
        super(WurstplusCategory.WURSTPLUS_RENDER);
        this.name = "Offhand Swing";
        this.tag = "OffhandSwing";
        this.description = "Swing your offhand instead of mainhand";
    }

    public void update() {

        mc.player.swingingHand = EnumHand.OFF_HAND;

    }

}
