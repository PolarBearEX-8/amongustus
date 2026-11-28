package net.minecraft.client.resources.data;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimationMetadataSection implements IMetadataSection {
   private final List animationFrames;
   private final int frameWidth;
   private final int frameHeight;
   private final int frameTime;
   private final boolean interpolate;

   public int getFrameHeight() {
      return this.frameHeight;
   }

   public int getFrameIndex(int n) {
      return ((AnimationFrame)this.animationFrames.get(n)).getFrameIndex();
   }

   public int getFrameTimeSingle(int n) {
      AnimationFrame animationFrame = this.getAnimationFrame(n);
      return animationFrame.hasNoTime() ? this.frameTime : animationFrame.getFrameTime();
   }

   public boolean frameHasTime(int n) {
      return !((AnimationFrame)this.animationFrames.get(n)).hasNoTime();
   }

   public Set getFrameIndexSet() {
      HashSet set = Sets.newHashSet();

      for (AnimationFrame animationFrame : (Iterable<AnimationFrame>)(this.animationFrames)) {
         set.add(animationFrame.getFrameIndex());
      }

      return set;
   }

   public boolean isInterpolate() {
      return this.interpolate;
   }

   public int getFrameCount() {
      return this.animationFrames.size();
   }

   private AnimationFrame getAnimationFrame(int n) {
      return (AnimationFrame)this.animationFrames.get(n);
   }

   public int getFrameWidth() {
      return this.frameWidth;
   }

   public int getFrameTime() {
      return this.frameTime;
   }

   public AnimationMetadataSection(List list, int n, int n2, int n3, boolean flag) {
      this.animationFrames = list;
      this.frameWidth = n;
      this.frameHeight = n2;
      this.frameTime = n3;
      this.interpolate = flag;
   }
}
