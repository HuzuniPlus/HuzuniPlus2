package me.travis.wurstplus.wurstplustwo.hacks.render;

import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;


public class SwingAnimFix extends WurstplusHack {
	
	public SwingAnimFix() {
		

        super(WurstplusCategory.WURSTPLUS_RENDER);

        this.name = "Swing Anim Fix";
        this.tag = "SwingAnimFix";
        this.description = "pasted from zori lol";
    }
	@Override
    public void update() {
        if (mc.player == null) {
            return;
        }
        if (mc.entityRenderer.itemRenderer.equippedProgressMainHand < 1.0f) {
            mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
        }
        if (mc.entityRenderer.itemRenderer.itemStackMainHand != mc.player.getHeldItemMainhand()) {
            mc.entityRenderer.itemRenderer.itemStackMainHand = mc.player.getHeldItemMainhand();
        }
    }
}


