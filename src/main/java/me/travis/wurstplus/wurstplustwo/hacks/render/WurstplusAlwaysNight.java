package me.travis.wurstplus.wurstplustwo.hacks.render;

import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventRender;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;

public class WurstplusAlwaysNight extends WurstplusHack {

    public WurstplusAlwaysNight() {
        super(WurstplusCategory.WURSTPLUS_RENDER);

        this.name = "Time Changer";
        this.tag = "TimeChanger";
        this.description = "see even less";
    }

    WurstplusSetting time = create("Time", "TCTime", 18000, 0, 18000);

    @EventHandler
    private Listener<WurstplusEventRender> on_render = new Listener<>(event -> {
        if (mc.world == null) return;
        mc.world.setWorldTime(time.get_value(1));
    });

    @Override
    public void update() {
        if (mc.world == null) return;
        mc.world.setWorldTime(time.get_value(1));
    }
}
