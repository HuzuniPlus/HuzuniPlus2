package me.travis.wurstplus.wurstplustwo.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.wurstplus.wurstplustwo.command.WurstplusCommand;
import me.travis.wurstplus.wurstplustwo.hacks.misc.PlayerSpoofer;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;

public class PlayerSpooferCommand extends WurstplusCommand {

    public PlayerSpooferCommand() {
        super("playerspoofer", "changes the playerspoofer module value");
    }

    @Override
    public boolean get_message(String[] message) {
        if (message.length == 0){
            WurstplusMessageUtil.send_client_message("Enter a name dumbass!");
            return true;
        }
        if (message.length == 1){
            if (message[0].isEmpty()){
                WurstplusMessageUtil.send_client_message("Enter a name dumbass!");
                return true;
            }
            String name = message[0];
            PlayerSpoofer.INSTANCE.name = name;
            // reset skin
            PlayerSpoofer.INSTANCE.disable();
            PlayerSpoofer.INSTANCE.enable();
            // goods
            WurstplusMessageUtil.send_client_message("Set skin to " + ChatFormatting.BOLD + name);
        }
    }
}


