package augustus.modules.misc;

import client.module.Category;
import client.module.Module;
import java.awt.Color;

public class AntiAchievement extends Module {
    public AntiAchievement() {
        super("AntiAchievement", "GegenErrungenschaft", new Color(50, 168, 82), Category.MISC);
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
