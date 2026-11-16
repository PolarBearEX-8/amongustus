package augustus.modules.render;

import client.event.EventLivingUpdate;
import client.event.EventLivingUpdateCallback;
import client.event.EventTargetEntity;
import client.event.EventTargetEntityCallback;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.EasingFunction;
import client.setting.ModeSetting;
import client.util.BooleanSupplier;
import client.util.KeybindAnimation;
import java.awt.Color;
import net.minecraft.util.MathHelper;

public class EntityColor extends Module implements EventLivingUpdateCallback, EventTargetEntityCallback {
    public ColorSetting settingHitColor = new ColorSetting(this, "HitColor", new Color(0, 255, 0, 36));

    public ModeSetting settingHitColorAnimation = new ModeSetting(
        this, "HitColorAnimation", "easeInSine", KeybindAnimation.getStringArray(true)
    );
    public BooleanSetting settingReverseAnimation;
    public ColorSetting settingColor;

    public EntityColor() {
        super("EntityColor", "EntitätFarbe", new Color(12, 171, 152), Category.RENDER);
        BooleanSetting booleanSetting = new BooleanSetting(this, "ReverseAnimation", false);
        this.settingReverseAnimation = (BooleanSetting)booleanSetting.getSetting(() -> this.isHitColorAnimationVisible());
        this.settingColor = new ColorSetting(this, "Color", new Color(0, 0, 0, 0));
    }

    @Override
    public void onEnable() {
        this.registerEvent(10);
        this.registerEvent(50);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onTargetEntity(EventTargetEntity event) {
        if (!this.settingHitColorAnimation.getSelected().equals("None")) {
            KeybindAnimation keybindAnimation = event.getTargetEntity().keybindAnimation;
            EasingFunction easingFunction = KeybindAnimation.getEasingFunction(this.settingHitColorAnimation.getSelected());
            byte state = (byte)(this.settingReverseAnimation.isEnabled() ? 1 : 0);
            keybindAnimation.updateEasingFunction(500, easingFunction, state, true);
        }
    }

    @Override
    public void onLivingUpdate(EventLivingUpdate event) {
        if (event.getTargetEntity().hurtTime > 0) {
            if (!this.settingHitColorAnimation.getSelected().equals("None")) {
                this.settingHitColor.getInt12(0);
                event.processAction4(this.settingHitColor.getFloat());
                event.processAction2(this.settingHitColor.getFloat2());
                event.processAction(this.settingHitColor.getFloat4());
                event.processAction3(
                    (float)MathHelper.getInt12(
                            (int)((double)this.settingHitColor.getInt4() * event.getTargetEntity().keybindAnimation.getDouble3()),
                            0,
                            255
                        )
                        / 255.0F
                );
            } else {
                event.processAction(this.settingHitColor.getInt12(0));
            }
        } else {
            if (this.settingColor.getInt9() > 10) {
                event.processAction(this.settingColor.getInt12(0));
                event.setActive(true);
            }
        }
    }

    private boolean isHitColorAnimationVisible() {
        return !this.settingHitColorAnimation.getSelected().equals("None");
    }
}
