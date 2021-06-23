package me.travis.wurstplus.wurstplustwo.hacks;

//Imports

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;


//Module

public class ExampleModule extends WurstplusHack {

    //Module Info
    public ExampleModule() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);

        this.name        = "Module Name"; //Commands and Clickgui
        this.tag         = "ModuleTag"; //Config and Arraylist
        this.description = "ModuleDescription"; //Useless but normally i add this
    }

    //Module Settings
    WurstplusSetting example = create("Setting Name", "InConfigName", true);
    WurstplusSetting examplenumber = create("Setting Name", "InConfigName", 0, 0, 0);
    WurstplusSetting examplemode = create("Mode Name", "InConfigName", "Default Mode", combobox("First Mode","Second Mode", "Third Mode"));

}
