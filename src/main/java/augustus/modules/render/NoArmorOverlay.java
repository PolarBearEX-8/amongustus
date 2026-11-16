package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.MultiSelectSetting;
import client.setting.NoArmorOverlayHelmetType;
import java.awt.Color;

public class NoArmorOverlay extends Module {
    public MultiSelectSetting settingArmorTypes = new MultiSelectSetting(this, "ArmorTypes", NoArmorOverlayHelmetType.values());

    public NoArmorOverlay() {
        super("NoArmorOverlay", "KeineArmor", new Color(157, 224, 190), Category.RENDER);
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
