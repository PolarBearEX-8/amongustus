package net.augustus.cosmetics.model;

import java.util.ArrayList;
import java.util.List;

public class GeoAnimationBuilder {
   private List animationList = new ArrayList();

   public GeoAnimationBuilder addAnimation(String str, Boolean bool) {
      this.animationList.add(new GeoQueuedAnimation(str, bool));
      return this;
   }

   public GeoAnimationBuilder addRepeatingAnimation(String str, int n) {

      assert n > 0;

      for (int i = 0; i < n; i++) {
         Boolean bool = false;
         this.addAnimation(str, bool);
      }

      return this;
   }

   public GeoAnimationBuilder clearAnimations() {
      this.animationList.clear();
      return this;
   }

   public GeoAnimationBuilder addAnimation(String str) {
      this.animationList.add(new GeoQueuedAnimation(str, (Boolean)null));
      return this;
   }

   public List getAnimationList() {
      return this.animationList;
   }
}
