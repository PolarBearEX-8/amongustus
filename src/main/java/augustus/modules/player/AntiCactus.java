package augustus.modules.player;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import java.awt.Color;

public class AntiCactus extends Module {
    public BooleanSetting settingOnlySide = new BooleanSetting(this, "OnlySide", true);

    public AntiCactus() {
        super("AntiCactus", "Gegenkaktus", new Color(222, 129, 71), Category.PLAYER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }
}
