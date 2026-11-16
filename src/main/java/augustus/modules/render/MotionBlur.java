package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import java.awt.Color;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class MotionBlur extends Module {
    public boolean amdUnsupported;
    public NumberSetting settingFrameMultiplier;
    public NumberSetting settingAdditionalFramePrecision;
    public NumberSetting settingBrightness;
    public BooleanSetting settingOnlyWhenFocused;

    public MotionBlur() {
        super("MotionBlur", "BewegungsUnschärfe", new Color(50, 168, 151), Category.RENDER);
        if (GL11.glGetString(7937).toLowerCase().contains("amd")) {
            this.amdUnsupported = true;
            this.settingFrameMultiplier = new NumberSetting(this, "Frame Multiplier", 50.0, 0.0, 100.0, 1).getNumberSetting(() -> this.isFrameMultiplierVisible());
            this.settingAdditionalFramePrecision = new NumberSetting(this, "Additional frame precision", 0.0, 0.0, 100.0, 1).getNumberSetting(() -> this.isAdditionalFramePrecisionVisible());
            this.settingBrightness = new NumberSetting(this, "Brightness", 50.0, 5.0, 100.0, 1).getNumberSetting(() -> this.isBrightnessVisible());
            this.settingOnlyWhenFocused = new BooleanSetting(this, "Only when focused", true);
        } else if (!GL11.glGetString(7937).toLowerCase().contains("rx ")) {
            this.amdUnsupported = false;
            this.settingFrameMultiplier = new NumberSetting(this, "Frame Multiplier", 50.0, 0.0, 100.0, 1).getNumberSetting(() -> this.isFrameMultiplierVisible());
            this.settingAdditionalFramePrecision = new NumberSetting(this, "Additional frame precision", 0.0, 0.0, 100.0, 1).getNumberSetting(() -> this.isAdditionalFramePrecisionVisible());
            this.settingBrightness = new NumberSetting(this, "Brightness", 50.0, 5.0, 100.0, 1).getNumberSetting(() -> this.isBrightnessVisible());
            this.settingOnlyWhenFocused = new BooleanSetting(this, "Only when focused", true);
        } else {
            this.amdUnsupported = true;
            this.settingFrameMultiplier = new NumberSetting(this, "Frame Multiplier", 50.0, 0.0, 100.0, 1).getNumberSetting(() -> this.isFrameMultiplierVisible());
            this.settingAdditionalFramePrecision = new NumberSetting(this, "Additional frame precision", 0.0, 0.0, 100.0, 1).getNumberSetting(() -> this.isAdditionalFramePrecisionVisible());
            this.settingBrightness = new NumberSetting(this, "Brightness", 50.0, 5.0, 100.0, 1).getNumberSetting(() -> this.isBrightnessVisible());
            this.settingOnlyWhenFocused = new BooleanSetting(this, "Only when focused", true);
        }
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

    private boolean isFrameMultiplierVisible() {
        return !this.amdUnsupported;
    }

    public void updateState() {
        if (!this.settingOnlyWhenFocused.isEnabled() || Display.isActive()) {
            float floatEntry = this.getFloat();
            GL11.glAccum(259, floatEntry);
            if (this.settingBrightness.getValue() == 50.0) {
                GL11.glAccum(256, 1.0F - floatEntry);
            } else {
                float value = (float)((double)((1.0F - floatEntry) / 100.0F) * this.settingBrightness.getValue() * 2.0);
                GL11.glAccum(256, value);
            }

            GL11.glAccum(258, 1.0F);
        }
    }

    public float getFloat() {
        float value = (float)(this.settingFrameMultiplier.getValue() * 10.0);
        if (this.settingAdditionalFramePrecision.getValue() != 0.0) {
            value = (float)((double)value + this.settingAdditionalFramePrecision.getValue() / 5.0);
        }

        if (value > 990.0F) {
            value = 990.0F;
        }

        return value / 1000.0F;
    }

    private boolean isAdditionalFramePrecisionVisible() {
        return !this.amdUnsupported;
    }

    private boolean isBrightnessVisible() {
        return !this.amdUnsupported;
    }
}
