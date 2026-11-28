package net.augustus.render;

import net.augustus.utils.interfaces.MC;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public final class StencilUtil implements MC {

   public static void readStencilBuffer(int n) {
      GL11.glColorMask(true, true, true, true);
      GL11.glStencilFunc(514, n, 1);
      GL11.glStencilOp(7680, 7680, 7680);
   }

   public static void setupFBO(Framebuffer framebuffer) {
      EXTFramebufferObject.glDeleteRenderbuffersEXT(framebuffer.depthBuffer);
      int n = EXTFramebufferObject.glGenRenderbuffersEXT();
      EXTFramebufferObject.glBindRenderbufferEXT(36161, n);
      EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, mc.displayWidth, mc.displayHeight);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, n);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, n);
   }

   public static void beginStencilWrite() {
      GL11.glStencilFunc(519, 1, 1);
      GL11.glStencilOp(7681, 7681, 7681);
      GL11.glColorMask(false, false, false, false);
   }

   public static void initStencilToWrite(Framebuffer framebuffer) {
      framebuffer.bindFramebuffer(false);
      checkSetupFBO(framebuffer);
      GL11.glClear(1024);
      GL11.glEnable(2960);
   }

   public static void checkSetupFBO(Framebuffer framebuffer) {
      if (framebuffer != null && framebuffer.depthBuffer > -1) {
         setupFBO(framebuffer);
         framebuffer.depthBuffer = -1;
      }
   }

   public static void initStencilToWrite() {
      initStencilToWrite(mc.getFramebuffer());
   }

   public static void uninitStencilBuffer() {
      GL11.glDisable(2960);
   }



}
