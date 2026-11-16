package augustus.modules.render;

import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import java.awt.Color;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class Chat extends Module implements RenderListener {
    public BooleanSetting settingRemove = new BooleanSetting(this, "Remove", false);
    public BooleanSetting settingNoChatLimit;
    public BooleanSetting settingBackGround;
    public BooleanSetting settingCustomBackGroundColor;
    public ColorSetting settingBackGroundColor;
    public ShaderOptions settingBackGroundColor2;

    public Chat() {
        super("Chat", "Plaudern", new Color(169, 3, 252), Category.RENDER);
        BooleanSetting booleanSetting = new BooleanSetting(this, "NoChatLimit", true);
        this.settingNoChatLimit = (BooleanSetting)booleanSetting.getSetting(() -> this.canExecute7());
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "BackGround", true);
        this.settingBackGround = (BooleanSetting)booleanSetting2.getSetting(() -> this.canExecute6());
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "CustomBackGroundColor", false);
        this.settingCustomBackGroundColor = (BooleanSetting)booleanSetting3.getSetting(() -> this.canExecute5());
        ColorSetting colorSetting = new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 150));
        this.settingBackGroundColor = (ColorSetting)colorSetting.getSetting(() -> this.isBackGroundColorVisible2());
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        ShaderOptions shaderOptions2 = shaderOptions.getShaderOptions(black);
        this.settingBackGroundColor2 = (ShaderOptions)shaderOptions2.getSetting(() -> this.isBackGroundColorVisible());
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (this.checkCondition()) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, (float)(scaledResolution.getScaledHeight() - 48), 0.0F);
            GlStateManager.enableBlend();
            GuiNewChat internalLoggerEntry = minecraft.ingameGUI.getInternalLoggerEntry();
            int index = minecraft.ingameGUI.getIndex();
            boolean enabled = this.settingBackGround.isEnabled();
            int index2;
            if (this.settingCustomBackGroundColor.isEnabled() && this.settingBackGround.isEnabled()) {
                index2 = this.settingBackGroundColor.getInt12(0);
            } else {
                index2 = Integer.MIN_VALUE;
            }

            internalLoggerEntry.processAction(index, enabled, index2);
            GlStateManager.popMatrix();
        }

        RenderUtils.processAction();
    }

    private boolean canExecute5() {
        return this.settingRemove.isEnabled() ? false : this.settingBackGround.isEnabled();
    }

    private boolean canExecute6() {
        return !this.settingRemove.isEnabled();
    }

    private boolean canExecute7() {
        return !this.settingRemove.isEnabled();
    }

    private boolean checkCondition() {
        return !this.settingRemove.isEnabled();
    }

    private boolean isBackGroundColorVisible() {
        return this.settingRemove.isEnabled() ? false : this.settingBackGround.isEnabled();
    }

    private boolean isBackGroundColorVisible2() {
        if (this.settingRemove.isEnabled()) {
            return false;
        } else {
            return !this.settingBackGround.isEnabled() ? false : this.settingCustomBackGroundColor.isEnabled();
        }
    }
}
