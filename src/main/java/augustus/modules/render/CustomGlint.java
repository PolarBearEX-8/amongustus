package augustus.modules.render;

import client.event.EventStepHeight;
import client.event.EventStepHeightCallback;
import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.CustomGlintSwordsType;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import java.awt.Color;

public class CustomGlint extends Module implements EventStepHeightCallback, RenderListener {
    public BooleanSetting settingRemove = new BooleanSetting(this, "Remove", false);
    public BooleanSetting settingBrighterEnchantments = new BooleanSetting(this, "BrighterEnchantments", false);
    public BooleanSetting settingCustomColor;
    public ColorSetting settingColor;
    public MultiSelectSetting settingFakeEnchant;
    public NumberSetting settingSpeed;
    public int keyCode2;

    public CustomGlint() {
        super("CustomGlint", "KundenspezifischesGlitzern", new Color(128, 64, 204), Category.RENDER);
        BooleanSetting booleanSetting = new BooleanSetting(this, "CustomColor", false);
        this.settingCustomColor = (BooleanSetting)booleanSetting.getSetting(() -> this.canExecute5());
        ColorSetting colorSetting = new ColorSetting(this, "Color", new Color(128, 64, 204));
        this.settingColor = (ColorSetting)colorSetting.getSetting(() -> this.isColorVisible());
        this.settingFakeEnchant = new MultiSelectSetting(this, "FakeEnchant", CustomGlintSwordsType.values()).getMultiSelectSetting(() -> this.isFakeEnchantVisible());
        this.settingSpeed = new NumberSetting(this, "Speed", 1.0, 0.01, 10.0, 2).getNumberSetting(() -> this.isSpeedVisible());
        this.keyCode2 = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(12);
        this.registerEvent(32);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onStepHeight(EventStepHeight event) {
        if (this.settingCustomColor.isEnabled()) {
            event.processAction(this.settingColor.getInt12(this.keyCode2));
            this.keyCode2++;
        }

        if (this.settingRemove.isEnabled()) {
            event.cancel();
        }

        event.processAction(this.settingSpeed.getValue());
    }

    @Override
    public void onRender3D(float partialTicks) {
        this.keyCode2 = 0;
    }

    private boolean canExecute5() {
        return !this.settingRemove.isEnabled();
    }

    private boolean isFakeEnchantVisible() {
        return !this.settingRemove.isEnabled();
    }

    private boolean isColorVisible() {
        return !this.settingCustomColor.isEnabled() ? false : this.settingCustomColor.isVisible();
    }

    private boolean isSpeedVisible() {
        return !this.settingRemove.isEnabled();
    }
}
