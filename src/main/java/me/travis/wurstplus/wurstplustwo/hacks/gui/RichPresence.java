package me.travis.wurstplus.wurstplustwo.hacks.gui;

import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.WarriorRPC;

public class RichPresence extends WurstplusHack {
	
	public RichPresence() {
		
        super(WurstplusCategory.WURSTPLUS_GUI);
        this.name = "Rich Presence";
        this.tag = "RichPresence";
        this.description = "shows discord rpc";
	}
    @Override
    public void enable() {
        WarriorRPC.init();
    }
    @Override
    public void disable() {
    	WarriorRPC.shutdown();
    }
}