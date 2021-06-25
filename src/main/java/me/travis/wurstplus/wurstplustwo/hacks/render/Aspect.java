package me.travis.wurstplus.wurstplustwo.hacks.render;

//Imports

import me.travis.wurstplus.wurstplustwo.event.events.PerspectiveEvent;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;


//Module

public class Aspect extends WurstplusHack {

    //Module Info
    public Aspect() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);

        this.name        = "Aspect"; //Commands and Clickgui
        this.tag         = "Aspect"; //Config and Arraylist
        this.description = ""; //Useless but normally i add this
    }

    //Module Settings




    WurstplusSetting aspect = create("Aspect", "Aspect", mc.displayWidth / mc.displayHeight + 0.0, 0.0, 3.0);

    public void onPerspectiveEvent(PerspectiveEvent event) {
        event.setAspect(aspect.get_value(1));
    }


}