package net.augustus.render.waveycape;

public class StickPoint {
   public CapeVector2f prevPosition;
   public boolean locked;
   public CapeVector2f position = new CapeVector2f(0.0F, 0.0F);

   public StickPoint() {
      this.prevPosition = new CapeVector2f(0.0F, 0.0F);
   }

   public float getLerpX(float f) {
      return CapeMathHelper.lerp(f, this.prevPosition.x, this.position.x);
   }

   public float getLerpY(float f) {
      return CapeMathHelper.lerp(f, this.prevPosition.y, this.position.y);
   }
}
