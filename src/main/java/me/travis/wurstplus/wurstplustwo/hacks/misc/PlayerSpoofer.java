package me.travis.wurstplus.wurstplustwo.hacks.misc;

//Imports

import com.google.gson.Gson;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


//Module

public class PlayerSpoofer extends WurstplusHack {

    //Module Info
    public PlayerSpoofer() {

        super(WurstplusCategory.WURSTPLUS_MISC);

        this.name        = "Player Spoofer"; //Commands and Clickgui
        this.tag         = "PlayerSpoofer"; //Config and Arraylist
        this.description = "spoofs your nick name and skin"; //Useless but normally i add this
        INSTANCE = this;
    }

    //Module Settings

    public static PlayerSpoofer INSTANCE;


    public String name = "Pou";
    public File tmp;

    @Override
    public void enable() {
        BufferedImage image;
        try {
            this.tmp = new File("HuzuniPlusTwo"+ File.separator + "tmp");
            if (!this.tmp.exists()) {
                this.tmp.mkdirs();
            }
            Gson gson = new Gson();
            if (name == null) {
                WurstplusMessageUtil.send_client_message("Please set the player name!");
                this.disable();
            }
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            Reader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            Map<?, ?> map = (Map<?, ?>) gson.fromJson(reader, Map.class);
            ConcurrentHashMap<String, String> valsMap = new ConcurrentHashMap<>();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                valsMap.put(key, val);
            }
            reader.close();
            String uuid = valsMap.get("id");
            URL url2 = new URL("https://mc-heads.net/skin/" + uuid);
            image = ImageIO.read(url2);
            ImageIO.write(image, "png", new File("HuzuniPlusTwo/tmp/skin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disable() {
        deleteSkinChangerFiles();
    }

    public void deleteSkinChangerFiles() {
        for (File file : mc.gameDir.listFiles()) {
            if (!file.isDirectory() && file.getName().contains("-skinchanger")) file.delete();
        }
    }

    public String getOldName(){
        return mc.getSession().getUsername();
    }

}
