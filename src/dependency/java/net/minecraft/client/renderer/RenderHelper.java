package net.minecraft.client.renderer;

import client.util.VectorMatcher;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class RenderHelper {
   public static FloatBuffer floatBuffer = VectorMatcher.resolveFloatBuffer(16);
   public static final Vec3 vec3 = new Vec3(0.2F, 1.0, -0.7F).normalize();
   public static final Vec3 vec2 = new Vec3(-0.2F, 1.0, 0.7F).normalize();

   public static void enableStandardItemLighting() {
      GlStateManager.applyState13();
      GlStateManager.enableState4(0);
      GlStateManager.enableState4(1);
      GlStateManager.applyState6();
      GlStateManager.glColorMaterial(1032, 5634);
      float factor = 0.4F;
      float factor2 = 0.6F;
      float factor3 = 0.0F;
      GL11.glLight(16384, 4611, resolveFloatBuffer(vec3.xCoord, vec3.yCoord, vec3.zCoord, 0.0));
      GL11.glLight(16384, 4609, resolveFloatBuffer(factor2, factor2, factor2, 1.0F));
      GL11.glLight(16384, 4608, resolveFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
      GL11.glLight(16384, 4610, resolveFloatBuffer(factor3, factor3, factor3, 1.0F));
      GL11.glLight(16385, 4611, resolveFloatBuffer(vec2.xCoord, vec2.yCoord, vec2.zCoord, 0.0));
      GL11.glLight(16385, 4609, resolveFloatBuffer(factor2, factor2, factor2, 1.0F));
      GL11.glLight(16385, 4608, resolveFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
      GL11.glLight(16385, 4610, resolveFloatBuffer(factor3, factor3, factor3, 1.0F));
      GlStateManager.glShadeModel(7424);
      GL11.glLightModel(2899, resolveFloatBuffer(factor, factor, factor, 1.0F));
   }

   public static void enableGUIStandardItemLighting() {
      GlStateManager.pushMatrix();
      GlStateManager.rotate(-30.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(165.0F, 1.0F, 0.0F, 0.0F);
      enableStandardItemLighting();
      GlStateManager.popMatrix();
   }

   private static FloatBuffer resolveFloatBuffer(double posX, double posX2, double posY, double posZ) {
      return resolveFloatBuffer((float)posX, (float)posX2, (float)posY, (float)posZ);
   }

   public static void disableStandardItemLighting() {
      GlStateManager.applyState3();
      GlStateManager.enableLight(0);
      GlStateManager.enableLight(1);
      GlStateManager.applyState7();
   }

   private static FloatBuffer resolveFloatBuffer(float posX, float posX2, float posY, float posZ) {
      ((Buffer)floatBuffer).clear();
      floatBuffer.put(posX).put(posX2).put(posY).put(posZ);
      ((Buffer)floatBuffer).flip();
      return floatBuffer;
   }
}
