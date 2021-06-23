package me.travis.wurstplus.wurstplustwo.hacks.render;

//Imports

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//Module

public class NoRender extends WurstplusHack {

    //Module Info
    public NoRender() {
        super(WurstplusCategory.WURSTPLUS_RENDER);

        this.name        = "No Render"; //Commands and Clickgui
        this.tag         = "NoRender"; //Config and Arraylist
        this.description = "stop the renderization of some things"; //Useless but normally i add this
    }

    //Module Settings
    WurstplusSetting falling = create("Falling Blocks", "Falling", false);
    WurstplusSetting weather = create("Wheather", "Weather", true);
    WurstplusSetting potionef = create("Potion Icons", "PotionIcons", false);
    WurstplusSetting pumpkin = create("Pumpkin Overlay", "Pumpkin", false);
    WurstplusSetting portal = create("Portal Overlay", "PortalOverlay", false);
    WurstplusSetting fireworks = create("FireWorks", "FireWorks", false);
    WurstplusSetting advancements = create("Advancements", "Advancements", false);
    WurstplusSetting fire = create("Fire", "Fire", false);
    WurstplusSetting hurtcam = create("Hurt Cam", "HurtCam", false);
    WurstplusSetting armor = create("Armor", "Armor", false);



    @Override
    public void update() {
        if(falling.get_value(true))
        for (final Entity e : mc.world.loadedEntityList) {
            if (e instanceof EntityFallingBlock) {
                mc.world.removeEntity(e);

            if (this.mc.world == null) {
                return;
            } 
            if (this.mc.world.isRaining() && weather.get_value(true)) {
                this.mc.world.setRainStrength(0.0f);
            }

            }
        }
    }
}




