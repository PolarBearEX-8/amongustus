package augustus.modules.render;

import augustus.modes.render.EspRenderMode;
import client.ClientGlobals;
import client.event.EventRenderEntity;
import client.event.RenderEntityListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.ShaderProgram;
import client.render.StencilFramebuffer;
import client.setting.BlurItemsOnGroundType;
import client.setting.BlurTargetEntity;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.BuildManager;
import client.util.ChatUtil;
import client.util.MovementHandler;
import client.util.PreEndHandManager;
import client.util.StencilBlurUtils;
import java.awt.Color;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
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

public class Blur extends Module implements RenderEntityListener {
    public ModeSetting settingModesNew;
    public NumberSetting settingSigma;
    public BooleanSetting settingInScreens;
    public BooleanSetting settingBlurEntities;
    public MultiSelectSetting settingEntities;
    public MultiSelectSetting settingItemsToBlur;
    public Framebuffer framebuffer;
    public Framebuffer framebuffer2;
    public Framebuffer framebuffer3;
    public ShaderProgram blurShader;
    public double reachDistance;
    public FloatBuffer floatBuffer;
    public FloatBuffer floatBuffer2;
    public static String[] stringArray;

    public Blur() {
        super("Blur", "Unschärfe", new Color(170, 181, 182, 255), Category.RENDER);
        this.settingModesNew = new ModeSetting(this, "ModesNew", "Kawase", new String[]{"Kawase", "Gaussian", "Box"}).getModeSetting("Modes");
        this.settingSigma = new NumberSetting(this, "Sigma", 7.0, 4.0, 30.0, 1);
        this.settingInScreens = new BooleanSetting(this, "InScreens", true);
        this.settingBlurEntities = new BooleanSetting(this, "BlurEntities", false);
        this.settingEntities = new MultiSelectSetting(this, "Entities", BlurTargetEntity.values()).getMultiSelectSetting(this.settingBlurEntities::isEnabled);
        this.settingItemsToBlur = new MultiSelectSetting(this, "ItemsToBlur", BlurItemsOnGroundType.values());
        this.framebuffer = new StencilFramebuffer(minecraft.displayWidth, minecraft.displayHeight, false);
        this.framebuffer2 = new StencilFramebuffer(minecraft.displayWidth, minecraft.displayHeight, false);
        this.framebuffer3 = new StencilFramebuffer(minecraft.displayWidth, minecraft.displayHeight, true);
        this.blurShader = new ShaderProgram("vertex.vert", "blur.glsl");
        this.floatBuffer = null;
        this.floatBuffer2 = null;
    }

    @Override
    public void onEnable() {
        this.registerEvent(31);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.blurShader = new ShaderProgram("vertex.vert", "blur.glsl");
    }

    @Override
    public void onRenderEntity(EventRenderEntity event) {
        if (this.settingBlurEntities.isEnabled()) {
            if (!ClientGlobals.moduleManager.esp.isToggled() || !EspRenderMode.VANILLA.isSelected()) {
                EntityLivingBase livingEntity;
                livingEntity = event.getTargetEntity();
                if (!((BlurTargetEntity.MYSELF.isSelected()) && (livingEntity == (minecraft.thePlayer)))) {
                    if (livingEntity == minecraft.thePlayer) {
                        return;
                    }

                    if (!ChatUtil.checkEntity5(
                    livingEntity,
                    BlurTargetEntity.PLAYERS.isSelected(),
                    BlurTargetEntity.MOBS.isSelected(),
                    
                    BlurTargetEntity.ANIMALS.isSelected(),
                    BlurTargetEntity.VILLAGER.isSelected(),
                    BlurTargetEntity.ARMOR_STANDS.isSelected(),
                    BlurTargetEntity.INVISIBLE.isSelected(),
                    false,
                    false,
                    true
                    )) {
                        return;
                    }
                }

                MovementHandler.processAction6();
                MovementHandler.processAction11();
                RendererLivingEntity rendererLivingEntity = event.getRendererLivingEntity();
                EntityLivingBase targetEntity = event.getTargetEntity();
                MovementHandler.applyRunnableTask(!livingEntity.isState1(), renderPass -> onRenderEntity2(event, targetEntity, rendererLivingEntity, livingEntity, renderPass));
            }
        }
    }

    private static void onRenderEntity2(EventRenderEntity event, EntityLivingBase livingEntity, RendererLivingEntity rendererLivingEntity, Entity entity, RenderPass renderPass) {
        double offset = entity.posX - entity.lastTickPosX;
        double offset2 = entity.lastTickPosX + offset * (double)minecraft.getTimer().scaleFactor6;
        double offset3 = entity.posY - entity.lastTickPosY;
        double offset4 = entity.lastTickPosY + offset3 * (double)minecraft.getTimer().scaleFactor6;
        double offset5 = entity.posZ - entity.lastTickPosZ;
        double offset6 = entity.lastTickPosZ + offset5 * (double)minecraft.getTimer().scaleFactor6;
        event.applyEntityLivingBase(livingEntity, rendererLivingEntity, offset2, offset4, offset6, true);
    }

