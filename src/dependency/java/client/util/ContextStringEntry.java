package client.util;

import client.setting.MovementWeightProperty;

public class ContextStringEntry {
   public static final int count = 64;
   public static final int count3 = 24;
   public static final String labelText = "01";

   public static int computeCount(MovementWeightProperty movementWeightProperty) {
      int count2 = 0;
      count2 = DirectIntArrayEntry.countArray[movementWeightProperty.ordinal()];
      if (count2 == 1) {
         return 1152;
      } else if (count2 == 3) {
         return 832;
      } else if (count2 == 4) {
         return 576;
      } else if (count2 == 2) {
         return 1088;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static int computeCountByMovementWeightPr(MovementWeightProperty movementWeightProperty) {
      int count2 = 0;
      count2 = DirectIntArrayEntry.countArray[movementWeightProperty.ordinal()];
      if (count2 == 3) {
         return 768;
      } else if (count2 == 2) {
         return 512;
      } else if (count2 == 1) {
         return 448;
      } else if (count2 == 4) {
         return 1024;
      } else {
         throw new IllegalArgumentException();
      }
   }
}
