package net.augustus.utils.render;

import net.augustus.ClientManagers;
import net.augustus.render.FramebufferStack;
import net.augustus.utils.shader.ShaderProgram;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.optifine.config.Config;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class BlurShaderRenderer {
   private final GlStateSaver glStateSaver;
   private final Minecraft mc;
   private final Framebuffer guiMaskFramebuffer;
   private final Framebuffer hudMaskFramebuffer;
   private final ShaderProgram cutoutShader;

   public BlurShaderRenderer() {
       super();
      this.glStateSaver = GlStateSaver.create();
      this.mc = Minecraft.getMinecraft();
      this.guiMaskFramebuffer = new ResizableFramebuffer(this.mc.displayWidth, this.mc.displayHeight, true);
      this.hudMaskFramebuffer = new ResizableFramebuffer(this.mc.displayWidth, this.mc.displayHeight, true);
      this.cutoutShader = new ShaderProgram(RenderSystemAssetLoader.loadAssetString("legacy.vert"), RenderSystemAssetLoader.loadAssetString("cutout.frag"));
   }

   public void endMaskCapture() {
      if (Config.isShaderPackLoaded()) {
         FramebufferStack.popFramebuffer();
      } else {
         this.mc.getFramebuffer().bindFramebuffer(Minecraft.isRunningOnMac);
      }
   }

   public void beginMaskCapture(boolean flag, boolean flag2) {
      if (Config.isShaderPackLoaded()) {
         FramebufferStack.pushFramebuffer();
      }

      if (flag2) {
         this.guiMaskFramebuffer.bindFramebuffer(flag);
      } else {
         this.hudMaskFramebuffer.bindFramebuffer(flag);
      }

   }

   public void renderBlurPass(boolean flag, int n) {
      if (Display.isActive()) {
         Framebuffer framebuffer = flag ? this.guiMaskFramebuffer : this.hudMaskFramebuffer;
         BlurMode blurMode = BlurMode.fromName(ClientManagers.moduleManager.blur.modesNew.getSelectedValue());
         GL11.glPushMatrix();
         this.glStateSaver.setCull(false);
         this.glStateSaver.setTexture2D(true);
         this.glStateSaver.setBlend(false);
         if (blurMode == BlurMode.KAWASE) {
            Framebuffer framebuffer4 = blurMode.applyBlurToFramebuffer(this.mc.getFramebuffer(), n);
            this.mc.getFramebuffer().bindFramebuffer(Minecraft.isRunningOnMac);
            this.cutoutShader.bind();
            String str = "shapes";
            this.cutoutShader.bindTextureUniform(str, framebuffer);
            String str2 = "tex";
            this.cutoutShader.bindTextureUniform(str2, framebuffer4);
            float framebufferTextureWidth = (float)framebuffer.framebufferTextureWidth;
            float framebufferTextureHeight = (float)framebuffer.framebufferTextureHeight;
            String str3 = "resolution";
            this.cutoutShader.setUniform2f(str3, framebufferTextureWidth, framebufferTextureHeight);
            float f4 = (float)framebuffer.framebufferTextureWidth;
            float f5 = (float)framebuffer.framebufferTextureHeight;
            float f7 = 0.0F;
            float f8 = 0.0F;
            this.cutoutShader.drawQuad(f8, f7, f4, f5);
            this.cutoutShader.unbind();
         } else {
            blurMode.applyMaskedBlur(this.mc.getFramebuffer(), framebuffer, n);
         }

         this.guiMaskFramebuffer.framebufferClear();
         this.hudMaskFramebuffer.framebufferClear();
         this.mc.getFramebuffer().bindFramebuffer(Minecraft.isRunningOnMac);
         this.glStateSaver.restoreCull();
         this.glStateSaver.restoreTexture2D();
         this.glStateSaver.restoreBlend();
         GL11.glPopMatrix();
      }
   }




}
