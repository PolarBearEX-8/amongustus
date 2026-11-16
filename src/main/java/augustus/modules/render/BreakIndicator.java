package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.KeybindAnimation;
import client.util.MovementHandler;
import client.util.MovingObjectType;
import client.util.RandomUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class BreakIndicator extends Module implements RenderListener {
    public NumberSetting settingDecimalPlaces = new NumberSetting(this, "DecimalPlaces", 1.0, 0.0, 4.0, 0);
    public NumberSetting settingScale = new NumberSetting(this, "Scale", 2.0, 1.0, 4.0, 2);
    public NumberSetting settingPosX = new NumberSetting(this, "PosX", 52.0, 0.0, 100.0, 2);
    public NumberSetting settingPosY = new NumberSetting(this, "PosY", 50.0, 0.0, 100.0, 2);
    public ColorSetting settingColor = new ColorSetting(this, "Color", Color.white);
    public BooleanSetting settingBackGround = new BooleanSetting(this, "BackGround", true);
    public ColorSetting settingBackGroundColor;
    public ShaderOptions settingBackGroundColor2;
    public ModeSetting settingToggleAnimation;
    public NumberSetting settingAnimationTime;
    public KeybindAnimation keybindAnimation2;
    public float motionMultiplier;

    public BreakIndicator() {
        super("BreakIndicator", "BlockBrechAnzeige", new Color(50, 168, 123), Category.RENDER);
        ColorSetting colorSetting = new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 100));
        this.settingBackGroundColor = (ColorSetting)colorSetting.getSetting(this.settingBackGround::isEnabled);
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingBackGroundColor2 = shaderOptions.getShaderOptions(black);
        this.settingToggleAnimation = new ModeSetting(this, "ToggleAnimation", "easeInOutCubic", KeybindAnimation.getStringArray(true));
        this.settingAnimationTime = new NumberSetting(this, "AnimationTime", 500.0, 0.0, 1000.0, 0).getNumberSetting(() -> this.isAnimationTimeVisible());
        this.keybindAnimation2 = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
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
        float value;
        if (minecraft.objectMouseOver != null && minecraft.playerController.state && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            value = minecraft.playerController.getScaleFactor();
        } else {
            value = 0.0F;
        }

        if (value > 0.0F && this.motionMultiplier == 0.0F) {
            this.keybindAnimation2
            .updateEasingFunction(
            (int)this.settingAnimationTime.getValue(),
            KeybindAnimation.getEasingFunction(this.settingToggleAnimation.getSelected()),
            1,
            true
            );
        } else {
            if (value == 0.0F
            && this.motionMultiplier > 0.0F) {
                this.keybindAnimation2
                .updateEasingFunction(
                (int)this.settingAnimationTime.getValue(),
                KeybindAnimation.getEasingFunction(this.settingToggleAnimation.getSelected()),
                0,
                true
                );
            }
        }

        this.motionMultiplier = value;
        if (this.keybindAnimation2.getDouble2() != 1.0
        && !this.settingToggleAnimation.getSelected().equals("None")
        || value != 0.0F) {
            GL11.glPushMatrix();
            float scaleFactor = (float)scaledResolution.getScaleFactor();
            GL11.glScaled((double)(1.0F / scaleFactor), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
            String text = RuntimeBridge.formatNumberString(
            (double)(minecraft.playerController.getScaleFactor() * 100.0F), Math.max((int)this.settingDecimalPlaces.getValue(), 0)
            )
            + "%";
            float value2 = (float)(minecraft.fontRendererObj.getIndex(text) + 4) / 2.0F;
            GL11.glTranslated(
            (double)value2 * this.settingScale.getValue()
            + this.settingPosX.getValue()
            / 100.0
            * ((double)minecraft.displayWidth - (double)(value2 * 2.0F) * this.settingScale.getValue()),
            6.0 * this.settingScale.getValue()
            + this.settingPosY.getValue()
            / 100.0
            * ((double)minecraft.displayHeight - 12.0 * this.settingScale.getValue()),
            0.0
            );
            GL11.glScaled(this.settingScale.getValue(), this.settingScale.getValue(), 1.0);
            double double3 = this.keybindAnimation2.getDouble3();
            if (this.keybindAnimation2.getDouble2() < 1.0) {
                GL11.glScaled(double3, double3, 1.0);
            }

            int index = this.settingBackGround.isEnabled() ? this.settingBackGroundColor.getInt12(0) : 0;
            int int12 = this.settingColor.getInt12(0);
            MovementHandler.processBoolean(
            this.settingBackGroundColor2.canExecute(), this.settingBackGroundColor2.canExecute2()
            );
            MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass(value2, index, text, int12, renderPass));
            GL11.glPopMatrix();
            RenderUtils.processAction();
        }
    }

    private boolean isAnimationTimeVisible() {
        return !this.settingToggleAnimation.getSelected().equals("None");
    }

    private void applyRenderPass(float value, int index2, String text, int index3, RenderPass renderPass) {
        if (this.settingBackGround.isEnabled()) {
            double offset = (double)(-value);
            double offset2 = (double)value;
            int index = RandomUtil.getIndex(renderPass, index2);
            RenderUtils.processDouble4(offset, -6.0, offset2, 6.0, this.settingBackGroundColor2.getIndex(renderPass, index));
        }

        if (renderPass != RenderPass.BLUR) {
            minecraft.fontRendererObj.getIndex(text, -value + 2.0F, -3.5F, index3);
        }
    }
}
