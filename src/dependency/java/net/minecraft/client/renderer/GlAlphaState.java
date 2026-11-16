package net.minecraft.client.renderer;

public class GlAlphaState {
   public boolean enabled;
   public int count;
   public float factor;

   public void applyGlAlphaState(GlAlphaState glAlphaState) {
      this.enabled = glAlphaState.enabled;
      this.count = glAlphaState.count;
      this.factor = glAlphaState.factor;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public int getCount() {
      return this.count;
   }

   public float getFactor() {
      return this.factor;
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public void updateState2() {
      this.enabled = true;
   }

   public void applyFlag(boolean flag, int count2, float factor2) {
      this.enabled = flag;
      this.count = count2;
      this.factor = factor2;
   }

   public void updateState() {
      this.enabled = false;
   }

   public void applyIndex(int count2, float factor2) {
      this.count = count2;
      this.factor = factor2;
   }

   public GlAlphaState() {
      this(false, 519, 0.0F);
   }

   public GlAlphaState(boolean flag, int count2, float factor2) {
      this.enabled = flag;
      this.count = count2;
      this.factor = factor2;
   }

   public GlAlphaState(boolean flag) {
      this(flag, 519, 0.0F);
   }

   @Override
   public String toString() {
      return "enabled: " + this.enabled + ", func: " + this.count + ", ref: " + this.factor;
   }
}
