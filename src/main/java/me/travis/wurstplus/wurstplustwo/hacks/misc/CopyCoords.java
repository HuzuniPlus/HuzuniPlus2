package me.travis.wurstplus.wurstplustwo.hacks.misc;

//Imports

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;


//Module

public class CopyCoords extends WurstplusHack {

    //Module Info
    public CopyCoords() {
        super(WurstplusCategory.WURSTPLUS_MISC);

        this.name        = "Copy Coords"; //Commands and Clickgui
        this.tag         = "CopyCoords"; //Config and Arraylist
        this.description = "Copies your coorinates to clipboard"; //Useless but normally i add this
    }

    String coords;

    //Module Settings

    public void enable() {
        this.coords = mc.player.posX + " " + mc.player.posY + " " + mc.player.posZ;
        String myString = this.coords;
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        WurstplusMessageUtil.send_client_message("Copied coords to clipboard");
        toggle();

    }

}
