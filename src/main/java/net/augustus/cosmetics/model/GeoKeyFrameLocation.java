package net.augustus.cosmetics.model;

public class GeoKeyFrameLocation {
   public double startTick;
   public AnimationKeyframe keyframe;

   public GeoKeyFrameLocation(AnimationKeyframe animationKeyframe, double d) {
      this.keyframe = animationKeyframe;
      this.startTick = d;
   }
}
