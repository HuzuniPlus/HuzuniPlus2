package me.travis.wurstplus.wurstplustwo.hacks.chat;

import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventPacket;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketChatMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VenecoInsulter extends WurstplusHack {

    /*
     *    Updated by NathanW because we need to end racism on anarchy servers
     */

    public VenecoInsulter() {
        super(WurstplusCategory.WURSTPLUS_CHAT);

        this.name = "Veneco Insulter";
        this.tag = "VenecoInsulter";
        this.description = "i love black squares (circles on the other hand...)";
    }

    WurstplusSetting delay = create("Delay", "AntiRacistDelay", 10, 0, 100);
    WurstplusSetting anti_nword = create("AntiNword", "AntiRacismAntiNword", true);
    WurstplusSetting chanter = create("Chanter", "AntiRacismChanter", false);

    List<String> chants = new ArrayList<>();

    Random r = new Random();
    int tick_delay;

    @Override
    protected void enable() {
        tick_delay = 0;

        chants.add("Adrise Veneco");
        chants.add("Leroy Veneco");
        chants.add("Reken Veneco");
        chants.add("Venuz Veneco");
        chants.add("Adrise Marica");
        chants.add("Leroy Marica");
        chants.add("Reken Marica");
        chants.add("Venuz Marica");
        chants.add("Mapeadoh gay");
    }

    String[] random_correction = {
    };


    CharSequence nigger = "nigger";
    CharSequence nigga = "nigga";

    @Override
    public void update() {

        if(chanter.get_value(true)) {

            tick_delay++;

            if (tick_delay < delay.get_value(1) * 10) return;

            String s = chants.get(r.nextInt(chants.size()));
            String name = get_random_name();

            if (name.equals(mc.player.getName())) return;

            mc.player.sendChatMessage(s.replace("<player>", name));
            tick_delay = 0;

            }
        }

    public String get_random_name() {

            List<EntityPlayer> players = mc.world.playerEntities;
            return players.get(r.nextInt(players.size())).getName();
        }


    public String random_string(String[] list) {
        return list[r.nextInt(list.length)];
    }

    // Anti n-word

    @EventHandler
    private Listener<WurstplusEventPacket.SendPacket> listener = new Listener<>(event -> {

        if (!(event.get_packet() instanceof CPacketChatMessage)) {
            return;
        }

        if(anti_nword.get_value(true)) {

            String message = ((CPacketChatMessage) event.get_packet()).getMessage().toLowerCase();

            if (message.contains(nigger) || message.contains(nigga)) {

                String x = Integer.toString((int) (mc.player.posX));
                String z = Integer.toString((int) (mc.player.posZ));

                String coords = x + " " + z;

                message = (random_string(random_correction));
                mc.player.connection.sendPacket(new CPacketChatMessage("Hi, im at " + coords + ", come teach me a lesson about racism"));

            }

            ((CPacketChatMessage) event.get_packet()).message = message;
        }
    });


}
