package client.setting;

public class RecordNodeImpl {
   public static final RecordNodeImpl recordNodeImpl = new RecordNodeImpl();
   public final int[] countArray = new int[RenderBooleanProperty.values().length];

   public RecordNodeImpl() {
      try {
         this.countArray[RenderBooleanProperty.POSITION.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         this.countArray[RenderBooleanProperty.UV.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         this.countArray[RenderBooleanProperty.COLOR.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         this.countArray[RenderBooleanProperty.NORMAL.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
