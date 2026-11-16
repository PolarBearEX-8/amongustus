package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import java.awt.Color;

public class CustomHurtCam extends Module {
    public BooleanSetting settingRemoveHurtCamAtDeath = new BooleanSetting(this, "RemoveHurtCamAtDeath", true);
    public NumberSetting settingStrength = new NumberSetting(this, "Strength", 0.0, -35.0, 35.0, 1);

    public CustomHurtCam() {
        super("CustomHurtCam", "GegenSchmerzCam", new Color(245, 162, 54), Category.RENDER);
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

    public boolean canExecute5() {
        return !this.settingRemoveHurtCamAtDeath.isEnabled() ? false : this.isToggled();
    }

    public float getFloat() {
        return !this.isToggled() ? 14.0F : (float)this.settingStrength.getValue();
    }
}
