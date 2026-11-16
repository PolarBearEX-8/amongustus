package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import java.awt.Color;

public class NoBlockParticles extends Module {
    public NoBlockParticles() {
        super("NoBlockParticles", "KeineBlockBrechEffekte", new Color(219, 181, 75), Category.RENDER);
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
