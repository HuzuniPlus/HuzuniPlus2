package me.travis.wurstplus.wurstplustwo.hacks.misc;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
//made by mapeadoh :coolguy:
// pasted from AnasheClient+
public class AutoMsg extends WurstplusHack{

    public AutoMsg() {

        super(WurstplusCategory.WURSTPLUS_MISC);

        this.name = "Auto Msg";
        this.tag = "AutoMsg";
        this.description = "automatically run commands";
    }
    WurstplusSetting mode = create("Message", "Mode", "Kit", combobox("Kit", "Kill"/*, "Duel" WIP*/));

    public void update(){
        if(mode.in("Kill")){
            mc.player.sendChatMessage("/kill");
            this.toggle();
        }
        if(mode.in("Kit")){
            mc.player.sendChatMessage("/kit");
            this.toggle();
        }
    }
}