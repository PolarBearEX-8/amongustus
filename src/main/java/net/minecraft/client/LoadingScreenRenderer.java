package net.minecraft.client;

import net.augustus.modules.misc.ClientSettings;
import net.augustus.utils.shader.BackgroundShader;
import net.augustus.utils.render.ResizableFramebuffer;
import net.augustus.Augustus;
import net.augustus.utils.render.GlStateSaver;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MinecraftError;
import net.optifine.CustomLoadingScreen;
import net.optifine.CustomLoadingScreens;

public class LoadingScreenRenderer implements IProgressUpdate {
   private final GlStateSaver glStateSaver;
   private String message;
   private final Minecraft mc;
   private String currentlyDisplayedText;
   private long systemTime;
   private boolean loadingSuccess;
   private final ScaledResolution scaledResolution;
   private ResizableFramebuffer framebuffer;

   public LoadingScreenRenderer(Minecraft mc2) {
       super();
      this.glStateSaver = GlStateSaver.create();
      this.message = "";
      this.currentlyDisplayedText = "";
      this.systemTime = Minecraft.getSystemTime();
      this.framebuffer = null;
      this.mc = mc2;
      this.scaledResolution = new ScaledResolution(mc2);
      if (this.framebuffer == null) {
         this.framebuffer = new ResizableFramebuffer(mc2.displayWidth, mc2.displayHeight, false);
      }

      this.framebuffer.setFramebufferFilter(9728);
   }

   public void resetProgressAndMessage(String str) {
      this.loadingSuccess = false;
      this.displayString(str);
   }

   public void displaySavingString(String str) {
      this.loadingSuccess = true;
      this.displayString(str);
   }

   public void setDoneWorking() {
   }

