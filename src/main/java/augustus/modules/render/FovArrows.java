package augustus.modules.render;

import client.Client;
import client.ClientGlobals;
import client.module.Category;
import client.module.Module;
import client.render.EntityEspRenderer;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.RandomUtil;
import client.util.SimulatedPlayer;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class FovArrows extends Module {
    public NumberSetting settingMaxTargetDistance = new NumberSetting(this, "MaxTargetDistance", 20.0, 1.0, 30.0, 0);
    public NumberSetting settingCrossHairDistance = new NumberSetting(this, "CrossHairDistance", 30.0, 0.0, 50.0, 0);
    public NumberSetting settingScale = new NumberSetting(this, "Scale", 2.0, 1.0, 4.0, 2);
    public ColorSetting settingColor = new ColorSetting(this, "Color", Color.red);
    public BooleanSetting settingDistAlpha = new BooleanSetting(this, "DistAlpha", true);
    public BooleanSetting settingDistColor = new BooleanSetting(this, "DistColor", true);
    public BooleanSetting settingRenderNames = new BooleanSetting(this, "RenderNames", false);
    public BooleanSetting settingOutline = new BooleanSetting(this, "Outline", true);
    public BooleanSetting settingDifferentOutlineColor;
    public ColorSetting settingOutlineColor;
    public ShaderOptions settingOutlineColor2;
    public ArrayList itemList3;
    public EntityEspRenderer entityEspRenderer;

    public FovArrows() {
        super("FovArrows", "SichtfeldPfeile", new Color(124, 83, 245), Category.RENDER);
        BooleanSetting booleanSetting = new BooleanSetting(this, "DifferentOutlineColor", true);
        this.settingDifferentOutlineColor = (BooleanSetting)booleanSetting.getSetting(this.settingOutline::isEnabled);
        ColorSetting colorSetting = new ColorSetting(this, "OutlineColor", Color.black);
        this.settingOutlineColor = (ColorSetting)colorSetting.getSetting(() -> this.isOutlineColorVisible());
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingOutlineColor2 = shaderOptions.getShaderOptions(black).getShaderOptions5();
        this.itemList3 = new ArrayList();
        this.entityEspRenderer = new EntityEspRenderer();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
    }

    public void applyRenderPass(RenderPass renderPass) {
        int index3 = 0;
        if (minecraft.thePlayer != null) {
            for (EntityPlayer player : (Iterable<EntityPlayer>) this.itemList3) {
                float value = (float)(player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6);
                float value2 = (float)(player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6);
                float value3 = (float)(player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6);
                double offset = (double)(
                SimulatedPlayer.getFloatArray2(
                
                (double)value,
                (double)value2,
                (double)value3,
                Client.getClient().getViaProtocolHelper().scaleFactor1,
                minecraft.thePlayer.rotationPitch
                )[0]
                % 360.0F
                );
                ScaledResolution scaledResolution = new ScaledResolution(minecraft);
                GL11.glPushMatrix();
                GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
                GL11.glScaled(this.settingScale.getValue(), this.settingScale.getValue(), 1.0);
                ScaledResolution scaledResolution2 = new ScaledResolution(
                (double)minecraft.displayWidth * (1.0 / this.settingScale.getValue()),
                (double)minecraft.displayHeight * (1.0 / this.settingScale.getValue()),
                (int)this.settingScale.getValue()
                );
                float value4 = (float)(
                (double)(scaledResolution2.getScaledWidth() / 2) + this.settingCrossHairDistance.getValue() * Math.sin((Math.PI / 180.0) * offset)
                );
                float value5 = (float)(
                (double)(scaledResolution2.getScaledHeight() / 2) + this.settingCrossHairDistance.getValue() * -Math.cos((Math.PI / 180.0) * offset)
                );
                if (this.settingRenderNames.isEnabled()) {
                    GL11.glScaled(0.5, 0.5, 1.0);
                    minecraft.fontRendererObj
                    .getIndex(
                    player.getText(),
                    (value4 - (float)minecraft.fontRendererObj.getIndex(player.getText()) / 4.0F) * 2.0F,
                    (value5 + 8.0F) * 2.0F,
                    this.settingOutlineColor2.getIndex(renderPass, -1)
                    );
                    GL11.glScaled(2.0, 2.0, 1.0);
                }

                GlStateManager.translate(value4, value5, 5.0F);
                GlStateManager.rotate((float)offset + 180.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(-value4, -value5, 5.0F);
                int int12 = this.settingColor.getInt12(index3);
                double offset2 = 255.0 / MathHelper.getDouble7(this.settingMaxTargetDistance.getValue(), 0.0, 255.0);
                if (this.settingDistAlpha.isEnabled()) {
                    int12 = RandomUtil.getInt13(
                    int12, (int)(255.0 - Math.min((double)minecraft.thePlayer.getDistanceSqToEntity(player) * offset2, 255.0))
                    );
                }

                if (this.settingDistColor.isEnabled()) {
                    float[] floatArray = RandomUtil.getFloatArray(int12);
                    float value6 = 30.0F / MathHelper.clamp_float((float)this.settingMaxTargetDistance.getValue() - 6.0F, 0.0F, 255.0F);
                    int12 = RandomUtil.getInt13(
                    Color.HSBtoRGB(
                    (float)(
                    0.01141552511
                    * (double)(
                    MathHelper.clamp_float(
                    minecraft.thePlayer.getDistanceToEntity(player) - 6.0F,
                    0.0F,
                    (float)(this.settingMaxTargetDistance.getValue() - 6.0)
                    )
                    * value6
                    )
                    ),
                    floatArray[1],
                    floatArray[2]
                    ),
                    RandomUtil.processAction3(int12)
                    );
                }

                int index = this.settingOutlineColor2.getIndex(renderPass, int12);
                RenderUtils.processAction(value4, value5, 10.0F, index);
                if (this.settingOutline.isEnabled()) {
                    GL11.glLineWidth(1.0F);
                    if (this.settingDifferentOutlineColor.isEnabled()) {
                        index = this.settingOutlineColor.getInt12(index3);
                    }

                    int index2 = this.settingOutlineColor2.getIndex(renderPass, index);
                    byte state = (byte)(this.settingDifferentOutlineColor.isEnabled() ? RandomUtil.getInt13(index2, 255) : index2);
                    RenderUtils.processAction2(value4, value5, 10.0F, state);
                }

                GL11.glPopMatrix();
                index3++;
            }
        }
    }

    private boolean isOutlineColorVisible() {
        return !this.settingDifferentOutlineColor.isEnabled() ? false : this.settingOutline.isEnabled();
    }

    public boolean checkEntityPlayer(EntityPlayer player) {
        if (ClientGlobals.moduleManager.antiBot.checkEntity2(player)) {
            return false;
        } else {
            return ClientGlobals.moduleManager.midClick.checkEntity(player) && !ClientGlobals.moduleManager.midClick.enabled2
            ? false
            : !ClientGlobals.moduleManager.teams.checkEntity2(player);
        }
    }

    public void processShort() {
        this.entityEspRenderer.processDouble(minecraft.getRenderViewEntity().posX, minecraft.getRenderViewEntity().posY, minecraft.getRenderViewEntity().posZ);
        this.itemList3.clear();
        if (minecraft.thePlayer != null) {
            for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
                if (entity instanceof EntityPlayer
                && !(entity instanceof EntityPlayerSP)
                && ChatUtil.checkEntity5(entity, true, false, false, false, false, true, false, false, false)) {
                    EntityPlayer player = (EntityPlayer)entity;
                    if (this.checkEntityPlayer(player)
                    && !this.entityEspRenderer.checkAxisAlignedBB(player.getBoundingBox2())
                    && (double)minecraft.thePlayer.getDistanceToEntity(player) < this.settingMaxTargetDistance.getValue()) {
                        this.itemList3.add(player);
                    }
                }
            }
        }
    }
}
