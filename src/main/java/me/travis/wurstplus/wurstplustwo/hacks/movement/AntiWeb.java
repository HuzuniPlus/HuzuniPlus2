package me.travis.wurstplus.wurstplustwo.hacks.movement;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.network.play.client.CPacketPlayer;

public class AntiWeb
        extends WurstplusHack {
    public AntiWeb() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);
        this.name = "Anti Web";
        this.tag = "AntiWeb";
        this.description = "no te bajan la velocidad las cobwebs";
    }


    WurstplusSetting vertical = create("Vertical Speed", "VerticalSpeed", 2.0, 0.0, 100.0);
    WurstplusSetting horizontal = create("Horizontal Speed", "HorizontalSpeed", 2.0, 0.0, 100.0);

    @Override
    public void update() {

        if(mc.player.isInWeb) {

            mc.player.motionX *= horizontal.get_value(0);
            mc.player.motionY *= vertical.get_value(0);
            mc.player.motionZ *= horizontal.get_value(0);

        }

        }
}



