package me.travis.wurstplus.wurstplustwo.hacks.chat;

import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;

public class Shrug
        extends WurstplusHack {
    public Shrug() {
        super(WurstplusCategory.WURSTPLUS_CHAT);
        this.name = "Shrug";
        this.tag = "Shrug";
        this.description = "shrugs";
    }

    @Override
    public void enable() {
        Shrug.mc.player.sendChatMessage(" \u00af\\_(\u30c4)_/\u00af");
        this.toggle();
    }
}