   public void setLoadingProgress(int n) {
      if (!this.mc.running) {
         if (!this.loadingSuccess) {
            throw new MinecraftError();
         }
      } else {
         long systemTime2 = Minecraft.getSystemTime();
         if (systemTime2 - this.systemTime >= 100L) {
            this.systemTime = systemTime2;
            ScaledResolution sr = new ScaledResolution(this.mc);
            int scaleFactor = sr.getScaleFactor();
            int scaledWidth = sr.getScaledWidth();
            int scaledHeight = sr.getScaledHeight();
            if (OpenGlHelper.isFramebufferEnabled()) {
               this.framebuffer.framebufferClear();
            } else {
               GlStateManager.clear(256);
            }

            this.framebuffer.bindFramebuffer(false);
            GlStateManager.matrixMode(5889);
            GlStateManager.loadIdentity();
            GlStateManager.ortho(0.0, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 0.0, 100.0, 300.0);
            GlStateManager.matrixMode(5888);
            GlStateManager.loadIdentity();
            GlStateManager.translate(0.0F, 0.0F, -200.0F);
            if (!OpenGlHelper.isFramebufferEnabled()) {
               GlStateManager.clear(16640);
            }

            Tessellator instance = Tessellator.getInstance();
            WorldRenderer worldRenderer = instance.getWorldRenderer();
            ClientSettings augustusServer = Augustus.getInstance().getModuleManager().clientSettings;
            if (augustusServer.isToggled() && augustusServer.customBackground.getValue()) {
               this.glStateSaver.setCull(false);
               this.glStateSaver.setTexture2D(true);
               this.glStateSaver.setBlend(false);
               Augustus.getInstance().getBackgroundShaderManager().getCurrentShader().render(1800.0F);
               this.glStateSaver.restoreCull();
               this.glStateSaver.restoreTexture2D();
               this.glStateSaver.restoreBlend();
            } else {
               CustomLoadingScreen customLoadingScreen = CustomLoadingScreens.getCustomLoadingScreen();
               if (customLoadingScreen != null) {
                  customLoadingScreen.drawBackground(sr.getScaledWidth(), sr.getScaledHeight());
               } else {
                  this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
                  float f2 = 32.0F;
                  worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                  worldRenderer.pos(0.0, (double)scaledHeight, 0.0).tex(0.0, (double)((float)scaledHeight / f2)).color(64, 64, 64, 255).endVertex();
                  worldRenderer.pos((double)scaledWidth, (double)scaledHeight, 0.0)
                     .tex((double)((float)scaledWidth / f2), (double)((float)scaledHeight / f2))
                     .color(64, 64, 64, 255)
                     .endVertex();
                  worldRenderer.pos((double)scaledWidth, 0.0, 0.0).tex((double)((float)scaledWidth / f2), 0.0).color(64, 64, 64, 255).endVertex();
                  worldRenderer.pos(0.0, 0.0, 0.0).tex(0.0, 0.0).color(64, 64, 64, 255).endVertex();
                  instance.draw();
               }
            }

            if (n >= 0) {
               byte b = 100;
               byte b2 = 2;
               int n2 = scaledWidth / 2 - b / 2;
               int n3 = scaledHeight / 2 + 16;
               GlStateManager.disableTexture2D();
               worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
               worldRenderer.pos((double)n2, (double)n3, 0.0).color(128, 128, 128, 255).endVertex();
               worldRenderer.pos((double)n2, (double)(n3 + b2), 0.0).color(128, 128, 128, 255).endVertex();
               worldRenderer.pos((double)(n2 + b), (double)(n3 + b2), 0.0).color(128, 128, 128, 255).endVertex();
               worldRenderer.pos((double)(n2 + b), (double)n3, 0.0).color(128, 128, 128, 255).endVertex();
               worldRenderer.pos((double)n2, (double)n3, 0.0).color(128, 255, 128, 255).endVertex();
               worldRenderer.pos((double)n2, (double)(n3 + b2), 0.0).color(128, 255, 128, 255).endVertex();
               worldRenderer.pos((double)(n2 + n), (double)(n3 + b2), 0.0).color(128, 255, 128, 255).endVertex();
               worldRenderer.pos((double)(n2 + n), (double)n3, 0.0).color(128, 255, 128, 255).endVertex();
               instance.draw();
               GlStateManager.enableTexture2D();
            }

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            this.mc.fontRendererObj.drawStringWithShadow(this.currentlyDisplayedText, (float)((scaledWidth - this.mc.fontRendererObj.getStringWidth(this.currentlyDisplayedText)) / 2), (float)(scaledHeight / 2 - 4 - 16), 16777215);
            this.mc.fontRendererObj.drawStringWithShadow(this.message, (float)((scaledWidth - this.mc.fontRendererObj.getStringWidth(this.message)) / 2), (float)(scaledHeight / 2 - 4 + 8), 16777215);
            this.framebuffer.unbindFramebuffer();
            if (OpenGlHelper.isFramebufferEnabled()) {
               this.framebuffer.framebufferRender(scaledWidth * scaleFactor, scaledHeight * scaleFactor);
            }

            this.mc.updateDisplay();

            try {
               Thread.yield();
            } catch (Exception e) {
            }
         }
      }
   }

   private void displayString(String str) {
      this.currentlyDisplayedText = str;
      if (!this.mc.running) {
         if (!this.loadingSuccess) {
            throw new MinecraftError();
         }
      } else {
         GlStateManager.clear(256);
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         if (OpenGlHelper.isFramebufferEnabled()) {
            int scaleFactor = this.scaledResolution.getScaleFactor();
            GlStateManager.ortho(
               0.0, (double)(this.scaledResolution.getScaledWidth() * scaleFactor), (double)(this.scaledResolution.getScaledHeight() * scaleFactor), 0.0, 100.0, 300.0
            );
         } else {
            ScaledResolution sr = new ScaledResolution(this.mc);
            GlStateManager.ortho(0.0, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 0.0, 100.0, 300.0);
         }

         GlStateManager.matrixMode(5888);
         GlStateManager.loadIdentity();
         GlStateManager.translate(0.0F, 0.0F, -200.0F);
      }
   }

   public void displayLoadingString(String str) {
      if (!this.mc.running) {
         if (!this.loadingSuccess) {
            throw new MinecraftError();
         }
      } else {
         this.systemTime = 0L;
         this.message = str;
         this.setLoadingProgress(-1);
         this.systemTime = 0L;
      }
   }
}
