package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import java.awt.Color;

public class AntiBlind extends Module {
    public AntiBlind() {
        super("AntiBlind", "GegenBlind", new Color(38, 111, 145), Category.RENDER);
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
