package me.travis.wurstplus.wurstplustwo.hacks.movement;

import me.travis.wurstplus.wurstplustwo.event.WurstplusEventBus;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;

import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.InputUpdateEvent;



public class NoSlow extends WurstplusHack
{
    private boolean sneaking;
    
    public NoSlow() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);
        
		this.name        = "No Slow";
		this.tag         = "NoSlow";
		this.description = "Just no slows";
    }
    
    
    @EventHandler
    private Listener<InputUpdateEvent> eventListener = new Listener<>(event -> {
        if (mc.player.isHandActive() && !mc.player.isRiding()) {
            // ups
            event.getMovementInput().moveStrafe *= 5;
            event.getMovementInput().moveForward *= 5;
        }
    });

    public void onEnable(){
        WurstplusEventBus.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        WurstplusEventBus.EVENT_BUS.unsubscribe(this);
    }

    }