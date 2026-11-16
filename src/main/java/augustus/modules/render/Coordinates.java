package augustus.modules.render;

import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.MovementHandler;
import client.util.RandomUtil;
import client.util.RunnableTask;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class Coordinates extends Module implements RenderListener {
    public NumberSetting settingPosX = new NumberSetting(this, "PosX", 0.0, 0.0, 100.0, 2);
    public NumberSetting settingPosY = new NumberSetting(this, "PosY", 0.0, 0.0, 100.0, 2);
    public NumberSetting settingScale = new NumberSetting(this, "Scale", 2.0, 1.0, 4.0, 1);
    public ColorSetting settingColor1 = new ColorSetting(this, "Color1", new Color(85, 85, 85));
    public ColorSetting settingColor2 = new ColorSetting(this, "Color2", new Color(-1));
    public BooleanSetting settingBackGround = new BooleanSetting(this, "BackGround", true);
    public ColorSetting settingBackGroundColor;
    public ShaderOptions settingBackGround2;

    public Coordinates() {
        super("Coordinates", "Koordinaten", new Color(209, 69, 81), Category.RENDER);
        ColorSetting colorSetting = new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 100));
        this.settingBackGroundColor = (ColorSetting)colorSetting.getSetting(() -> this.settingBackGround.isEnabled());
        ShaderOptions shaderOptions = new ShaderOptions(this, "BackGround");
        Color black = Color.black;
        ShaderOptions shaderOptions2 = shaderOptions.getShaderOptions(black);
        this.settingBackGround2 = (ShaderOptions)shaderOptions2.getSetting(() -> this.settingBackGround.isEnabled());
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
        int x = (int)minecraft.thePlayer.posX;
        int y = (int)minecraft.thePlayer.posY;
        int z = (int)minecraft.thePlayer.posZ;
        int int12 = this.settingColor1.getInt12(0);
        int int122 = this.settingColor2.getInt12(0);
        GL11.glPushMatrix();
        GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
        double value = this.settingScale.getValue();
        GL11.glScaled(value, this.settingScale.getValue(), 1.0);
        double displayWidth = (double)minecraft.displayWidth;
        double offset = displayWidth / this.settingScale.getValue();
        double displayHeight = (double)minecraft.displayHeight;
        double offset2 = displayHeight / this.settingScale.getValue();
        int int6 = MathHelper.truncateDoubleToInt(offset);
        int int62 = MathHelper.truncateDoubleToInt(offset2);
        String text = "X: " + x + "  Y: " + y + "  Z: " + z;
        float value2 = (float)minecraft.fontRendererObj.getIndex(text);
        double offset3 = (double)((float)int6 - value2);
        int index = (int)(offset3 * (this.settingPosX.getValue() / 100.0));
        double offset4 = (double)(int62 - minecraft.fontRendererObj.indexOffset);
        int index2 = (int)(offset4 * (this.settingPosY.getValue() / 100.0));
        int int123 = this.settingBackGroundColor.getInt12(0);
        boolean enabled;
        if (this.settingBackGround.isEnabled() && this.settingBackGround2.canExecute()) {
            enabled = true;
        } else {
            enabled = false;
        }

        boolean enabled2;
        if (this.settingBackGround.isEnabled() && this.settingBackGround2.canExecute2()) {
            enabled2 = true;
        } else {
            enabled2 = false;
        }

        MovementHandler.processBoolean(enabled, enabled2);
        MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass(index, index2, value2, int123, x, y, int12, int122, z, renderPass));
        GL11.glPopMatrix();
        RenderUtils.processAction();
    }

    private void applyRenderPass(int index2, int index3, float value, int x, int x2, int x3, int x4, int y, int z, RenderPass renderPass) {
        if (renderPass == RenderPass.NONE) {
            if (this.settingBackGround.isEnabled()) {
                double offset = (double)(index2 - 1);
                double offset2 = (double)(index3 - 1);
                double offset3 = (double)((float)index2 + value + 1.0F);
                RenderUtils.processDouble4(offset, offset2, offset3, (double)(index3 + minecraft.fontRendererObj.indexOffset), x);
            }

            float value2 = (float)minecraft.fontRendererObj.getIndex("" + x2);
            float value3 = (float)minecraft.fontRendererObj.getIndex("" + x3);
            minecraft.fontRendererObj.getIndex2("X: ", (float)index2, (float)index3, x4);
            minecraft.fontRendererObj.getIndex2("" + x2, (float)(index2 + 11), (float)index3, y);
            minecraft.fontRendererObj.getIndex2("  Y: ", (float)(index2 + 11) + value2, (float)index3, x4);
            minecraft.fontRendererObj.getIndex2("" + x3, (float)(index2 + 11) + value2 + 17.0F, (float)index3, y);
            minecraft.fontRendererObj.getIndex2("  Z: ", (float)(index2 + 11) + value2 + 17.0F + value3, (float)index3, x4);
            minecraft.fontRendererObj.getIndex2("" + z, (float)(index2 + 11) + value2 + 17.0F + value3 + 17.0F, (float)index3, y);
        } else {
            double offset4 = (double)(index2 - 1);
            double offset5 = (double)(index3 - 1);
            double offset6 = (double)((float)index2 + value + 1.0F);
            double offset7 = (double)(index3 + minecraft.fontRendererObj.indexOffset);
            int index = RandomUtil.getIndex(renderPass, x);
            RenderUtils.processDouble4(offset4, offset5, offset6, offset7, this.settingBackGround2.getInt3(index));
        }
    }
}
