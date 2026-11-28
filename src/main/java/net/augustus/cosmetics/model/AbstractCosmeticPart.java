package net.augustus.cosmetics.model;

public abstract class AbstractCosmeticPart extends AnimatedCosmeticPart {
   private float scale = 1.0F;
   private float roll;
   private float offsetZ;
   private float yaw;
   private float offsetY;
   private float pitch;
   private CosmeticBodyPart bodyPart;
   private float offsetX;

   public void setScale(float f) {
      this.scale = f;
   }

   public void setYaw(float f) {
      this.yaw = f;
   }

   public void setOffsetY(float f) {
      this.offsetY = f;
   }

   public void setOffsetX(float f) {
      this.offsetX = f;
   }

   public float getOffsetZ() {
      return this.offsetZ;
   }

   public float getPitch() {
      return this.pitch;
   }

   public CosmeticBodyPart getBodyPart() {
      return this.bodyPart;
   }

   public float getYaw() {
      return this.yaw;
   }

   public void setPitch(float f) {
      this.pitch = f;
   }

   public float getOffsetY() {
      return this.offsetY;
   }

   public void setRoll(float f) {
      this.roll = f;
   }

   public void setBodyPart(CosmeticBodyPart cosmeticBodyPart) {
      this.bodyPart = cosmeticBodyPart;
   }

   public float getOffsetX() {
      return this.offsetX;
   }

   public float getRoll() {
      return this.roll;
   }

   public void setOffsetZ(float f) {
      this.offsetZ = f;
   }

   public float getScale() {
      return this.scale;
   }

   public AbstractCosmeticPart(CosmeticBodyPart cosmeticBodyPart) {
      this.bodyPart = cosmeticBodyPart;
   }
}
