package client.setting;

public class RecordNodeHandler {
   public static final RecordNodeHandler recordNodeHandler = new RecordNodeHandler();
   public final int[] countArray = new int[CombatValueProperty.values().length];

   public RecordNodeHandler() {
      try {
         this.countArray[CombatValueProperty.FLOAT.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         this.countArray[CombatValueProperty.UINT.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         this.countArray[CombatValueProperty.INT.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         this.countArray[CombatValueProperty.USHORT.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         this.countArray[CombatValueProperty.SHORT.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         this.countArray[CombatValueProperty.UBYTE.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         this.countArray[CombatValueProperty.BYTE.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }
   }
}
