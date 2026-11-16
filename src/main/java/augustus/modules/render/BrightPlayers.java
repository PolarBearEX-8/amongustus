package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import java.awt.Color;

public class BrightPlayers extends Module {
    public BrightPlayers() {
        super("BrightPlayers", "HelleSpieler", new Color(175, 187, 250), Category.RENDER);
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
