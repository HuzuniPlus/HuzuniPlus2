package me.travis.wurstplus.wurstplustwo.hacks.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityMule;

public class EntitySearch
        extends WurstplusHack {
    int delay;
    WurstplusSetting donkeys = this.create("Donkeys", "Donkeys", true);
    WurstplusSetting llamas = this.create("Llamas", "Llamas", true);
    WurstplusSetting mules = this.create("Mules", "Mules", false);
    WurstplusSetting slimes = this.create("Slimes", "Slimes", false);

    public EntitySearch() {
        super(WurstplusCategory.WURSTPLUS_CHAT);
        this.name = "Entity Search";
        this.tag = "EntitySearch";
        this.description = "sends a message when finds an entity";
    }

    @Override
    public void enable() {
        this.delay = 0;
    }

    @Override
    public void update() {
        if (this.delay > 0) {
            --this.delay;
        }
        if (this.donkeys.get_value(true)) {
            EntitySearch.mc.world.loadedEntityList.forEach(entity -> {
                if (entity instanceof EntityDonkey && this.delay == 0) {
                    WurstplusMessageUtil.send_client_message("Found a donkey at: " + Math.round(entity.lastTickPosX) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.lastTickPosY) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity.lastTickPosZ));
                    this.delay = 200;
                }
                if (this.llamas.get_value(true)) {
                    EntitySearch.mc.world.loadedEntityList.forEach(entity2 -> {
                        if (entity2 instanceof EntityLlama && this.delay == 0) {
                            WurstplusMessageUtil.send_client_message("Found a llama at: " + Math.round(entity2.lastTickPosX) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity2.lastTickPosY) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity2.lastTickPosZ));
                            this.delay = 200;
                        }
                        if (this.mules.get_value(true)) {
                            EntitySearch.mc.world.loadedEntityList.forEach(entity3 -> {
                                if (entity3 instanceof EntityMule && this.delay == 0) {
                                    WurstplusMessageUtil.send_client_message("Found a mule at: " + Math.round(entity3.lastTickPosX) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity3.lastTickPosY) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity3.lastTickPosZ));
                                    this.delay = 200;
                                }
                                if (this.slimes.get_value(true)) {
                                    EntitySearch.mc.world.loadedEntityList.forEach(entity4 -> {
                                        if (entity4 instanceof EntitySlime && this.delay == 0) {
                                            WurstplusMessageUtil.send_client_message("Found a slime at: " + Math.round(entity4.lastTickPosX) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity4.lastTickPosY) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(entity4.lastTickPosZ));
                                            this.delay = 200;
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });
        }
    }
}
