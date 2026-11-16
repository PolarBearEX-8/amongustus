package augustus.modules.movement;

import client.module.Category;
import client.module.Module;
import java.awt.Color;

public class SnapTap extends Module {
    public SnapTap() {
        super("SnapTap", "SchnappBerührung", new Color(255, 244, 140), Category.MOVEMENT);
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
