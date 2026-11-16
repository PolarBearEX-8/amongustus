package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import java.awt.Color;

public class ArrowHitboxes extends Module {
    public ArrowHitboxes() {
        super("ArrowHitboxes", "PfeilTrefferkasten", new Color(153, 0, 153), Category.RENDER);
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
