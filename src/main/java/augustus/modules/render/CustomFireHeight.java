package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import java.awt.Color;

public class CustomFireHeight extends Module {
    public BooleanSetting settingRemoveFire = new BooleanSetting(this, "RemoveFire", false);
    public NumberSetting settingFireHeight;

    public CustomFireHeight() {
        super("CustomFireHeight", "EnstellbareFeureHöhe", new Color(235, 229, 52), Category.RENDER);
        this.settingFireHeight = new NumberSetting(this, "FireHeight", -0.3, -0.7, 0.0, 2).getNumberSetting(() -> this.isFireHeightVisible());
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

    private boolean isFireHeightVisible() {
        return !this.settingRemoveFire.isEnabled();
    }
}
