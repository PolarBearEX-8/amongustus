package net.optifine.render;


public class GlBlendState {
   private boolean enabled;
   private int srcFactor;
   private int dstFactor;
   private int srcFactorAlpha;
   private int dstFactorAlpha;

   public GlBlendState(boolean flag, int n, int n2) {
      this(flag, n, n2, n, n2);
   }

   public void setEnabled(boolean flag) {
      this.enabled = flag;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public int getSrcFactor() {
      return this.srcFactor;
   }

   public GlBlendState(boolean flag) {
      this(flag, 1, 0);
   }

   public void setFactors(int n, int n2) {
      this.srcFactor = n;
      this.dstFactor = n2;
      this.srcFactorAlpha = n;
      this.dstFactorAlpha = n2;
   }

   public void setDisabled() {
      this.enabled = false;
   }

   public GlBlendState(boolean flag, int n, int n2, int n3, int n4) {
      this.enabled = flag;
      this.srcFactor = n;
      this.dstFactor = n2;
      this.srcFactorAlpha = n3;
      this.dstFactorAlpha = n4;
   }

   public void setEnabled() {
      this.enabled = true;
   }

   public void setState(GlBlendState glBlendState) {
      this.enabled = glBlendState.enabled;
      this.srcFactor = glBlendState.srcFactor;
      this.dstFactor = glBlendState.dstFactor;
      this.srcFactorAlpha = glBlendState.srcFactorAlpha;
      this.dstFactorAlpha = glBlendState.dstFactorAlpha;
   }

   @Override
   public String toString() {
      return "enabled: " + this.enabled + ", src: " + this.srcFactor + ", dst: " + this.dstFactor + ", srcAlpha: " + this.srcFactorAlpha + ", dstAlpha: " + this.dstFactorAlpha;
   }

   public int getDstFactor() {
      return this.dstFactor;
   }

   public void setState(boolean flag, int n, int n2, int n3, int n4) {
      this.enabled = flag;
      this.srcFactor = n;
      this.dstFactor = n2;
      this.srcFactorAlpha = n3;
      this.dstFactorAlpha = n4;
   }

   public int getSrcFactorAlpha() {
      return this.srcFactorAlpha;
   }

   public int getDstFactorAlpha() {
      return this.dstFactorAlpha;
   }

   public boolean isSeparate() {
      return this.srcFactor != this.srcFactorAlpha || this.dstFactor != this.dstFactorAlpha;
   }

   public GlBlendState() {
      this(false, 1, 0);
   }

   public void setFactors(int n, int n2, int n3, int n4) {
      this.srcFactor = n;
      this.dstFactor = n2;
      this.srcFactorAlpha = n3;
      this.dstFactorAlpha = n4;
   }
}
