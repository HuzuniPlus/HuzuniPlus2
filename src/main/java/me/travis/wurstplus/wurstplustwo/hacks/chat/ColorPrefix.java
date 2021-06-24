package me.travis.wurstplus.wurstplustwo.hacks.chat;

import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventPacket;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.manager.WurstplusCommandManager;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;

public class ColorPrefix extends WurstplusHack {
	public ColorPrefix() {
        super(WurstplusCategory.WURSTPLUS_CHAT);
        this.name = "Color Prefix";
        this.tag = "ColorPrefix";
        this.description = "it does exactly what you think";
	}
	
	WurstplusSetting mode = create("Prefix", "CPrefix", ">", combobox(">","`", "#", "&", "%", "$", "*", ","));
	WurstplusSetting space = create("Space", "CPrefixSpace", true);

    @EventHandler
    private Listener<WurstplusEventPacket.SendPacket> listener = new Listener<>(event -> {
        if(event.get_packet() instanceof CPacketChatMessage){
            if(((CPacketChatMessage) event.get_packet()).getMessage().startsWith("/") || ((CPacketChatMessage) event.get_packet()).getMessage().startsWith(WurstplusCommandManager.get_prefix())) return;
            String message = ((CPacketChatMessage) event.get_packet()).getMessage();
            String prefix = "";
            String sp = "";
            
            
            if(this.mode.in(">")) prefix = ">";
            if(this.mode.in("`")) prefix = "`";
            if(this.mode.in("#")) prefix = "#";
            if(this.mode.in("&")) prefix = "&";
            if(this.mode.in("%")) prefix = "%";
            if(this.mode.in("$")) prefix = "$";
            if(this.mode.in("*")) prefix = "*";
            if(this.mode.in(",")) prefix = ",";
            
            if(space.get_value(true)) sp = " "; 
            
         else 
        	
        	sp = "";
        
        
            
            String s = prefix + sp + message;
            if(s.length() > 255) return;
            ((CPacketChatMessage) event.get_packet()).message = s;
        }
    });
    
	
}
