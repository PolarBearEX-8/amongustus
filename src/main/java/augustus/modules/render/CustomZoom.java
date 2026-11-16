package augustus.modules.render;

import client.event.EventFovUpdate;
import client.event.EventFovUpdateCallback;
import client.event.EventSilentMove;
import client.event.SilentMoveListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.KeybindAnimation;
import java.awt.Color;

public class CustomZoom extends Module implements EventFovUpdateCallback, SilentMoveListener {
    public NumberSetting settingZoomFactor = new NumberSetting(this, "ZoomFactor", 4.0, 1.0, 10.0, 2);
    public ModeSetting settingAnimationModes = new ModeSetting(this, "AnimationModes", "easeOutQuint", KeybindAnimation.getStringArray(true));
    public NumberSetting settingAnimationTime;
    public BooleanSetting settingFastZoom;
    public KeybindAnimation keybindAnimation2;
    public boolean expanded2;

    public CustomZoom() {
        super("CustomZoom", "EinstellbarerZoom", new Color(252, 186, 3), Category.RENDER);
        this.settingAnimationTime = new NumberSetting(this, "AnimationTime", 200.0, 0.0, 1000.0, 0).getNumberSetting(() -> this.isAnimationTimeVisible());
        this.settingFastZoom = new BooleanSetting(this, "FastZoom", false);
        this.keybindAnimation2 = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(46);
        this.registerEvent(19);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onSilentMove(EventSilentMove event) {
        if (minecraft.gameSettings.ofKeyBindZoom.pressed) {
            ;
        }

        this.expanded2 = minecraft.gameSettings.ofKeyBindZoom.pressed;
    }

    @Override
    public void onFovUpdate(EventFovUpdate event) {
        float value = (float)this.settingZoomFactor.getValue() - 1.0F;
        if (!this.settingAnimationModes.getSelected().equals("None")) {
            if (this.keybindAnimation2.getDouble2() < 1.0) {
                value = 1.0F + (float)((double)value * this.keybindAnimation2.getDouble3());
            } else {
                value++;
            }
        }

        event.processAction(value);
    }

    private boolean isAnimationTimeVisible() {
        return !this.settingAnimationModes.getSelected().equalsIgnoreCase("None");
    }

    public KeybindAnimation getKeybindAnimation() {
        return this.keybindAnimation2;
    }
}
