package net.augustus.cosmetics.model;


public class GeoAnimationPoint {
   public final Double animationStartValue;
   public final Double animationEndTick;
   public final AnimationKeyframe keyframe;
   public final Double animationEndValue;
   public final Double currentTick;

   public GeoAnimationPoint(AnimationKeyframe animationKeyframe, double d, double d2, float f, double d3) {
      this.keyframe = animationKeyframe;
      this.currentTick = d;
      this.animationEndTick = d2;
      this.animationStartValue = (double)f;
      this.animationEndValue = d3;
   }

   public GeoAnimationPoint(AnimationKeyframe animationKeyframe, Double d, Double d2, Double d3, Double d4) {
      this.keyframe = animationKeyframe;
      this.currentTick = d;
      this.animationEndTick = d2;
      this.animationStartValue = d3;
      this.animationEndValue = d4;
   }

   @Override
   public String toString() {
      return "Tick: " + this.currentTick + " | End Tick: " + this.animationEndTick + " | Start Value: " + this.animationStartValue + " | End Value: " + this.animationEndValue;
   }
}
