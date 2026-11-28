package net.augustus.cosmetics.model;


import net.augustus.cosmetics.CosmeticTickListener;
public class GeoAnimationTickListener implements CosmeticTickListener {
   private final GeoAnimationData animationData;

   public GeoAnimationTickListener(GeoAnimationData geoAnimationData) {
      this.animationData = geoAnimationData;
   }

   @Override
   public void onTick(int n) {
      this.animationData.tick++;
   }
}
