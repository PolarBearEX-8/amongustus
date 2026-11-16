package augustus.modules.render;

import client.ClientGlobals;
import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.util.BooleanSupplier;
import client.util.MovementHandler;
import java.awt.Color;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;

public class Crosshair extends Module implements RenderListener {
    public BooleanSetting settingDefaultCrossHair;
    public BooleanSetting settingThirdPerson;
    public NumberSetting settingScale;
    public ColorSetting settingColor;
    public NumberSetting settingLength;
    public NumberSetting settingWidth;
    public NumberSetting settingMargin;
    public BooleanSetting settingDot;
    public BooleanSetting settingTStyle;
    public BooleanSetting settingShadow;

    public Crosshair() {
        super("Crosshair", "Fadenkreuz", new Color(157, 230, 106), Category.RENDER);
        this.settingDefaultCrossHair = new BooleanSetting(this, "DefaultCrossHair", false);
        this.settingThirdPerson = new BooleanSetting(this, "ThirdPerson", false);
        this.settingScale = new NumberSetting(this, "Scale", 2.0, 1.0, 4.0, 2).getNumberSetting(() -> this.settingDefaultCrossHair.isEnabled());
        Color color = new Color(94, 0, 255, 255);
        this.settingColor = (ColorSetting)new ColorSetting(this, "Color", color).getSetting(() -> this.isColorVisible());
        this.settingLength = new NumberSetting(this, "Length", 4.0, 0.0, 40.0, 0).getNumberSetting(() -> this.isLengthVisible());
        this.settingWidth = new NumberSetting(this, "Width", 1.0, 1.0, 20.0, 0).getNumberSetting(() -> this.isWidthVisible());
        this.settingMargin = new NumberSetting(this, "Margin", 1.0, 0.0, 40.0, 0).getNumberSetting(() -> this.isMarginVisible2());
        this.settingDot = (BooleanSetting)new BooleanSetting(this, "Dot", true).getSetting(() -> this.isMarginVisible());
        this.settingTStyle = (BooleanSetting)new BooleanSetting(this, "T-Style", false).getSetting(() -> this.isMarginVisible3());
        this.settingShadow = (BooleanSetting)new BooleanSetting(this, "Shadow", true).getSetting(() -> this.isMarginVisible4());
    }

    @Override
    public void onEnable() {
        this.registerEvent(32, 1000000);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (!ClientGlobals.moduleManager.crosshair.settingDefaultCrossHair.isEnabled()) {
            if (minecraft.ingameGUI.canExecute()) {
                if (minecraft.gameSettings.indexOffset64 >= 1) {
                    if (!ClientGlobals.moduleManager.crosshair.isToggled()) {
                        return;
                    }

                    if (!ClientGlobals.moduleManager.crosshair.settingThirdPerson.isEnabled()) {
                        return;
                    }
                }

                boolean enabled;
                if (ClientGlobals.moduleManager.crosshair.settingShadow.isEnabled() && ClientGlobals.moduleManager.shadow.isToggled()) {
                    enabled = true;
                } else {
                    enabled = false;
                }

                MovementHandler.processBoolean(false & true, enabled);
                MovementHandler.applyRunnableTask(false, renderPass -> applyScaledResolution2(scaledResolution, renderPass));
                RenderUtils.processAction();
            }
        }
    }

    private boolean isMarginVisible() {
        return !this.settingDefaultCrossHair.isEnabled();
    }

    private boolean isColorVisible() {
        return !this.settingDefaultCrossHair.isEnabled();
    }

    private boolean isMarginVisible2() {
        return !this.settingDefaultCrossHair.isEnabled();
    }

    private static void applyScaledResolution2(ScaledResolution scaledResolution, RenderPass renderPass) {
        GuiIngame.applyScaledResolution12(scaledResolution);
    }

    private boolean isLengthVisible() {
        return !this.settingDefaultCrossHair.isEnabled();
    }

    private boolean isMarginVisible3() {
        return !this.settingDefaultCrossHair.isEnabled();
    }

    private boolean isWidthVisible() {
        return !this.settingDefaultCrossHair.isEnabled();
    }

    private boolean isMarginVisible4() {
        return !this.settingDefaultCrossHair.isEnabled();
    }
}
