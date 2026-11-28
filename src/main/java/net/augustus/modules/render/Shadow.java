package net.augustus.modules.render;

import net.augustus.utils.render.ShaderRenderPass;
import com.google.gson.JsonObject;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.shader.ResourceShaderProgram;
import net.augustus.events.EventRenderEntity;
import net.augustus.render.RenderPassTask;
import net.augustus.ClientManagers;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.render.ResizableFramebuffer;
import net.augustus.settings.options.ShadowItemOption;
import net.augustus.render.StencilUtil;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.events.listeners.RenderEntityListener;
import net.augustus.settings.property.ConfigSerializerProvider;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.UnaryFunction;
import net.augustus.render.FramebufferStack;
import net.augustus.settings.options.EspRenderModeOption;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.config.Config;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shadow extends Module implements TickListener, RenderEntityListener {
   public final StringValue shadowMode;
   public final BooleanValue customColor;
   public final ColorSetting shadowColor;
   public final DoubleValue sigma;
   public final BooleanValue shadowEntities;
   private final TargetSelectionSettings entities;
   private final MultiOptionSetting itemsToShadow;
   private final Framebuffer horizontalPassFramebuffer;
   private final Framebuffer verticalPassFramebuffer;
   public final Framebuffer shadowMaskFramebuffer;
   private ResourceShaderProgram shadowShader;
   private boolean lastCustomColorEnabled;
   private String lastShadowMode;

   public Shadow() {
       super("Shadow", "Schatten", new Color(170, 181, 182, 255), Categorys.RENDER);
      this.shadowMode = new StringValue(this, "ShadowMode", "Gaussian", new String[]{"Gaussian", "Box"});
      this.customColor = new BooleanValue(this, "CustomColor", true);
      ColorSetting colorSetting = new ColorSetting(this, "Color", new Color(0, 0, 0, 1));
      BooleanValue customColor2 = this.customColor;
      BooleanValue booleanValue = customColor2;
      this.shadowColor = (ColorSetting)colorSetting.withVisibility(() -> booleanValue.getValue());
      this.sigma = new DoubleValue(this, "Sigma", 5.0, 0.0, 30.0, 1);
      this.shadowEntities = new BooleanValue(this, "ShadowEntities", false);
      TargetSelectionSettings targetSelectionSettings2 = new TargetSelectionSettings(this, TargetFilterPresets.createRenderFilterWithSelf()).withTargetsDisplayName("Entities");
      customColor2 = this.shadowEntities;
      BooleanValue booleanValue2 = customColor2;
      this.entities = targetSelectionSettings2.withVisibility(() -> booleanValue2.getValue());
      this.itemsToShadow = new MultiOptionSetting(this, "ItemsToShadow", ShadowItemOption.values());
      this.horizontalPassFramebuffer = new ResizableFramebuffer(mc.displayWidth, mc.displayHeight, false);
      this.verticalPassFramebuffer = new ResizableFramebuffer(mc.displayWidth, mc.displayHeight, false);
      this.shadowMaskFramebuffer = new ResizableFramebuffer(mc.displayWidth, mc.displayHeight, true);
      this.lastShadowMode = "";
      this.shadowShader = new ResourceShaderProgram("vertex.vert", this.getFragmentShaderPath());
   }

   private String getFragmentShaderPath() {
      if (this.shadowMode.getSelectedValue().equals("Box")) {
         String str;
         if (this.customColor.getValue()) {
            str = "boxShadow2.glsl";
         } else {
            short s = 15295;
            str = "boxShadow.glsl";
         }

         return str;
      } else {
         return this.customColor.getValue() ? "alphaBlur2.glsl" : "alphaBlur.glsl";
      }
   }

   private String getShadowMode() {
      return this.shadowMode.getSelectedValue();
   }

   @Override
   public void onEnable() {
      this.shadowShader = new ResourceShaderProgram("vertex.vert", this.getFragmentShaderPath());
      BlurShadowManager.shouldWarnIncompatibleSettings = true;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(31);
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      JsonObject json2 = super.serialize(configSerializerProvider, json, unaryFunction);
      if (!mc.isCallingFromMinecraftThread()) {
         return json2;
      } else {
         this.shadowShader = new ResourceShaderProgram("vertex.vert", this.getFragmentShaderPath());
         return json2;
      }
   }

   @Override
   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      super.deserialize(configSerializerProvider, json, flag, unaryFunction);
      if (mc.isCallingFromMinecraftThread()) {
         this.shadowShader = new ResourceShaderProgram("vertex.vert", this.getFragmentShaderPath());
      }
   }

   @Override
   public void onTick() {
      String shadowMode2 = this.getShadowMode();
      if (this.lastCustomColorEnabled != this.customColor.getValue() || !this.lastShadowMode.equals(shadowMode2)) {
         this.shadowShader = new ResourceShaderProgram("vertex.vert", this.getFragmentShaderPath());
         this.lastShadowMode = shadowMode2;
      }

      this.lastCustomColorEnabled = this.customColor.getValue();
   }

   @Override
   public void onRenderEntity(EventRenderEntity event) {
      if (this.shadowEntities.getValue() && (!ClientManagers.moduleManager.esp.isToggled() || !EspRenderModeOption.VANILLA.isSelected())) {
         EntityLivingBase entity = event.getEntity();
         if (this.entities.getTargetFilter().matches(entity)) {
            BlurShadowManager.enableShadowOnce();
            BlurShadowManager.disableNormalPass();
            EntityLivingBase entity2 = event.getEntity();
            RendererLivingEntity renderer = event.getRenderer();
            boolean flag = !entity.isRenderNameTag();
            RenderPassTask renderPassTask = shaderRenderPass -> {
               event.renderEntityModel(entity2, renderer, entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)mc.getTimer().renderPartialTicks, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)mc.getTimer().renderPartialTicks, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks, true);
               BlurShadowManager.disableNormalPass();
            };
            BlurShadowManager.submitRenderTask(flag, renderPassTask);
         }
      }
   }

   private void renderToShadowMask(Runnable runnable, boolean flag) {
      if (Display.isActive()) {
         this.bindShadowMaskFramebuffer();
         runnable.run();
         this.unbindShadowMaskFramebuffer();
         if (flag) {
            runnable.run();
         }
      }
   }

   public void bindShadowMaskFramebuffer() {
      if (Config.isShaderPackLoaded()) {
         FramebufferStack.pushFramebuffer();
      }

      this.shadowMaskFramebuffer.bindFramebuffer(false);
   }

   public void unbindShadowMaskFramebuffer() {
      if (Config.isShaderPackLoaded()) {
         FramebufferStack.popFramebuffer();
      } else {
         mc.getFramebuffer().bindFramebuffer(true);
      }
   }

   public void renderShadowOverlay(ScaledResolution sr) {
      if (Display.isActive()) {
         this.renderToShadowMask(() -> Gui.drawRect(-2001, -2001, -2000, -2000, Color.red.getRGB()), false);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.pushMatrix();
         GlStateManager.enableAlpha();
         GlStateManager.alphaFunc(516, 0.0F);
         GlStateManager.blendFunc(770, 771);
         GlStateManager.enableDepth();
         GlStateManager.enableTexture2D();
         GlStateManager.disableLighting();
         double scaledWidthDouble = sr.getScaledWidth_double();
         double scaledHeightDouble = sr.getScaledHeight_double();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.shadowShader.bind();
         this.uploadShaderUniforms();
         int n = (int)scaledWidthDouble;
         int n2 = (int)scaledHeightDouble;
         int n3 = n;
         Framebuffer framebuffer = this.horizontalPassFramebuffer;
         this.renderBlurPass((byte)0, this.shadowMaskFramebuffer.framebufferTexture, framebuffer, n3, n2);
         n = (int)scaledWidthDouble;
         int n4 = (int)scaledHeightDouble;
         int n5 = n;
         Framebuffer framebuffer2 = this.verticalPassFramebuffer;
         this.renderBlurPass((byte)1, this.horizontalPassFramebuffer.framebufferTexture, framebuffer2, n5, n4);
         this.shadowShader.unbind();
         StencilUtil.initStencilToWrite();
         StencilUtil.beginStencilWrite();
         this.drawFullscreenTexture(this.shadowMaskFramebuffer.framebufferTexture, scaledWidthDouble, scaledHeightDouble);
         StencilUtil.readStencilBuffer(0);
         this.drawFullscreenTexture(this.verticalPassFramebuffer.framebufferTexture, scaledWidthDouble, scaledHeightDouble);
         StencilUtil.uninitStencilBuffer();
         GlStateManager.bindTexture(0);
         GlStateManager.alphaFunc(516, 0.2F);
         GlStateManager.disableAlpha();
         GlStateManager.popMatrix();
         GlStateManager.disableBlend();
         this.shadowMaskFramebuffer.framebufferClear();
         mc.getFramebuffer().bindFramebuffer(false);
         GlStateManager.resetColor();
      }
   }

   public void renderBlurPass(int n, int n2, Framebuffer framebuffer, int n3, int n4) {
      framebuffer.framebufferClear();
      framebuffer.bindFramebuffer(false);
      String str = "direction";
      GL20.glUniform2f(this.shadowShader.getUniformLocation(str), (float)(1 - n), (float)n);
      GL11.glBindTexture(3553, n2);
      float f = (float)n3;
      float f3 = (float)n4;
      this.shadowShader.drawFullscreenQuad(f, f3);
   }

   public void uploadShaderUniforms() {
      String str = "texelSize";
      GL20.glUniform2f(this.shadowShader.getUniformLocation(str), 1.0F / (float)mc.displayWidth, 1.0F / (float)mc.displayHeight);
      String str2 = "texture";
      GL20.glUniform1i(this.shadowShader.getUniformLocation(str2), 0);
      float value = (float)this.sigma.getValue();
      String shadowMode2 = this.getShadowMode();
      if (shadowMode2.equals("Box")) {
         String str3 = "radius";
         GL20.glUniform1f(this.shadowShader.getUniformLocation(str3), value);
         if (this.customColor.getValue()) {
            this.shadowColor.getColorInt((byte)0);
            String str4 = "startColor";
            GL20.glUniform3f(this.shadowShader.getUniformLocation(str4), this.shadowColor.getRedFloat(), this.shadowColor.getGreenFloat(), this.shadowColor.getBlueFloat());
         }
      } else if (shadowMode2.equals("Gaussian")) {
         String str5 = "sigma";
         GL20.glUniform1f(this.shadowShader.getUniformLocation(str5), value);
         if (this.customColor.getValue()) {
            this.shadowColor.getColorInt((byte)0);
            String str6 = "startColor";
            GL20.glUniform3f(this.shadowShader.getUniformLocation(str6), this.shadowColor.getRedFloat(), this.shadowColor.getGreenFloat(), this.shadowColor.getBlueFloat());
         }
      }
   }

   private void drawFullscreenTexture(int n, double d, double d2) {
      GlStateManager.enableBlend();
      GL11.glBindTexture(3553, n);
      GL11.glBegin(7);
      GL11.glTexCoord2d(0.0, 1.0);
      GL11.glVertex2d(0.0, 0.0);
      GL11.glTexCoord2d(0.0, 0.0);
      GL11.glVertex2d(0.0, d2);
      GL11.glTexCoord2d(1.0, 0.0);
      GL11.glVertex2d(d, d2);
      GL11.glTexCoord2d(1.0, 1.0);
      GL11.glVertex2d(d, 0.0);
      GL11.glEnd();
   }

}