    private float getFloat(float value, float value2) {
        return (float)(Math.exp((double)(-(value * value) / (2.0F * value2 * value2))) / Math.sqrt((Math.PI * 2) * (double)value2 * (double)value2));
    }

    public void updateState4() {
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
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0.0F, -1.0F);
            GlStateManager.applyState14();
            GlStateManager.alphaFunc(516, 0.0F);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            if (BuildManager.canExecute()) {
                PreEndHandManager.updateState2();
            }

            GlStateManager.applyState3();
            ScaledResolution scaledResolution = new ScaledResolution(minecraft);
            float coordOffset = (float)scaledResolution.getCoordOffset();
            float coordOffset3 = (float)scaledResolution.getCoordOffset3();
            this.blurShader.processAction();
            this.updateState2();
            this.applyFramebuffer(0, minecraft.getFramebuffer().indexOffset, this.framebuffer, (int)coordOffset, (int)coordOffset3);
            this.applyFramebuffer(1, this.framebuffer.indexOffset, this.framebuffer2, (int)coordOffset, (int)coordOffset3);
            this.blurShader.processAction2();
            StencilBlurUtils.updateState();
            StencilBlurUtils.updateState2();
            this.processAction(this.framebuffer3.indexOffset, (double)coordOffset, (double)coordOffset3);
            StencilBlurUtils.updateInt(1);
            this.processAction(this.framebuffer2.indexOffset, (double)coordOffset, (double)coordOffset3);
            StencilBlurUtils.updateState3();
            minecraft.getFramebuffer().setState(false);
            GlStateManager.setActiveTexture(0);
            GlStateManager.alphaFunc(516, 0.2F);
            GlStateManager.applyState10();
            GlStateManager.popMatrix();
            GlStateManager.applyState();
            this.framebuffer3.updateState();
            minecraft.getFramebuffer().setState(false);
        }
    }

    public static String[] getStringArray() {
        return stringArray;
    }

    private void processAction(int index, double offset, double offset2) {
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

    public void updateState2() {
        GL20.glUniform2f(this.blurShader.processAction("texelSize"), 1.0F / (float)minecraft.displayWidth, 1.0F / (float)minecraft.displayHeight);
        GL20.glUniform1i(this.blurShader.processAction("texture"), 0);
        double reachDistance2 = this.reachDistance;
        double value = this.settingSigma.getValue();
        if (reachDistance2 != value || this.floatBuffer == null || this.floatBuffer2 == null) {
            double value2 = this.settingSigma.getValue();
            float value3 = (float)(value2 * 1.75);
            this.floatBuffer = BufferUtils.createFloatBuffer((int)value3 + 1);

            for (int i = 0; (float)i <= value3; i++) {
                this.floatBuffer.put(this.getFloat((float)i, (float)this.settingSigma.getValue()));
            }

            ((Buffer)this.floatBuffer).flip();
            this.floatBuffer2 = BufferUtils.createFloatBuffer((int)value3 + 1);

            for (int i2 = 0; (float)i2 <= value3; i2++) {
                this.floatBuffer2.put((float)i2);
            }

            FloatBuffer floatBuffer22 = this.floatBuffer2;
            ((Buffer)floatBuffer22).flip();
        }

        this.reachDistance = this.settingSigma.getValue();
        GL20.glUniform1(this.blurShader.processAction("sigmas"), this.floatBuffer);
        GL20.glUniform1(this.blurShader.processAction("add"), this.floatBuffer2);
        GL20.glUniform1i(this.blurShader.processAction("count"), this.floatBuffer2.limit());
    }

    public void updateState() {
        if (this.framebuffer3 != null && this.framebuffer3.indexOffset >= 0) {
            this.framebuffer3.setState(false);
        }
    }

    public static void setStringArray(String[] textArray) {
        stringArray = textArray;
    }

    public void updateState3() {
        if (minecraft.getFramebuffer() != null && minecraft.getFramebuffer().indexOffset >= 0) {
            minecraft.getFramebuffer().setState(false);
        }
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
                this.updateState();
                runnable.run();
                this.updateState3();
                if (enabled) {
                    if (Display.isActive()) {
                        runnable.run();
                    }
                }
            }
        }
    }

    public void applyFramebuffer(int index, int index2, Framebuffer framebuffer4, int index3, int index4) {
        framebuffer4.updateState();
        framebuffer4.setState(false);
        GL20.glUniform2f(this.blurShader.processAction("direction"), (float)(1 - index), (float)index);
        GL11.glBindTexture(3553, index2);
        this.blurShader.processFloat((float)index3, (float)index4);
    }
}
