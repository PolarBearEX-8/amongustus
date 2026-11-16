package augustus.modules.combat;

import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import java.awt.Color;
import net.minecraft.entity.Entity;

public class TargetArea extends Module {
    public ActionSetting settingX = new ActionSetting(this, "x", -1000.0, 1000.0, -1000.0, 1000.0, 0);
    public ActionSetting settingY = new ActionSetting(this, "y", 0.0, 255.0, 0.0, 255.0, 0);
    public ActionSetting settingZ = new ActionSetting(this, "z", -1000.0, 1000.0, -1000.0, 1000.0, 0);
    public BooleanSetting settingReverse = new BooleanSetting(this, "Reverse", false);

    public TargetArea() {
        super("TargetArea", "GegnerBereich", Color.red, Category.COMBAT);
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

    public boolean checkEntity(Entity entity) {
        boolean enabled;
        if (!(entity.posX < this.settingX.getDouble3())
            && !(entity.posX > this.settingX.getDouble6())
            && !(entity.posY < this.settingY.getDouble3())
            && !(entity.posY > this.settingY.getDouble6())
            && !(entity.posZ < this.settingZ.getDouble3())
            && entity.posZ <= this.settingZ.getDouble6()) {
            enabled = true;
        } else {
            enabled = false;
        }

        return (byte)(this.settingReverse.isEnabled() != enabled ? 1 : 0) != 0;
    }
}
