package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.KeybindAnimation;
import java.awt.Color;

public class FovModifier extends Module {
    public KeybindAnimation fovAnimation = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
    public BooleanSetting settingIgnoreDynamicFov = new BooleanSetting(this, "IgnoreDynamicFov", true);
    public NumberSetting settingDefaultFov = new NumberSetting(this, "DefaultFov", 85.0, 30.0, 150.0, 0);
    public NumberSetting settingSprintFov = new NumberSetting(this, "SprintFov", 90.0, 30.0, 150.0, 0);
    public NumberSetting settingSpeedFov = new NumberSetting(this, "SpeedFov", 100.0, 30.0, 150.0, 0);
    public ModeSetting settingAnimationModes = new ModeSetting(this, "AnimationModes", "easeOutQuint", KeybindAnimation.getStringArray(true));
    public NumberSetting settingAnimationTime;
    public float motionMultiplier1;
    public float motionMultiplier;
    public boolean enabled2;
    public boolean expanded2;

    public FovModifier() {
        super("FovModifier", "SichtfeldModifizierer", new Color(252, 186, 3), Category.RENDER);
        this.settingAnimationTime = new NumberSetting(this, "AnimationTime", 200.0, 1.0, 1000.0, 0).getNumberSetting(() -> this.isAnimationTimeVisible());
        this.motionMultiplier1 = 70.0F;
        this.motionMultiplier = 70.0F;
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.motionMultiplier = (float)this.settingDefaultFov.getValue();
    }

    private boolean isAnimationTimeVisible() {
        return !this.settingAnimationModes.getSelected().equalsIgnoreCase("None");
    }
}
