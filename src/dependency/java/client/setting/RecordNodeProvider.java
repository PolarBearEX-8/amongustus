package client.setting;

public class RecordNodeProvider {
   public static final RecordNodeProvider recordNodeProvider = new RecordNodeProvider();
   public final int[] countArray = new int[ItemNoneType.values().length];

   public RecordNodeProvider() {
      try {
         this.countArray[ItemNoneType.NONE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         this.countArray[ItemNoneType.EAT.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         this.countArray[ItemNoneType.DRINK.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         this.countArray[ItemNoneType.BLOCK.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         this.countArray[ItemNoneType.BOW.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }
   }
}
