package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import java.awt.Color;

public class CenteredPotionInventory extends Module {
    public CenteredPotionInventory() {
        super("CenteredPotionInventory", "ZentralesGetränkeInventar", new Color(77, 90, 112), Category.RENDER);
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
