package cc.baritone.utils;

public final class AStarSystem {
   public final JUMPMOTIONEnum jUMPMOTIONEnum;
   public final DefaultCustom defaultCustom;
   public float factor;
   public float factor2;

   public DefaultCustom getDefaultCustom() {
      return this.defaultCustom;
   }

   public void setFactor2(float factor3) {
      this.factor2 = factor3;
   }

   public JUMPMOTIONEnum getJUMPMOTIONEnum() {
      return this.jUMPMOTIONEnum;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public void setFactor(float factor3) {
      this.factor = factor3;
   }

   public float getFactor() {
      return this.factor;
   }

   public AStarSystem(JUMPMOTIONEnum jUMPMOTIONEnum2, float factor3, float factor4) {
      this.jUMPMOTIONEnum = jUMPMOTIONEnum2;
      this.defaultCustom = new DefaultCustom(factor3, factor4);
      this.factor = factor3;
      this.factor2 = factor4;
   }
}
