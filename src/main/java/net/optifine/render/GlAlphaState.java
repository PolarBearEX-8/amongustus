package net.optifine.render;


public class GlAlphaState {
   private boolean enabled;
   private int func;
   private float ref;

   public int getFunc() {
      return this.func;
   }

   public void setDisabled() {
      this.enabled = false;
   }

   public void setState(GlAlphaState glAlphaState) {
      this.enabled = glAlphaState.enabled;
      this.func = glAlphaState.func;
      this.ref = glAlphaState.ref;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setState(boolean flag, int n, float f) {
      this.enabled = flag;
      this.func = n;
      this.ref = f;
   }

   public void setEnabled() {
      this.enabled = true;
   }

   public GlAlphaState() {
      this(false, 519, 0.0F);
   }

   public void setFuncRef(int n, float f) {
      this.func = n;
      this.ref = f;
   }

   public GlAlphaState(boolean flag, int n, float f) {
      this.enabled = flag;
      this.func = n;
      this.ref = f;
   }

   public GlAlphaState(boolean flag) {
      this(flag, 519, 0.0F);
   }

   @Override
   public String toString() {
      return "enabled: " + this.enabled + ", func: " + this.func + ", ref: " + this.ref;
   }

   public void setEnabled(boolean flag) {
      this.enabled = flag;
   }

   public float getRef() {
      return this.ref;
   }
}
