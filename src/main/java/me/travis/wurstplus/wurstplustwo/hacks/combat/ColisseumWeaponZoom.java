package me.travis.wurstplus.wurstplustwo.hacks.combat;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class ColisseumWeaponZoom extends WurstplusHack {
    public ColisseumWeaponZoom() {
        super(WurstplusCategory.WURSTPLUS_COMBAT);

        this.name        = "Weapon Zoom";
        this.tag         = "WeaponZoom";
        this.description = "decreases fov when holding a weapon";
    }


    private float fov;
    WurstplusSetting zoom = create("Fov", "Fov", 30, 1, 100);

    public void enable() {
        fov = mc.gameSettings.fovSetting;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    protected void disable() {
        mc.gameSettings.fovSetting = fov;
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @Override
    public void update() {
         mc.gameSettings.fovSetting = zoom.get_value(1);
    }

        }


