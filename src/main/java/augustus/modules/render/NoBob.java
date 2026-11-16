package augustus.modules.render;

import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.ModeSetting;
import java.awt.Color;

public class NoBob extends Module implements UpdateStateListener {
    public ModeSetting settingMode = new ModeSetting(this, "Mode", "Normal", new String[]{"Normal", "NoScreenBobbing"});

    public NoBob() {
        super("NoBob", "KeinBob", new Color(121, 232, 163), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(43);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPreUpdateState() {
        if (this.settingMode.getSelected().equals("Normal")) {
            minecraft.thePlayer.distanceWalkedModified = 0.0F;
        }
    }
}
