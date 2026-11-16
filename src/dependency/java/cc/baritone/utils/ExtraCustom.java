package cc.baritone.utils;

public abstract class ExtraCustom {
   public static final int count = 2304;
   public static final int count2 = 2;

   public abstract void updateState3();

   public abstract void applyIndex(int count3, short shortValue);

   public abstract void updateState();

   public abstract void updateState2();

   public void applyFloatArray(int count3, float[] factorArray) {
      int count4 = 0;

      while (count4 < 32) {
         short shortValue = this.computeShortCode(factorArray[count4++]);
         this.applyIndex(count3, shortValue);
      }
   }

   public abstract void applyIndex(int count3);

   private final short computeShortCode(float factor) {
      return factor > 32767.0F ? 32767 : (factor < -32768.0F ? -32768 : (short)((int)factor));
   }
}
