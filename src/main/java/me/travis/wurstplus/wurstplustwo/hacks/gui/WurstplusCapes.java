package me.travis.wurstplus.wurstplustwo.hacks.gui;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;

public class WurstplusCapes extends WurstplusHack {

    public WurstplusCapes() {
        super(WurstplusCategory.WURSTPLUS_GUI);

        this.name = "Capes";
        this.tag = "Capes";
        this.description = "see epic capes behind epic dudes";
    }

    WurstplusSetting cape = create("Cape", "CapeCape", "New", combobox("New"));

}
