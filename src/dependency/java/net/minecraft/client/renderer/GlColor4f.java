package net.minecraft.client.renderer;

public class GlColor4f {
   public float red = 1.0F;
   public float green = 1.0F;
   public float blue = 1.0F;
   public float alpha = 1.0F;

   public GlColor4f() {
   }

   public GlColor4f(float posX, float posX2, float posY, float posZ) {
      this.red = posX;
      this.green = posX2;
      this.blue = posY;
      this.alpha = posZ;
   }
}
