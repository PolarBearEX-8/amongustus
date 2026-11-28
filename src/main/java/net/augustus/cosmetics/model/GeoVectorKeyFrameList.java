package net.augustus.cosmetics.model;

import java.util.ArrayList;
import java.util.List;

public class GeoVectorKeyFrameList {
   public List xKeyFrames;
   public List yKeyFrames;
   public List zKeyFrames;

   public GeoVectorKeyFrameList(List list, List list2, List list3) {
      this.xKeyFrames = list;
      this.yKeyFrames = list2;
      this.zKeyFrames = list3;
   }

   public GeoVectorKeyFrameList() {
      this.xKeyFrames = new ArrayList();
      this.yKeyFrames = new ArrayList();
      this.zKeyFrames = new ArrayList();
   }

   public double getLastKeyframeTime() {
      double d = 0.0;

      for (AnimationKeyframe animationKeyframe : (Iterable<AnimationKeyframe>)(this.xKeyFrames)) {
         d += animationKeyframe.getLength();
      }

      double d2 = 0.0;

      for (AnimationKeyframe animationKeyframe2 : (Iterable<AnimationKeyframe>)(this.yKeyFrames)) {
         d2 += animationKeyframe2.getLength();
      }

      double d3 = 0.0;

      for (AnimationKeyframe animationKeyframe3 : (Iterable<AnimationKeyframe>)(this.zKeyFrames)) {
         d3 += animationKeyframe3.getLength();
      }

      return Math.max(d, Math.max(d2, d3));
   }
}
