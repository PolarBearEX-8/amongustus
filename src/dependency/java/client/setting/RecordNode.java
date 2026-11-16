package client.setting;

public class RecordNode {
   public static final RecordNode recordNode = new RecordNode();
   public final int[] countArray = new int[GlStateManagerSType.values().length];

   public RecordNode() {
      try {
         this.countArray[GlStateManagerSType.S.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         this.countArray[GlStateManagerSType.T.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         this.countArray[GlStateManagerSType.R.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         this.countArray[GlStateManagerSType.Q.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
