package augustus.modules.render;

import client.event.RenderListener;
import client.gui.KeyStrokeRenderer;
import client.module.Category;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.ColorSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class KeyStrokes extends Module implements RenderListener {
    public KeyStrokeRenderer keyW;
    public KeyStrokeRenderer keyA;
    public KeyStrokeRenderer keyS;
    public KeyStrokeRenderer keyD;
    public KeyStrokeRenderer keyLMB;
    public KeyStrokeRenderer keyRMB;
    public ColorSetting settingBackGroundColor;
    public ColorSetting settingBackGroundColorPressed;
    public ColorSetting settingKeyColor;
    public ColorSetting settingKeyColorPressed;
    public NumberSetting settingScale;
    public NumberSetting settingFadeSpeed;
    public NumberSetting settingX;
    public NumberSetting settingY;
    public ShaderOptions settingY2;
    public ArrayList itemList3;

    public KeyStrokes() {
        super("KeyStrokes", "TastenStreicheln", new Color(95, 218, 222), Category.RENDER);
        this.keyW = new KeyStrokeRenderer("W", minecraft.gameSettings.keyBindForward, 20, 0, 18, 18);
        this.keyA = new KeyStrokeRenderer("A", minecraft.gameSettings.keyBindLeft, 0, 20, 18, 18);
        this.keyS = new KeyStrokeRenderer("S", minecraft.gameSettings.keyBindBack, 20, 20, 18, 18);
        this.keyD = new KeyStrokeRenderer("D", minecraft.gameSettings.keyBindRight, 40, 20, 18, 18);
        this.keyLMB = new KeyStrokeRenderer("LMB", minecraft.gameSettings.keyBindAttack, 0, 40, 28, 18);
        this.keyRMB = new KeyStrokeRenderer("RMB", minecraft.gameSettings.keyBindUseItem, 30, 40, 28, 18);
        this.settingBackGroundColor = new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 100));
        this.settingBackGroundColorPressed = new ColorSetting(this, "BackGroundColorPressed", new Color(100, 100, 100, 100));
        this.settingKeyColor = new ColorSetting(this, "KeyColor", Color.LIGHT_GRAY);
        this.settingKeyColorPressed = new ColorSetting(this, "KeyColorPressed", Color.white);
        this.settingScale = new NumberSetting(this, "Scale", 2.0, 1.0, 4.0, 1);
        this.settingFadeSpeed = new NumberSetting(this, "FadeSpeed", 300.0, 0.0, 1000.0, 0);
        this.settingX = new NumberSetting(this, "X", 80.0, 0.0, 100.0, 2);
        this.settingY = new NumberSetting(this, "Y", 80.0, 0.0, 100.0, 2);
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingY2 = shaderOptions.getShaderOptions(black);
        this.itemList3 = new ArrayList();
        this.itemList3.add(this.keyW);
        this.itemList3.add(this.keyA);
        this.itemList3.add(this.keyS);
        this.itemList3.add(this.keyD);
        this.itemList3.add(this.keyLMB);
        this.itemList3.add(this.keyRMB);
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
        GL11.glPushMatrix();
        GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
        GL11.glScaled(this.settingScale.getValue(), this.settingScale.getValue(), 1.0);
        double offset = (double)minecraft.displayWidth / this.settingScale.getValue();
        double offset2 = (double)minecraft.displayHeight / this.settingScale.getValue();
        int int6 = MathHelper.truncateDoubleToInt(offset);
        int int62 = MathHelper.truncateDoubleToInt(offset2);
        int index = (int)((double)(int6 - 58) * (this.settingX.getValue() / 100.0));
        int index2 = (int)((double)(int62 - 58) * (this.settingY.getValue() / 100.0));
        Iterator iterator = this.itemList3.iterator();

        while (iterator.hasNext()) {
            ((KeyStrokeRenderer)iterator.next()).processAction(index, index2);
        }

        GL11.glPopMatrix();
        RenderUtils.processAction();
    }
}
