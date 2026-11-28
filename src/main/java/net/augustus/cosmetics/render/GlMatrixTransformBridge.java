package net.augustus.cosmetics.render;

import net.minecraft.client.renderer.GlStateManager;

public class GlMatrixTransformBridge implements MatrixTransformBridge {
   private static GlMatrixTransformBridge instance = new GlMatrixTransformBridge();

   @Override
   public void push() {
      GlStateManager.pushMatrix();
   }

   @Override
   public void rotate(float f, float f2, float f3) {
      GlStateManager.rotate(f3, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(f2, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
   }

   private GlMatrixTransformBridge() {
   }

   @Override
   public void pop() {
      GlStateManager.popMatrix();
   }

   public static GlMatrixTransformBridge getInstance() {
      return instance;
   }

   @Override
   public void scale(float f, float f2, float f3) {
      GlStateManager.scale(f, f2, f3);
   }

   @Override
   public void rotateZ(float f) {
      GlStateManager.rotate(f, 0.0F, 0.0F, 1.0F);
   }

   @Override
   public void rotateY(float f) {
      GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
   }

   @Override
   public void translate(float f, float f2, float f3) {
      GlStateManager.translate(f, f2, f3);
   }

   @Override
   public void rotateX(float f) {
      GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
   }
}
