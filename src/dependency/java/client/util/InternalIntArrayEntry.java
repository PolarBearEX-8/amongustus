package client.util;

public class InternalIntArrayEntry {
   public static final int[] $SwitchMap$MovingObjectType = new int[MovingObjectType.values().length];

   static {
      try {
         $SwitchMap$MovingObjectType[MovingObjectType.ENTITY.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         $SwitchMap$MovingObjectType[MovingObjectType.BLOCK.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         $SwitchMap$MovingObjectType[MovingObjectType.MISS.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
