package net.minecraft.client;

import augustus.modules.misc.ClientSettings;
import client.Client;
import client.event.CompositeListener;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Module;
import client.render.StencilFramebuffer;
import client.render.WorldRenderer;
import client.util.DefaultVertexFormats;
import client.util.ErrorAdapter;
import client.util.InterpolationChecker;
import client.util.ModularStringEntry;
import client.util.PngManager;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;

public class LoadingScreenRenderer implements CompositeListener {
   public String labelText = "";
   public final Minecraft mc;
   public String labelText2 = "";
   public long timestamp = Minecraft.getTimestamp();
   public boolean enabled;
   public final ScaledResolution scaledResolution;
   public StencilFramebuffer stencilFramebuffer = null;

   @Override
   public void applyText2(String text) {
      this.enabled = true;
      this.applyText3(text);
   }

   @Override
   public void setCount2(int count) {
      if (!this.mc.enabled17) {
         if (!this.enabled) {
            throw new ErrorAdapter();
         }
      } else {
         long timestamp2 = Minecraft.getTimestamp();
         if (timestamp2 - this.timestamp >= 100L) {
            this.timestamp = timestamp2;
            ScaledResolution scaledResolution2 = new ScaledResolution(this.mc);
            int scaleFactor = scaledResolution2.getScaleFactor();
            int scaledWidth = scaledResolution2.getScaledWidth();
            int scaledHeight = scaledResolution2.getScaledHeight();
            if (OpenGlHelper.isActiveAsFlag()) {
               this.stencilFramebuffer.updateState();
            } else {
               GlStateManager.glClear(256);
            }

            this.stencilFramebuffer.setState(false);
            GlStateManager.matrixMode(5889);
            GlStateManager.loadIdentity();
            GlStateManager.glStateAction(0.0, scaledResolution2.getCoordOffset(), scaledResolution2.getCoordOffset3(), 0.0, 100.0, 300.0);
            GlStateManager.matrixMode(5888);
            GlStateManager.loadIdentity();
            GlStateManager.translate(0.0F, 0.0F, -200.0F);
            if (!OpenGlHelper.isActiveAsFlag()) {
               GlStateManager.glClear(16640);
            }

            Tessellator tessellator = Tessellator.getTessellator();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            ClientSettings clientSettings = Client.getClient().getModuleManager().clientSettings;
            if (((Module)clientSettings).isToggled() && clientSettings.settingCustomBackground.isEnabled()) {
               ModularStringEntry modularStringEntry = Client.getClient().getGoldenShard().getModularStringEntry();
               float factor = 1800.0F;
               modularStringEntry.applyFloat(factor);
            } else {
               InterpolationChecker interpolationChecker = PngManager.getInterpolationChecker();
               if (interpolationChecker != null) {
                  interpolationChecker.applyIndex(scaledResolution2.getScaledWidth(), scaledResolution2.getScaledHeight());
               } else {
                  this.mc.getTextureManager().applyResourceLocation3(TexturesGuiIconsPngManager.resourceLocation);
                  float factor2 = 32.0F;
                  worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
                  worldRenderer.getWorldRenderer(0.0, (double)scaledHeight, 0.0)
                     .getWorldRenderer6(0.0, (double)((float)scaledHeight / factor2))
                     .resolveWorldRenderer(64, 64, 64, 255)
                     .updateState();
                  worldRenderer.getWorldRenderer((double)scaledWidth, (double)scaledHeight, 0.0)
                     .getWorldRenderer6((double)((float)scaledWidth / factor2), (double)((float)scaledHeight / factor2))
                     .resolveWorldRenderer(64, 64, 64, 255)
                     .updateState();
                  worldRenderer.getWorldRenderer((double)scaledWidth, 0.0, 0.0)
                     .getWorldRenderer6((double)((float)scaledWidth / factor2), 0.0)
                     .resolveWorldRenderer(64, 64, 64, 255)
                     .updateState();
                  worldRenderer.getWorldRenderer(0.0, 0.0, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(64, 64, 64, 255).updateState();
                  tessellator.updateState();
               }
            }

            if (count >= 0) {
               int count2 = scaledWidth / 2 - 100 / 2;
               int count3 = scaledHeight / 2 + 16;
               GlStateManager.enableTexture2D();
               worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
               worldRenderer.getWorldRenderer((double)count2, (double)count3, 0.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
               worldRenderer.getWorldRenderer((double)count2, (double)(count3 + 2), 0.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
               worldRenderer.getWorldRenderer((double)(count2 + 100), (double)(count3 + 2), 0.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
               worldRenderer.getWorldRenderer((double)(count2 + 100), (double)count3, 0.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
               worldRenderer.getWorldRenderer((double)count2, (double)count3, 0.0).resolveWorldRenderer(128, 255, 128, 255).updateState();
               worldRenderer.getWorldRenderer((double)count2, (double)(count3 + 2), 0.0).resolveWorldRenderer(128, 255, 128, 255).updateState();
               worldRenderer.getWorldRenderer((double)(count2 + count), (double)(count3 + 2), 0.0).resolveWorldRenderer(128, 255, 128, 255).updateState();
               worldRenderer.getWorldRenderer((double)(count2 + count), (double)count3, 0.0).resolveWorldRenderer(128, 255, 128, 255).updateState();
               tessellator.updateState();
               GlStateManager.disableTexture2D();
            }

            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            this.mc
               .fontRendererObj
               .getIndex2(this.labelText2, (float)((scaledWidth - this.mc.fontRendererObj.getIndex(this.labelText2)) / 2), (float)(scaledHeight / 2 - 4 - 16), 16777215);
            this.mc
               .fontRendererObj
               .getIndex2(this.labelText, (float)((scaledWidth - this.mc.fontRendererObj.getIndex(this.labelText)) / 2), (float)(scaledHeight / 2 - 4 + 8), 16777215);
            this.stencilFramebuffer.renderElement();
            if (OpenGlHelper.isActiveAsFlag()) {
               this.stencilFramebuffer.applyIndexByIndex(scaledWidth * scaleFactor, scaledHeight * scaleFactor);
            }

            this.mc.updateState6();

            try {
               Thread.yield();
            } catch (Exception exception) {
            }
         }
      }
   }

   @Override
   public void applyTextByText(String text) {
      this.enabled = false;
      this.applyText3(text);
   }

   private void applyText3(String text) {
      this.labelText2 = text;
      if (!this.mc.enabled17) {
         if (!this.enabled) {
            throw new ErrorAdapter();
         }
      } else {
         GlStateManager.glClear(256);
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         if (OpenGlHelper.isActiveAsFlag()) {
            int scaleFactor = this.scaledResolution.getScaleFactor();
            GlStateManager.glStateAction(
               0.0, (double)(this.scaledResolution.getScaledWidth() * scaleFactor), (double)(this.scaledResolution.getScaledHeight() * scaleFactor), 0.0, 100.0, 300.0
            );
         } else {
            ScaledResolution scaledResolution2 = new ScaledResolution(this.mc);
            GlStateManager.glStateAction(0.0, scaledResolution2.getCoordOffset(), scaledResolution2.getCoordOffset3(), 0.0, 100.0, 300.0);
         }

         GlStateManager.matrixMode(5888);
         GlStateManager.loadIdentity();
         GlStateManager.translate(0.0F, 0.0F, -200.0F);
      }
   }

   @Override
   public void applyText(String text) {
      if (!this.mc.enabled17) {
         if (!this.enabled) {
            throw new ErrorAdapter();
         }
      } else {
         this.timestamp = 0L;
         this.labelText = text;
         this.setCount2(-1);
         this.timestamp = 0L;
      }
   }

   @Override
   public void updateState() {
   }

   public LoadingScreenRenderer(Minecraft mc2) {
      this.mc = mc2;
      this.scaledResolution = new ScaledResolution(mc2);
      if (this.stencilFramebuffer == null) {
         this.stencilFramebuffer = new StencilFramebuffer(mc2.displayWidth, mc2.displayHeight, false);
      }

      this.stencilFramebuffer.applyIndex(9728);
   }
}
