package me.travis.wurstplus.wurstplustwo.hacks.chat;


import java.util.Objects;
import com.mojang.realmsclient.gui.ChatFormatting;

import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventPacket;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.manager.WurstplusCommandManager;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.text.TextComponentString;
import scala.util.Random;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class WurstplusChatMods extends WurstplusHack {
    
    public WurstplusChatMods() {
        super(WurstplusCategory.WURSTPLUS_CHAT);

        this.name = "Chat Modifications";
        this.tag = "ChatModifications";
        this.description = "this breaks things";
    }

    // GuiNewChat nc = new GuiNewChat(mc);

    WurstplusSetting timestamps = create("Timestamps", "ChatModsTimeStamps", true);
    WurstplusSetting dateformat = create("Date Format", "ChatModsDateFormat", "24HR", combobox("24HR", "12HR"));
    WurstplusSetting name_highlight = create("Name Highlight", "ChatModsNameHighlight", true);
    WurstplusSetting prefix = create("ChatPrefix", "ChatPrefixModule", true);
    WurstplusSetting prefix_mode = create("Prefix", "CPrefix", ">", combobox(">","`", "#", "&", "%", "$", "*", ","));
	WurstplusSetting space = create("Space", "CPrefixSpace", true);
    WurstplusSetting suffix = create("ChatSuffix", "ChatSuffixModule", true);
    WurstplusSetting suffix_mode = create("Suffix", "CSuffix", "Default", combobox("Default", "Random"));
    WurstplusSetting ignore = create("Ignore", "ChatSuffixIgnore", true);
	WurstplusSetting clear_chatbox = create("Clear Chatbox", "ClearChatbox", false);

    int delay_count = 0;

    @EventHandler
    private Listener<WurstplusEventPacket.ReceivePacket> PacketEvent = new Listener<>(event -> {

        if (event.get_packet() instanceof SPacketChat) {

            final SPacketChat packet = (SPacketChat) event.get_packet();

            if (packet.getChatComponent() instanceof TextComponentString) {
                final TextComponentString component = (TextComponentString) packet.getChatComponent();

            if (timestamps.get_value(true)) {

                    String date = "";

                    if (dateformat.in("12HR")) {
                        date = new SimpleDateFormat("h:mm a").format(new Date());
                    }

                    if (dateformat.in("24HR")) {
                        date = new SimpleDateFormat("k:mm").format(new Date());

                    }

                    component.text = "\2477[" + date + "]\247r " + component.text;

                }

                String text = component.getFormattedText();

                if (text.contains("combat for")) return;

                if (name_highlight.get_value(true) && mc.player != null) {

                    if (text.toLowerCase().contains(mc.player.getName().toLowerCase())) {

                        text = text.replaceAll("(?i)" + mc.player.getName(), ChatFormatting.GOLD + mc.player.getName() + ChatFormatting.RESET);

                    }

                }

                event.cancel();

                WurstplusMessageUtil.client_message(text);

            }
        }
    });
    // prefics
    @EventHandler
    private Listener<WurstplusEventPacket.SendPacket> listener = new Listener<>(event -> {
        if(event.get_packet() instanceof CPacketChatMessage){
            if(((CPacketChatMessage) event.get_packet()).getMessage().startsWith("/") || ((CPacketChatMessage) event.get_packet()).getMessage().startsWith(WurstplusCommandManager.get_prefix())) return;
            String message = ((CPacketChatMessage) event.get_packet()).getMessage();
            String prefix_msg = "";
            String sp = "";
            
            
            if(this.prefix_mode.in(">")) prefix_msg = ">";
            if(this.prefix_mode.in("`")) prefix_msg = "`";
            if(this.prefix_mode.in("#")) prefix_msg = "#";
            if(this.prefix_mode.in("&")) prefix_msg = "&";
            if(this.prefix_mode.in("%")) prefix_msg = "%";
            if(this.prefix_mode.in("$")) prefix_msg = "$";
            if(this.prefix_mode.in("*")) prefix_msg = "*";
            if(this.prefix_mode.in(",")) prefix_msg = ",";
            
            if(space.get_value(true)) sp = " "; 
            
        else 

        sp = "";
        
        
            if (prefix.get_value(true)){
                String s = prefix_msg + sp + message;
            if(s.length() > 255) return;
            ((CPacketChatMessage) event.get_packet()).message = s;
            }
            
        }
    });
    boolean accept_suffix;
	boolean suffix_default;
	boolean suffix_random;

	StringBuilder suffics;

	String[] random_client_name = {
		"huzuniplus",
		"pouware",
		"warriorclient",
		"skided",
	};

	String[] random_client_finish = {
		" sex",
		" god",
		" rated",
		" +",
		" | discord.gg/JsEwSu5G",
		" | discord.gg/pou",
		""
	};

	@EventHandler
	private Listener<WurstplusEventPacket.SendPacket> listener2 = new Listener<>(event -> {
		// If not be the CPacketChatMessage return.
		if (!(event.get_packet() instanceof CPacketChatMessage)) {
			return;
		}

		// Start event suffix.
		accept_suffix = true;

		// Get value.
		boolean ignore_prefix = ignore.get_value(true);

		String message = ((CPacketChatMessage) event.get_packet()).getMessage();

		// If is with some caracther.
		if (message.startsWith("/")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("\\") && ignore_prefix) accept_suffix = false;
		if (message.startsWith("!")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith(":")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith(";")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith(".")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith(",")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("@")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("&")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("*")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("$")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("#")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("(")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith(")")  && ignore_prefix) accept_suffix = false;

		// Compare the values type.
		if (suffix_mode.in("Default")) {
			suffix_default = true;
			suffix_random  = false;
		}

		if (suffix_mode.in("Random")) {
			suffix_default = false;
			suffix_random  = true;
		}

		// If accept.
		if (accept_suffix) {
			if (suffix_default) {
				// Just default.
				message += Wurstplus.WURSTPLUS_SIGN + convert_base(" | " + Wurstplus.HUZUNI);
			}

			if (suffix_random) {
				// Create first the string builder.
				StringBuilder suffix_with_randoms = new StringBuilder();

				// Convert the base using the TravisFont.
				suffix_with_randoms.append(convert_base(random_string(random_client_name)));
				suffix_with_randoms.append(convert_base(random_string(random_client_finish)));

				message += Wurstplus.WURSTPLUS_SIGN + suffix_with_randoms.toString(); 
			}

			// If message 256 string length substring.
			if (message.length() >= 256) {
				message.substring(0, 256);
			}
		}

		// Send the message.
		((CPacketChatMessage) event.get_packet()).message = message;
	});

	// Get the random values string.
	public String random_string(String[] list) {
		return list[new Random().nextInt(list.length)];
	}

	// Convert the base using the TravisFont.
	public String convert_base(String base) {
		return Wurstplus.smoth(base);
	}

	@Override
	public String array_detail() {
		// Update the detail.
		return this.suffix_mode.get_current_value();
	}
}