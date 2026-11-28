package net.minecraft.potion;

import net.minecraft.util.ResourceLocation;

public class PotionHealth extends Potion {
   public PotionHealth(int n, ResourceLocation location, boolean flag, int n2) {
      super(n, location, flag, n2);
   }

   @Override
   public boolean isReady(int n, int n2) {
      return n >= 1;
   }

   @Override
   public boolean isInstant() {
      return true;
   }
}
