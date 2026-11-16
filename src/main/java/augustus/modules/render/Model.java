package augustus.modules.render;

import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderManager;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.MathHelper;

public class Model extends Module implements RenderListener, TickListener {
    public NumberSetting settingXCoord = new NumberSetting(this, "X-Coord", 1.0, 0.0, 100.0, 2);
    public NumberSetting settingYCoord = new NumberSetting(this, "Y-Coord", 8.0, 0.0, 100.0, 2);
    public NumberSetting settingScale = new NumberSetting(this, "Scale", 30.0, 10.0, 100.0, 0);
    public BooleanSetting settingYawRot = new BooleanSetting(this, "YawRot", true);
    public float motionMultiplier1 = 0.0F;
    public float motionMultiplier = 0.0F;

    public Model() {
        super("Model", "Model", new Color(252, 186, 3), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        int index = (int)(
            1.0 * this.settingScale.getValue() / 2.0
                + ((double)scaledResolution.getScaledWidth() - 1.0 * this.settingScale.getValue()) / 100.0 * this.settingXCoord.getValue()
        );
        int index2 = (int)(
            2.0 * this.settingScale.getValue()
                + ((double)scaledResolution.getScaledHeight() - 2.0 * this.settingScale.getValue()) / 100.0 * this.settingYCoord.getValue()
        );
        this.applyEntityPlayerSP(index, index2, (int)this.settingScale.getValue(), 0.0F, 0.0F, minecraft.thePlayer);
        RenderUtils.processAction();
    }

    @Override
    public void onRunTick() {
        this.motionMultiplier = this.motionMultiplier1;
        this.motionMultiplier1 = minecraft.thePlayer.rotationYaw;
    }

    private void applyEntityPlayerSP(int x, int y, int z, float value, float value2, EntityPlayerSP player) {
        GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.applyState6();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, 50.0F);
        GlStateManager.translate3f3((float)(-z), (float)z, (float)z);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float scaleFactor8 = player.scaleFactor8;
        float rotationYaw = player.rotationYaw;
        float rotationPitch = player.rotationPitch;
        float scaleFactor22 = player.scaleFactor22;
        float scaleFactor12 = player.scaleFactor12;
        float scaleFactor21 = player.scaleFactor21;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(value2 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        player.scaleFactor8 = (float)Math.atan((double)(value / 40.0F)) * 20.0F;
        float value3;
        if (!this.settingYawRot.isEnabled()) {
            value3 = 0.0F;
        } else {
            value3 = MathHelper.clamp_float(this.motionMultiplier - this.motionMultiplier1, -8.0F, 8.0F);
        }

        player.rotationYaw = 0.0F + value3;
        player.rotationPitch = 0.0F;
        player.scaleFactor21 = 0.0F;
        player.scaleFactor12 = player.rotationYaw;
        player.scaleFactor22 = player.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        renderManager.setScaleFactor(180.0F);
        renderManager.setState1(false);
        player.setState1(false);
        renderManager.renderEntity(player, 0.0, 0.0, 0.0, 0.0F, 1.0F);
        player.setState1(true);
        renderManager.setState1(true);
        player.scaleFactor8 = scaleFactor8;
        player.scaleFactor21 = scaleFactor21;
        player.rotationYaw = rotationYaw;
        player.rotationPitch = rotationPitch;
        player.scaleFactor22 = scaleFactor22;
        player.scaleFactor12 = scaleFactor12;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.applyState8();
        GlStateManager.enableState5(OpenGlHelper.defaultTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
        GlStateManager.popMatrix();
    }
}
