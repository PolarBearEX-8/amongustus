package client.util;

import client.setting.MovementConfigProperty;

public abstract class PrimaryFloatEntry extends StandardHashMapEntry {
   public float factor;
   public MovementConfigProperty movementConfigProperty;
   public float factor4;
   public float factor3;
   public float factor2;
   public float factor5;
   public float factor7;
   public float factor6 = 1.0F;

   public void setFactor4(float factor8) {
      this.factor4 = factor8;
   }

   public float getFactor7() {
      return this.factor7;
   }

   public MovementConfigProperty getMovementConfigProperty() {
      return this.movementConfigProperty;
   }

   public void setFactor5(float factor8) {
      this.factor5 = factor8;
   }

   public float getFactor() {
      return this.factor;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public void setFactor6(float factor8) {
      this.factor6 = factor8;
   }

   public void setMovementConfigProperty(MovementConfigProperty movementConfigProperty2) {
      this.movementConfigProperty = movementConfigProperty2;
   }

   public void setFactor2(float factor8) {
      this.factor2 = factor8;
   }

   public float getFactor3() {
      return this.factor3;
   }

   public void setFactor(float factor8) {
      this.factor = factor8;
   }

   public void setFactor3(float factor8) {
      this.factor3 = factor8;
   }

   public float getFactor6() {
      return this.factor6;
   }

   public float getFactor5() {
      return this.factor5;
   }

   public void setFactor7(float factor8) {
      this.factor7 = factor8;
   }

   public float getFactor4() {
      return this.factor4;
   }

   public PrimaryFloatEntry(MovementConfigProperty movementConfigProperty2) {
      this.movementConfigProperty = movementConfigProperty2;
   }
}
