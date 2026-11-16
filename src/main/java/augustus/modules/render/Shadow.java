package augustus.modules.render;

import augustus.modes.render.EspRenderMode;
import client.ClientGlobals;
import client.event.EventRenderEntity;
import client.event.Listener;
import client.event.RenderEntityListener;
import client.event.TickListener;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.ShaderProgram;
import client.render.StencilFramebuffer;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.ShadowItemsOnGroundType;
import client.setting.ShadowTargetEntity;
import client.util.BooleanSupplier;
import client.util.BuildManager;
import client.util.ChatUtil;
import client.util.MovementHandler;
import client.util.StencilBlurUtils;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.function.Function;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shadow extends Module implements TickListener, RenderEntityListener {
    public BooleanSetting settingCustomColor = new BooleanSetting(this, "CustomColor", true);
    public ColorSetting settingColor;
    public NumberSetting settingSigma;
    public BooleanSetting settingShadowEntities;
    public MultiSelectSetting settingEntities;
    public MultiSelectSetting settingItemsToShadow;
    public Framebuffer framebuffer;
    public Framebuffer framebuffer2;
    public Framebuffer framebuffer3;
    public ShaderProgram alphaBlurShader;
    public double reachDistance;
    public boolean expanded2;
    public FloatBuffer floatBuffer;
    public static String displayName2;

    public Shadow() {
        super("Shadow", "Schatten", new Color(170, 181, 182, 255), Category.RENDER);
        ColorSetting colorSetting = new ColorSetting(this, "Color", new Color(0, 0, 0, 1));
        this.settingColor = (ColorSetting)colorSetting.getSetting(() -> this.settingCustomColor.isEnabled());
        this.settingSigma = new NumberSetting(this, "Sigma", 5.0, 0.0, 30.0, 1);
        this.settingShadowEntities = new BooleanSetting(this, "ShadowEntities", false);
        this.settingEntities = new MultiSelectSetting(this, "Entities", ShadowTargetEntity.values()).getMultiSelectSetting(() -> this.settingShadowEntities.isEnabled());
        this.settingItemsToShadow = new MultiSelectSetting(this, "ItemsToShadow", ShadowItemsOnGroundType.values());
        this.framebuffer = new StencilFramebuffer(minecraft.displayWidth, minecraft.displayHeight, false);
        this.framebuffer2 = new StencilFramebuffer(minecraft.displayWidth, minecraft.displayHeight, false);
        this.framebuffer3 = new StencilFramebuffer(minecraft.displayWidth, minecraft.displayHeight, true);
        this.floatBuffer = null;
        this.alphaBlurShader = new ShaderProgram("vertex.vert", this.settingCustomColor.isEnabled() ? "alphaBlur2.glsl" : "alphaBlur.glsl");
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(31);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.alphaBlurShader = new ShaderProgram(
            "vertex.vert", this.settingCustomColor.isEnabled() ? "alphaBlur2.glsl" : "alphaBlur.glsl"
        );
    }

    @Override
    public void onRenderEntity(EventRenderEntity event) {
        if (this.settingShadowEntities.isEnabled()) {
            if (!ClientGlobals.moduleManager.esp.isToggled() || !EspRenderMode.VANILLA.isSelected()) {
                EntityLivingBase targetEntity = event.getTargetEntity();
                if (!ShadowTargetEntity.MYSELF.isSelected() || targetEntity != minecraft.thePlayer) {
                    if (targetEntity == minecraft.thePlayer) {
                        return;
                    }

                    if (!ChatUtil.checkEntity5(
                        targetEntity,
                        ShadowTargetEntity.PLAYERS.isSelected(),
                        ShadowTargetEntity.MOBS.isSelected(),
                        
                        ShadowTargetEntity.ANIMALS.isSelected(),
                        ShadowTargetEntity.VILLAGER.isSelected(),
                        ShadowTargetEntity.ARMOR_STANDS.isSelected(),
                        ShadowTargetEntity.INVISIBLE.isSelected(),
                        false,
                        false,
                        true
                    )) {
                        return;
                    }
                }

                MovementHandler.processAction3();
                MovementHandler.processAction11();
                EntityLivingBase targetEntity2 = event.getTargetEntity();
                RendererLivingEntity rendererLivingEntity = event.getRendererLivingEntity();
                MovementHandler.applyRunnableTask(!targetEntity.isState1(), renderPass -> onRenderEntity2(event, targetEntity2, rendererLivingEntity, targetEntity, renderPass));
            }
        }
    }

    @Override
    public void applyJsonObject(JsonObject jsonObject) {
        super.applyJsonObject(jsonObject);
        if (minecraft.isSingleplayer()) {
            this.alphaBlurShader = new ShaderProgram(
                "vertex.vert", this.settingCustomColor.isEnabled() ? "alphaBlur2.glsl" : "alphaBlur.glsl"
            );
        }
    }

    @Override
    public void updateListener(Listener listener, JsonObject jsonObject, boolean enabled, Function function) {
        super.updateListener(listener, jsonObject, enabled, function);
        if (minecraft.isSingleplayer()) {
            this.alphaBlurShader = new ShaderProgram(
                "vertex.vert", this.settingCustomColor.isEnabled() ? "alphaBlur2.glsl" : "alphaBlur.glsl"
            );
        }
    }

    @Override
    public void applyJsonObject2(JsonObject jsonObject) {
        super.applyJsonObject2(jsonObject);
        if (minecraft.isSingleplayer()) {
            this.alphaBlurShader = new ShaderProgram(
                "vertex.vert", this.settingCustomColor.isEnabled() ? "alphaBlur2.glsl" : "alphaBlur.glsl"
            );
        }
    }

    @Override
    public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function function) {
        JsonObject jsonObject2 = super.getJsonObject(listener, jsonObject, function);
        if (!minecraft.isSingleplayer()) {
            return jsonObject2;
        } else {
            this.alphaBlurShader = new ShaderProgram(
                "vertex.vert", this.settingCustomColor.isEnabled() ? "alphaBlur2.glsl" : "alphaBlur.glsl"
            );
            return jsonObject2;
        }
    }

    @Override
    public void onRunTick() {
        if (this.expanded2 != this.settingCustomColor.isEnabled()) {
            if (this.settingCustomColor.isEnabled()) {
                this.alphaBlurShader = new ShaderProgram("vertex.vert", "alphaBlur2.glsl");
            } else {
                this.alphaBlurShader = new ShaderProgram("vertex.vert", "alphaBlur.glsl");
            }
        }

        this.expanded2 = this.settingCustomColor.isEnabled();
    }

    private static void onRenderEntity2(EventRenderEntity event, EntityLivingBase livingEntity, RendererLivingEntity rendererLivingEntity, Entity entity, RenderPass renderPass) {
        double offset = entity.posX - entity.lastTickPosX;
        double offset2 = entity.lastTickPosX + offset * (double)minecraft.getTimer().scaleFactor6;
        double offset3 = entity.posY - entity.lastTickPosY;
        double offset4 = entity.lastTickPosY + offset3 * (double)minecraft.getTimer().scaleFactor6;
        double offset5 = entity.posZ - entity.lastTickPosZ;
        double offset6 = entity.lastTickPosZ + offset5 * (double)minecraft.getTimer().scaleFactor6;
        event.applyEntityLivingBase(livingEntity, rendererLivingEntity, offset2, offset4, offset6, true);
        MovementHandler.processAction11();
    }

    public void applyScaledResolution(ScaledResolution scaledResolution) {
        if (minecraft.getFramebuffer() == null
            || minecraft.getFramebuffer().indexOffset < 0
            || this.framebuffer == null
            || this.framebuffer.indexOffset < 0
            || this.framebuffer2 == null
            || this.framebuffer2.indexOffset < 0
            || this.framebuffer3 == null
            || this.framebuffer3.indexOffset < 0) {
            return;
        }
        if (Display.isActive()) {
            this.applyRunnable(() -> updateState2(), false, false);
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.pushMatrix();
            GlStateManager.applyState14();
            GlStateManager.alphaFunc(516, 0.0F);
            GlStateManager.glStateAction3(770, 771);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            GlStateManager.applyState3();
            double coordOffset = scaledResolution.getCoordOffset();
            double coordOffset3 = scaledResolution.getCoordOffset3();
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            this.alphaBlurShader.processAction();
            this.updateState3();
            this.applyFramebuffer(0, this.framebuffer3.indexOffset, this.framebuffer, (int)coordOffset, (int)coordOffset3);
            this.applyFramebuffer(1, this.framebuffer.indexOffset, this.framebuffer2, (int)coordOffset, (int)coordOffset3);
            this.alphaBlurShader.processAction2();
            StencilBlurUtils.updateState();
            StencilBlurUtils.updateState2();
            this.processAction(this.framebuffer3.indexOffset, coordOffset, coordOffset3);
            StencilBlurUtils.updateInt(0);
            this.processAction(this.framebuffer2.indexOffset, coordOffset, coordOffset3);
            StencilBlurUtils.updateState3();
            GlStateManager.setActiveTexture(0);
            GlStateManager.alphaFunc(516, 0.2F);
            GlStateManager.applyState10();
            GlStateManager.popMatrix();
            GlStateManager.enableBlend();
            this.framebuffer3.updateState();
            minecraft.getFramebuffer().setState(false);
            GlStateManager.applyState();
        }
    }

    public static void updateText(String text) {
        displayName2 = text;
    }

    public void updateState3() {
        GL20.glUniform2f(this.alphaBlurShader.processAction("texelSize"), 1.0F / (float)minecraft.displayWidth, 1.0F / (float)minecraft.displayHeight);
        GL20.glUniform1i(this.alphaBlurShader.processAction("texture"), 0);
        float sigma = (float)this.settingSigma.getValue();
        GL20.glUniform1f(this.alphaBlurShader.processAction("sigma"), sigma);
        double reachDistance2 = this.reachDistance;
        double value = this.settingSigma.getValue();
        if (reachDistance2 != value || this.floatBuffer == null) {
            double value2 = this.settingSigma.getValue();
            float value3 = (float)(value2 * 1.75);
            int index3 = 0;

            for (float i = -value3; i <= value3; i++) {
                index3++;
            }

            this.floatBuffer = BufferUtils.createFloatBuffer(index3);
            float value4 = -value3;
            FloatBuffer buffer;
            if (!(value4 <= value3)) {
                buffer = this.floatBuffer;
            } else {
                this.floatBuffer.put(this.getFloat(value4, (float)this.settingSigma.getValue()));

                for (float i2 = value4 + 1.0F; i2 <= value3; i2++) {
                    this.floatBuffer.put(this.getFloat(i2, (float)this.settingSigma.getValue()));
                }

                buffer = this.floatBuffer;
            }

            ((Buffer)buffer).flip();
        }

        this.reachDistance = this.settingSigma.getValue();
        GL20.glUniform1(this.alphaBlurShader.processAction("sigmas"), this.floatBuffer);
        this.settingColor.getInt12(0);
        GL20.glUniform3f(
            this.alphaBlurShader.processAction("startColor"),
            this.settingColor.getFloat(),
            this.settingColor.getFloat2(),
            this.settingColor.getFloat4()
        );
    }

    public void processAction() {
        minecraft.getFramebuffer().setState(true);
    }

    private float getFloat(float value, float value2) {
        return (float)(Math.exp((double)(-(value * value) / (2.0F * value2 * value2))) / Math.sqrt((Math.PI * 2) * (double)value2 * (double)value2));
    }

    public void applyRunnable(Runnable runnable, boolean enabled, boolean enabled2) {
        if (minecraft.getFramebuffer() == null
            || minecraft.getFramebuffer().indexOffset < 0
            || this.framebuffer3 == null
            || this.framebuffer3.indexOffset < 0) {
            return;
        }
        if (!enabled2 || !BuildManager.canExecute()) {
            if (Display.isActive()) {
                this.framebuffer3.setState(false);
                runnable.run();
                minecraft.getFramebuffer().setState(false);
                if (enabled) {
                    runnable.run();
                }
            }
        }
    }

    public void updateState() {
        if (this.framebuffer3 != null && this.framebuffer3.indexOffset >= 0) {
            this.framebuffer3.setState(false);
        }
    }

    private void processAction(int index, double offset, double offset2) {
        GlStateManager.disableBlend();
        GL11.glBindTexture(3553, index);
        GL11.glBegin(7);
        GL11.glTexCoord2d(0.0, 1.0);
        GL11.glVertex2d(0.0, 0.0);
        GL11.glTexCoord2d(0.0, 0.0);
        GL11.glVertex2d(0.0, offset2);
        GL11.glTexCoord2d(1.0, 0.0);
        GL11.glVertex2d(offset, offset2);
        GL11.glTexCoord2d(1.0, 1.0);
        GL11.glVertex2d(offset, 0.0);
        GL11.glEnd();
    }

    public static String getText2() {
        return displayName2;
    }

    private static void updateState2() {
        TexturesGuiIconsPngManager.processAction(-2001, -2001, -2000, -2000, Color.red.getRGB());
    }

    public void applyFramebuffer(int index, int index2, Framebuffer framebuffer4, int index3, int index4) {
        framebuffer4.updateState();
        framebuffer4.setState(false);
        GL20.glUniform2f(this.alphaBlurShader.processAction("direction"), (float)(1 - index), (float)index);
        GL11.glBindTexture(3553, index2);
        this.alphaBlurShader.processFloat((float)index3, (float)index4);
    }
}
