package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import java.awt.Color;

public class Barriers extends Module {
    public Barriers() {
        super("Barriers", "Barrieren", new Color(93, 98, 227), Category.RENDER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        minecraft.renderGlobal.updateState();
    }

    @Override
    public void onPreDisable() {
        minecraft.renderGlobal.updateState();
    }
}
