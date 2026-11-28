package net.augustus.cosmetics.model;

public class CosmeticEffect {
   private final CosmeticEffectType type;
   private CosmeticAxis axis;
   private float multiplier = 1.0F;

   public void setMultiplier(float f) {
      this.multiplier = f;
   }

   public float getMultiplier() {
      return this.multiplier;
   }

   public void setAxis(CosmeticAxis cosmeticAxis) {
      this.axis = cosmeticAxis;
   }

   public CosmeticEffectType getType() {
      return this.type;
   }

   public CosmeticAxis getAxis() {
      return this.axis;
   }

   public CosmeticEffect(CosmeticEffectType cosmeticEffectType) {
      this.type = cosmeticEffectType;
      this.axis = cosmeticEffectType.getDefaultAxis();
   }
}
