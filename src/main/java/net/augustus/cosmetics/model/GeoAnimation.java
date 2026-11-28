package net.augustus.cosmetics.model;

import java.util.ArrayList;
import java.util.List;

public class GeoAnimation {
   public List boneAnimations;
   public String animationName;
   public List customInstructionKeyframes;
   public boolean loop;
   public List particleKeyFrames;
   public List soundKeyFrames;
   public Double animationLength;

   public GeoAnimation() {
      this.loop = true;
      this.soundKeyFrames = new ArrayList();
      this.particleKeyFrames = new ArrayList();
      this.customInstructionKeyframes = new ArrayList();
   }



}
