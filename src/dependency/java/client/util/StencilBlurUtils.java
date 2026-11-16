package client.util;

import client.setting.Setting;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public final class StencilBlurUtils implements MinecraftAccessor {
   public static void updateInt(int count) {
      GL11.glColorMask(true, true, true, true);
      GL11.glStencilFunc(514, count, 1);
      GL11.glStencilOp(7680, 7680, 7680);
   }

   public static void applyFramebufferByFramebuffer(Framebuffer framebuffer, short shortValue) {
      framebuffer.setState(false);
      setCount(framebuffer);
      GL11.glClear(1024);
      GL11.glEnable(2960);
   }

   public static void updateState3() {
      GL11.glDisable(2960);
   }

   public static Setting[] getSettingArray() {
      return null;
   }

   public static void updateState2() {
      GL11.glStencilFunc(519, 1, 1);
      GL11.glStencilOp(7681, 7681, 7681);
      GL11.glColorMask(false, false, false, false);
   }

   public static void applyFramebuffer(Framebuffer framebuffer) {
      EXTFramebufferObject.glDeleteRenderbuffersEXT(framebuffer.count);
      int count = EXTFramebufferObject.glGenRenderbuffersEXT();
      EXTFramebufferObject.glBindRenderbufferEXT(36161, count);
      EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, MinecraftAccessor.minecraft.displayWidth, MinecraftAccessor.minecraft.displayHeight);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, count);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, count);
   }

   public static void updateState() {
      long value = 0x267162e4baa3L;
      int count = (int)((0x5ff40884d577L ^ value ^ 0x4bdba9c11909L) << 48 >>> 48);
      Framebuffer framebuffer = MinecraftAccessor.minecraft.getFramebuffer();
      short shortValue = (short)count;
      applyFramebufferByFramebuffer(framebuffer, shortValue);
   }

   public static void setCount(Framebuffer count) {
      if (count != null && count.count > -1) {
         applyFramebuffer(count);
         count.count = -1;
      }
   }
}
