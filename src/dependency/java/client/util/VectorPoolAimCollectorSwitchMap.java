package client.util;

public class VectorPoolAimCollectorSwitchMap {
   public static final int[] countArray = new int[AimCollector.values().length];

   static {
      try {
         countArray[AimCollector.ANVIL.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[AimCollector.BEACON.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[AimCollector.BREWING_STAND.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[AimCollector.CHEST.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[AimCollector.CRAFTING.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[AimCollector.CREATIVE.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[AimCollector.DISPENSER.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[AimCollector.ENCHANTMENT.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[AimCollector.FURNACE.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[AimCollector.HOPPER.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray[AimCollector.HORSE.ordinal()] = 11;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray[AimCollector.INVENTORY.ordinal()] = 12;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray[AimCollector.SHULKER_BOX.ordinal()] = 13;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         countArray[AimCollector.VILLAGER.ordinal()] = 14;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }
   }
}
