package client.setting;

public class RecordNodeBridge {
   public static final RecordNodeBridge recordNodeBridge = new RecordNodeBridge();
   public final int[] countArray = new int[RenderBooleanProperty.values().length];

   public RecordNodeBridge() {
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
   }
}
