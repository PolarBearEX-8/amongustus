package net.augustus.cosmetics.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class GeoAnimationFile {
   private HashMap animations = new HashMap();

   public Collection getAllAnimations() {
      return this.animations.values();
   }

   public GeoAnimation getAnimation(String str) {
      return (GeoAnimation)this.animations.get(str);
   }

   public String getFirstAnimationName() {
      Optional optional = this.animations.keySet().stream().findFirst();
      return optional.isPresent() ? (String)optional.get() : null;
   }

   public void putAnimation(String str, GeoAnimation geoAnimation) {
      this.animations.put(str, geoAnimation);
   }



}
