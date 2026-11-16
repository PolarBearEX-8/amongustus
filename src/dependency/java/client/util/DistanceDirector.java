package client.util;

import client.setting.CrafterLeatherType;

public class DistanceDirector {
   public static final DistanceDirector distanceDirector = new DistanceDirector();
   public final int[] countArray = new int[CrafterLeatherType.values().length];

   public DistanceDirector() {
      try {
         this.countArray[CrafterLeatherType.LEATHER.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         this.countArray[CrafterLeatherType.CHAIN.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         this.countArray[CrafterLeatherType.IRON.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         this.countArray[CrafterLeatherType.GOLD.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         this.countArray[CrafterLeatherType.DIAMOND.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }
   }
}
