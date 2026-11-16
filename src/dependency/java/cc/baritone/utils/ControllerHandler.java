package cc.baritone.utils;

public final class ControllerHandler {
   public static final float factor = Float.NEGATIVE_INFINITY;
   public static final ControllerHandler controllerHandler = new ControllerHandler();
   public static final int count = 32;
   public final float[] factorArray = new float[32];

   public float[] getFloatArray() {
      float[] factorArray2 = new float[32];

      for (int index = 0; index < 32; index++) {
         factorArray2[index] = this.pow(this.factorArray[index]);
      }

      return factorArray2;
   }

   public void applyFloatArray(float[] factorArray2) {
      this.updateState();
      int count2 = factorArray2.length > 32 ? 32 : factorArray2.length;

      for (int index = 0; index < count2; index++) {
         this.factorArray[index] = this.computeFactor(factorArray2[index]);
      }
   }

   public void applyAbstractControllerImpl(AbstractControllerImpl abstractControllerImpl) {
      this.updateState();

      for (int index = 0; index < 32; index++) {
         this.factorArray[index] = this.computeFactor(abstractControllerImpl.computeFactor(index));
      }
   }

   public ControllerHandler(AbstractControllerImpl abstractControllerImpl) {
      this.applyAbstractControllerImpl(abstractControllerImpl);
   }

   public float pow(float factor2) {
      return factor2 == Float.NEGATIVE_INFINITY ? 0.0F : (float)Math.pow(2.0, (double)factor2);
   }

   public ControllerHandler() {
   }

   public void updateState() {
      for (int index = 0; index < 32; index++) {
         this.factorArray[index] = 0.0F;
      }
   }

   public int getCount() {
      return this.factorArray.length;
   }

   public float computeFactor(int count2) {
      float factor2 = 0.0F;
      if (count2 >= 0 && count2 < 32) {
         factor2 = this.factorArray[count2];
      }

      return factor2;
   }

   public ControllerHandler(float[] factorArray2) {
      this.applyFloatArray(factorArray2);
   }

   public float computeFactor(int count2, float factor2) {
      float factor3 = 0.0F;
      if (count2 >= 0 && count2 < 32) {
         factor3 = this.factorArray[count2];
         this.factorArray[count2] = this.computeFactor(factor2);
      }

      return factor3;
   }

   public void applyControllerHandler(ControllerHandler controllerHandler2) {
      if (controllerHandler2 != this) {
         this.applyFloatArray(controllerHandler2.factorArray);
      }
   }

   private float computeFactor(float factor2) {
      if (factor2 == Float.NEGATIVE_INFINITY) {
         return factor2;
      } else if (factor2 > 1.0F) {
         return 1.0F;
      } else {
         return factor2 < -1.0F ? -1.0F : factor2;
      }
   }
}
