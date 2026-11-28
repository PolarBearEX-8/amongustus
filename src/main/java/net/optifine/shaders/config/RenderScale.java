package net.optifine.shaders.config;


public class RenderScale {
   private float scale = 1.0F;
   private float offsetX = 0.0F;
   private float offsetY = 0.0F;

   public RenderScale(float f, float f2, float f3) {
      this.scale = f;
      this.offsetX = f2;
      this.offsetY = f3;
   }

   public float getScale() {
      return this.scale;
   }

   public float getOffsetX() {
      return this.offsetX;
   }

   @Override
   public String toString() {
      return "" + this.scale + ", " + this.offsetX + ", " + this.offsetY;
   }

   public float getOffsetY() {
      return this.offsetY;
   }
}
