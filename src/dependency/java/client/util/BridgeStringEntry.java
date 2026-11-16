package client.util;

import client.setting.MovementAlphaProperty;

public class BridgeStringEntry {
   public String labelText;
   public float factor = 1.0F;
   public float factor2 = 1.0F;
   public int count = 1;
   public MovementAlphaProperty movementAlphaProperty = MovementAlphaProperty.FILE;
   public boolean enabled = false;

   public void setFactor(float factor3) {
      this.factor = factor3;
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public void setFactor2(float factor3) {
      this.factor2 = factor3;
   }

   public void setCount(int count2) {
      this.count = count2;
   }

   public void setMovementAlphaProperty(MovementAlphaProperty movementAlphaProperty2) {
      this.movementAlphaProperty = movementAlphaProperty2;
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public MovementAlphaProperty getMovementAlphaProperty() {
      return this.movementAlphaProperty;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public float getFactor() {
      return this.factor;
   }

   public int getCount() {
      return this.count;
   }

   public float getFactor2() {
      return this.factor2;
   }
}
